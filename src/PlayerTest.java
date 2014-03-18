import java.util.*;
public class PlayerTest {

	public static void main(String[] args) {
		Deck b = new Deck();

		String names[] = { "Lucas", "Xy"};

		PlayerFacade p = new PlayerFacade(names);
		p.createPalaceFestival();

		//System.out.print(b.toString());
		OnePointPalaceCard festivalCard1;
		TwoPointPalaceCard festivalCard2;

		/*
		if(b.getFestivalCard() instanceof OnePointPalaceCard)
		{
			festivalCard1 = (OnePointPalaceCard) b.getFestivalCard();
			System.out.println("\nThe current festival card is: " + festivalCard1.getSymbol() + "\n\n");
		}
		else
		{
			festivalCard2 = (TwoPointPalaceCard) b.getFestivalCard();
			System.out.println("\nThe current festival card is: " + festivalCard2.getFirstSymbol() + "  " + festivalCard2.getSecondSymbol()+ "\n\n");
		}
		*/


		/*System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(new OnePointPalaceCard("PUPPET").toString());
		System.out.println(new OnePointPalaceCard("MASK").toString());
		System.out.println(new OnePointPalaceCard("DRUM").toString());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
		*/ 

		System.out.println("\nPlayer1's palace cards:");
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		System.out.println("\n");

		ArrayList<PalaceCard> list1 = p.getCardsForPlayer(p.getCurrentPlayer());
		for(int i = 0; i < list1.size(); i++)
		{
			if(list1.get(0) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard o = (OnePointPalaceCard) list1.get(0);
				//System.out.println(o.getSymbol());
			}
				
			else
			{
				TwoPointPalaceCard t = (TwoPointPalaceCard) list1.get(0);
				//System.out.println(t.getFirstSymbol() + "  " + t.getSecondSymbol());
			}
				
		}
		

		p.addScore(100);
		p.addScore(10);

		p.useActionToken();

		//System.out.println(p.getCurrentPlayer());

		p.changeTurn();
		p.addScore(10);
		System.out.println("\n");
		System.out.println("Player2's palace cards:");
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		p.addCard(b.drawCard());
		System.out.println("\n");


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