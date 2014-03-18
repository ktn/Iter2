
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
<<<<<<< HEAD
		type = TileType.TWO;
	}


=======
	}

>>>>>>> parent of 2624437... Removed everything but BoardFacade
	public String toString() {
		String s = head.getType().toString() + head.getJoined(0).toString();
		return s;
	}
}