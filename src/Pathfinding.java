import java.util.ArrayList;

//assuming xOld and yOld are dev location and xNew and yNew are the location
class Pathfinding {
	Board board;
	Board.Coordinates coords;
	int check[][];
<<<<<<< HEAD
	int lengths[][];
=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade
	int xOld;
	int yOld;
	int xNew;
	int yNew;
	int APUsed;
	ArrayList<Board.Coordinates> shortestPath;

	public Pathfinding(Board b) {
		board = b;
		coords = board.getLargest();
		check = new int[coords.x][coords.y];
		for (int i = 0; i < coords.x; i++) {
			for (int j = 0; j < coords.y; j++) {
				check[i][j] = 99;
			}
		}
<<<<<<< HEAD
		lengths = new int[coords.x][coords.y];
		for (int i = 0; i < coords.x; i++) {
			for (int j = 0; j < coords.y; j++) {
				lengths[i][j] = 99;
			}
		}
=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade
		xOld = 0;
		yOld = 0;
		xNew = 0;
		yNew = 0;
		APUsed = 99;
		shortestPath = new ArrayList<Board.Coordinates>();
	}

	public int findShortestPath(int xo, int yo, int xn, int yn) {
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

		shortestPath = new ArrayList<Board.Coordinates>();

<<<<<<< HEAD
		if (board.getTileType(board.new Coordinates(xOld, yOld)) != TileType.EMPTY) {
			checkNextSpot(new ArrayList<Board.Coordinates>(), xOld, yOld,
					board.new Coordinates(xOld, yOld), 0);
		}
		return APUsed;

=======
		checkNextSpot(new ArrayList<Board.Coordinates>(), xOld, yOld,
				board.new Coordinates(xOld, yOld), 0);

		return shortestPath.size();
>>>>>>> parent of 2624437... Removed everything but BoardFacade
	}

	public void checkNextSpot(ArrayList<Board.Coordinates> currentPath, int x, int y,
			Board.Coordinates prevCoord, int AP) {
		Board.Coordinates newCoord = board.new Coordinates(x, y);
		currentPath.add(newCoord);
<<<<<<< HEAD
		boolean cont = true;
=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade

		// check tile type change and add AP if tile type has changed
		if (board.getTileType(newCoord) != board.getTileType(prevCoord)) {
			AP++;
		}

		if (x == xNew && y == yNew) {
<<<<<<< HEAD
			cont = false;
			if (AP < APUsed) {
				shortestPath = currentPath;
				APUsed = AP;
				check[xNew][yNew] = AP;
			} else if (AP == APUsed) {
				if (currentPath.size() < lengths[xNew][yNew]) {
					lengths[x][y] = currentPath.size();
					shortestPath = currentPath;
				}
			}
		} else if (AP <= check[x][y]) {
			// recursively call this function for each of the four directions,
			// but only if the current AP is less than the new AP by 2 (or 1 if
			// the two tiles are of the same type)
			
=======
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
>>>>>>> parent of 2624437... Removed everything but BoardFacade
			if (x + 1 < coords.x) {
				if (board.getTileType(newCoord) == board
						.getTileType(board.new Coordinates(x + 1, y))
						&& AP < check[x + 1][y]
						&& board.getTileType(board.new Coordinates(x + 1, y)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Board.Coordinates>(currentPath),
							x + 1, y, newCoord, AP);
				}
			}
			if (x - 1 >= 0) {
				if (board.getTileType(newCoord) == board
						.getTileType(board.new Coordinates(x - 1, y))
						&& AP < check[x - 1][y]
						&& board.getTileType(board.new Coordinates(x - 1, y)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Board.Coordinates>(currentPath),
							x - 1, y, newCoord, AP);
				}
			}
			if (y + 1 < coords.y) {
				if (board.getTileType(newCoord) == board
						.getTileType(board.new Coordinates(x, y + 1))
						&& AP < check[x][y + 1]
						&& board.getTileType(board.new Coordinates(x, y + 1)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Board.Coordinates>(currentPath), x,
							y + 1, newCoord, AP);
				}
			}
			if (y - 1 >= 0) {
				if (board.getTileType(newCoord) == board
						.getTileType(board.new Coordinates(x, y - 1))
						&& AP < check[x][y - 1]
						&& board.getTileType(board.new Coordinates(x, y - 1)) != TileType.EMPTY) {
					checkNextSpot(new ArrayList<Board.Coordinates>(currentPath), x,
							y - 1, newCoord, AP);
<<<<<<< HEAD

=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade
				}
			}
		}
	}

	public ArrayList<Board.Coordinates> getShortestPath() {
		return shortestPath;
	}
<<<<<<< HEAD

	public int getShortestPathSize() {
		return shortestPath.size();
	}

	public int getAPUsed() {
		return APUsed;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" AP Used: " + this.getAPUsed() + NEW_LINE);
		result.append(" Shortest Path Size: " + this.getShortestPathSize()
				+ NEW_LINE);
		result.append(" Coordinates in Path: " + NEW_LINE);

		for (Board.Coordinates c : shortestPath) {
			result.append(" (" + c.x + " , " + c.y + ")" + NEW_LINE);
		}
		result.append("}" + NEW_LINE);

		return result.toString();

	}
=======
>>>>>>> parent of 2624437... Removed everything but BoardFacade
}
