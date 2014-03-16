class Sanitation {
	PlayerFacade player;
	BoardFacade board;

	public Sanitation(PlayerFacade player, BoardFacade board) {
		this.player = player;
		this.board = board;
	}

	public boolean placeDeveloperChecker(Coordinates coord) throws BlockNotPlayedException, NotEnoughAPException, CoordinateException, CoordinatesOutOfBoundsException{
		boolean result = true;
		int ap = player.actionPointsLeft();
		int requiredAP = board.checkDeveloperCost(blah blah blah);
		int x = coord.x;
		int y = coord.y;
		// Check to see if <x,y> is on edge of central java
		if(!board.inbounds(x)) {
			result = false;
			throw new CoordinatesOutOfBoundsException("Can't place developers.", x, y);
		}
		else if(ap >= requiredAP) {
			result = false;
			throw new NotEnoughAPException("Can't place developer.");
		}
		else if(ap - requiredAP < 1 && player.playedBlock()) {
			result = false;
			throw new BlockNotPlayedException("Not enough AP remaining to play block.");
		}
		
		// Create command? Call command?
		
		return result;
	}

	public boolean moveDeveloperChecker(Coordinates oldCoords, Coordinates newCoords) throws NotEnoughAPException, CoordinatesOutOfBoundsException, CoordinateException, NoDeveloperAtCoordinatesException{
		boolean result = true;
		int ap = player.actionPointsLeft();
		int old_x = oldCoords.x;
		int old_y = oldCoords.y;
		if(board.getDeveloper(oldCoords) instanceof null) {
			result = false;
			throw new NoDeveloperAtCoordinatesException("Error while moving developer", old_x, old_y);
		}
		// Check to see if <new_x,new_y> is on central Java. Possibly getSpaceHeight() > 0?
		// Check to see if valid path from old position to new position.
		// Check to see if player has enough AP
		
		// Create command? Call command?
		
		return result;
	}

	public boolean actionTokenChecker() {
		boolean result = true;
		// Check to see if player has any action tokens left.
		result = (player.actionTokenUsable()) ? result && true : false; // Check
																		// to
																		// see
																		// if
																		// player
																		// has
																		// used
																		// an
																		// action
																		// token
																		// already.

		// Create command? Call command?

		return result;
	}

	public boolean placeTileChecker(Block b, Coordinates coords) {
		return placeBlockChecker(b, coords.x, coords.y);
	}

	public boolean placeBlockChecker(Block b, Coordinates coords)
			throws NoBlocksLeftException, IllegalBlockPlacementException {
		boolean result = true;
		BlockType type = BlockTypeConverter.convertToBlockType(b);
		int x = coords.x;
		int y = coords.y;

		if (board.validPlacement(b, x, y)) {
			result = false;
			throw new IllegalBlockPlacementException(
					"Error when placing block", type, x, y);
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

	public boolean upgradeChecker(int level, int x, int y) {
		boolean result = true;
		// Check to see if space is palace tile
		// Check to see if level > current palace level
		result = (board.getSpaceHeight(x, y) < level) ? false : result && true;
		Developer hd = board.highestDeveloper(x, y);
		result = (level % 2 == 0 && level <= 10) ? result && true : false;
		result = (player.getCurrentPlayer() == hd.getPlayer()) ? result && true
				: false;

		return result;
	}

	public boolean cardChecker() {
		return false;
	}

	public boolean changeTurn() {
		boolean result = true;
		if (player.blockPlayed() == false) {
			result = false;
			throw new BlockNotPlayedException(
					"Block must be played before ending turn.");
		}
		return result;
	}
}