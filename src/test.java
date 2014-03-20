import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class test {

	public static void main(String[] args) throws FileNotFoundException,
			IllegalBlockPlacementException {

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);
		BoardFacade b = new BoardFacade();

		BoardFacade b2 = new BoardFacade();
		PlayerFacade p2 = new PlayerFacade(names);

		Command a;
		a = new PlaceIrrigationTileCommand(b, p, b.getCoordinates(1, 0));
		a.execute();

		a = new PlaceRiceTileCommand(b, p, b.getCoordinates(4, 1));
		a.execute();

		a = new PlacePalaceTileCommand(b, p, b.getCoordinates(3, 5), 2);
		a.execute();

		a = new PlacePalaceTileCommand(b, p, b.getCoordinates(2, 3), 4);
		a.execute();

		a = new PlaceTwoBlockCommand(b, p, b.getCoordinates(8, 9));
		a.execute();

		a = new PlaceThreeBlockCommand(b, p, b.getCoordinates(2, 3));
		a.execute();

		a = new PlaceDeveloperCommand(b, p, b.getCoordinates(2, 3));
		a.execute();

		CommandStack.save("test");

	}

}