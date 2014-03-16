public class OnePointPalaceCard extends PalaceCard {
	private Symbol symbol;

	public OnePointPalaceCard(String symbolType) {
		if (symbolType == "MASK") {
			symbol = Symbol.MASK;
		} else if (symbolType == "DRUM") {
			symbol = Symbol.DRUM;
		} else {
			symbol = Symbol.PUPPET;
		}
	}

	public String getSymbol() {
		return this.symbol.toString();
	}
}