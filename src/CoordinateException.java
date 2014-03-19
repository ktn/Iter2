public class CoordinateException extends Exception {

	int x, y;

	public CoordinateException() {
		super();
	}

	public CoordinateException(String m) {
		super(m);
		x = -99;
		y = -99;
	}

	public CoordinateException(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public CoordinateException(String m, int x, int y) {
		super(m);
		this.x = x;
		this.y = y;
	}

	public String toString() {
		String result = super.toString();
		if (x != -99 && y != -99)
			result += " (" + x + ", " + y + ")";
		return result;
	}
}