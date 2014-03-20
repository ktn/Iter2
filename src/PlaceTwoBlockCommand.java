public class PlaceTwoBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;

	PlaceTwoBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
	}

	public void execute() {
		// assume checks have been made
		block = new TwoBlock();
		player.placeTwoBlock();
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
		board.updateBoard();
	}

	public void undo() {
		board.removeBlock(coords);
		player.returnTwoBlock();
		board.updateBoard();
	}

	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y);
		return result.toString();
	}
}