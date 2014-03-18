import java.util.*;
public class PlayerTest {

	public static void main(String[] args) {
		Deck b = new Deck();

		String names[] = { "Lucas", "Xy"};

		PlayerFacade p = new PlayerFacade(names);

		//System.out.print(b.toString());

		p.addCard(p.deck.drawCard());
		p.addCard(p.deck.drawCard());
		p.addCard(p.deck.drawCard());

		ArrayList<PalaceCard> list1 = p.getCardsForPlayer(p.getCurrentPlayer());
		for(int i = 0; i < list1.size(); i++)
		{
			if(list1.get(0) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard o = (OnePointPalaceCard) list1.get(0);
				System.out.println(o.getSymbol());
			}
				
			else
			{
				TwoPointPalaceCard t = (TwoPointPalaceCard) list1.get(0);
				System.out.println(t.getFirstSymbol() + "  " + t.getSecondSymbol());
			}
				
		}
		

		p.addScore(100);
		p.addScore(10);

		p.useActionToken();

		//System.out.println(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());


		ArrayList<PalaceCard> list2 = p.getCardsForPlayer(p.getCurrentPlayer());
		for(int i = 0; i < list2.size(); i++)
		{
			if(list2.get(0) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard o = (OnePointPalaceCard) list2.get(0);
				System.out.println(o.getSymbol());
			}	
			else
			{
				TwoPointPalaceCard t = (TwoPointPalaceCard) list2.get(0);
				System.out.println(t.getFirstSymbol() + "  " + t.getSecondSymbol());
			}
		}

		p.changeTurn();
		//p.startFestival();

		//System.out.print(p.getCurrentPlayer());

	}

}