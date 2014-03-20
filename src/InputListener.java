import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;


public class InputListener implements KeyListener {
	TurnPhase turn;
	public InputListener() {
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(new String[] {"Bob", "Steve", "Frank", "Alice"}));

		View view=new View(names, this);
		turn = new TurnPhase();
		view.addKeyListener(this);
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		turn.receiveKeyEvent(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("released");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("typed");
	}

}
