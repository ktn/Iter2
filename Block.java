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

	Block(Tile ... tiles){

		head = tiles[0];

		for(Tile t : tiles){
			head.join(t);
		}

	}

	public void rotate(){
		head.rotate();
	}
	
	public Tile getTile(){
		return head;
	}
}