public class PlaceIrrigationTileCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;

	PlaceIrrigationTileCommand(BoardFacade b, PlayerFacade p,
			Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
	}

	public void execute() {
		// assume checks have been made
		block = board.getIrrigationTile();
		player.playThreeBlock();
		// if(board.validPlacement(coors, b)
		board.placeBlock(block, coords);

		this.save();

	}

	public void undo() {
		board.removeBlock(coords);

		player.returnThreeBlock();

	}

	public void save() {

	}

	public void load() {

	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y);
		return result.toString();
	}
}