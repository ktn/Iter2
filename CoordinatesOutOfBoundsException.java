public class CoordinatesOutOfBoundsException extends CoordinateException {
	
	public CoordinatesOutOfBoundsException() {
		super();
	}

	public CoordinatesOutOfBoundsException(String m) {
		super(m);
	}
	public CoordinatesOutOfBoundsException(int x, int y) {
		super(x, y);
	}
	
	public CoordinatesOutOfBoundsException(String m, int x, int y) {
		super(m, x, y);
	}
}