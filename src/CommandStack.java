import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public final class CommandStack {
	static Stack<Command> commands = new Stack<Command>();

	public static void storeCommand(Command c) {
		commands.push(c);
	}

	public static void save(String fileName) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		for (Command comm : commands) {
			writer.println(comm);
		}
		writer.close();
	}

	public static void load(String fileName, PlayerFacade p, BoardFacade b)
			throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream(fileName));
		while (in.hasNextLine()) {
			String s = in.nextLine();
			String[] sa = s.split(" ");
			
			Command c;
			switch (sa[0]) {
			case "PlaceIrrigationTileCommand":
				c = new PlaceIrrigationTileCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])));
				c.execute();
				break;
			case "PlaceRiceTileCommand":
				c = new PlaceRiceTileCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])));
				c.execute();
				break;
			case "PlacePalaceTileCommand":
				c = new PlacePalaceTileCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])),
						Integer.parseInt(sa[3]));
				c.execute();
				break;
			case "PlaceTwoBlockCommand":
				c = new PlaceTwoBlockCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])));
				c.execute();
				break;
			case "PlaceThreeBlockCommand":
				c = new PlaceThreeBlockCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])));
				c.execute();
				break;
			case "PlaceDeveloperCommand":
				c = new PlaceDeveloperCommand(b, p, b.getCoordinates(
						Integer.parseInt(sa[1]), Integer.parseInt(sa[2])));
				c.execute();
				break;

			case "ChangeTurnCommand":
				c = new ChangeTurnCommand(p);
				c.execute();
				break;
			case "DrawDeckCardCommand":
				c = new DrawDeckCardCommand(b, p);
				c.execute();
				break;
			case "Deck":
				for (int i = 1; i < 50; i++) {
					Arrays.copyOfRange(sa, 1, sa.length);
				}
				break;
			}
		}
		in.close();
	}

	@SuppressWarnings("resource")
	public static ArrayList<PalaceCard> loadDeck() {
		Scanner in = null;
		try {
			in = new Scanner(new FileInputStream("Deck"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = in.nextLine();
		String[] sa = s.split(",");
		ArrayList<PalaceCard> ca = new ArrayList<PalaceCard>();
		for (int i = 0; i < sa.length; i++) {
			String[] sb = sa[i].split(" ");
			if (sb.length == 1) {
				OnePointPalaceCard c = new OnePointPalaceCard(sb[0]);
				ca.add(c);
			} else if (sb.length == 2) {
				TwoPointPalaceCard c = new TwoPointPalaceCard(sb[0], sb[1]);
				ca.add(c);
			}
		}
		return ca;
	}

	public static String[] loadPlayers() {
		Scanner in = null;
		try {
			in = new Scanner(new FileInputStream("Players"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = in.nextLine();
		String[] sa = s.split(" ");

		return sa;
	}

	public static boolean empty() {
		return commands.empty();
	}

	public static Command popCommand() {
		return commands.pop();
	}
}
