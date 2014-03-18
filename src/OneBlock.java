public class OneBlock extends Block {

	OneBlock(TileType type) {
		if (type == TileType.VILLAGE) {
			this.head = new VillageTile();
			type = TileType.VILLAGE;
		} else if (type == TileType.IRRIGATION) {
			this.head = new IrrigationTile();
			type = TileType.IRRIGATION;
		} else if (type == TileType.RICE) {
			this.head = new RiceTile();
			type = TileType.RICE;
		} else {
			throw new IllegalArgumentException("Illegal One Block type");
		}
	}

	public String toString() {
		return head.getType().toString();
	}
}