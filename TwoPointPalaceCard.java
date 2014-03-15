public class TwoPointPalaceCard extends PalaceCard
{
	//String representing the symbol on the card
	private Symbol firstSymbol;
	private Symbol secondSymbol;
	
	public TwoPointPalaceCard(String symbol1, String symbol2)
	{
		if(symbol1 == "MASK")
		{
			firstSymbol = MASK; 
		}
		else if(symbol1 == "DRUM")
		{
			firstSymbol = DRUM;
		}
		else
		{
			firstSymbol = PUPPET;
		}

		if(symbol2 == "MASK")
		{
			secondSymbol = MASK; 
		}
		else if(symbol2 == "DRUM")
		{
			secondSymbol = DRUM;
		}
		else
		{
			secondSymbol = PUPPET;
		}
	}

	public Symbol getFirstSymbol()
	{
		return this.firstSymbol;
	}

	public Symbol getSecondSymbol()
	{
		return this.secondSymbol;
	}
}