public abstract class Block {
	private Tile head;

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/


	public void rotate(){
		head.rotate();
	}

	public Tile getTile(){
		return head;
	}
}
