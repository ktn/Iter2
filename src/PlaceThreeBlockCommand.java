public class PlaceThreeBlockCommand implements Command{
	private Board board;
	private Board.Coordinates coords;
	
	PlaceThreeBlockCommand(Board.Coordinates c, Board b){
		this.board = b;
		this.coords = c;
	}

	public void execute(){
		//if(board.validPlacement(c, b))
			board.placeBlock(coords, (Block)(new ThreeBlock()));

			this.save();
	}

	public void undo(){
		board.removeBlock(coords);
	}

	public void save(){
		
	}

	public void load(){
		
	}
}