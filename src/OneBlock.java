public class OneBlock extends Block{
	
	OneBlock(TileType type){
		if(type == TileType.VILLAGE){
			this.head = new VillageTile();
		}
		else if(type == TileType.IRRIGATION){
			this.head = new IrrigationTile();
		}
		else if(type == TileType.RICE){
			this.head = new RiceTile();
		}
		else{
			throw new IllegalArgumentException("Illegal One Block type");
		}
	}
}