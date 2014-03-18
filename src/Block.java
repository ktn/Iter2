public abstract class Block {
<<<<<<< HEAD
	private Tile head;
	protected TileType type;
=======
	protected Tile head;
>>>>>>> parent of 2624437... Removed everything but BoardFacade

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/

	public void rotate() {
		head.rotate();
	}

	public Tile getTile() {
		return head;
	}
<<<<<<< HEAD

	public TileType getType(){
		return type;
	}
=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade
	
	public String toString(){
		return null;
	}
}
