public abstract class Tile {
	private Tile head

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/

	Block(Tile ... tiles){

		head = tiles;

		for(Tile t : tiles){
			head.join(t);
		}

	}

	public void rotate(){
		head.rotate();
	}
}