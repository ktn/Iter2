public class PlaceThreeBlockCommand implements Command{
	private Board board;
	private Board.Coordinates coords;
	private Block block;
	PlaceThreeBlockCommand(Board b, Board.Coordinates c){
		this.board = b;
		this.coords = c;
	}

	public void execute(){
		
		//if(board.validPlacement(coorsd, b)
			board.placeBlock(coords, block);

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