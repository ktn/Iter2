public class IllegalBlockPlacementException extends CoordinateException {
	BlockType block;

	public IllegalBlockPlacementException() {
		super();
	}

	public IllegalBlockPlacementException(String m) {
		super(m);
	}

	public IllegalBlockPlacementException(BlockType b) {
		super();
		block = b;
	}

	public IllegalBlockPlacementException(String m, BlockType b) {
		super(m);
		block = b;
	}

	public IllegalBlockPlacementException(String m, int x, int y) {
		super(m, x, y);
	}

	public IllegalBlockPlacementException(BlockType b, int x, int y) {
		super(x, y);
		block = b;
	}

	public IllegalBlockPlacementException(String m, BlockType b, int x, int y) {
		super(m, x, y);
		block = b;
	}

	public String toString() {
		String result = super.toString();
		if (block != null)
			result += " (" + block.toString() + ")";
		return result;
	}
}
