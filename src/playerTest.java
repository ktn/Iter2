public class playerTest {

	public static void main(String[] args) {
		Deck b = new Deck();

		String names[] = { "Lucas", "Xy", "Trouble" };

		PlayerFacade p = new PlayerFacade(names);
		System.out.print(b.toString());

		p.addCard(b.drawCard());
		System.out.print(p.getCurrentPlayer());

		p.changeTurn();
		System.out.print(p.getCurrentPlayer());

	}

}