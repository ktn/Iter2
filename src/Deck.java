import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Deck {
	private static ArrayList<PalaceCard> cardStack = new ArrayList<PalaceCard>();
	private PalaceCard festivalCard;
	private ArrayList<PalaceCard> discardStack;

	public Deck() {
		// Assumption: 10 two-symbol cards and 20 one symbol

		for (int i = 0; i < 5; i++) {
			// Five total of each type of card
			cardStack.add(new OnePointPalaceCard("MASK"));
			cardStack.add(new OnePointPalaceCard("DRUM"));
			cardStack.add(new OnePointPalaceCard("PUPPET"));
			cardStack.add(new TwoPointPalaceCard("MASK", "DRUM"));
			cardStack.add(new TwoPointPalaceCard("DRUM", "PUPPET"));
			cardStack.add(new TwoPointPalaceCard("PUPPET", "MASK"));
		}

		shuffle(cardStack);
		setFestivalCard();

		discardStack = new ArrayList<PalaceCard>();
	}

	public Deck(ArrayList<PalaceCard> cstack) {
		// Assumption: 10 two-symbol cards and 20 one symbol
		cardStack = cstack;

		setFestivalCard();

		discardStack = new ArrayList<PalaceCard>();
	}

	public void shuffle(ArrayList<PalaceCard> cardStack) {
		Collections.shuffle(cardStack);
	}

	public PalaceCard drawCard() {
		PalaceCard ret = cardStack.remove(cardStack.size() - 1);
		if (cardStack.isEmpty()) {
			cardStack = discardStack;
			shuffle(cardStack);
			discardStack.clear();
		}
		return ret;
	}

	public void setFestivalCard() {
		festivalCard = this.drawCard();
	}

	public void returnFestivalCard(PalaceCard c) {
		this.returnTopCard(this.getFestivalCard());
		this.festivalCard = c;
	}

	public PalaceCard drawFestivalCard() {
		PalaceCard previousFestCard = this.festivalCard;
		this.setFestivalCard();
		return previousFestCard;
	}

	public void discardCard(PalaceCard c) {
		discardStack.add(c);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		for (PalaceCard c : cardStack) {
			result.append(c + ", ");
		}

		return result.toString();
	}

	public PalaceCard getFestivalCard() {
		return festivalCard;
	}

	public void returnTopCard(PalaceCard c) {
		cardStack.add(c);
	}

	public static String printCards() {
		StringBuilder result = new StringBuilder(100);

		for (PalaceCard c : cardStack) {
			result.append(c.getSymbol() + ", ");
		}
		return result.toString();
	}

	public String showCards() {
		StringBuilder result = new StringBuilder(100);
		for (PalaceCard c : cardStack) {
			result.append(c + ",");
		}
		return result.toString();
	}

	public void save() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Deck.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.println(showCards());
		writer.close();
	}

}
