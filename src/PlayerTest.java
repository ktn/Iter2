import java.util.*;
public class PlayerTest {

	public static void main(String[] args) {
		Deck b = new Deck();

		String names[] = { "Lucas", "Xy"};

		PlayerFacade p = new PlayerFacade(names);

		//System.out.print(b.toString());
		OnePointPalaceCard festivalCard1;
		TwoPointPalaceCard festivalCard2;

		if(b.getFestivalCard() instanceof OnePointPalaceCard)
		{
			festivalCard1 = (OnePointPalaceCard) b.getFestivalCard();
			System.out.println(festivalCard1.getSymbol());
		}
		else
		{
			festivalCard2 = (TwoPointPalaceCard) b.getFestivalCard();
			System.out.println(festivalCard2.getFirstSymbol() + "  " + festivalCard2.getSecondSymbol());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(new OnePointPalaceCard("PUPPET").toString());
		System.out.println(new OnePointPalaceCard("MASK").toString());
		System.out.println(new OnePointPalaceCard("DRUM").toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");

		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());

		ArrayList<PalaceCard> list1 = p.getCardsForPlayer(p.getCurrentPlayer());
		for(int i = 0; i < list1.size(); i++)
		{
			if(list1.get(0) instanceof OnePointPalaceCard)
			{
				System.out.println("test");
				OnePointPalaceCard o = (OnePointPalaceCard) list1.get(0);
				System.out.println(o.getSymbol());
			}
				
			else
			{
				System.out.println("test");
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
				System.out.println("test");
				OnePointPalaceCard o = (OnePointPalaceCard) list2.get(0);
				System.out.println(o.getSymbol());
				System.out.println("test");
			}	
			else
			{
				System.out.println("test");
				TwoPointPalaceCard t = (TwoPointPalaceCard) list2.get(0);
				System.out.println(t.getFirstSymbol() + "  " + t.getSecondSymbol());
				System.out.println("test");
			}
		}

		p.changeTurn();
		//p.startFestival();

		//System.out.print(p.getCurrentPlayer());

	}

}