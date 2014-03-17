import java.util.*;

/*
 ******************************************************************************
 PlayerFacade is a relationship object that connects the Player class with the rest of the code
 ******************************************************************************
 */

public class PlayerFacade {
	PlayerTurn playerTurn;
	Player currentPlayer;
	int numPlayers;
	boolean tokenUsed;
	int actionPoints;
	boolean blockPlayed;
	Deck deck;

	// initialization requires names of players, assumes acceptable size
	public PlayerFacade(String[] n) {
		numPlayers = n.length;
		if (numPlayers > 4) {
			numPlayers = 4;
		}

		playerTurn = new PlayerTurn(n);
		currentPlayer = playerTurn.getCurrentPlayer();

		tokenUsed = false;
		actionPoints = 6;
		blockPlayed = false;

		deck = new Deck();
	}

	// change player turn
	public void changeTurn() {
		playerTurn.changeTurn();
		currentPlayer = playerTurn.getCurrentPlayer();
	}

	public Player getCurrentPlayer() {
		return playerTurn.getCurrentPlayer();
	}

	// checks, placements, and returns
	public boolean actionTokenUsable() {
		return playerTurn.actionTokenUsable();
	}

	public void useActionToken() {
		playerTurn.useActionToken();
	}

	public void returnActionToken()
	{
		playerTurn.returnActionToken();
	}

	public boolean checkTwoBlock() {
		if (currentPlayer.twoBlocksLeft() > 0 && actionPoints > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeTwoBlock() {
		currentPlayer.placeTwoBlock();
		actionPoints--;
	}

	public void returnTwoBlock() {
		currentPlayer.returnTwoBlock();
	}

	public boolean checkVillage() {
		if (currentPlayer.villageBlocksLeft() > 0 && actionPoints > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeVillage() {
		currentPlayer.placeVillageBlock();
		actionPoints--;
	}

	public void returnVillageBlock() {
		currentPlayer.returnVillageBlock();
		actionPoints++;
	}

	public boolean checkRice() {
		if (currentPlayer.riceBlocksLeft() > 0 && actionPoints > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeRice() {
		currentPlayer.placeRiceBlock();
		actionPoints--;
	}

	public void returnRiceBlock() {
		currentPlayer.returnRiceBlock();
		actionPoints++;
	}

	public void playThreeBlock() {
		blockPlayed = true;
		actionPoints--;
	}

	public void returnThreeBlock()
	{
		actionPoints++;
	}

	public boolean blockPlayed() {
		return blockPlayed;
	}

	public void blockNotPlayed()
	{
		blockPlayed = false;
	}

	// changing score
	public void addScore(int s) {
		currentPlayer.addScore(s);
	}

	public void decrementScore(int s) {
		currentPlayer.decrementScore(s);
	}

	// changing colors
	public void currentPlayerColor(String c) {
		currentPlayer.setColor(c);
	}

	// assumes it will always receive a valid input
	public void setPlayerColor(String s, int p) {
		playerTurn.getPlayer(p).setColor(s);
	}

	// palace card stuff
	public List<PalaceCard> getCards() {
		return currentPlayer.getCards();
	}

	public void addCard(PalaceCard c) {
		currentPlayer.addCard(c);
	}

	public void returnCard(int p, PalaceCard c) {
		playerTurn.getPlayer(p).addCard(c);
	}

	public boolean playerCanParticipate(int p, String t) {
		boolean ret = false;
		ret = playerTurn.getPlayer(p).cardsContain(t);
		return ret;
	}

	public boolean playerHasPC(int p, String[] t) {
		boolean ret = false;
		ret = playerTurn.getPlayer(p).hasCardWith(t);
		return ret;
	}

	public void playerUsePC(int p, String[] t) {
		playerTurn.getPlayer(p).useCardWith(t);
		playerTurn.giveFestivalPoints(p, t);
	}

	public PalaceCard topCard()
	{
		return deck.drawCard();
	}

	public void returnTopCard(PalaceCard c)
	{
		deck.returnTopCard(c);
	}

	public void createPalaceFestival()
	{
		playerTurn.createPalaceFestival(deck.getFestivalCard(), this);
	}

	public void startFestival(Player[] p)
	{
		playerTurn.startFestival(p);
	}

	public int getPlayerInt(Player p)
	{
		return playerTurn.getPlayerInt(Player p);
	}

	public List<PalaceCard> getCardsForPlayer(Player p)
	{
		return playerTurn.getCardsForPlayer(p);
	}
}
