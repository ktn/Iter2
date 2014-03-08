class Sanitation {
	PlayerTurn player;
	BoardFacade board;
	
	public Sanitation(PlayerTurn player, BoardFacade board) {
		this.player = player;
		this.board = board;
	}
	
	public boolean placeDeveloperChecker(int x, int y) {
		boolean result = true;
		int ap = player.actionPointsLeft();
		// Check to see if <x,y> is on edge of central java
		// Check to see if developer is from mountains or lowlands
		// Check to see if the player has enough AP
		
		// Create command? Call command?
		
		return result;
	}
	
	public boolean moveDeveloperChecker(int old_x, int old_y, int new_x, int new_y) {
		boolean result = true;
		int ap = player.actionPointsLeft();
		result = (board.getDeveloper(old_x, old_y) instanceof null) ? false : result && true; // Check to see if developer is on position.
		// Check to see if <new_x,new_y> is on central Java. Possibly getSpaceHeight() > 0?
		// Check to see if valid path from old position to new position.
		// Check to see if player has enough AP
		
		// Create command? Call command?
		
		return result;
	}
	
	public boolean actionTokenChecker(){
		boolean result = true;
		// Check to see if player has any action tokens left.
		result = (player.actionTokenUsable()) ? result && true : false; // Check to see if player has used an action token already.
		
		// Create command? Call command?
		
		return result;
	}
	
	public boolean placeTileChecker(Block b, int x, int y) {
		return placeBlockChecker(b, x, y);
	}
	
	public boolean placeBlockChecker(Block b, int x, int y) throws NoBlocksLeftException, IllegalBlockPlacementException {
		boolean result = true;
		BlockType type = BlockTypeConverter.convertToBlockType(b);
		
		if(board.validPlacement(Block b, int x, int y)) {
			result = false;
			throw new IllegalBlockPlacementException("Error when placing block", type, x, y);
		}
		
		switch(type) {
		case IRRIGATION:
			if(board.irrigationBlocksLeft() < 1) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block", type);
			}
			break;
		case RICE:
			if(!player.checkRice()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block", type);
			}
			break;
		case THREE:
			if(board.threeBlocksLeft() < 1) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block", type);
			}
			break;
		case TWO:
			if(!player.checkTwoBlock()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block", type);
			}
			break;
		case VILLAGE:
			if(!player.checkVillage()) {
				result = false;
				throw new NoBlocksLeftException("Error when placing block", type);
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
		result = (player.getCurrentPlayer() == hd.getPlayer()) ? result && true : false;
		
		return result;
	}
	
	public boolean cardChecker() {
		return false;
	}
	
}