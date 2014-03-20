import java.util.ArrayList;
import java.util.List;

class Sanitation {
	PlayerFacade player;
	BoardFacade board;

	public Sanitation(PlayerFacade player, BoardFacade board) {
		this.player = player;
		this.board = board;
	}

	public boolean placeDeveloperChecker(Board.Coordinates coord) 
			throws BlockNotPlayedException, NotEnoughAPException, CoordinateException, CoordinatesOutOfBoundsException{
		boolean result = true;
		int ap = player.getActionPoints();
		int requiredAP = 1;
		if(board.isMountains(coord)) {
			requiredAP = 2;
		}
		int x = coord.x;
		int y = coord.y;
		int max_x = board.getLargest().x;
		int max_y = board.getLargest().y;
		// Check if coordinates are in bounds of board
		if(!board.inBounds(coord)) {
			result = false;
			throw new CoordinatesOutOfBoundsException("Can't place developers.", x, y);
		}
		// Check if coordinate is an empty tile
		else if(board.getTileType(coord) == TileType.EMPTY) {
			result = false;
			throw new CoordinateException("Coordinate is empty tile.", x, y);
		}
		// Check if coordinates aren't on the border of the map
		else if((coord.x > 1 && coord.x < max_x - 1) || (coord.y > 1 && coord.y < max_y - 1)) {
			result = false;
			throw new CoordinateException("Coordinate isn't on border", x, y);
		}
		// Check if coordinate is surrounded by blocks
		else if((coord.x == 1 || coord.x == max_x - 1) && (coord.y == 1 || coord.y == max_x - 1)) {
			boolean emptyPresent = false;
			if(coord.x == 1) {
				if(board.getTileType(0, y) == TileType.EMPTY) {
					emptyPresent = true;
				}
			}
			else {
				if(board.getTileType(x + 1, y) == TileType.EMPTY) {
					emptyPresent = true;
				}
			}
			if(coord.y == 1) {
				if(board.getTileType(x, 0) == TileType.EMPTY) {
					emptyPresent = true;
				}
			}
			else {
				if(board.getTileType(x, y + 1) == TileType.EMPTY) {
					emptyPresent = true;
				}
			}
			if(!emptyPresent) {
				result = false;
				throw new CoordinateException("Coordinate is surrounded by tiles", x, y);
			}
		}
		// Check if enough AP
		else if(ap < requiredAP) {
			result = false;
			throw new NotEnoughAPException("Can't place developer.");
		}
		// Check if remaining AP is enough to play a block if a block hasn't been played
		else if(!isAPLeftForBlocks(ap, requiredAP)) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining to play block.");
		}
		
		// Create command? Call command?
		
