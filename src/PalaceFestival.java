import java.util.*;

public class PalaceFestival {
	// private PlayerFacade playerFacade;
	private PalaceCard festivalCard;
	private PlayerFacade playerFacade;
	private ArrayList<Player> players;
	private int[] playerScores;

	public PalaceFestival()
	{
		festivalCard = null;
		playerFacade = null;
		players = new ArrayList<Player>();
		playerScores = new int[4];
		for(int i = 0; i < 4; i++)
		{
			playerScores[i] = 0;
		}
	}

	public PalaceFestival(PalaceCard festivalCard, PlayerFacade p) {
		this.setFestivalCard(festivalCard);
		playerFacade = p;
		players = new ArrayList<Player>();
		playerScores = new int[4];
		for(int i = 0; i < 4; i++)
		{
			playerScores[i] = 0;
		}
	}

	public void startFestival(Player[] p) {
		String a = null;
		OnePointPalaceCard onePointPalaceCard = null;
		TwoPointPalaceCard twoPointPalaceCard = null;
		if(festivalCard instanceof OnePointPalaceCard)
		{
			onePointPalaceCard = (OnePointPalaceCard) festivalCard;
			a = onePointPalaceCard.getSymbol();
		}
		else if (festivalCard instanceof TwoPointPalaceCard)
		{
			twoPointPalaceCard = (TwoPointPalaceCard) festivalCard;
			a = twoPointPalaceCard.getFirstSymbol();
		}
		for(int i = 0; i < p.length; i++)
		{
			if(playerFacade.playerCanParticipate(i, a))
			{
				players.add(p[i]);
			}
		}
		if(festivalCard instanceof TwoPointPalaceCard)
		{
			a = twoPointPalaceCard.getSecondSymbol();
			for(int i = 0; i < p.length; i++)
			{
				if(playerFacade.playerCanParticipate(i, a) && !players.contains(p[i]))
				{
					players.add(p[i]);
				}
			}
		}

	}

	public void endFestival() {
		// Score fame points
	}

	public PalaceCard getFestivalCard() {
		return festivalCard;
	}

	public void setFestivalCard(PalaceCard festivalCard) {
		this.festivalCard = festivalCard;
	}

	public List<PalaceCard> getPlayableCards(Player p)
	{
		//Call playerfacade to call player turn to call player
		return playerFacade.getCardsForPlayer(p);
	}

	public void giveFestivalPoints(Player p, String[] t)
	{
		TwoPointPalaceCard twoPointPalaceCard;
		int pInt = 0;
		for(int i = 0; i < players.size(); i++)
		{
			if(p == players.get(i))
			{
				pInt = i;
				break;
			}
		}
		if(festivalCard instanceof OnePointPalaceCard)
		{
			playerScores[pInt] += 1;
		}
		else if(festivalCard instanceof TwoPointPalaceCard)
		{
			twoPointPalaceCard = (TwoPointPalaceCard) festivalCard;
			if(t[0] == twoPointPalaceCard.getFirstSymbol() || t[0] == twoPointPalaceCard.getSecondSymbol())
			{
				playerScores[pInt] +=1;
			}
			if(t[1] == twoPointPalaceCard.getFirstSymbol() || t[1] == twoPointPalaceCard.getSecondSymbol())
			{
				playerScores[pInt] +=1;
			}
		}
	}

	public ArrayList<Player> getVictors()
	{
		ArrayList<Player> victors = new ArrayList<Player>();
		int max = 0;
		for(int i : playerScores)
		{
			if(i > max)
			{
				max = i;
			}
		}
		for(int i = 0; i < playerScores.length; i++)
		{
			if(playerScores[i] == max)
			{
				victors.add(players.get(i));
			}
		}
		return victors;
	}
}
