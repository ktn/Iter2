import java.util.ArrayList;

//assuming xOld and yOld are dev location and xNew and yNew are the location
class Pathfinding {
	Board board;
	Coordinates coords;
	int check[][];
	int lengths[][];
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
		lengths = new int[coords.x][coords.y];
		for (int i = 0; i < coords.x; i++) {
			for (int j = 0; j < coords.y; j++) {
				lengths[i][j] = 99;
			}
		}
		xOld = 0;
		yOld = 0;
		xNew = 0;
		yNew = 0;
		APUsed = 99;
		shortestPath = new ArrayList<Coordinates>();
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

		shortestPath = new ArrayList<Coordinates>();

		if (board.getTileType(new Coordinates(xOld, yOld)) != TileType.EMPTY) {
			checkNextSpot(new ArrayList<Coordinates>(), xOld, yOld,
					new Coordinates(xOld, yOld), 0);
		}
		return APUsed;
	}

	public void checkNextSpot(ArrayList<Coordinates> currentPath, int x, int y,
			Coordinates prevCoord, int AP) {
		Coordinates newCoord = new Coordinates(x, y);
		currentPath.add(newCoord);
		boolean cont = true;

		// check tile type change and add AP if tile type has changed
		if (board.getTileType(newCoord) != board.getTileType(prevCoord)) {
			AP++;
		}

		if (x == xNew && y == yNew) {
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
			if (AP < check[x][y]) {
				check[x][y] = AP;
				lengths[x][y] = currentPath.size();
			} else {
				if (currentPath.size() < lengths[x][y]) {
					lengths[x][y] = currentPath.size();
				} else {
					cont = false;
				}
			}
			if (x + 1 < coords.x && cont) {
				if (board.getTileType(new Coordinates(x + 1, y)) != TileType.EMPTY) {
					if (AP < check[x + 1][y]) {
						if (board.getTileType(new Coordinates(x + 1, y)) == board
								.getTileType(new Coordinates(x, y))) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x + 1, y, newCoord, newAP);
						} else if (board.getTileType(new Coordinates(x + 1, y)) != board
								.getTileType(new Coordinates(x, y))
								&& AP + 1 < check[x + 1][y]) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x + 1, y, newCoord, newAP);
						}
					}
				}
			}
			if (x - 1 >= 0 && cont) {
				if (board.getTileType(new Coordinates(x - 1, y)) != TileType.EMPTY) {
					if (AP < check[x - 1][y]
							&& board.getTileType(new Coordinates(x - 1, y)) != TileType.EMPTY) {
						if (board.getTileType(new Coordinates(x - 1, y)) == board
								.getTileType(new Coordinates(x, y))) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x - 1, y, newCoord, newAP);
						} else if (board.getTileType(new Coordinates(x - 1, y)) != board
								.getTileType(new Coordinates(x, y))
								&& AP + 1 < check[x - 1][y]) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x - 1, y, newCoord, newAP);
						}
					}
				}
			}
			if (y + 1 < coords.y && cont) {
				if (board.getTileType(new Coordinates(x, y + 1)) != TileType.EMPTY) {
					if (AP < check[x][y + 1]
							&& board.getTileType(new Coordinates(x, y + 1)) != TileType.EMPTY) {
						if (board.getTileType(new Coordinates(x, y + 1)) == board
								.getTileType(new Coordinates(x, y))) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x, y + 1, newCoord, newAP);
						} else if (board.getTileType(new Coordinates(x, y + 1)) != board
								.getTileType(new Coordinates(x, y))
								&& AP + 1 < check[x][y + 1]) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x, y + 1, newCoord, newAP);
						}
					}
				}
			}
			if (y - 1 >= 0 && cont) {
				if (board.getTileType(new Coordinates(x, y - 1)) != TileType.EMPTY) {
					if (AP < check[x][y - 1]
							&& board.getTileType(new Coordinates(x, y - 1)) != TileType.EMPTY) {
						if (board.getTileType(new Coordinates(x, y - 1)) == board
								.getTileType(new Coordinates(x, y))) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x, y - 1, newCoord, newAP);
						} else if (board.getTileType(new Coordinates(x, y - 1)) != board
								.getTileType(new Coordinates(x, y))
								&& AP + 1 < check[x][y - 1]) {
							int newAP = AP;
							ArrayList<Coordinates> newPath = new ArrayList<Coordinates>();
							newPath.addAll(currentPath);
							checkNextSpot(newPath, x, y - 1, newCoord, newAP);
						}
					}
				}
			}
		}
	}

	public ArrayList<Coordinates> getShortestPath() {
		return shortestPath;
	}

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

		for (Coordinates c : shortestPath) {
			result.append(" ( " + c.x + " , " + c.y + " )" + NEW_LINE);
		}
		result.append("}" + NEW_LINE);

		return result.toString();

	}
}
