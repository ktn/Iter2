import java.util.*;

/*
****************************************************************************
Player is a state object with the inventory and information of the player.  It does not directly do anything besides return itself and its friends.

Still need to implement Palace Cards
****************************************************************************
*/

public class Player
{
	private String name;
	private int score;
	private String color;		//this may end up being something different, just a placeholder for now
	private int develsOffBoard;	//The number of developers not placed on the board
	private int develsOnBoard;	//The number of developers players owns on the board
	private int actionTokens;
	private int twoBlocks;
	private int villageBlocks;
	private int riceBlocks;
	private List<PalaceCard> cards;

	public Player()
	{
		name = "Joe Shmoe";
		score = 0;
		color = "black";
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();
	}

	public Player(String n)
	{
		name = n;
		score = 0;
		color = "black";
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();
	}

	public Player(String n, String c)
	{
		name = n;
		score = 0;
		color = c;
		develsOffBoard = 12;
		develsOnBoard = 0;
		actionTokens = 3;
		twoBlocks = 5;
		villageBlocks = 2;
		riceBlocks = 3;
		cards = new ArrayList<PalaceCard>();
	}

	public void setColor(String c)
	{
		color = c;
	}

	//Getters:
	public String getName()
	{
		return name;
	}

	public int getScore()
	{
		return score;
	}

	public String getColor()
	{
		return color;
	}

	public int getDevelsOn()
	{
		return develsOnBoard;
	}

	public int getDevelsOff()
	{
		return develsOffBoard;
	}

	public int getActionTokens()
	{
		return actionTokens;
	}

	public int twoBlocksLeft()
	{
		return twoBlocks;
	}

	public int villageBlocksLeft()
	{
		return villageBlocks;
	}

	public int riceBlocksLeft()
	{
		return riceBlocks;
	}

	//Functions used when placing piece on board or returning piece to player

	public void placeDeveloper()
	{
		develsOnBoard++;
		develsOffBoard--;
	}

	public void removeDeveloper()
	{
		develsOnBoard--;
		develsOffBoard++;
	}

	public void useActionToken()
	{
		actionTokens--;
	}

	public void returnActionToken()
	{
		actionTokens++;
	}

	public void placeTwoBlock()
	{
		twoBlocks--;
	}

	public void returnTwoBlock()
	{
		twoBlocks++;
	}

	public void placeVillageBlock()
	{
		villageBlocks--;
	}

	public void returnVillageBlock()
	{
		villageBlocks++;
	}

	public void placeRiceBlock()
	{
		riceBlocks--;
	}

	public void returnRiceBlock()
	{
		riceBlocks++;
	}

	//altering score
	public void addScore(int p)
	{
		score += p;
	}

	public void decrementScore(int p)
	{
		score -= p;
	}

	public Player getPlayer()
	{
		return this;
	}

	//Palace card stuff
	public List<PalaceCard> getCards()
	{
		return cards;
	}
	
	public void addCard(PalaceCard c)
	{
		cards.add(c);
	}
	
	public boolean cardsContain(String t)
	{
		boolean ret = false;
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if(current.getSymbol() == t)
				{
					ret = true;
					break;
				}
			}
			else if(cards.get(i) instanceof TwoPointPalaceCard)
			{
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if(current.getFirstSymbol() == t || current.getSecondSymbol() == t)
				{
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	public boolean hasCardWith(String[] t) //string is 1 or 2 symbols
	{
		boolean ret = false;
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if(current.getSymbol() == t[0])
				{
					ret = true;
					break;
				}
			}
			else if(cards.get(i) instanceof TwoPointPalaceCard)
			{
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if(current.getFirstSymbol() == t[0] && current.getSecondSymbol() == t[1] || current.getFirstSymbol() == t[1] && current.getSecondSymbol() == t[0])
				{
					ret = true;
					break;
				}
			}
		}
		return ret;
	}

	public void useCardWith(String[] t)
	{
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i) instanceof OnePointPalaceCard)
			{
				OnePointPalaceCard current = (OnePointPalaceCard) cards.get(i);
				if(current.getSymbol() == t[0])
				{
					cards.remove(i);
					break;
				}
			}
			else if(cards.get(i) instanceof TwoPointPalaceCard)
			{
				TwoPointPalaceCard current = (TwoPointPalaceCard) cards.get(i);
				if(current.getFirstSymbol() == t[0] && current.getSecondSymbol() == t[1] || current.getFirstSymbol() == t[1] && current.getSecondSymbol() == t[0])
				{
					cards.remove(i);
					break;
				}
			}
		}
	}
}
