import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException,
			IllegalBlockPlacementException {
		Deck d = new Deck();

		ArrayList<Command> c = new ArrayList<Command>();
		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);
		BoardFacade b = new BoardFacade();

		p.addScore(100);
		p.addScore(10);

		p.returnRiceBlock();
		p.useActionToken();
		Block rice = new OneBlock(TileType.RICE);
		Block two = new TwoBlock();
		Block three = new ThreeBlock();

		b.placeBlock(rice, b.getCoordinates(1,0));
		//b.placeBlock(rice, b.getCoordinates(1, 1));

		//b.placeBlock(two, b.getCoordinates(1, 1));
		b.placeBlock(two, b.getCoordinates(0, 0));
		print(b.board);
		/*
		b.placeBlock(two, b.getCoordinates(2, 0));
		b.placeBlock(two, b.getCoordinates(3, 0));
		two.rotate();
		b.placeBlock(two, b.getCoordinates(0, 2));
		b.placeBlock(two, b.getCoordinates(0, 3));
		two.rotate();
		two.rotate();
		b.placeBlock(two, b.getCoordinates(0, 2));
		b.placeBlock(two, b.getCoordinates(2, 3));
		b.placeBlock(two, b.getCoordinates(2, 2));
		b.placeBlock(two, b.getCoordinates(3, 3));*/

		//System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		//System.out.print(p.getCurrentPlayer());
		p.addCard(d.drawCard());
		p.addScore(100);
		p.useActionToken();
		//print(p.getCurrentPlayer());

		c.add(new PlaceIrrigationTileCommand(b, p, b.getCoordinates(1, 2)));
		p.changeTurn();
		p.addScore(10);

		//print(p.getCurrentPlayer());

		save("test", c);
		print(b.board);

		/*
		 * try { load("test", p, b); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 */

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

			switch (sa[0]) {
			case "PlaceIrrigationTileCommand":
				Command c = new PlaceIrrigationTileCommand(b, p,
						b.getCoordinates(Integer.parseInt(sa[1]),
								Integer.parseInt(sa[2])));
				print(c + " Kevin is Gay");
				break;
			}
		}
	}
}
