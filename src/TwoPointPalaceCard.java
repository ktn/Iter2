public class TwoPointPalaceCard extends PalaceCard {
	// String representing the symbol on the card
	private Symbol firstSymbol;
	private Symbol secondSymbol;

	public TwoPointPalaceCard(String symbol1, String symbol2) {
		if (symbol1 == "MASK") {
			firstSymbol = Symbol.MASK;
		} else if (symbol1 == "DRUM") {
			firstSymbol = Symbol.DRUM;
		} else {
			firstSymbol = Symbol.PUPPET;
		}

		if (symbol2 == "MASK") {
			secondSymbol = Symbol.MASK;
		} else if (symbol2 == "DRUM") {
			secondSymbol = Symbol.DRUM;
		} else {
			secondSymbol = Symbol.PUPPET;
		}
	}

	public String getSymbol() {
		return getFirstSymbol() + " " + getSecondSymbol();
	}

	public String getFirstSymbol() {
		return this.firstSymbol.toString();
	}

	public String getSecondSymbol() {
		return this.secondSymbol.toString();
	}

	public String toString() {
		return getSymbol();
	}
}