import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class runGame {

	public runGame() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		if (args.length >= 2 && args.length <= 4) {
			InputListener listener = new InputListener(args);
			System.out.println(listener);
			saveNames(args);

		} else
			System.out.println("Please re-run game with 2-4 players");
		// listener.theView.addKeyListener(listener);
	}

	public static void saveNames(String[] args) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("Players");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < args.length; i++) {
			writer.print(args[i] + " ");
		}
		writer.close();

	}
}
