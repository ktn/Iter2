public abstract class Block {
	protected Tile head;

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

	public String toString(){
		return null;
	}
}
