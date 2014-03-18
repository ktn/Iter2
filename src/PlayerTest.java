public class PlayerTest {

	public static void main(String[] args) {
		Deck b = new Deck();

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);

		System.out.print(b.toString());

		p.addCard(b.drawCard());
		p.addScore(100);
		p.addScore(10);

		p.useActionToken();

		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);

		System.out.print(p.getCurrentPlayer());

	}

}