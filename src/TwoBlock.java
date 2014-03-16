public class OneBlock extends Block{
	
	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/

	TwoBlock(){
		this.head = new VillageTile();

		head.join(new RiceTile());
		type = TileType.TWO;
	}
}