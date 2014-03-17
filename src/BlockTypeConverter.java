public class BlockTypeConverter {

	public BlockTypeConverter() {
		// TODO Auto-generated constructor stub
	}

	public static TileType convertToBlockType(Block b) {
		TileType type;
		if (b instanceof TwoBlock) {
			type = TileType.TWO;
		} else if (b instanceof ThreeBlock) {
			type = TileType.THREE;
		} else {
			type = b.getTile().getType();
		}
		return type;
	}
}
