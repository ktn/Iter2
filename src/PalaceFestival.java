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
		String a;
		if(festivalCard instanceof OnePointPalaceCard)
		{
			a = (OnePointPalaceCard) festivalCard.getSymbol();
		}
		else if (festivalCard instanceof TwoPointPalaceCard)
		{
			a = (TwoPointPalaceCard) festivalCard.getFirstSymbol();
		}
		for(int i = 0; i < p.length(); i++)
		{
			if(playerFacade.playerCanParticipate(p[i], a))
			{
				players.add(p[i]);
			}
		}
		if(festivalCard instanceof TwoPointPalaceCard)
		{
			a = (TwoPointPalaceCard) festivalCard.getSecondSymbol();
			for(int i = 0; i < p.length(); i++)
			{
				if(playerFacade.playerCanParticipate(p[i], a) && !players.contains(p[i]))
				{
					players.add(p[i]);
				}
			}
		}


		for(int i = 0; i < players.length)
		/*
		 * PalaceCard p = playerFacade.playPalaceCard(); if(p instanceof
		 * OnePointPalaceCard && this.festivalCard instanceof
		 * OnePointPalaceCard) {
		 * 
		 * } else if (p instanceof OnePointPalaceCard && this.festivalCard
		 * instanceof TwoPointPalaceCard) {
		 * 
		 * } else if (p instanceof TwoPointPalaceCard && this.festivalCard
		 * instanceof OnePointPalaceCard) {
		 * 
		 * } //else both are TwoPointPalaceCards else {
		 * 
		 * }
		 */
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
		int pInt = 0;
		for(int i = 0; i < players.size(); i++)
		{
			if(p = players.get(i))
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
			if(t[0] == (TwoPointPalaceCard) festivalCard.getFirstSymbol() || t[0] == (TwoPointPalaceCard) festivalCard.getSecondSymbol())
			{
				playerScores[pInt] +=1;
			}
			if(t[1] == (TwoPointPalaceCard) festivalCard.getFirstSymbol() || t[1] == (TwoPointPalaceCard) festivalCard.getSecondSymbol())
			{
				playerScores[pInt] +=1;
			}
		}
	}
}
