import java.util.List;


public class PlayPalaceCardCommand implements Command {
	private PlayerFacade player;
	private String[] type;

	PlayPalaceCardCommand(PlayerFacade p, String[] type) {
		this.player = p;
		this.type = type;
	}

	public void execute() {
		player.playCard(type);
		this.save();
		List<PalaceCard> cards = player.getCurrentPlayerCards();
		ViewFacade.displayPalaceInventory();
	}

	public void undo() {
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