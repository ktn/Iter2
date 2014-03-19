public class IllegalBlockPlacementException extends CoordinateException {

	private static final long serialVersionUID = 1L;
	TileType block;

	public IllegalBlockPlacementException() {
		super();
	}

	public IllegalBlockPlacementException(String m) {
		super(m);
	}

	public IllegalBlockPlacementException(TileType b) {
		super();
		block = b;
	}

	public IllegalBlockPlacementException(String m, TileType b) {
		super(m);
		block = b;
	}

	public IllegalBlockPlacementException(String m, int x, int y) {
		super(m, x, y);
	}

	public IllegalBlockPlacementException(TileType b, int x, int y) {
		super(x, y);
		block = b;
	}

	public IllegalBlockPlacementException(String m, TileType b, int x, int y) {
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
