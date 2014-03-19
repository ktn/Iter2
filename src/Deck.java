import java.util.*;

public class Deck {
	private ArrayList<PalaceCard> cardStack;
	private PalaceCard festivalCard;
	private ArrayList<PalaceCard> discardStack;

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
		setFestivalCard();
		
		discardStack = new ArrayList<PalaceCard>();
	}

	public void shuffle(ArrayList<PalaceCard> cardStack) {
		Collections.shuffle(cardStack);
	}

	public PalaceCard drawCard() {
		PalaceCard ret = cardStack.remove(cardStack.size()-1);
		if(cardStack.isEmpty())
		{
			cardStack = discardStack;
			shuffle(cardStack);
			discardStack.clear();
		}
		return ret;
	}

	public void setFestivalCard() {
		festivalCard = this.drawCard();
	}

	public PalaceCard drawFestivalCard() {
		PalaceCard previousFestCard = this.festivalCard;
		this.setFestivalCard();
		return previousFestCard;
	}

	public void discardCard(PalaceCard c)
	{
		discardStack.add(c);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " Object {" + NEW_LINE);

		int i = 1;
		for (PalaceCard c : cardStack) {
			result.append(" " + i++ + ". ");
			result.append(c.getSymbol() + NEW_LINE);
		}
		result.append("}" + NEW_LINE);

		return result.toString();
	}

	public PalaceCard getFestivalCard()
	{
		return festivalCard;
	}

	public void returnTopCard(PalaceCard c)
	{
		cardStack.add(c);
	}
}
