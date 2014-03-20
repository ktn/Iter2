public class PlaceThreeBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;

	PlaceThreeBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
	}

	public void execute() {
		// assume checks have been made
		block = board.getThreeBlock();
		player.playThreeBlock();
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
	}

	public void undo() {
		board.removeBlock(coords);
		board.putBackThreeBlock(block);
		player.returnThreeBlock();
	}

	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y);
		return result.toString();
	}
}