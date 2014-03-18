public class PlaceTwoBlockCommand implements Command{
	private Board board;
	private Board.Coordiantes coords;
	private Block block;

	PlaceTwoBlockMethod(Board b, Board.Coordinates c){
		this.board = b;
		this.coords = c;
	}

	public void execute(){
		
		//if(board.validPlacement(coords, ))
			board.placeBlock(coords. block);
	}

	public void undo(){
		board.removeBlock(coords);
	}

	public void save(){
		
	}

	public void load(){
		
	}
}
