import java.util.*;

public class Deck {
	private ArrayList<PalaceCard> cardStack;
	private PalaceCard festivalCard;

	public Deck() {
		// Assumption: 10 two-symbol cards and 20 one symbol
		cardStack = new ArrayList<PalaceCard>();

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
	}

	public void shuffle(ArrayList<PalaceCard> cardStack) {
		Collections.shuffle(cardStack);
	}

	public PalaceCard drawCard() {
		return cardStack.remove(0);
	}

	public void setFestivalCard() {
		festivalCard = this.drawCard();
	}

	public PalaceCard drawFestivalCard() {
		PalaceCard previousFestCard = this.festivalCard;
		this.setFestivalCard();
		return previousFestCard;
	}
}