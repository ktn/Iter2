import java.util.Arrays;


public class PhaseDeveloperPlacement {
	int[] selectedPos;
	ModelFacade model;
	
	public PhaseDeveloperPlacement(ModelFacade model) {
		this.model = model;
	}
	public int[] getSelectedPos() {
		return selectedPos;
	}
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
	public Command placeDeveloper(int[] coords) {
		return commandCreator.placeDeveloper(coords);
	}
}
