import java.awt.event.KeyEvent;

public class TurnPhase {
	private enum GameMode {
		NORMALMODE("Normal mode"), REPLAYMODE("Replay mode"), PLANNINGMODE(
				"Planning mode"), FESTIVALMODE("Festival mode");

		String s;

		private GameMode(String s) {
			this.s = s;
		}

		public String toString() {
			return s;
		}
	}

	GameMode currentMode = GameMode.NORMALMODE;

	PhaseActive normal;
	PhaseReplay replay;
	PhasePlanning planning;
	PhaseFestival festival;

	PlayerFacade player;
	BoardFacade board;
	Sanitation sanitation;

	public TurnPhase(String[] argNames) {
		player = new PlayerFacade(argNames);
		board = new BoardFacade();
		sanitation = new Sanitation(player, board);
		this.normal = new PhaseActive(player, board, sanitation);
		this.replay = new PhaseReplay(board, player);
		this.planning = new PhasePlanning(player, board, sanitation);
		currentMode = GameMode.NORMALMODE;
		// this.festival = new PhaseFestival(model);
		ViewFacade.updateBoard(board.board);
	}

	public void receiveKeyEvent(KeyEvent k) {
		System.out.println("Key pressed");
		if (currentMode == GameMode.NORMALMODE) {
			normalModeInterpreter(k);
		} else if (currentMode == GameMode.PLANNINGMODE) {
			planningModeInterpreter(k);
		} else if (currentMode == GameMode.REPLAYMODE) {
			replayModeInterpreter(k);
		} else if (currentMode == GameMode.FESTIVALMODE) {
			festivalModeInterpreter(k);
		}
	}

	private void normalModeInterpreter(KeyEvent k) {
		boolean query = false;
		int key = k.getKeyCode();
		switch (key) {
		case KeyEvent.VK_NUMPAD8:
		case KeyEvent.VK_UP:

			normal.moveUp();
			break;
		case KeyEvent.VK_NUMPAD6:
		case KeyEvent.VK_RIGHT:

			normal.moveRight();
			break;
		case KeyEvent.VK_NUMPAD4:
		case KeyEvent.VK_LEFT:

			normal.moveLeft();
			break;
		case KeyEvent.VK_NUMPAD2:
		case KeyEvent.VK_DOWN:

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
			normal.blockMode();
			break;
		case KeyEvent.VK_4:
			normal.palaceMode();
			break;
		case KeyEvent.VK_5:
			break;
		case KeyEvent.VK_6:
			query = ViewFacade.promptPlayer("Do you want to enter planning mode?");
			if(query) {
				planning = new PhasePlanning(player, board, sanitation);
				currentMode = GameMode.PLANNINGMODE;
				normal = null;
			}
			break;
		case KeyEvent.VK_7:
			// replay = new PhaseReplay(player, board, sanitation);
			boolean query2 = ViewFacade.promptPlayer("Do you want to enter replay mode?");
			if(query2) {
				
				currentMode = GameMode.REPLAYMODE;
				normal = null;
				replay.replay();
			}
			else{
				//currentMode = GameMode.NORMALMODE;
			}
			break;
		case KeyEvent.VK_8:
			query = ViewFacade.promptPlayer("Do you want to start a palace festival?");
			if(query) {
				// festival = new PhaseFestival(player, board, sanitation);
				currentMode = GameMode.FESTIVALMODE;
				normal = null;
			}
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
		boolean query = false;
		int key = k.getKeyCode();
		switch (key) {
		case KeyEvent.VK_NUMPAD8:
			planning.moveUp();
			break;
		case KeyEvent.VK_NUMPAD6:
			planning.moveRight();
			break;
		case KeyEvent.VK_NUMPAD4:
			planning.moveLeft();
			break;
		case KeyEvent.VK_NUMPAD2:
			planning.moveDown();
			break;
		case KeyEvent.VK_TAB:
			planning.switchSelected();
			break;
		case KeyEvent.VK_1:
			planning.placeDeveloperMode();
			break;
		case KeyEvent.VK_2:
			planning.moveDeveloperMode();
			break;
		case KeyEvent.VK_3:
			planning.blockMode();
			break;
		case KeyEvent.VK_4:
			planning.palaceMode();
			break;
		case KeyEvent.VK_5:
			query = ViewFacade.promptPlayer("Do you want to enter action mode?");
			if(query) {
				normal = new PhaseActive(player, board, sanitation);
				currentMode = GameMode.NORMALMODE;
				planning = null;
			}
			break;
		case KeyEvent.VK_6:
			//planning = new PhasePlanning(player, board, sanitation);
			//currentMode = GameMode.PLANNINGMODE;
			//planning = null;
			break;
		case KeyEvent.VK_7:
			// replay = new PhaseReplay(player, board, sanitation);
			boolean query2 = ViewFacade.promptPlayer("Do you want to enter replay mode?");
			if(query2) {
				
				currentMode = GameMode.REPLAYMODE;
				planning = null;
				replay.replay();
			}
			else{
				//currentMode = GameMode.NORMALMODE;
			}
			break;
		case KeyEvent.VK_8:
			// festival = new PhaseFestival(player, board, sanitation);
			currentMode = GameMode.FESTIVALMODE;
			planning = null;
			break;
		case KeyEvent.VK_ENTER:
			planning.placeBlock();
			planning.placeDeveloper();
			planning.placePalace();
			planning.moveDeveloper();
			break;
		case KeyEvent.VK_S:
			planning.save();
			break;
		case KeyEvent.VK_L:
			planning.load();
			break;
		case KeyEvent.VK_A:
			planning.useActionToken();
			break;
		case KeyEvent.VK_E:
			planning.changeTurn();
			break;
		case KeyEvent.VK_R:
			planning.rotateBlock();
			break;
		default:
			break;
		}
	}

	private void replayModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
		switch (key){
		case KeyEvent.VK_RIGHT:
			replay.doCommand();
			break;
		}
	}

	private void festivalModeInterpreter(KeyEvent k) {
		int key = k.getKeyCode();
	}
}
