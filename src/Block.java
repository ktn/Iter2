public abstract class Block {
<<<<<<< HEAD
	private Tile head;

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/

=======
	protected Tile head;

	/*
	 * ======================================== The standard orientation will be
	 * 
	 * 
	 * head --> child
	 * 
	 * | | v
	 * 
	 * child =========================================
	 */

	public void rotate() {
		head.rotate();
	}
>>>>>>> pathfinding

	public Tile getTile() {
		return head;
	}
<<<<<<< HEAD

	public Tile getTile(){
		return head;
=======
	
	public String toString(){
		return null;
>>>>>>> pathfinding
	}
}
