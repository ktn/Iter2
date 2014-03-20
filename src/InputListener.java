import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputListener implements KeyListener {
	TurnPhase turn;
	public InputListener(PlayerFacade player, BoardFacade board, Sanitation sanitation) {
		turn = new TurnPhase();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		turn.receiveKeyEvent(arg0);
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
