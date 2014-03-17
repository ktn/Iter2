import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputListener implements KeyListener {
	TurnPhase turn;
	ModelFacade model;
	public InputListener(ModelFacade model) {
		this.model = model;
		turn = new TurnPhase(model);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
