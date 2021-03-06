
public class TwoBlock extends Block {

	/* ======================================== 
	 *	The standard orientation will be
	 * 
	 * 
	 * 		head --> child
	 * 		  |
	 * 		  |
	 * 		  v
	 *		child
	 * =========================================*/

	TwoBlock() {
		this.head = new VillageTile();
		head.set(0, new RiceTile());
		type = TileType.TWO;
	}
	
	public String toString() {
		String s = head.getType().toString() + head.getJoined(0).toString();
		return s;
	}
}