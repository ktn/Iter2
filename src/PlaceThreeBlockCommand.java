public PlaceThreeBlockCommand implements Command{
	private Board board;
	private Board.Coordinatees coords;
	
	PlaceThreeBlockCommand(Board.Coordinates c, Board b){
		this.board = b;
		this.coords = c;
	}

	public void execute(){
		
		
	}

	public void undo(){
		
	}

	public void save(){
		
	}

	public void load(){
		
	}
}