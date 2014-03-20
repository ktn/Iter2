public class PlaceTwoBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int rotation;

	PlaceTwoBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c, int r) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = r;
	}

	PlaceTwoBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = 0;
	}

	public void execute() {
		// assume checks have been made
		block = new TwoBlock();
		player.placeTwoBlock();


		for(int i = 0; i < rotation; i++)
		{
			block.rotate();
		}
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
	}

	public void undo() {
		board.removeBlock(coords);
		player.returnTwoBlock();
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