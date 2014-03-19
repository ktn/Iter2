import java.util.*;
public class PlayerTest {

	public static void main(String[] args) {
		String[] names = {"alex", "manny", "kevin", "fink"};
		
		PlayerFacade pf = new PlayerFacade(names);
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		pf.addCard(pf.topCard());
		pf.addCard(pf.topCard());
		pf.addCard(pf.topCard());
		pf.addCard(pf.topCard());
		pf.addCard(pf.topCard());
		pf.addCard(pf.topCard());
		
		players.add(pf.getCurrentPlayer());
		
		pf.changeTurn();
		
		pf.addCard(pf.topCard());
		
		players.add(pf.getCurrentPlayer());
		
		pf.changeTurn();
		
		pf.addCard(pf.topCard());
		
		players.add(pf.getCurrentPlayer());
		
		pf.changeTurn();
		
		pf.addCard(pf.topCard());
		
		players.add(pf.getCurrentPlayer());
		
		pf.changeTurn();

		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).toString());
		}
		
		boolean festival = true;
		
		Player[] p = new Player[players.size()];
		
		for(int i = 0; i < players.size(); i++)
		{
			p[i] = players.get(i);
		}
		
		System.out.println("Starting a Palace Festival.");
		pf.createPalaceFestival();
		pf.startFestival(p);
		System.out.println("Palace Festival Started!");
		System.out.println("The Palace Festival card is: " + pf.getFestivalCard());
		
		players = pf.getParticipants();
		
		Scanner s = new Scanner(System.in);
		
		System.out.println("People participating in the festival:");
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).getName());
		}
		
		while(festival)
		{
			System.out.println("The current player is " + pf.getPFPlayer().getName());
			System.out.println("What would you like to do?");
			System.out.println("1. View playable cards.");
			System.out.println("2. Play a card.");
			System.out.println("3. Do not play a card (freeze for the round).");
			
			switch(s.nextInt())
			{
				case 1:
					ArrayList<PalaceCard> cards = pf.getCurrentPlayerCards();
					for(int i = 0; i < cards.size(); i++)
					{
						System.out.println(cards.get(i).toString());
					}
					break;
				case 2:
					System.out.println("What card do you want to play?");
					System.out.println("1. MASK");
					System.out.println("2. DRUM");
					System.out.println("3. PUPPET");
					System.out.println("4. MASK DRUM");
					System.out.println("5. DRUM PUPPET");
					System.out.println("6. PUPPET MASK");
					String[] play;
					switch(s.nextInt())
					{
						case 1:
							play = new String[1];
							play[0] = "MASK";
							break;
						case 2:
							play = new String[1];
							play[0] = "DRUM";
							break;
						case 3:
							play = new String[1];
							play[0] = "PUPPET";
							break;
						case 4:
							play = new String[2];
							play[0] = "MASK";
							play[1] = "DRUM";
							break;
						case 5:
							play = new String[2];
							play[0] = "DRUM";
							play[1] = "PUPPET";
						case 6:
							play = new String[2];
							play[0] = "PUPPET";
							play[1] = "MASK";
							break;
						default:
							play = new String[1];
							break;
					}
					if(pf.playCard(play))
					{
						System.out.println("Card played.  Next player...");
						pf.nextPFPlayer();
					}
					else
					{
						System.out.println("You do not own this card.  Please make a new decision.");
					}
					break;
				case 3:
					System.out.println("Freeze for this round?  You may no longer play cards if you accept.");
					System.out.println("1. Yes.");
					System.out.println("2. No.");
					switch (s.nextInt())
					{
						case 1:
							System.out.println("You are frozen.");
							pf.freezePlayer();
							pf.nextPFPlayer();
							if(pf.checkEnd())
							{
								festival = false;
							}
							break;
						case 2:
							System.out.println("Please make another decision.");
							break;
					}
					break;
				default:
					break;	
			}
		}
		System.out.println("Festival is over!  The winner(s) is/are: ");
		players = pf.getVictors();
		for(int i = 0; i < players.size(); i++)
		{
			System.out.println(players.get(i).getName());
		}
		
	}
}