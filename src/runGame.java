
public class runGame {

	public runGame() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length >=2 && args.length <=4) {
			InputListener listener = new InputListener(args);
			System.out.println(listener);
		}
		else
			System.out.println("Please re-run game with 2-4 players");
		//listener.theView.addKeyListener(listener);
	}

}
