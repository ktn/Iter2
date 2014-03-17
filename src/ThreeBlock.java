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
		head.set(1, new RiceTile());
	}
}