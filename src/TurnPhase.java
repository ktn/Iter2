import java.awt.event.KeyEvent;


public class TurnPhase {
	private enum GameMode {
		NORMALMODE("Normal mode"), REPLAYMODE("Replay mode"), PLANNINGMODE("Planning mode"),
		FESTIVALMODE("Festival mode");

		String s;

		private GameMode(String s) {
			this.s = s;
		}

		public String toString() {
			return s;
		}
	}
	GameMode currentMode;
	
	PhaseActive normal;
	PhaseReplay replay;
	PhasePlanning planning;
	PhaseFestival festival;
	
	PlayerFacade player;
	BoardFacade board;
	Sanitation sanitation;
	
	public TurnPhase(PlayerFacade player, BoardFacade board, Sanitation sanitation) {
		this.player = player;
		this.board = board;
		this.sanitation = sanitation;
		this.normal = new PhaseActive(player, board, sanitation);
		this.replay = new PhaseReplay(player, board, sanitation);
		this.planning = new PhasePlanning(player, board, sanitation);
		this.festival = new PhaseFestival(model);
	}
	
	public void receiveKeyEvent(KeyEvent k) {
		if(currentMode == GameMode.NORMALMODE) {
			normalModeInterpreter(k);
		}
		else if(currentMode == GameMode.PLANNINGMODE) {
			planningModeInterpreter(k);
		}
		else if(currentMode == GameMode.REPLAYMODE) {
			replayModeInterpreter(k);
		}
		else if(currentMode == GameMode.FESTIVALMODE) {
			festivalModeInterpreter(k);
		}
	}
	
	private void normalModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
		switch(key) {
		case KeyEvent.VK_NUMPAD8:
			normal.moveUp();
			break;
		case KeyEvent.VK_NUMPAD6:
			normal.moveRight();
			break;
		case KeyEvent.VK_NUMPAD4:
			normal.moveLeft();
			break;
		case KeyEvent.VK_NUMPAD2:
			normal.moveDown();
			break;
		case KeyEvent.VK_TAB:
			normal.switchSelected();
			break;
		case KeyEvent.VK_1:
			normal.placeDeveloperMode();
			break;
		case KeyEvent.VK_2:
			normal.moveDeveloperMode();
			break;
		case KeyEvent.VK_3:
			normal.placeBlock();
			break;
		case KeyEvent.VK_4:
			normal.placePalace();
			break;
		case KeyEvent.VK_ENTER:
			normal.placeBlock();
			normal.placeDeveloper();
			normal.placePalace();
			normal.moveDeveloper();
			break;
		case KeyEvent.VK_S:
			normal.save();
			break;
		case KeyEvent.VK_L:
			normal.load();
			break;
		case KeyEvent.VK_A:
			normal.useActionToken();
			break;
		case KeyEvent.VK_E:
			normal.changeTurn();
			break;
		case KeyEvent.VK_R:
			normal.rotateBlock();
			break;
		default:
			break;
			
		}
	}
	
	private void planningModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
	}
	
	private void replayModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
	}
	
	private void festivalModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
	}
}
