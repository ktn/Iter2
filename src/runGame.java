
public class runGame {

	public runGame() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputListener listener = new InputListener();
		System.out.println(listener);
		listener.theView.addKeyListener(listener);
	}

}
