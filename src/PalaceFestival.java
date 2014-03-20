import java.util.*;

public class PalaceFestival {
	// private PlayerFacade playerFacade;
	private PalaceCard festivalCard;
	private PlayerFacade playerFacade;
	private ArrayList<Player> players;
	private int[] playerScores;
	private int currentPlayer;
	private boolean[] playerFrozen;
	private boolean endFestival;

	public PalaceFestival() {
		festivalCard = null;
		playerFacade = null;
		players = new ArrayList<Player>();
		playerScores = new int[4];
		playerFrozen = new boolean[4];
		for (int i = 0; i < 4; i++) {
			playerScores[i] = 0;
			playerFrozen[i] = false;
		}
		currentPlayer = 0;
		endFestival = false;
	}

	public PalaceFestival(PalaceCard festivalCard, PlayerFacade p) {
		this.setFestivalCard(festivalCard);
		playerFacade = p;
		players = new ArrayList<Player>();
		playerScores = new int[4];
		playerFrozen = new boolean[4];
		for (int i = 0; i < 4; i++) {
			playerScores[i] = 0;
			playerFrozen[i] = false;
		}
		currentPlayer = 0;
		endFestival = false;
	}

	public void startFestival(Player[] p) {
		String a = null;
		OnePointPalaceCard onePointPalaceCard = null;
		TwoPointPalaceCard twoPointPalaceCard = null;
		if (festivalCard instanceof OnePointPalaceCard) {
			onePointPalaceCard = (OnePointPalaceCard) festivalCard;
			a = onePointPalaceCard.getSymbol();
		} else if (festivalCard instanceof TwoPointPalaceCard) {
			twoPointPalaceCard = (TwoPointPalaceCard) festivalCard;
			a = twoPointPalaceCard.getFirstSymbol();
		}
		for (int i = 0; i < p.length; i++) {
			if (playerFacade.playerCanParticipate(i, a)) {
				players.add(p[i]);
			}
		}
		if (festivalCard instanceof TwoPointPalaceCard) {
			a = twoPointPalaceCard.getSecondSymbol();
			for (int i = 0; i < p.length; i++) {
				if (playerFacade.playerCanParticipate(i, a)
						&& !players.contains(p[i])) {
					players.add(p[i]);
				}
			}
		}
		for (int i = 3; i > players.size() - 1; i--) {
			playerFrozen[i] = true;
		}
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	public boolean getEndFestival() {
		return endFestival;
	}

	public void freezeCurrentPlayer() {
		playerFrozen[currentPlayer] = true;
	}

	public void unfreezeCurrentPlayer() {
		playerFrozen[currentPlayer] = false;
	}

	public void nextPlayer() {
		int frozen = 0;
		for (int i = 0; i < players.size(); i++) {
			if (playerFrozen[i]) {
				frozen++;
			}
		}
		if (frozen == players.size()) {
			endFestival();
		} else {
			changePlayer();
			while (playerFrozen[currentPlayer]) {
				changePlayer();
			}
		}
	}

	private void changePlayer() {
		currentPlayer++;
		if (currentPlayer >= players.size()) {
			currentPlayer = 0;
		}
	}

	public ArrayList<Player> getParticipants() {
		return players;
	}

	public void endFestival() {
		endFestival = true;
		// calculate fame points
	}

	public boolean checkEnd() {
		return endFestival;
	}

	public PalaceCard getFestivalCard() {
		return festivalCard;
	}

	public void setFestivalCard(PalaceCard festivalCard) {
		this.festivalCard = festivalCard;
	}

	public List<PalaceCard> getPlayableCards(Player p) {
		// Call playerfacade to call player turn to call player
		return playerFacade.getCardsForPlayer(p);
	}

	public void giveFestivalPoints(Player p, String[] s) {
		String[] t = new String[2];
		t[0] = s[0];
		if (s.length == 1) {
			t[1] = " ";
		} else {
			t[1] = s[1];
		}
		TwoPointPalaceCard twoPointPalaceCard;
		int pInt = 0;
		for (int i = 0; i < players.size(); i++) {
			if (p == players.get(i)) {
				pInt = i;
				break;
			}
		}
		if (festivalCard instanceof OnePointPalaceCard) {
			playerScores[pInt] += 1;
		} else if (festivalCard instanceof TwoPointPalaceCard) {
			twoPointPalaceCard = (TwoPointPalaceCard) festivalCard;
			if (twoPointPalaceCard.getFirstSymbol().equals(t[0])
					|| twoPointPalaceCard.getSecondSymbol().equals(t[0])) {
				playerScores[pInt] += 1;
			}
			if (twoPointPalaceCard.getFirstSymbol().equals(t[1])
					|| twoPointPalaceCard.getSecondSymbol().equals(t)) {
				playerScores[pInt] += 1;
			}
		}
	}

	public ArrayList<Player> getVictors() {
		ArrayList<Player> victors = new ArrayList<Player>();
		int max = 0;
		for (int i = 0; i < players.size(); i++) {
			if (i > max) {
				max = playerScores[i];
			}
		}
		for (int i = 0; i < players.size(); i++) {
			if (playerScores[i] == max) {
				victors.add(players.get(i));
			}
		}
		return victors;
	}

	public boolean playerCanPlayCard(String[] t) {
		boolean ret = false;
		String[] s = new String[2];
		s[0] = t[0];

		if (t.length == 1) {
			s[1] = " ";
		} else {
			s[1] = t[1];
		}

		if (festivalCard instanceof OnePointPalaceCard) {
			OnePointPalaceCard current = (OnePointPalaceCard) festivalCard;
			if (current.getSymbol().equals(s[0])
					|| current.getSymbol().equals(s[1])) {
				ret = true;
			}
		} else if (festivalCard instanceof TwoPointPalaceCard) {
			TwoPointPalaceCard current = (TwoPointPalaceCard) festivalCard;
			if (current.getFirstSymbol().equals(s[0])
					|| current.getFirstSymbol().equals(s[1])
					|| current.getSecondSymbol().equals(s[0])
					|| current.getSecondSymbol().equals(s[1])) {
				ret = true;
			}
		}

		return ret;
	}

	public boolean isOnePlayerLeft()
	{
		int count = 0;
		for(int i = 0; i < playerFrozen.length; i++)
		{
			if(playerFrozen[i] == false)
			{
				count++;
			}
		}

		if(count == 1)
		{
			return true;
		}

		return false;
	}

	public boolean canEndFestival()
	{
		int max = 0;
		for(int i = 0; i < playerScores.length; i++)
		{
			if(playerScores[i] > max)
			{
				max = playerScores[i];
			}
		}

		int numberOfTies = 0;
		for(int i = 0; i < playerScores.length; i++)
		{
			if(playerScores[i] == max)
			{
				numberOfTies++;
			}
		}

		if(numberOfTies > 1)
		{
			return true;
		}

		return false;

	}
}
