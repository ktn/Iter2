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

		b.placeBlock(rice, b.new Coordinates(3, 3));
		b.placeBlock(two, b.new Coordinates(1, 1));
		b.placeBlock(three, b.new Coordinates(4, 3));

		Pathfinding pf = new Pathfinding(b);

		System.out.println(pf.findShortestPath(0, 0, 0, 3)
				+ " Shortest Path Size");
		System.out.print(b.toString());

		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		System.out.print(p.getCurrentPlayer());

	}
}