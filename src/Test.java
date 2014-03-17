public class Test {

	public static void main(String[] args) {
		Deck d = new Deck();
		Board b = new Board();

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);

		p.addCard(d.drawCard());
		p.addScore(100);
		p.addScore(10);

		p.returnRiceBlock();
		p.useActionToken();
		Block rice = new OneBlock(TileType.RICE);
		Block two = new TwoBlock();

		// b.placeBlock(rice, new Coordinates(1, 1));

		b.placeBlock(two, new Coordinates(0, 0));
		b.placeBlock(two, new Coordinates(1, 0));
		b.placeBlock(two, new Coordinates(2, 0));
		b.placeBlock(two, new Coordinates(3, 0));
		b.placeBlock(two, new Coordinates(4, 0));
		b.placeBlock(two, new Coordinates(5, 0));

		Pathfinding pf = new Pathfinding(b);

		System.out.println(pf.findShortestPath(0, 0, 3, 0) + " Shortest Path Size");
		System.out.print(b.toString());

		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		System.out.print(p.getCurrentPlayer());

	}
}