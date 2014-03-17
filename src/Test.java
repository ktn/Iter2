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
		Block three = new ThreeBlock();

<<<<<<< HEAD
		b.placeBlock(rice, b.new Coordinates(3, 3));
		b.placeBlock(two, b.new Coordinates(1, 1));
		b.placeBlock(three, b.new Coordinates(4, 3));
=======
		b.placeBlock(rice, new Coordinates(3, 3));
		b.placeBlock(two, new Coordinates(1, 1));
		b.placeBlock(three, new Coordinates(4, 3));
		b.placeBlock(two, new Coordinates(0, 0));
		b.placeBlock(two, new Coordinates(1, 0));
		b.placeBlock(two, new Coordinates(2, 0));
		b.placeBlock(two, new Coordinates(3, 0));
		two.rotate();
		b.placeBlock(two, new Coordinates(0, 2));
		b.placeBlock(two, new Coordinates(0, 3));
		two.rotate();
		two.rotate();
		b.placeBlock(two, new Coordinates(0, 2));
		b.placeBlock(two, new Coordinates(2, 3));
		b.placeBlock(two, new Coordinates(2, 2));
		b.placeBlock(two, new Coordinates(3, 3));
>>>>>>> origin/pathfinding

		Pathfinding pf = new Pathfinding(b);

		pf.findShortestPath(0, 0, 4, 4);
		System.out.print(pf);

		System.out.print(b.toString());

		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		System.out.print(p.getCurrentPlayer());

	}
}