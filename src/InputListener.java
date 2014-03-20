import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;


public class InputListener implements KeyListener {
	TurnPhase turn;
	View theView;
	public InputListener() {
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(new String[] {"Bob", "Steve", "Frank", "Alice"}));
		theView = new View(names);
		theView.setFocusable(true);
        theView.requestFocusInWindow();
		turn = new TurnPhase();
		theView.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		turn.receiveKeyEvent(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
