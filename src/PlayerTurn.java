/*
 ******************************************************************************
PlayerTurn is an object that keeps track of whose turn it is
 ******************************************************************************
 */
import java.util.*;

public class PlayerTurn {
	Player[] players;
	int currentPlayer;
	int numPlayers;
	boolean tokenUsed;
	int actionPoints;
	boolean blockPlayed;
	PalaceFestival festival;

	// initialization requires names of players, assumes acceptable size
	public PlayerTurn(String[] n) {
		numPlayers = n.length;

		if (numPlayers > 4) {
			numPlayers = 4;
		}

		players = new Player[numPlayers];
		currentPlayer = 0;

		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(n[i]);
		}
		tokenUsed = false;
		actionPoints = 6;
		blockPlayed = false;

		festival = new PalaceFestival();
	}

	// change player turn
	public void changeTurn() {
		currentPlayer++;
		if (currentPlayer >= numPlayers) {
			currentPlayer = 0;
		}
		tokenUsed = false;
		actionPoints = 6;
		blockPlayed = false;
	}

	public Player getCurrentPlayer() {
		return players[currentPlayer].getPlayer();
	}

	public Player getPlayer(int p) {
		return players[p];
	}
	
	public int getActionPoints() {
		return actionPoints;
	}

	// checks, placements, and returns
	public boolean actionTokenUsable() {
		if (players[currentPlayer].getActionTokens() > 0 && !tokenUsed) {
			return true;
		} else {
			return false;
		}
	}

	public void useActionToken() {
		players[currentPlayer].useActionToken();
		tokenUsed = true;
		actionPoints++;
	}

	public boolean blockPlayed() {
		return blockPlayed;
	}

	public void playedBlock() {
		blockPlayed = true;
	}

	public void returnActionToken() {
		players[currentPlayer].returnActionToken();
	}

	public void createPalaceFestival(PalaceCard c, PlayerFacade p) {
		festival = new PalaceFestival(c, p);
	}

	public void startFestival(Player[] p) {
		festival.startFestival(p);
	}

	public int getPlayerInt(Player p) {
		int ret = -1;
		for (int i = 0; i < numPlayers; i++) {
			if (players[i] == p) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	public PalaceCard getFestivalCard() {
		return festival.getFestivalCard();
	}

	public ArrayList<PalaceCard> getCardsForPlayer(Player p) {
		PalaceCard currentFestCard = this.festival.getFestivalCard();
		ArrayList<PalaceCard> cardList = new ArrayList<PalaceCard>();
		ArrayList<PalaceCard> temp = new ArrayList<PalaceCard>();
		OnePointPalaceCard onePointPalaceCard;
		TwoPointPalaceCard twoPointPalaceCard;

		// Type of the festival card
		String[] type;

		System.out.println("Current festival card: "
				+ currentFestCard.toString() + "\n");

		if (currentFestCard instanceof OnePointPalaceCard) {
			onePointPalaceCard = (OnePointPalaceCard) currentFestCard;
			// System.out.println("One point fest card " + currentFestCard);
			type = new String[1];
			type[0] = onePointPalaceCard.getSymbol();
			cardList.addAll(p.getPlayablePalaceCards(type));
		} else if (currentFestCard instanceof TwoPointPalaceCard) {
			twoPointPalaceCard = (TwoPointPalaceCard) currentFestCard;
			// System.out.println("Two point fest card " + currentFestCard);
			type = new String[2];
			type[0] = twoPointPalaceCard.getFirstSymbol();
			cardList.addAll(p.getPlayablePalaceCards(type));
			type[1] = twoPointPalaceCard.getSecondSymbol();
			temp.addAll(p.getPlayablePalaceCards(type));

			for (int i = 0; i < temp.size(); i++) {
				if (!cardList.contains(temp.get(i))) {
					cardList.add(temp.get(i));
				}
			}

		}

		return cardList;
	}

	public ArrayList<Player> getParticipants() {
		return festival.getParticipants();
	}

	public boolean checkEnd() {
		return festival.checkEnd();
	}

	public Player getPFPlayer() {
		return festival.getCurrentPlayer();
	}

	public void freezeCurrentPlayer() {
		festival.freezeCurrentPlayer();
	}

	public void unfreezeCurrentPlayer()
	{
		festival.unfreezeCurrentPlayer();
	}
	
	public void nextPFPlayer()
	{

		festival.nextPlayer();
	}

	public void giveFestivalPoints(Player p, String[] t) {
		festival.giveFestivalPoints(p, t);
	}

	public void giveFestivalPoints(int p, String[] t) {
		// Find player corresponding to p
		Player player = this.getPlayer(p);
		festival.giveFestivalPoints(player, t);
	}

	public ArrayList<Player> getVictors() {
		return festival.getVictors();
	}

	public Player[] getPlayers()
	{
		return players;
	}

	public boolean playerCanPlayCard(String[] t)
	{
		return festival.playerCanPlayCard(t);
	}

	public boolean isOnePlayerLeft()
	{
		return festival.isOnePlayerLeft();
	}

	public boolean canEndFestival()
	{
		return festival.canEndFestival();
	}

	public boolean getEndFestival()
	{
		return festival.getEndFestival();
	}


}
