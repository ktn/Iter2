public class DrawPalaceCardCommand implements Command {
	private BoardFacade board;
	private PlayerFacade player;
	private PalaceCard card;

	DrawPalaceCardCommand(BoardFacade b, PlayerFacade p) {
		this.board = b;
		this.player = p;

	}

	public void execute() {
		card = player.drawFestivalCard();
		player.addCard(card);
		this.save();

	}

	public void undo() {
		// return player card
		// return festival cards
		player.removeCard(card);
		player.returnFestivalCard(card);

	}

	public void load() {

	}

	public void save() {
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName());
		return result.toString();
	}
}