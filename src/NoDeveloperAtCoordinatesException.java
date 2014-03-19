public class NoDeveloperAtCoordinatesException extends CoordinateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDeveloperAtCoordinatesException() {
		super();
	}

	public NoDeveloperAtCoordinatesException(String m) {
		super(m);
	}

	public NoDeveloperAtCoordinatesException(int x, int y) {
		super(x, y);
	}

	public NoDeveloperAtCoordinatesException(String m, int x, int y) {
		super(m, x, y);
	}
}