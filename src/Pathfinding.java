import java.util.ArrayList;

//assuming xOld and yOld are dev location and xNew and yNew are the location
class Pathfinding {
	Board board;
	Coordinates coords;
	int check[][];
	int xOld;
	int yOld;
	int xNew;
	int yNew;
	int APUsed;
	ArrayList<Coordinates> shortestPath;

	public Pathfinding(Board b) {
		board = b;
		coords = board.getLargest();
		check = new int[coords.x][coords.y];
		for (int i = 0; i < coords.x; i++) {
			for (int j = 0; j < coords.y; j++) {
				check[i][j] = 99;
			}
		}
		xOld = 0;
		yOld = 0;
		xNew = 0;
		yNew = 0;
		APUsed = 99;
		shortestPath = new ArrayList<Coordinates>();
	}

	public void findShortestPath(int xo, int yo, int xn, int yn) {
		xOld = xo;
		yOld = yo;
		xNew = xn;
		yNew = yn;
		for (int i = 0; i < coords.x; i++) {
			for (int j = 0; j < coords.y; j++) {
				check[i][j] = 99;
			}
		}

		check[xOld][yOld] = 0;
		APUsed = 99;

		shortestPath = new ArrayList<Coordinates>();

		checkNextSpot(new ArrayList<Coordinates>(), xOld, yOld,
				new Coordinates(xOld, yOld), 0);
	}

	public void checkNextSpot(ArrayList<Coordinates> currentPath, int x, int y,
			Coordinates prevCoord, int AP) {
		Coordinates newCoord = new Coordinates(x, y);
		currentPath.add(newCoord);

		// check tile type change and add AP if tile type has changed
		if (board.getTileType(newCoord) != board.getTileType(prevCoord)) {
			AP++;
		}

		if (x == xOld && y == yOld) {
			if (check[x][y] == -1) {
				shortestPath = currentPath;
				APUsed = AP;
			} else {
				if (AP < APUsed) {
					shortestPath = currentPath;
					APUsed = AP;
				}
			}
		} else {
			// recursively call this function for each of the four directions,
			// but only if the current AP is less than the new AP by 2 (or 1 if
			// the two tiles are of the same type)
			if (x + 1 < coords.x) {
				if (board.getTileType(newCoord) == board
						.getTileType(new Coordinates(x + 1, y))
						&& AP < check[x + 1][y]
						&& board.getTileType(new Coordinates(x + 1, y)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Coordinates>(currentPath),
							x + 1, y, newCoord, AP);
				}
			}
			if (x - 1 >= 0) {
				if (board.getTileType(newCoord) == board
						.getTileType(new Coordinates(x - 1, y))
						&& AP < check[x - 1][y]
						&& board.getTileType(new Coordinates(x - 1, y)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Coordinates>(currentPath),
							x - 1, y, newCoord, AP);
				}
			}
			if (y + 1 < coords.y) {
				if (board.getTileType(newCoord) == board
						.getTileType(new Coordinates(x, y + 1))
						&& AP < check[x][y + 1]
						&& board.getTileType(new Coordinates(x, y + 1)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Coordinates>(currentPath), x,
							y + 1, newCoord, AP);
				}
			}
			if (y - 1 >= 0) {
				if (board.getTileType(newCoord) == board
						.getTileType(new Coordinates(x, y - 1))
						&& AP < check[x][y - 1]
						&& board.getTileType(new Coordinates(x, y - 1)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Coordinates>(currentPath), x,
							y - 1, newCoord, AP);
				}
			}
		}
	}

	public ArrayList<Coordinates> getShortestPath() {
		return shortestPath;
	}
}
