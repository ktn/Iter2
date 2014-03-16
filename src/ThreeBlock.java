public class ThreeBlock extends Block{

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/	

	ThreeBlock(){
		this.head = new VillageTile();

		head.join(new RiceTile());
		head.join(new RiceTile());
	}
}