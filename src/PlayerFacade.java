import java.awt.Color;
import java.util.*;

/*
 ******************************************************************************
 PlayerFacade is a relationship object that connects the Player class with the rest of the code
 ******************************************************************************
 */

public class PlayerFacade {
	PlayerTurn playerTurn;
	int numPlayers;
	Deck deck;

	// initialization requires names of players, assumes acceptable size
	public PlayerFacade(String[] n) {
		numPlayers = n.length;
		if (numPlayers > 4) {
			numPlayers = 4;
		}

		playerTurn = new PlayerTurn(n);

		setBlockPlayed(false);

		deck = new Deck();
	}

	// change player turn
	public void changeTurn() {
		playerTurn.changeTurn();
	}

	public void changeTurn(int i) {
		playerTurn.changeTurn(i);
	}

	// public void revertTurn() {
		// playerTurn.revertTurn();
		// currentPlayer = playerTurn.getCurrentPlayer();
	// }

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

	public int getActionPoints() {
		return playerTurn.getActionPoints();
	}

	public void returnActionToken() {
		playerTurn.returnActionToken();
	}

	public void setBlockPlayed(boolean b) {
		playerTurn.setBlockPlayed(b);
	}

	public boolean checkTwoBlock() {
		if (playerTurn.getCurrentPlayer().twoBlocksLeft() > 0
				&& playerTurn.getActionPoints() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeTwoBlock() {
		playerTurn.getCurrentPlayer().placeTwoBlock();
		setBlockPlayed(true);
		playerTurn.addToActionPoints(-1);
	}

	public void returnTwoBlock() {
		playerTurn.getCurrentPlayer().returnTwoBlock();
	}

	public boolean checkVillage() {
		if (playerTurn.getCurrentPlayer().villageBlocksLeft() > 0
				&& playerTurn.getActionPoints() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeVillage() {
		playerTurn.getCurrentPlayer().placeVillageBlock();
		setBlockPlayed(true);
		playerTurn.addToActionPoints(-1);
	}

	public void returnVillageBlock() {
		playerTurn.getCurrentPlayer().returnVillageBlock();
		playerTurn.addToActionPoints(1);
	}

	public boolean checkRice() {
		if (playerTurn.getCurrentPlayer().riceBlocksLeft() > 0
				&& playerTurn.getActionPoints() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void placeRice() {
		playerTurn.getCurrentPlayer().placeRiceBlock();
		setBlockPlayed(true);
		playerTurn.addToActionPoints(-1);
	}

	public void returnRiceBlock() {
		playerTurn.getCurrentPlayer().returnRiceBlock();
		playerTurn.addToActionPoints(1);

	}

	public void playThreeBlock() {
		setBlockPlayed(true);
		playerTurn.addToActionPoints(-1);
	}

	public void returnThreeBlock() {
		playerTurn.addToActionPoints(1);

	}

	public boolean blockPlayed() {
		return playerTurn.blockPlayed();
	}

	public void blockNotPlayed() {
		setBlockPlayed(false);
	}

	// changing score
	public void addScore(int s) {
		playerTurn.getCurrentPlayer().addScore(s);
	}

	public void addPlayerScore(Player p, int s) {
		p.addScore(s);
	}

	public void decrementScore(int s) {
		playerTurn.getCurrentPlayer().decrementScore(s);
	}

	public void decrementPlayerScore(Player p, int s) {
		p.decrementScore(s);
	}

	// changing colors
	public void currentPlayerColor(Color c) {
		playerTurn.getCurrentPlayer().setColor(c);
	}

	// assumes it will always receive a valid input
	public void setPlayerColor(Color s, int p) {
		playerTurn.getPlayer(p).setColor(s);
	}

	// palace card stuff
	public List<PalaceCard> getCards() {
		return playerTurn.getCurrentPlayer().getCards();
	}

	public void addCard(PalaceCard c) {
		playerTurn.getCurrentPlayer().addCard(c);
	}

	public void returnCard(int p, PalaceCard c) {
		playerTurn.getPlayer(p).addCard(c);
	}

	public boolean playerCanParticipate(int p, String t) {
		boolean ret = false;
		ret = playerTurn.getPlayer(p).cardsContain(t);
		return ret;
	}

	public boolean hasCardWith(String[] t) {
		return playerTurn.getPFPlayer().hasCardWith(t);
	}

	public boolean playerHasPC(int p, String[] t) {
		boolean ret = false;
		ret = playerTurn.getPlayer(p).hasCardWith(t);
		return ret;
	}

	public void playerUsePC(int p, String[] t) {
		PalaceCard palaceCard = playerTurn.getPFPlayer().useCardWith(t);
		// System.out.println("Current card about to be played: " +
		// palaceCard.toString());
		deck.discardCard(palaceCard);
		playerTurn.giveFestivalPoints(p, t);
	}

	public PalaceCard topCard() {
		return deck.drawCard();
	}

	public void removeCard(PalaceCard c) {
		playerTurn.getCurrentPlayer().removeCard(c);
	}

	public void returnTopCard(PalaceCard c) {
		deck.returnTopCard(c);
	}

	public void discardCard(PalaceCard c) {
		deck.discardCard(c);
	}

	public void createPalaceFestival() {
		playerTurn.createPalaceFestival(deck.getFestivalCard(), this);
	}

	public void startFestival(Player[] p) {
		playerTurn.startFestival(p);
	}

	public Player getPFPlayer() {
		return playerTurn.getPFPlayer();
	}

	public int getPlayerInt(Player p) {
		return playerTurn.getPlayerInt(p);
	}

	public ArrayList<Player> getParticipants() {
		return playerTurn.getParticipants();
	}

	public ArrayList<PalaceCard> getCardsForPlayer(Player p) {
		return playerTurn.getCardsForPlayer(p);
	}

	public ArrayList<PalaceCard> getCurrentPlayerCards() {
		return getCardsForPlayer(playerTurn.getPFPlayer());
	}

	public boolean playCard(String[] t) {
		if (playerHasPC(getPlayerInt(playerTurn.getPFPlayer()), t)
				&& playerCanPlayCard(t)) {

			playerUsePC(getPlayerInt(playerTurn.getPFPlayer()), t);
			playerTurn.giveFestivalPoints(playerTurn.getPFPlayer(), t);
			return true;
		} else {
			return false;
		}
	}

	public boolean playerCanPlayCard(String[] t) {
		return playerTurn.playerCanPlayCard(t);
	}

	public PalaceCard getFestivalCard() {

		return playerTurn.getFestivalCard();
	}

	public PalaceCard drawFestivalCard() {
		return deck.drawFestivalCard();
	}

	public void returnFestivalCard(PalaceCard c) {
		deck.returnFestivalCard(c);
	}

	public void freezePlayer() {
		playerTurn.freezeCurrentPlayer();
	}

	public void unfreezePlayer() {
		playerTurn.unfreezeCurrentPlayer();
	}

	public void nextPFPlayer() {
		playerTurn.nextPFPlayer();
	}

	public boolean checkEnd() {
		return playerTurn.checkEnd();
	}

	public ArrayList<Player> getVictors() {
		return playerTurn.getVictors();
	}

	public Player[] getPlayers() {
		return playerTurn.getPlayers();
	}

	public void placeDeveloper() {
		playerTurn.getCurrentPlayer().placeDeveloper();
	}

	public void removeDeveloper() {
		playerTurn.getCurrentPlayer().removeDeveloper();
	}

	public String getName() {
		return playerTurn.getCurrentPlayer().getName();
	}

	public void loadDeck(ArrayList<PalaceCard> c) {
		deck = new Deck(c);
	}
	
	public String toString() {
		return playerTurn.getCurrentPlayer().toString();
	}

	public boolean isOnePlayerLeft() {
		return playerTurn.isOnePlayerLeft();
	}

	public boolean canEndFestival() {
		return playerTurn.canEndFestival();
	}

	public boolean getEndFestival() {
		return playerTurn.getEndFestival();
	}

}
