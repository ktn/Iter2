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

		type = TileType.THREE;
		head.set(Grid.RIGHT, new RiceTile());
		head.getJoined(Grid.RIGHT).set(Grid.LEFT,head);
		head.set(Grid.BOTTOM, new RiceTile());
		head.getJoined(Grid.BOTTOM).set(Grid.TOP,head);
	}

	public String toString() {
		String s = head.getType().toString() + head.getJoined(0).toString()
				+ head.getJoined(3).toString();
		return s;
	}

}