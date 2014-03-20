import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputListener implements KeyListener {
	TurnPhase turn;
	PlayerFacade player;
	BoardFacade board;
	Sanitation sanitation;
	public InputListener(PlayerFacade player, BoardFacade board, Sanitation sanitation) {
		this.player = player;
		this.board = board;
		this.sanitation = sanitation;
		turn = new TurnPhase(player, board, sanitation);
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
