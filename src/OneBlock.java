public class OneBlock extends Block {

	OneBlock(TileType type) {
		if (type == TileType.VILLAGE) {
			this.head = new VillageTile();
<<<<<<< HEAD
			type = TileType.VILLAGE;
		}
		else if(type == TileType.IRRIGATION){
			this.head = new IrrigationTile();
			type = TileType.IRRIGATION;
		}
		else if(type == TileType.RICE){
			this.head = new RiceTile();
			type = TileType.RICE;
		}
		else{
=======
		} else if (type == TileType.IRRIGATION) {
			this.head = new IrrigationTile();
		} else if (type == TileType.RICE) {
			this.head = new RiceTile();
		} else {
>>>>>>> parent of 2624437... Removed everything but BoardFacade
			throw new IllegalArgumentException("Illegal One Block type");
		}
	}

	public String toString() {
		return head.getType().toString();
	}
}