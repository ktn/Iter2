public class PlaceThreeBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int rotation;

	PlaceThreeBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c, int r) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = r;
	}

	PlaceThreeBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = 0;
	}

	public void execute() {
		// assume checks have been made
		block = board.getThreeBlock();
		player.playThreeBlock();

		for(int i = 0; i < rotation; i++)
		{
			block.rotate();
		}
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
		board.updateBoard();
	}

	public void undo() {
		board.removeBlock(coords);
		board.putBackThreeBlock(block);
		player.returnThreeBlock();
		board.updateBoard();
	}

	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "+ coords.y+" "+ rotation);
		return result.toString();
	}
}