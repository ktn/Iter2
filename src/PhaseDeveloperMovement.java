import java.util.Arrays;


public class PhaseDeveloperMovement {
	int[] selectedPos;
	int[] selectedDeveloper;
	ModelFacade model;
	
	public PhaseDeveloperMovement(ModelFacade model) {
		this.model = model;
	}
	public int[] getSelectedPos() {
		return selectedPos;
	}
	public int[] getSelectedDeveloper() {
		return selectedDeveloper;
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
	public void switchDeveloper() {
		if(selectedDeveloper == null) {
			selectedDeveloper = model.getDeveloper();
		}
		else {
			selectedDeveloper = model.getDeveloper(selectedDeveloper);
		}
		selectedPos = Arrays.copyOf(selectedDeveloper, 2);
	}
	public Command moveDeveloper(int[] coords) {
		return commandCreator.moveDeveloper(selectedDeveloper, coords);
	}
}
