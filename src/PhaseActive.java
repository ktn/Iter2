import java.io.FileNotFoundException;
import java.util.Arrays;


public class PhaseActive {
	PlayerFacade player;
	BoardFacade board;
	Sanitation sanitation;

	public enum Mode { BLOCK, PALACE, PLACEDEVELOPER, MOVEDEVELOPER }
	public Mode state;
	
	public int[] selectedDeveloper;
	public int[] selectedPos;
	public Block selectedBlock;
	public int palaceLevel;
	public int rotationCount;
	
	public PhaseActive(PlayerFacade player, BoardFacade board, Sanitation sanitation) {
		this.player = player;
		this.board = board;
		this.sanitation = sanitation;
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
		palaceLevel = 2;
	}
	
	// General methods
	public void moveDown() {
		selectedPos[1] = (selectedPos[1] <= 0) ? 0 : selectedPos[1] - 1;
	}
	public void moveLeft() {
		selectedPos[0] = (selectedPos[0] <= 0) ? 0 : selectedPos[0] - 1;
	}
	public void moveRight() {
		selectedPos[0] = (selectedPos[0] >= board.getLargest().x) ? board.getLargest().x : selectedPos[0] + 1;
	}
	public void moveUp() {
		selectedPos[1] = (selectedPos[1] >= board.getLargest().y) ? board.getLargest().y : selectedPos[1] + 1;
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
	private void switchBlock() {
		if(selectedBlock == null || selectedBlock instanceof ThreeBlock) {
			selectedBlock = new OneBlock(TileType.RICE);
		}
		else if(selectedBlock instanceof TwoBlock) {
			selectedBlock = new ThreeBlock();
		}
		else if(selectedBlock.getType() == TileType.RICE) {
			selectedBlock = new OneBlock(TileType.IRRIGATION);
		}
		else if(selectedBlock.getType() == TileType.IRRIGATION) {
			selectedBlock = new OneBlock(TileType.VILLAGE);
		}
		else if(selectedBlock.getType() == TileType.VILLAGE) {
			selectedBlock = new TwoBlock();
		}
	}
	
	public void rotateBlock() {
		if(state != Mode.BLOCK) return;
		selectedBlock.rotate();
		rotationCount = (rotationCount + 1) % 4;
	}
	
	// Developer methods
	private void switchDeveloper() {
		if(selectedDeveloper == null) {
			Board.Coordinates c = board.getDeveloper(player.getCurrentPlayer());
			selectedDeveloper = new int[] {0, 0};
			selectedDeveloper[0] = c.x;
			selectedDeveloper[1] = c.y;
		}
		else {
			Board.Coordinates o = board.getCoordinates(selectedDeveloper[0], selectedDeveloper[1]);
			selectedDeveloper[0] = board.nextDeveloper(o).x;
			selectedDeveloper[1] = board.nextDeveloper(o).y;
		}
		selectedPos = Arrays.copyOf(selectedDeveloper, 2);
	}
	
	// Palace commands
	public void levelPalace() {
		if(state != Mode.PALACE) {
			return;
		}
		else if(palaceLevel >= 10) {
			palaceLevel = 0;
		}
		palaceLevel = palaceLevel + 2;
	}
	
	// Create commands
	public void placeDeveloper() {
		if(state != Mode.PLACEDEVELOPER) {
			return;
		}
		boolean valid = false;
		try {
			Board.Coordinates c = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placeDeveloperChecker(c);
			if(valid) {
				Command com = new PlaceDeveloperCommand(board, player, c);
				com.execute();
				placeDeveloperMode();
			}
		}
		catch(BlockNotPlayedException e) {
			ViewFacade.warnPlayer("Not enough AP remaining to play a block.");
		}
		catch(NotEnoughAPException e) {
			ViewFacade.warnPlayer("Not enough AP to place the developer.");
		}
		catch(CoordinatesOutOfBoundsException e) {
			ViewFacade.warnPlayer("How did you even get this?");
		}
		catch(CoordinateException e) {
			ViewFacade.warnPlayer("Invalid location.");
		}
	}
	public void moveDeveloper() {
		if(state != Mode.MOVEDEVELOPER) {
			return;
		}
		boolean valid = false;
		try {
			Board.Coordinates a = board.getCoordinates(selectedDeveloper[0], selectedDeveloper[1]);
			Board.Coordinates b = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.moveDeveloperChecker(a,b);
			if(valid) {
				Command com = new MoveDeveloperCommand(board, player, a, b);
				com.execute();
				moveDeveloperMode();
			}
		}
		catch(NotEnoughAPException e) {
			ViewFacade.warnPlayer("Not enough AP to move the developer");
		}
		catch(NoDeveloperAtCoordinatesException e) {
			ViewFacade.warnPlayer("How did you even get this?");
		}
		catch(CoordinateException e) {
			ViewFacade.warnPlayer("Invalid location.");
		}
		catch(BlockNotPlayedException e) {
			ViewFacade.warnPlayer("Not enough remaining AP to play a block.");
		}
	}
	public void useActionToken() {
		boolean valid = false;
		valid = sanitation.actionTokenChecker();
		if(valid) {
			Command com = new UseActionTokenCommand(board, player);
			com.execute();
		}
		else {
			ViewFacade.warnPlayer("Can't play an action token.");
		}
	}
	public void placeBlock() {
		if(state != Mode.BLOCK) {
			return;
		}
		boolean valid = false;
		try {
			Board.Coordinates c = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placeBlockChecker(selectedBlock, c);
			if(valid) {
				Command com;
				if(selectedBlock instanceof ThreeBlock) {
					com = new PlaceThreeBlockCommand(board, player, c);
					com.execute();
				}
				else if(selectedBlock instanceof TwoBlock) {
					com = new PlaceTwoBlockCommand(board, player, c);
					com.execute();
				}
				else if(selectedBlock.getType() == TileType.RICE) {
					com = new PlaceRiceTileCommand(board, player, c);
					com.execute();
				}
				else if(selectedBlock.getType() == TileType.VILLAGE) {
					com = new PlaceVillageTileCommand(board, player, c);
					com.execute();
				}
				else if(selectedBlock.getType() == TileType.IRRIGATION) {
					com = new PlaceIrrigationTileCommand(board, player, c);
					com.execute();
				}
				//com.rotate(rotationCount);
				//com.execute();
				blockMode();
			}
		}
		catch(IllegalBlockPlacementException e) {
			ViewFacade.warnPlayer("Invalid block placement.");
		}
		catch(NoBlocksLeftException e) {
			ViewFacade.warnPlayer("No blocks remaining.");
		}
	}
	
	public void placePalace() {
		if(state != Mode.PALACE) {
			return;
		}
		boolean valid = false;
		try {
			Board.Coordinates c = board.getCoordinates(selectedPos[0], selectedPos[1]);
			valid = sanitation.placePalaceChecker(palaceLevel, c);
			if(valid) {
				Command com = new PlacePalaceTileCommand(board, player, c, palaceLevel);
				com.execute();
				palaceMode();
			}
		}
		catch(PalaceUpgradeException e) {
			ViewFacade.warnPlayer("Invalid block placement.");
		}
		catch(NoDeveloperAtCoordinatesException e) {
			ViewFacade.warnPlayer("Put a developer at the highest location.");
		}
	}
	
	public void changeTurn() {
		boolean valid = false;
		try {
			valid = sanitation.changeTurnChecker();
			if(valid) {
				Command com = new ChangeTurnCommand(player);
				com.execute();
			}
		}
		catch(BlockNotPlayedException e) {
			ViewFacade.warnPlayer("Need to place a block before ending your turn.");
		}
	}
	
	public void drawPalaceCard() {
		boolean valid = false;
		try {
			valid = sanitation.drawPalaceCardChecker();
			if(valid) {
				Command com = new DrawPalaceCardCommand(board, player);
				com.execute();
			}
		}
		catch(NotEnoughAPException e) {	
			ViewFacade.warnPlayer("Not enough AP to draw a card.");
		}
		catch(BlockNotPlayedException e) {
			ViewFacade.warnPlayer("Not enough AP remaining to play a block.");
		}
	}
	
	public void drawCardFromDeck() {
		boolean valid = false;
		try {
			valid = sanitation.drawCardFromDeckChecker();
			if(valid) {
				Command com = new DrawDeckCardCommand(board, player);
				com.execute();
			}
		}
		catch(NotEnoughAPException e) {
			ViewFacade.warnPlayer("Not enough AP to draw a card.");
		}
		catch(BlockNotPlayedException e) {
			ViewFacade.warnPlayer("Not enough AP remaining to play a block.");
		}
	}
	public void save() {
		boolean query = ViewFacade.promptPlayer("Do you want to save?");
		if(!query) return;
		try {
			CommandStack.save("savefile");
			ViewFacade.warnPlayer("Saved");
			return;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		ViewFacade.warnPlayer("Didn't save");
	}
	public void load() {
		boolean query = ViewFacade.promptPlayer("Do you want to load the last-saved game?");
		if(!query) return;
		try {
			CommandStack.load("savefile", player, board);
			ViewFacade.warnPlayer("Loading");
			return;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		ViewFacade.warnPlayer("Didn't load");
	}
}
