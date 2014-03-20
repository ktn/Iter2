public class DrawDeckCardCommand implements Command {
	private BoardFacade board;
	private PlayerFacade player;
	private PalaceCard card;

	DrawDeckCardCommand(BoardFacade b, PlayerFacade p) {
		this.board = b;
		this.player = p;

	}

	public void execute() {
		card = player.topCard();
		player.addCard(card);
		this.save();
		board.updateBoard();

	}

	public void undo() {
		// return player card
		// return festival cards
		player.returnTopCard(card);
		player.removeCard(card);
		board.updateBoard();

	}

	public void load() {

	}

	public void save() {
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + card);
		return result.toString();
	}
}