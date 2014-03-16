public class Test {

	public static void main(String[] args) {
		Deck d = new Deck();

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);

		Board b = new Board();
		System.out.print(b.toString());

		p.addCard(d.drawCard());
		p.addScore(100);
		p.addScore(10);

		p.useActionToken();

		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		System.out.print(p.getCurrentPlayer());

	}

}