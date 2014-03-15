import java.util.*;

class DevelOwners
{
	private HashMap<Developer, Player> map;
	
	public DevelOwners(List<Player> players)
	{
		map = new HashMap<Developer, Player>();
		int size = players.size();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				map.put(new Developer(), players.get(i));
			}
		}
	}
	
	public Player findOwner(Developer d)
	{
		return map.get(d);
	}
	
	//Get developer that is not on the board
	public Developer findDevOffBoard(Player p)
	{
		Developer ret = null;
		for(Developer d : map.keySet())
		{
			if(p == map.get(d))
			{
				ret = d;
				break;
			}
		}
		return ret;
	}
}