		return result;
	}

	public boolean moveDeveloperChecker(Board.Coordinates oldCoords, Board.Coordinates newCoords) 
			throws NotEnoughAPException, CoordinateException, NoDeveloperAtCoordinatesException, BlockNotPlayedException{
		boolean result = true;
		int ap = player.getActionPoints();
		int requiredAP = board.findShortestPath(oldCoords, newCoords);
		int old_x = oldCoords.x;
		int old_y = oldCoords.y;
		int new_x = newCoords.x;
		int new_y = newCoords.y;
		if(board.getDeveloper(oldCoords) == null) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Error while moving developer", old_x, old_y);
		}
		else if(board.getDeveloper(newCoords) instanceof Developer) {
			result = false;
			throw new CoordinateException("Developer at destination", new_x, new_y);
		}
		else if(board.getTileType(newCoords) == TileType.EMPTY) {
			result = false;
			throw new CoordinateException("New position isn't a tile.", new_x, new_y);
		}
		else if(ap < requiredAP) {
			result = false;
			throw new NotEnoughAPException("Not enough AP to move developer.");
		}
		else if(!isAPLeftForBlocks(ap, requiredAP)) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining to place block.");
		}
		
		return result;
	}

	public boolean actionTokenChecker() {
		return player.actionTokenUsable();
	}
	
	public boolean placeTileChecker(Block b, Board.Coordinates coords) throws NoBlocksLeftException, IllegalBlockPlacementException, CoordinateException {
		return placeBlockChecker(b, coords);
	}

	public boolean placeBlockChecker(Block b, Board.Coordinates coords) throws NoBlocksLeftException, IllegalBlockPlacementException, CoordinateException {
		boolean result = true;
		TileType type = BlockTypeConverter.convertToBlockType(b);
		int x = coords.x;
		int y = coords.y;

		if (!board.validPlacement(coords, b)) {
			result = false;
			throw new IllegalBlockPlacementException("Error when placing block", type, x, y);
		}

		switch (type) {
		case IRRIGATION:
			if (board.irrigationBlocksLeft() < 1) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block",
						type);
			}
			break;
		case RICE:
			if (!player.checkRice()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block",
						type);
			}
			break;
		case THREE:
			if (board.threeBlocksLeft() < 1) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block",
						type);
			}
			break;
		case TWO:
			if (!player.checkTwoBlock()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block",
						type);
			}
			break;
		case VILLAGE:
			if (!player.checkVillage()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block",
						type);
			}
			else if(!board.notTwoPalacesBetweenCities(coords)) {
				result = false;
				throw new CoordinateException("Can't connect two cities");
			}
			break;
		default:
			break;
		}
		// Create command? Call command?

		return result;
	}

	public boolean placePalaceChecker(int level, Board.Coordinates coord) throws PalaceUpgradeException, NoDeveloperAtCoordinatesException {
		boolean result = true;
		if(level < 2 || level > 10 || level % 2 == 1) {
			result = false;
			throw new PalaceUpgradeException("Invalid level");
		}
		else if((board.getTileType(coord) != TileType.PALACE) && (board.getTileType(coord) != TileType.VILLAGE)) {
			result = false;
			throw new PalaceUpgradeException("Coordinate isn't a palace or village");
		}
		List<Developer> hd = board.findHighestDev(coord);
		if(hd.size() == 0) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Can't upgrade palace", coord.x, coord.y);
		}
		else if(hd.size() == 2) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Developer tie", coord.x, coord.y);
		}
		else if(hd.get(0).getPlayer() != player.getCurrentPlayer()) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Developer isn't highest", coord.x, coord.y);
		}

		return result;
	}

	public boolean changeTurnChecker() throws BlockNotPlayedException {
		boolean result = true;
		if (player.blockPlayed() == false) {
			result = false;
			throw new BlockNotPlayedException("Block must be played before ending turn.");
		}
		return result;
	}
	
	public boolean startFestivalChecker() throws BlockNotPlayedException {
		boolean result = true;
		if(player.blockPlayed() == false) {
			result = false;
			throw new BlockNotPlayedException("Block must be played before starting festival.");
		}
		else if(player.getEndFestival()) {
			result = false;
		}
		return result;
	}
	
	public boolean drawPalaceCardChecker() throws NotEnoughAPException, BlockNotPlayedException {
		boolean result = true;
		int ap = player.getActionPoints();
		if(ap < 1) {
			result = false;
			throw new NotEnoughAPException("Can't draw card");
		}
		else if(!isAPLeftForBlocks(ap, 1)) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining");
		}
		return result;
	}
	
	public boolean drawCardFromDeckChecker() throws NotEnoughAPException, BlockNotPlayedException {
		boolean result = true;
		int ap = player.getActionPoints();
		if(ap < 1) {
			result = false;
			throw new NotEnoughAPException("Can't draw card");
		}
		else if(!isAPLeftForBlocks(ap, 1)) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining");
		}
		return result;
	}
	
	public boolean playCardChecker(PalaceCard pc) throws NoFestivalException {
		boolean result = false;
		String[] type = null;

		if(pc instanceof OnePointPalaceCard)
		{
			OnePointPalaceCard current = (OnePointPalaceCard) pc;
			type = new String[1];
			type[0] = current.getSymbol();
		}
		else if(pc instanceof TwoPointPalaceCard)
		{
			TwoPointPalaceCard current = (TwoPointPalaceCard) pc;
			type = new String[2];
			type[0] = current.getFirstSymbol();
			type[1] = current.getSecondSymbol();
		}


		if(!player.getEndFestival()) {
			result = true;
			throw new NoFestivalException("There is no festival");
		}
		else if(player.hasCardWith(type)) {

			result = true;
		}
		return result;
	}
	
	public boolean endFestivalChecker() throws NoFestivalException {
		boolean result = false;
		if(!player.getEndFestival()) {
			result = false;
			throw new NoFestivalException("There is no festival.");
		}
		else if(!player.isOnePlayerLeft()) {
			result = false;
		}
		else if(player.canEndFestival()) {
			result = false;
		}
		return result;
	}
	
	private boolean isAPLeftForBlocks(int ap, int requiredAP) {
		boolean result = true;
		if(ap - requiredAP < 1 && !player.blockPlayed()) {
			result = false;
		}
		return result;
	}

}