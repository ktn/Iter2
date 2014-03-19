public class NoBlocksLeftException extends Exception {

	TileType block;

	public NoBlocksLeftException() {
		super();
	}

	public NoBlocksLeftException(String m) {
		super(m);
	}

	public NoBlocksLeftException(TileType b) {
		super();
		block = b;
	}

	public NoBlocksLeftException(String m, TileType b) {
		super(m);
		block = b;
	}

	public String toString() {
		String result = super.toString();
		if (block != null)
			result += " (" + block.toString() + ")";
		return result;
	}
}