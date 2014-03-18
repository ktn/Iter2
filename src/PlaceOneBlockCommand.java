public class PlaceOneBlockCommand implements Command{
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private TileType type;

	PlaceOneBlockCommand(BoardFacade b,PlayerFacade p, Board.Coordinates c, TileType type){
		this.board = b;
		this.coords = c;
		this.playerFacade = p;
		this.type = type;
	}

	public void execute(){
		if(type == TileType.RICE){
			//assume checks have been made
			block = new RiceTile();
			player.placeRice();
			//if(board.validPlacement(coors, b)
			board.placeBlock(coords, block);

			this.save();
		}
		else if(type == TileType.VILLAGE){
			//assume checks have been made
			block = new VillageTile();
			player.placeVillage();
			//if(board.validPlacement(coors, b)
			board.placeBlock(coords, block);

			this.save();
		}
		else if(type == TileType.PALACE){
			//assume checks have been made
			block = new PalaceTile();
			player.placeRice();
			//if(board.validPlacement(coors, b)
			board.placeBlock(coords, block);

			this.save();
		}
		
	}

	public void undo(){
		board.removeBlock(coords);
		if(type == TileType.RICE){
			
			player.returnVillageBlock();
			
		}
		else if(type == TileType.VILLAGE){
			
			player.returnVillageBlock();
			
		}
		else if(type == TileType.PALACE){
			
			player.placeRice();
			
		}
	}

	public void save(){
		
	}

	public void load(){
		
	}
}