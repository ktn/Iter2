public class OnePointPalaceCard extends PalaceCard {
	private Symbol symbol;

	public OnePointPalaceCard(String symbolType) {
		if (symbolType.equals("MASK")) {
			symbol = Symbol.MASK;
		} else if (symbolType.equals("DRUM")) {
			symbol = Symbol.DRUM;
		} else if (symbolType.equals("PUPPET")) {
			symbol = Symbol.PUPPET;
		}
	}

	public String getSymbol() {
		return symbol.toString();
	}

	public String toString() {
		return getSymbol();
	}
}