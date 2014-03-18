public abstract class Block {
	protected Tile head;
	protected TileType type;

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

	public TileType getType(){
		return type;
	}

	public String toString(){
		return null;
	}
}