public class TwoPointPalaceCard extends PalaceCard
{
	//String representing the symbol on the card
	Symbol firstSymbol;
	Symbol secondSymbol;
	
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
}