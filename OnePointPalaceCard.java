public class OnePointPalaceCard extends PalaceCard
{
	//String representing the symbol on the card
	Symbol symbol;
	
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
}