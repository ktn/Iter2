/*
 ******************************************************************************
PlayerTurn is an object that keeps track of whose turn it is
 ******************************************************************************
 */

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

		festival = new PalaceFestival;
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

	public void returnActionToken()
	{
		players[currentPlayer].returnActionToken();
	}

	public void createPalaceFestival(PalaceCard c, PlayerFacade p)
	{
		festival = new PalaceFestival(c, p);
	}

	public void startFestival(Player[] p)
	{
		festival.startFestival(p);
	}

	public int getPlayerInt(Player p)
	{
		int ret = -1;
		for(int i = 0; i < numPlayers; i++)
		{
			if(players[i] = p)
			{
				ret = i;
				break;
			}
		}
		return ret;
	}

	public List<PalaceCard> getCardsForPlayer(Player p)
	{
		PalaceCard currentFestCard = this.festival.getFestivalCard();

		//Type of the festival card
		String[] type;

		if(currentFestCard instanceof OnePointPalaceCard)
		{
			type = new String[1];
			type[0] = (OnePointPalaceCard) currentFestCard.getSymbol();
		}
		else if(currentFestCard instanceof TwoPointPalaceCard)
		{
			type = new String[2];
			type[0] = (TwoPointPalaceCard) currentFestCard.getFirstSybmol();
			type[1] = (TwoPointPalaceCard) currentFestCard.getSecondSymbol();
		}

		return p.getPlayablePalaceCards(type);
	}
}
