public class PlaceRiceTileCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;

	PlaceRiceTileCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
	}

	public void execute() {
		// assume checks have been made
		block = new OneBlock(TileType.RICE);
		player.placeRice();
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
		board.updateBoard();
		ViewFacade.getCurrentPlayerView().displayRiceBlocks(player.currentPlayer.riceBlocksLeft());
	}

	public void undo() {
		board.removeBlock(coords);

		player.returnRiceBlock();
		board.updateBoard();
		ViewFacade.getCurrentPlayerView().displayRiceBlocks(player.currentPlayer.riceBlocksLeft());
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