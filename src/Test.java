public class Test {

	public static void main(String[] args) {
		Deck d = new Deck();

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);

		print(d);

		p.addCard(d.drawCard());
		p.addScore(100);
		p.addScore(10);

		p.useActionToken();

		print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		print(p.getCurrentPlayer());

		BoardFacade b = new BoardFacade();

		print(b);
	}

	static <E> void print(E a) {
		System.out.println(a.toString());
	}

}