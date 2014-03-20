import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException,
			IllegalBlockPlacementException {

		ArrayList<Command> c = new ArrayList<Command>();
		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);
		BoardFacade b = new BoardFacade();
		
		BoardFacade b2 = new BoardFacade();
		PlayerFacade p2 = new PlayerFacade(names);

		Command a;
		a = new PlaceIrrigationTileCommand(b, p, b.getCoordinates(1, 0));
		a.execute();
		c.add(a);

		a = new PlaceRiceTileCommand(b, p, b.getCoordinates(4, 1));
		a.execute();
		c.add(a);

		a = new PlacePalaceTileCommand(b, p, b.getCoordinates(3, 5), 2);
		a.execute();
		c.add(a);

		a = new PlacePalaceTileCommand(b, p, b.getCoordinates(2, 3), 4);
		a.execute();
		c.add(a);

		a = new PlaceTwoBlockCommand(b, p, b.getCoordinates(8, 9));
		a.execute();
		c.add(a);

		a = new PlaceThreeBlockCommand(b, p, b.getCoordinates(2, 3));
		a.execute();
		c.add(a);

		a = new PlaceDeveloperCommand(b, p, b.getCoordinates(2, 3));
		a.execute();
		c.add(a);

		/*a = new ChangeTurnCommand(p);
		a.execute();
		c.add(a);*/

		save("test", c);

		try {
			load("test", p2, b2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		print(b.board);
		print(p.getCurrentPlayer());

		print(b2.board);
		print(p2.getCurrentPlayer());

	}

	static <E> void print(E a) {
		System.out.println(a.toString());
	}

	public static void save(String fileName, ArrayList<Command> c)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		for (Command comm : c) {
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
			}
		}
		in.close();
	}
}
