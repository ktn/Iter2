import java.util.ArrayList;
import java.util.List;

class Sanitation {
	PlayerFacade player;
	BoardFacade board;

	public Sanitation(PlayerFacade player, BoardFacade board) {
		this.player = player;
		this.board = board;
	}

	public boolean placeDeveloperChecker(Board.Coordinates coord) throws BlockNotPlayedException, NotEnoughAPException, CoordinateException, CoordinatesOutOfBoundsException{
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
		if(!board.inBounds(coord)) {
			result = false;
			throw new CoordinatesOutOfBoundsException("Can't place developers.", x, y);
		}
		else if((coord.x == 0 || coord.x == max_x) && (coord.y == 0 || coord.y == max_y)) {
			
		}
		else if(ap >= requiredAP) {
			result = false;
			throw new NotEnoughAPException("Can't place developer.");
		}
		else if(isAPLeftForBlocks(ap, requiredAP)) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining to play block.");
		}
		
		// Create command? Call command?
		
		return result;
	}

	public boolean moveDeveloperChecker(Board.Coordinates oldCoords, Board.Coordinates newCoords) throws NotEnoughAPException, CoordinatesOutOfBoundsException, CoordinateException, NoDeveloperAtCoordinatesException{
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
		else if(board.getTileType(newCoords) == TileType.EMPTY) {
			result = false;
			throw new CoordinateException("New position isn't a tile.", new_x, new_y);
		}
		else if(ap < requiredAP) {
			result = false;
			throw new NotEnoughAPException("Not enough AP to move developer.");
		}
		else if(isAPLeftForBlocks(ap, requiredAP)) {
			result = false;
			throw new NotEnoughAPException("Not enough AP remaining to place block.");
		}
		
		return result;
	}

	public boolean actionTokenChecker() {
		boolean result = true;
		// Check to see if player has any action tokens left.
		result = (player.actionTokenUsable()) ? result && true : false; // Check to	see if player has used action token already.

		// Create command? Call command?

		return result;
	}
	
	public boolean placeTileChecker(Block b, Board.Coordinates coords) throws NoBlocksLeftException, IllegalBlockPlacementException {
		return placeBlockChecker(b, coords);
	}

	public boolean placeBlockChecker(Block b, Board.Coordinates coords) throws NoBlocksLeftException, IllegalBlockPlacementException {
		boolean result = true;
		TileType type = BlockTypeConverter.convertToBlockType(b);
		int x = coords.x;
		int y = coords.y;

		if (board.validPlacement(b, coords)) {
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
			break;
		default:
			break;
		}
		// Create command? Call command?

		return result;
	}

	public boolean upgradeChecker(int level, Board.Coordinates coord) throws PalaceUpgradeException, NoDeveloperAtCoordinatesException {
		boolean result = true;
		if(level < 2 || level > 10 || level % 2 == 1) {
			result = false;
			throw new PalaceUpgradeException("Invalid level");
		}
		else if(board.getTileType(coord) != TileType.PALACE) {
			result = false;
			throw new PalaceUpgradeException("Coordinate isn't a palace");
		}
		// Check to see if level > current palace level
		else if(board.getPalaceLevel(coord) < level) {
			result = false;
			throw new PalaceUpgradeException("Level isn't higher than palace level.");
		}
		List<Developer> hd = board.findHighestDeveloper(coord);
		if(hd.size() == 0) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Can't upgrade palace", coord.x, coord.y);
		}
		else if(hd.size() == 2) {
			// Tie. What do?
		}
		else if(hd.get(0).getPlayer() != player.getCurrentPlayer()) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Developer isn't highest", coord.x, coord.y);
		}

		return result;
	}

	public boolean changeTurn() throws BlockNotPlayedException {
		boolean result = true;
		if (player.blockPlayed() == false) {
			result = false;
			throw new BlockNotPlayedException("Block must be played before ending turn.");
		}
		return result;
	}
	
	private boolean isAPLeftForBlocks(int ap, int requiredAP) {
		boolean result = true;
		if(ap - requiredAP < 1 && player.blockPlayed()) {
			result = false;
		}
		return result;
	}
}