import java.util.Arrays;


public class PhaseActive {
	ModelFacade model;
	
	PhaseDeveloperMovement developerMovement;
	PhaseDeveloperPlacement developerPlacement;
	
	
	private enum Mode { BLOCK, PALACE, PLACEDEVELOPER, MOVEDEVELOPER }
	private Mode state;
	
	private int[] selectedDeveloper;
	private int[] selectedPos;
	private TileType selectedBlock;
	public PhaseActive(ModelFacade model) {
		this.model = model;
	}
	
	// Mode switching
	public void blockMode() {
		state = Mode.BLOCK;
		selectedBlock = TileType.RICE;
		selectedPos = new int[] {0,0};
	}
	public void placeDeveloperMode() {
		state = Mode.PLACEDEVELOPER;
		developerMovement = new PhaseDeveloperMovement(model);
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
		if(selectedBlock == null) {
			selectedBlock = TileType.RICE;
		}
		else if(selectedBlock == TileType.RICE) {
			selectedBlock = TileType.VILLAGE;
		}
		else if(selectedBlock == TileType.VILLAGE) {
			selectedBlock = TileType.TWO;
		}
		else if(selectedBlock == TileType.TWO) {
			selectedBlock = TileType.THREE;
		}
	}
	
	// Developer methods
	public void switchDeveloper() {
		if(selectedDeveloper == null) {
			selectedDeveloper = model.getDeveloper();
		}
		else {
			selectedDeveloper = model.getDeveloper(selectedDeveloper);
		}
		selectedPos = Arrays.copyOf(selectedDeveloper, 2);
	}
	
	// Create commands
	public Command placeDeveloper(int[] coords) {
		try {
			commandCreator.placeDeveloper(coords);
		}
		
	}
	public Command moveDeveloper(int[] coords) {
		return commandCreator.moveDeveloper(selectedDeveloper, coords);
	}
}
