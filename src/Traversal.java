import java.util.*;

public class Traversal {
	Board board;

	public Traversal(Board b) {
		board = b;
	}

	public ArrayList<Developer> findHighestDev(Board.Coordinates c) {
		ArrayList<Board.Coordinates> visited = new ArrayList<Board.Coordinates>();
		ArrayList<Player> players = new ArrayList<Player>();

		HashMap<Player, int[]> map = new HashMap<Player, int[]>();
		HashMap<Player, Developer> highestDevs = new HashMap<Player, Developer>();

		int highestVal = 0;

		// check for which algorithm to use to search
		if (board.getTileType(c) == TileType.PALACE) {
			// DFS for highest rank developer in the surrounding city;
			Queue<Board.Coordinates> queuePath = new LinkedList<Board.Coordinates>();

			queuePath.add(c);
			visited.add(c);

			while (!queuePath.isEmpty()) {
				c = queuePath.remove();
				// System.out.println(coord[0]+" "+coord[1]);
				// FOUND A DEVELOPER
				Developer devFound = board.getDeveloper(c);
				if (devFound != null) {
					// System.out.print("Found developer: ");
					if (!players.contains(devFound.getPlayer())) // First
																	// instance
																	// of
																	// player,
																	// add it to
																	// arraylist
																	// and
																	// create
																	// its int
																	// array in
																	// map
					{
						// System.out.println(devFound.getPlayer());
						players.add(devFound.getPlayer());
						int[] temp = new int[50];
						for (int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(devFound.getPlayer(), temp);
					}
					if (board.getHeight(c) > highestVal) // if height is greater
															// than past
															// heighest value,
															// reset heighest
															// devs so code will
															// know what to pass
															// at the end
					{
						highestVal = board.getHeight(c);
						highestDevs.clear();
						highestDevs.put(devFound.getPlayer(), devFound);
					} else if (board.getHeight(c) == highestVal) {
						highestDevs.put(devFound.getPlayer(), devFound);
					}
					int[] temp = map.get(devFound.getPlayer());
					temp[board.getHeight(c)]++;
					// System.out.println(temp[board.getHeight(c)]);
					map.put(devFound.getPlayer(), temp);
				}
				if (board.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.VILLAGE
						&& !visited
								.contains(board.new Coordinates(c.x, c.y + 1))) {
					queuePath.add(board.new Coordinates(c.x, c.y + 1));
					visited.add(board.new Coordinates(c.x, c.y + 1));
				}
				if (board.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.VILLAGE
						&& !visited
								.contains(board.new Coordinates(c.x, c.y - 1))) {
					queuePath.add(board.new Coordinates(c.x, c.y - 1));
					visited.add(board.new Coordinates(c.x, c.y - 1));
				}
				if (board.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.VILLAGE
						&& !visited
								.contains(board.new Coordinates(c.x - 1, c.y))) {
					queuePath.add(board.new Coordinates(c.x - 1, c.y));
					visited.add(board.new Coordinates(c.x - 1, c.y));
				}
				if (board.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.VILLAGE
						&& !visited
								.contains(board.new Coordinates(c.x + 1, c.y))) {
					queuePath.add(board.new Coordinates(c.x + 1, c.y));
					visited.add(board.new Coordinates(c.x + 1, c.y));
				}
			}

		} else if (board.getTileType(c) == TileType.IRRIGATION) {
			Queue<Board.Coordinates> queuePath = new LinkedList<Board.Coordinates>();

			queuePath.add(c);
			visited.add(c);
			ArrayList<Board.Coordinates> check = new ArrayList<Board.Coordinates>();

			while (!queuePath.isEmpty()) {
				c = queuePath.remove();

				if (!check.contains(c)) {
					check.add(c);
				}
				if (!check.contains(board.getTile(board.new Coordinates(c.x,
						c.y + 1)))) {
					board.new Coordinates(c.x, c.y + 1);
				}
				if (!check.contains(board.getTile(board.new Coordinates(c.x,
						c.y - 1)))) {
					board.new Coordinates(c.x, c.y - 1);
				}
				if (!check.contains(board.getTile(board.new Coordinates(
						c.x + 1, c.y)))) {
					board.new Coordinates(c.x + 1, c.y);
				}
				if (!check.contains(board.getTile(board.new Coordinates(
						c.x - 1, c.y)))) {
					board.new Coordinates(c.x - 1, c.y);
				}

				if (board.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.IRRIGATION
						&& !visited
								.contains(board.new Coordinates(c.x, c.y + 1))) {
					queuePath.add(board.new Coordinates(c.x, c.y + 1));
					visited.add(board.new Coordinates(c.x, c.y + 1));
				}
				if (board.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.IRRIGATION
						&& !visited
								.contains(board.new Coordinates(c.x, c.y - 1))) {
					queuePath.add(board.new Coordinates(c.x, c.y - 1));
					visited.add(board.new Coordinates(c.x, c.y - 1));
				}
				if (board.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.IRRIGATION
						&& !visited
								.contains(board.new Coordinates(c.x - 1, c.y))) {
					queuePath.add(board.new Coordinates(c.x - 1, c.y));
					visited.add(board.new Coordinates(c.x - 1, c.y));
				}
				if (board.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.IRRIGATION
						&& !visited
								.contains(board.new Coordinates(c.x + 1, c.y))) {
					queuePath.add(board.new Coordinates(c.x + 1, c.y));
					visited.add(board.new Coordinates(c.x + 1, c.y));
				}
			}
			for (int checkI = 0; checkI < check.size(); checkI++) {
				c = check.get(checkI);
				Developer devFound = board.getDeveloper(c);
				if (devFound != null) {
					if (!players.contains(devFound.getPlayer())) {
						players.add(devFound.getPlayer());
						int[] temp = new int[50];
						for (int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(devFound.getPlayer(), temp);
					}
					if (board.getHeight(c) > highestVal) {
						highestVal = board.getHeight(c);
						highestDevs.clear();
						highestDevs.put(devFound.getPlayer(), devFound);
					} else if (board.getHeight(c) == highestVal) {
						highestDevs.put(devFound.getPlayer(), devFound);
					}
					int[] temp = map.get(devFound.getPlayer());
					temp[board.getHeight(c)]++;
					map.put(devFound.getPlayer(), temp);
				}
			}
		}
		ArrayList<Developer> highestDevsReturn = new ArrayList<Developer>();
		ArrayList<Player> lowerDevs = new ArrayList<Player>();
		for (int height = highestVal; highestDevsReturn.size() == 0
				&& height >= 0; height--) {
			int most = 0; // most developers on this level
			// System.out.print("Height: "+height+" most: "+most);
			for (int p = 0; p < players.size(); p++)
				// get most developers on this level
				if (map.get(players.get(p))[height] > most)
					most = map.get(players.get(p))[height];
			// System.out.print(" most: "+most+"Players: "+players.size());
			for (int p = 0; p < players.size(); p++)
				// if player has less than most developers, remove it from array
				// list
				if (map.get(players.get(p))[height] < most) {
					lowerDevs.add(players.get(p));
					players.remove(players.get(p));
				}
			// System.out.println(" Players: "+players.size());
			if (players.size() == 1) // if there is only one player left, he had
										// the highest developers
				highestDevsReturn.add(highestDevs.get(players.get(0)));
		}
		if (highestDevsReturn.size() == 1) {
			if (lowerDevs.size() == 1) {
				highestDevsReturn.add(highestDevs.get(lowerDevs.get(0)));
			} else {
				for (int height = highestVal; highestDevsReturn.size() == 0
						&& height >= 0; height--) {
					int most = 0; // most developers on this level
					// System.out.print("Height: "+height+" most: "+most);
					for (int p = 0; p < lowerDevs.size(); p++)
						// get most developers on this level
						if (map.get(lowerDevs.get(p))[height] > most)
							most = map.get(lowerDevs.get(p))[height];
					// System.out.print(" most: "+most+"lowerDevs: "+lowerDevs.size());
					for (int p = 0; p < lowerDevs.size(); p++)
						// if player has less than most developers, remove it
						// from array list
						if (map.get(lowerDevs.get(p))[height] < most) {
							lowerDevs.remove(lowerDevs.get(p));
						}
					// System.out.println(" lowerDevs: "+lowerDevs.size());
					if (lowerDevs.size() == 1) // if there is only one player
												// left, he had the highest
												// developers
						highestDevsReturn
								.add(highestDevs.get(lowerDevs.get(0)));
				}
			}
		}
		if (highestDevsReturn.size() == 0 & players.size() == 2)
			for (Player p : players)
				highestDevsReturn.add(highestDevs.get(p));
		return highestDevsReturn;
	}

	public boolean playerInCity(Player player, Board.Coordinates aCityTile) {
		Board.Coordinates c = aCityTile;
		Queue<Board.Coordinates> queuePath = new LinkedList<Board.Coordinates>();

		queuePath.add(c);

		ArrayList<Board.Coordinates> visited = new ArrayList<Board.Coordinates>();
		visited.add(c);

		while (!queuePath.isEmpty()) {
			c = queuePath.remove();
			// System.out.println(coord[0]+" "+coord[1]);
			// FOUND A DEVELOPER
			Developer devFound = board.getDeveloper(c);
			if (devFound != null) {
				if (devFound.getPlayer() == player)
					return true;
			}
			if ((board.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x, c.y + 1))) {
				queuePath.add(board.new Coordinates(c.x, c.y + 1));
				visited.add(board.new Coordinates(c.x, c.y + 1));
			}
			if ((board.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x, c.y - 1))) {
				queuePath.add(board.new Coordinates(c.x, c.y - 1));
				visited.add(board.new Coordinates(c.x, c.y - 1));
			}
			if ((board.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x - 1, c.y))) {
				queuePath.add(board.new Coordinates(c.x - 1, c.y));
				visited.add(board.new Coordinates(c.x - 1, c.y));
			}
			if ((board.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x + 1, c.y))) {
				queuePath.add(board.new Coordinates(c.x + 1, c.y));
				visited.add(board.new Coordinates(c.x + 1, c.y));
			}
		}
		return false;
	}

	public int numIrrigationTiles(Board.Coordinates anIrrigTile) {
		Board.Coordinates c = anIrrigTile;
		Queue<Board.Coordinates> queuePath = new LinkedList<Board.Coordinates>();

		queuePath.add(c);

		ArrayList<Board.Coordinates> visited = new ArrayList<Board.Coordinates>();
		visited.add(c);
		while (!queuePath.isEmpty()) {
			c = queuePath.remove();
			if ((board.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x, c.y + 1)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x, c.y + 1))) {
				queuePath.add(board.new Coordinates(c.x, c.y + 1));
				visited.add(board.new Coordinates(c.x, c.y + 1));
			}
			if ((board.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x, c.y - 1)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x, c.y - 1))) {
				queuePath.add(board.new Coordinates(c.x, c.y - 1));
				visited.add(board.new Coordinates(c.x, c.y - 1));
			}
			if ((board.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x - 1, c.y)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x - 1, c.y))) {
				queuePath.add(board.new Coordinates(c.x - 1, c.y));
				visited.add(board.new Coordinates(c.x - 1, c.y));
			}
			if ((board.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.PALACE || board
					.getTileType(board.new Coordinates(c.x + 1, c.y)) == TileType.VILLAGE)
					&& !visited.contains(board.new Coordinates(c.x + 1, c.y))) {
				queuePath.add(board.new Coordinates(c.x + 1, c.y));
				visited.add(board.new Coordinates(c.x + 1, c.y));
			}
		}
		return visited.size();
	}

	public ArrayList<Board.Coordinates> allPalaceTiles() {
		ArrayList<Board.Coordinates> palaceTiles = new ArrayList<Board.Coordinates>();
		Board.Coordinates c = board.new Coordinates(0, 0);
		for (int x = 0; board.inBounds(c); x++, c.x = x)
			for (int y = 0; board.inBounds(c); y++, c.y = y)
				if (board.getTileType(c) == TileType.PALACE)
					palaceTiles.add(c);
		return palaceTiles;
	}
}