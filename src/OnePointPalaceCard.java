public class OnePointPalaceCard extends PalaceCard {
	private Symbol symbol;

	public OnePointPalaceCard(String symbolType) {
		if (symbolType == "MASK") {
			symbol = Symbol.MASK;
		} else if (symbolType == "DRUM") {
			symbol = Symbol.DRUM;
		} else if (symbolType == "PUPPET") {
			symbol = Symbol.PUPPET;
		} else
			symbol = Symbol.NULL;
	}

	public String getSymbol() {
		return symbol.toString();
	}

	public String toString() {
		return getSymbol();
	}
}