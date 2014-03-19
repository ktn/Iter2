import java.util.Arrays;

public class PhasePlanning {
	PlayerFacade player;
	BoardFacade board;
	Sanitation sanitation;
	
	Queue<Command> commands;

	private enum Mode { BLOCK, PALACE, PLACEDEVELOPER, MOVEDEVELOPER }
	private Mode state;
	
	private int[] selectedDeveloper;
	private int[] selectedPos;
	private Block selectedBlock;
	private int palaceLevel;
	private int rotationCount;
	
	public PhasePlanning(PlayerFacade player, BoardFacade board, Sanitation sanitation) {
		this.player = player;
		this.board = board;
		this.sanitation = sanitation;
		commands = new ArrayBlockingQueue<Command>();
	}
	
	// Mode switching
	public void blockMode() {
		state = Mode.BLOCK;
		selectedBlock = new OneBlock(TileType.RICE);
		selectedPos = new int[] {0,0};
	}
	public void placeDeveloperMode() {
		state = Mode.PLACEDEVELOPER;
		selectedPos = new int[] {0,0};
	}
	public void moveDeveloperMode() {
		state = Mode.MOVEDEVELOPER;
		selectedDeveloper = null;
		switchDeveloper();
	}
	public void palaceMode() {
		state = Mode.PALACE;
		selectedPos = new int[] {0,0};
	}
	
	// General methods
	public void moveDown() {
		selectedPos[1]--;
	}
	public void moveLeft() {
		selectedPos[0]--;
	}
	public void moveRight() {
		selectedPos[0]++;
	}
	public void moveUp() {
		selectedPos[1]++;
	}
	
	public void switchSelected() {
		if(state == Mode.BLOCK) {
			switchBlock();
		}
		if(state == Mode.MOVEDEVELOPER) {
			switchDeveloper();
		}
	}
	
	// Block methods
	public void switchBlock() {
		if(selectedBlock == null || selectedBlock instanceof ThreeBlock) {
			selectedBlock = new OneBlock(TileType.RICE);
		}
		else if(selectedBlock.getTile().getType() == TileType.RICE) {
			selectedBlock = new OneBlock(TileType.VILLAGE);
		}
		else if(selectedBlock.getTile().getType() == TileType.VILLAGE) {
			selectedBlock = new TwoBlock();
		}
		else if(selectedBlock instanceof TwoBlock) {
			selectedBlock = new ThreeBlock();
		}
	}
	
	public void rotateBlock() {
		selectedBlock.rotate();
		rotationCount = (rotationCount + 1) % 4;
	}
	
	// Developer methods
	public void switchDeveloper() {
		if(selectedDeveloper == null) {
			selectedDeveloper = board.getDeveloper();
		}
		else {
			selectedDeveloper = board.getDeveloper(selectedDeveloper);
		}
		selectedPos = Arrays.copyOf(selectedDeveloper, 2);
	}
	
	// Create commands
	public void placeDeveloper() {
		boolean valid = false;
		try {
			Board.Coordinates c = board.createCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placeDeveloperChecker(c);
			if(valid) {
				Command create;
				placeDeveloperMode();
			}
		}
		catch(BlockNotPlayedException e) {
			view.sendMessage("Not enough AP remaining to play a block.");
		}
		catch(NotEnoughAPException e) {
			view.sendMessage("Not enough AP to place the developer.");
		}
		catch(CoordinateException e) {
			view.sendMessage("Invalid location.");
		}
		catch(CoordinateOutOfBoundsException e) {
			view.sendMessage("How did you even get this?");
		}
	}
	public void moveDeveloper() {
		boolean valid = false;
		try {
			Board.Coordinates a = board.createCoordinates(selectedDeveloper[0], selectedDeveloper[1]);
			Board.Coordinates b = board.createCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.moveDeveloperChecker(a,b);
			if(valid) {
				command create;
				moveDeveloperMode();
			}
		}
		catch(NotEnoughAPException e) {
			view.sendMessage("Not enough AP to move the developer");
		}
		catch(CoordinateException e) {
			view.sendMessage("Invalid location.");
		}
		catch(NoDeveloperAtCoordinatesException e) {
			view.sendMessage("How did you even get this?");
		}
		catch(BlockNotPlayedException e) {
			view.sendMessage("Not enough remaining AP to play a block.");
		}
	}
	public void useActionToken() {
		boolean valid = false;
		valid = sanitation.actionTokenChecker();
		if(valid) {
			command create;
		}
		else {
			view.sendMessage("Can't play an action token.");
		}
	}
	public void placeBlock() {
		boolean valid = false;
		try {
			Board.Coordinates c = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placeBlockChecker(selectedBlock, c);
			if(valid) {
				command create;
				command rotate based on rotationCount?
			}
		}
		catch(IllegalBlockPlacementException e) {
			view.sendMessage("Invalid block placement.");
		}
		catch(NoBlocksLeftException e) {
			view.sendMessage("No blocks remaining.");
		}
	}
	
	public void placePalace() {
		boolean valid = false;
		try {
			Board.Coordinates c = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placePalaceChecker(palaceLevel, c);
			if(valid) {
				command create;
			}
		}
		catch(PalaceUpgradeException e) {
			view.sendMessage("Invalid block placement.");
		}
		catch(NoDeveloperAtCoordinatesException e) {
			view.sendMessage("Put a developer at the highest location.");
		}
	}
	
	public void changeTurn() {
		boolean valid = false;
		try {
			valid = sanitation.changeTurnChecker();
			if(valid) {
				command create;
			}
		}
		catch(BlockNotPlacedException e) {
			view.sendMessage("Need to place a block before ending your turn.");
		}
	}
	
	public void drawPalaceCard() {
		boolean valid = false;
		try {
			valid = sanitation.drawPalaceCardChecker();
			if(valid) {
				command create;
			}
		}
		catch(NotEnoughAPException e) {
			view.sendMessage("Not enough AP to draw a card.");
		}
		catch(BlockNotPlayedException e) {
			view.sendMessage("Not enough AP remaining to play a block.");
		}
	}
	
	public void drawCardFromDeck() {
		boolean valid = false;
		try {
			valid = sanitation.drawCardFromDeckChecker();
			if(valid) {
				command create;
			}
		}
		catch(NotEnoughAPException e) {
			view.sendMessage("Not enough AP to draw a card.");
		}
		catch(BlockNotPlayedException e) {
			view.sendMessage("Not enough AP remaining to play a block.");
		}
	}
}
}
