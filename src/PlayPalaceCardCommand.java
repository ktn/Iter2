import java.util.List;


public class PlayPalaceCardCommand implements Command {
	private PlayerFacade player;
	private String[] type;
	private PalaceCard previouslyPlayed;

	PlayPalaceCardCommand(PlayerFacade p, String[] type) {
		this.player = p;
		this.type = type;
	}

	public void execute() {
		player.playCard(type);
		this.save();
		ArrayList<PalaceCard> cards = player.getCurrentPlayerCards();

		int cardCount = new int[6];
		for(int i = 0; i < 6; i++)
		{
			cardCount[i] = 0;
		}

		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if(current.getSymbol().equals("MASK"))
				{
					cardCount[0]++;
				}
				else if(current.getSymbol().equals("PUPPET"))
				{
					cardCount[1]++;
				}
				else if(current.getSymbol().equals("DRUM"))
				{
					cardCount[2]++;
				}
			}
			else if(cards.get(i) instanceof TwoPointPalaceCard)
			{
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if(current.getFirstSymbol().equals("MASK") && current.getSecondSymbol().equals("DRUM"))
				{
					cardCount[3]++;
				}
				else if(current.getFirstSymbol().equals("DRUM") && current.getSecondSymbol().equals("PUPPET"))
				{
					cardCount[4]++;
				}
				else if(current.getFirstSymbol().equals("PUPPET") && current.getSecondSymbol().equals("MASK"))
				{
					cardCount[5]++;
				}
			}
		}

		ViewFacade.displayPalaceInventory(cardCount);

		// mask
		// puppet
		// drum
		// mask drum
		// drum puppet
		// puppet mask
	}

	public void undo() {
		//Put the card back in the player inventory
		player.put the card back in the deck
		List<PalaceCard> cards = player.getCurrentPlayerCards();
		ViewFacade.displayPalaceInventory();
		board.updateBoard();
	}



	public void load() {
	}

	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coord.x + " " + coord.y);
		return result.toString();
	}
}