public class ThreeBlock extends Block {

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

	ThreeBlock() {
		head = new VillageTile();

		head.set(0, new RiceTile());
		head.set(3, new RiceTile());
	}

	public String toString() {
		String s = head.getType().toString() + head.getJoined(0).toString()
				+ head.getJoined(3).toString();
		return s;
	}

}