public class PlaceTwoBlockCommand implements Command{
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;

	PlaceTwoBlockCommand(BoardFacade b,PlayerFacade p, Board.Coordinates c){
		this.board = b;
		this.coords = c;
		this.playerFacade = p;
	}

	public void execute(){
		//assume checks have been made
		block = new TwoBlock();
		player.placeTwoBlock();
		//if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);

		this.save();
	}

	public void undo(){
		board.removeBlock(coords);
		board.putBackThreeBlock(block);
		player.returnThreeBlock();
	}

	public void save(){
		
	}

	public void load(){
		
	}
}