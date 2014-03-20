import java.util.*;

/*
 ****************************************************************************
 Player is a state object with the inventory and information of the player.  It does not directly do anything besides return itself and its friends.

 Still need to implement Palace Cards
 ****************************************************************************
 */

public class Player {
	private String name;
	private int score;
	private String color; // this may end up being something different, just a
							// placeholder for now
	private int develsOffBoard; // The number of developers not placed on the
								// board
	private int develsOnBoard; // The number of developers players owns on the
								// board
	private int actionTokens;
	private int twoBlocks;
	private int villageBlocks;
	private int riceBlocks;
	private List<PalaceCard> cards;

	public Player() {
		name = "Joe Shmoe";
		score = 0;
		color = "black";
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();

	}

	public Player(String n) {
		name = n;
		score = 0;
		color = "black";
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();
	}

	public Player(String n, String c) {
		name = n;
		score = 0;
		color = c;
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();
	}

	public void setColor(String c) {
		color = c;
	}

	// Getters:
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public String getColor() {
		return color;
	}

	public int getDevelsOn() {
		return develsOnBoard;
	}

	public int getDevelsOff() {
		return develsOffBoard;
	}

	public int getActionTokens() {
		return actionTokens;
	}

	public int twoBlocksLeft() {
		return twoBlocks;
	}

	public int villageBlocksLeft() {
		return villageBlocks;
	}

	public int riceBlocksLeft() {
		return riceBlocks;
	}

	// Functions used when placing piece on board or returning piece to player

	public void placeDeveloper() {
		develsOnBoard++;
		develsOffBoard--;
	}

	public void removeDeveloper() {
		develsOnBoard--;
		develsOffBoard++;
	}

	public void useActionToken() {
		actionTokens--;
	}

	public void returnActionToken() {
		actionTokens++;
	}

	public int getActionToken() {
		return actionTokens;
	}

	public void placeTwoBlock() {
		twoBlocks--;
	}

	public void returnTwoBlock() {
		twoBlocks++;
	}

	public void placeVillageBlock() {
		villageBlocks--;
	}

	public void returnVillageBlock() {
		villageBlocks++;
	}

	public void placeRiceBlock() {
		riceBlocks--;
	}

	public void returnRiceBlock() {
		riceBlocks++;
	}

	// altering score
	public void addScore(int p) {
		score += p;
	}

	public void decrementScore(int p) {
		score -= p;
	}

	public Player getPlayer() {
		return this;
	}

	// Palace card stuff
	public List<PalaceCard> getCards() {
		return cards;
	}

	public void removeCard(PalaceCard c) {
		for (PalaceCard card : cards) {
			if (c == card) {
				cards.remove(card);
			}
		}
	}

	public void addCard(PalaceCard c) {
		cards.add(c);
	}

	public boolean cardsContain(String t) {
		boolean ret = false;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) instanceof OnePointPalaceCard) {
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if (current.getSymbol().equals(t)) {
					ret = true;
					break;
				}
			} else if (cards.get(i) instanceof TwoPointPalaceCard) {
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if (current.getFirstSymbol().equals(t)
						|| current.getSecondSymbol().equals(t)) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}

	public String showCards() {
		StringBuilder result = new StringBuilder(100);
		int i = 1;
		for (PalaceCard c : cards) {

			result.append(i++ + ". ");
			result.append(c.getSymbol() + " ");
		}

		return result.toString();
	}

	public boolean hasCardWith(String[] s) // string is 1 or 2 symbols
	{
		String[] t = new String[2];
		t[0] = s[0];
		if (s.length == 1) {
			t[1] = " ";
		} else {
			t[1] = s[1];
		}
		boolean ret = false;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) instanceof OnePointPalaceCard && t[1].equals(" ")) {
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if (current.getSymbol().equals(t[0])) {
					ret = true;
					break;
				}
			} else if (cards.get(i) instanceof TwoPointPalaceCard) {
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if (current.getFirstSymbol().equals(t[0])
						&& current.getSecondSymbol().equals(t[1])
						|| current.getFirstSymbol().equals(t[1])
						&& current.getSecondSymbol().equals(t[0])) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}

	public PalaceCard useCardWith(String[] s) {
		PalaceCard ret = null;
		String[] t = new String[2];
		t[0] = s[0];
		if (s.length == 1) {
			t[1] = " ";
		} else {
			t[1] = s[1];
		}
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) instanceof OnePointPalaceCard) {
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if (current.getSymbol().equals(t[0])) {
					ret = cards.remove(i);
					break;
				}
			} else if (cards.get(i) instanceof TwoPointPalaceCard) {
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if (current.getFirstSymbol().equals(t[0])
						&& current.getSecondSymbol().equals(t[1])
						|| current.getFirstSymbol().equals(t[1])
						&& current.getSecondSymbol().equals(t[0])) {
					ret = cards.remove(i);
					break;
				}
			}
		}
		return ret;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" Name: " + this.getName() + NEW_LINE);
		result.append(" Score: " + this.getScore() + NEW_LINE);
		result.append(" Color: " + this.getColor() + NEW_LINE);
		result.append(" Developers on Board: " + this.getDevelsOn() + NEW_LINE);
		result.append(" Developers off Board: " + this.getDevelsOff()
				+ NEW_LINE);
		result.append(" Action Points: " + this.getActionTokens() + NEW_LINE);
		result.append(" TwoBlocks: " + this.twoBlocksLeft() + NEW_LINE);
		result.append(" Village Blocks: " + this.villageBlocksLeft() + NEW_LINE);
		result.append(" Rice Blocks: " + this.riceBlocksLeft() + NEW_LINE);
		result.append(" Deck: " + this.showCards() + NEW_LINE);
		result.append("}" + NEW_LINE);
		return result.toString();
	}

	public List<PalaceCard> getPlayablePalaceCards(String[] s) {
		String[] t = new String[2];
		t[0] = s[0];
		if (s.length == 1) {
			t[1] = " ";
		} else {
			t[1] = s[1];
		}
		ArrayList<PalaceCard> playableCards = new ArrayList<PalaceCard>();

		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i) instanceof OnePointPalaceCard) {
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if (current.getSymbol().equals(t[0])) {
					playableCards.add(current);
				}
			} else if (cards.get(i) instanceof TwoPointPalaceCard) {
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if (current.getFirstSymbol().equals(t[0])
						|| current.getSecondSymbol().equals(t[0])) {
					playableCards.add(current);
				}
				if (t.length > 1) {
					if (current.getFirstSymbol().equals(t[1])
							|| current.getSecondSymbol().equals(t[1]))
						playableCards.add(current);
				}
			}

		}
		return playableCards;

	}

}
