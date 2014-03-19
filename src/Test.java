import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws IOException {
		Deck d = new Deck();

		ArrayList<Command> c = new ArrayList<Command>();
		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);
		BoardFacade b = new BoardFacade();

		p.addCard(d.drawCard());
		p.addScore(100);
		p.useActionToken();
		print(p.getCurrentPlayer());

		c.add(new PlaceOneBlockCommand(b, p, b.getCoordinates(1, 2),
				TileType.IRRIGATION));
		p.changeTurn();
		p.addScore(10);

		print(p.getCurrentPlayer());

		save("test", c);
		print(b);
	}

	static <E> void print(E a) {
		System.out.println(a.toString());
	}

	public static void save(String fileName, ArrayList<Command> c) throws IOException {
		FileOutputStream fout = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(c);
		fout.close();
	}

}