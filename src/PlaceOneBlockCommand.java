public class PlaceOneBlockCommand implements Command{
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private TileType type;

	PlaceOneBlockCommand(BoardFacade b,PlayerFacade p, Board.Coordinates c, TileType type){
		this.board = b;
		this.coords = c;
		this.player = p;
		this.type = type;
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