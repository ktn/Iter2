import java.util.*;

public class HighestDeveloper
{
	Board board;
	ArrayList<Developer> developers;
	public HighestDeveloper(Board b, ArrayList<Developer> d)
	{
		board = b;
		developers = d;
	}
	public ArrayList<Developer> findHighestDev(Coordinates c)
	{
		ArrayList<Space> visited = new ArrayList<Space>();
		ArrayList<Player> players = new ArrayList<Player>();
		
		HashMap<Player, int[]> map = new HashMap<Player, int[]>();
		HashMap<Player, Developer> highestDevs = new HashMap<Player, Developer>();

		Space s = board.get(c);

		int highestVal = 0;
		
		// check for which algorithm to use to search
		if (s.getTile().getType() == TileType.PALACE)
		{
			// DFS for highest rank developer in the surrounding city;
			Queue<Space> queuePath = new LinkedList<Space>();

			queuePath.add(s);
			visited.add(s);

			while (!queuePath.isEmpty())
			{
				s = queuePath.remove();
				//System.out.println(coord[0]+" "+coord[1]);
				// FOUND A DEVELOPER
				Developer devFound = null;
				for(Developer dev : developers)
				{
					if(dev.getSpace() == s)
					{
						devFound = dev;
						break;
					}
				}
				if(devFound != null)
				{
					//System.out.print("Found developer: ");
					if(!players.contains(devFound.getPlayer()))	//First instance of player, add it to arraylist and create its int array in map
					{
						//System.out.println(devFound.getPlayer());
						players.add(devFound.getPlayer());
						int[] temp = new int[50];
						for(int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(devFound.getPlayer(), temp);
					}
					if(s.getHeight()>highestVal)	//if height is greater than past heighest value, reset heighest devs so code will know what to pass at the end
					{
						highestVal = s.getHeight();
						highestDevs.clear();
						highestDevs.put(devFound.getPlayer(),devFound);
					}
					else if(s.getHeight() == highestVal)
					{
						highestDevs.put(devFound.getPlayer(), devFound);
					}
					int[] temp = map.get(devFound.getPlayer());
					temp[s.getHeight()]++;
					//System.out.println(temp[s.getHeight()]);
					map.put(devFound.getPlayer(), temp);
				}
				if (s.getTop().getTile().getType() == TileType.PALACE
						&& !visited.contains(s.getTop())) {
					queuePath.add(s.getTop());
					visited.add(s.getTop());
				}
				if (s.getBottom().getTile().getType() == TileType.PALACE
						&& !visited.contains(s.getBottom())) {
					queuePath.add(s.getBottom());
					visited.add(s.getBottom());
				}
				if (s.getLeft().getTile().getType() == TileType.PALACE
						&& !visited.contains(s.getLeft())) {
					queuePath.add(s.getLeft());
					visited.add(s.getLeft());
				}
				if (s.getRight().getTile().getType() == TileType.PALACE
						&& !visited.contains(s.getRight())) {
					queuePath.add(s.getRight());
					visited.add(s.getRight());
				}
			}
			
		} else if (s.getTile().getType() == TileType.IRRIGATION) {
			Queue<Space> queuePath = new LinkedList<Space>();

			queuePath.add(s);
			visited.add(s);
			ArrayList<Space> check = new ArrayList<Space>();

			while (!queuePath.isEmpty())
			{
				s = queuePath.remove();
				
				if (!check.contains(s)) {
					check.add(s);
				}
				if (!check.contains(s.getTop())) {
					check.add(s.getTop());
				}
				if (!check.contains(s.getBottom())) {
					check.add(s.getBottom());
				}
				if (!check.contains(s.getRight())) {
					check.add(s.getRight());
				}
				if (!check.contains(s.getLeft())) {
					check.add(s.getLeft());
				}
				
				
				if (s.getTop().getTile().getType() == TileType.IRRIGATION
						&& !visited.contains(s.getTop())) {
					queuePath.add(s.getTop());
					visited.add(s.getTop());
				}
				if (s.getBottom().getTile().getType() == TileType.IRRIGATION
						&& !visited.contains(s.getBottom())) {
					queuePath.add(s.getBottom());
					visited.add(s.getBottom());
				}
				if (s.getLeft().getTile().getType() == TileType.IRRIGATION
						&& !visited.contains(s.getLeft())) {
					queuePath.add(s.getLeft());
					visited.add(s.getLeft());
				}
				if (s.getRight().getTile().getType() == TileType.IRRIGATION
						&& !visited.contains(s.getRight())) {
					queuePath.add(s.getRight());
					visited.add(s.getRight());
				}
			}
			for(int checkI=0; checkI<check.size();checkI++)
			{
				s = check.get(checkI);
				Developer devFound = null;
				for(Developer dev : developers)
				{
					if(dev.getSpace() == s)
					{
						devFound = dev;
						break;
					}
				}
				if (devFound != null)
				{
					if(!players.contains(devFound.getPlayer()))
					{
						players.add(devFound.getPlayer());
						int[] temp = new int[50];
						for(int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(devFound.getPlayer(), temp);
					}
					if(s.getHeight()>highestVal)
					{
						highestVal = s.getHeight();
						highestDevs.clear();
						highestDevs.put(devFound.getPlayer(), devFound);
					}
					else if(s.getHeight() == highestVal)
					{
						highestDevs.put(devFound.getPlayer(), devFound);
					}
					int[] temp = map.get(devFound.getPlayer());
					temp[s.getHeight()]++;
					map.put(devFound.getPlayer(), temp);
				}
			}
		}
		ArrayList<Developer> highestDevsReturn = new ArrayList<Developer>();
		ArrayList<Player> lowerDevs = new ArrayList<Player>();
		for( int height=highestVal; highestDevsReturn.size() == 0 && height >= 0; height--)
		{
			int most = 0;	//most developers on this level
			//System.out.print("Height: "+height+" most: "+most);
			for(int p = 0; p < players.size(); p++)	//get most developers on this level
				if(map.get(players.get(p))[height] > most)
					most = map.get(players.get(p))[height];
			//System.out.print(" most: "+most+"Players: "+players.size());
			for(int p = 0; p < players.size(); p++)	//if player has less than most developers, remove it from array list
				if(map.get(players.get(p))[height] < most)
				{
					lowerDevs.add(players.get(p));
					players.remove(players.get(p));
				}
			//System.out.println(" Players: "+players.size());
			if(players.size() == 1)	//if there is only one player left, he had the highest developers
				highestDevsReturn.add(highestDevs.get(players.get(0)));
		}
		if(highestDevsReturn.size() == 1)
		{
			if(lowerDevs.size() == 1)
			{
				highestDevsReturn.add(highestDevs.get(lowerDevs.get(0)));
			}
			else
			{
				for( int height=highestVal; highestDevsReturn.size() == 0 && height >= 0; height--)
				{
					int most = 0;	//most developers on this level
					//System.out.print("Height: "+height+" most: "+most);
					for(int p = 0; p < lowerDevs.size(); p++)	//get most developers on this level
						if(map.get(lowerDevs.get(p))[height] > most)
							most = map.get(lowerDevs.get(p))[height];
					//System.out.print(" most: "+most+"lowerDevs: "+lowerDevs.size());
					for(int p = 0; p < lowerDevs.size(); p++)	//if player has less than most developers, remove it from array list
						if(map.get(lowerDevs.get(p))[height] < most)
						{
							lowerDevs.remove(lowerDevs.get(p));
						}
					//System.out.println(" lowerDevs: "+lowerDevs.size());
					if(lowerDevs.size() == 1)	//if there is only one player left, he had the highest developers
						highestDevsReturn.add(highestDevs.get(lowerDevs.get(0)));
				}
			}
		}
		if(highestDevsReturn.size() == 0 & players.size() == 2)
			for(Player p : players)
				highestDevsReturn.add(highestDevs.get(p));	
		return highestDevsReturn;
	}
}