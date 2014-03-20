public class PlacePalaceTileCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int level;

	PlacePalaceTileCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c,
			int l) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.level = l;
	}

	public void execute() {
		// assume checks have been made
		block = new OneBlock(new PalaceTile(level));
		player.playThreeBlock();
		board.placeBlock(coords, block);

		player.getCurrentPlayer().addScore(1);
		
		this.save();
		board.updateBoard();

		ViewFacade.getPublicInventoryView().fullUpdate(board);
	}

	public void undo() {
		board.removeBlock(coords);

		player.returnThreeBlock();
		board.updateBoard();
		
		player.getCurrentPlayer().addScore(-1);

		ViewFacade.getPublicInventoryView().fullUpdate(board);
	}



	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y + " " + this.level);
		return result.toString();
	}
}