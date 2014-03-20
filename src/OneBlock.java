public class OneBlock extends Block {

	OneBlock(TileType type) {
		if (type == TileType.VILLAGE) {
			this.head = new VillageTile();
			this.type = TileType.VILLAGE;
		}
		else if(type == TileType.IRRIGATION){
			this.head = new IrrigationTile();
			this.type = TileType.IRRIGATION;
		}
		else if(type == TileType.RICE){
			this.head = new RiceTile();
			this.type = TileType.RICE;
		}
		else{
			throw new IllegalArgumentException("Illegal One Block type");
		}
	}

	OneBlock(PalaceTile p){
		this.head = p;
	}

	public String toString() {
		return head.getType().toString();
	}
}