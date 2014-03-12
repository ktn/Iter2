public class BlockTypeConverter {

	public BlockTypeConverter() {
		// TODO Auto-generated constructor stub
	}

	public static BlockType convertToBlockType(Block b) {
		BlockType type;
		if (b instanceof TwoBlock) {
			type = BlockType.TWO;
		} else if (b instanceof ThreeBlock) {
			type = BlockType.THREE;
		} else if (b instanceof Village) {
			type = BlockType.VILLAGE;
		} else if (b instanceof Rice) {
			type = BlockType.RICE;
		} else if (b instanceof Irrigation) {
			type = BlockType.IRRIGATION;
		} else if (b instanceof Palace) {
			type = BlockType.PALACE;
		}
		return type;
	}
}
