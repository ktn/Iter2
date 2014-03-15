public class OnePointPalaceCard extends PalaceCard
{
	private Symbol symbol;
	
	public OnePointPalaceCard(String symbolType)
	{
		if(symbolType == "MASK")
		{
			symbol = MASK; 
		}
		else if(symbolType == "DRUM")
		{
			symbol = DRUM;
		}
		else
		{
			symbol = PUPPET;
		}
	}

	public Symbol getSymbol()
	{
		return this.symbol;
	}
}