public class NoBlocksLeftException extends Exception {
	BlockType block;
	
	public NoBlocksLeftException() {
		super();
	}

	public NoBlocksLeftException(String m) {
		super(m);
	}
	
	public NoBlocksLeftException(BlockType b) {
		super();
		block = b;
	}
	
	public NoBlocksLeftException(String m, BlockType b) {
		super(m);
		block = b;
	}
	
	public String toString() {
		String result = super.toString();
		if(block != null) result += " (" + block.toString() + ")";
		return result;
	}
}