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
		int requiredAP = board.checkDeveloperCost(blah blah blah);
		int x = coord.x;
		int y = coord.y;
		// Check to see if <x,y> is on edge of central java
		if(!board.inBounds(coord)) {
			result = false;
			throw new CoordinatesOutOfBoundsException("Can't place developers.", x, y);
		}
		else if(ap >= requiredAP) {
			result = false;
			throw new NotEnoughAPException("Can't place developer.");
		}
		else if(ap - requiredAP < 1 && player.blockPlayed()) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining to play block.");
		}
		
		// Create command? Call command?
		
		return result;
	}

	public boolean moveDeveloperChecker(Board.Coordinates oldCoords, Board.Coordinates newCoords) throws NotEnoughAPException, CoordinatesOutOfBoundsException, CoordinateException, NoDeveloperAtCoordinatesException{
		boolean result = true;
		int ap = player.getActionPoints();
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
		// Check to see if valid path from old position to new position.
		// Check to see if player has enough AP
		
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

		if (board.validPlacement(b, x, y)) {
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

	public boolean upgradeChecker(int level, int x, int y) throws PalaceUpgradeException {
		boolean result = true;
		Board.Coordinates coord = new Board.Coordinates(x, y);
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
		Developer hd = board.highestDeveloper(x, y);
		result = (player.getCurrentPlayer() == hd.getPlayer()) ? result && true
				: false;

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
}