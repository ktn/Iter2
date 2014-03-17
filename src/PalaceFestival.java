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
	}

	public PalaceFestival(PalaceCard festivalCard, PlayerFacade p) {
		this.setFestivalCard(festivalCard);
		playerFacade = p;
		players = new ArrayList<Player>();
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
		playerFacade.getCardsForPlayer(p);
	}
}
