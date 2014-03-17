public class TwoBlock extends Block {

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

	TwoBlock() {
		this.head = new VillageTile();
		head.set(0, new RiceTile());
	}
}