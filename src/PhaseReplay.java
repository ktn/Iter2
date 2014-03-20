
import java.util.Stack;


public class PhaseReplay {
	BoardFacade board;
	PlayerFacade player;
	public PhaseReplay(BoardFacade b, PlayerFacade p) {
		this.board = b;
		this.player = p;
	}


	public void replay(){
		Stack<Command> replayStack = new Stack<Command>();

		//get num of players
		Player[] temp = player.getPlayers();
		int numPlayers = temp.length;

		//loop until 4 player turns have been popped off or no commands left
		while(numPlayers >= 0 || !CommandStack.empty()){
			Command c = CommandStack.popCommand();

			//someones turn ended so decrement counter
			if(c instanceof ChangeTurnCommand){
				numPlayers--;
			}

			//undo command
			c.undo();
			replayStack.push(c);
		}

		//replay stack filled, replay commands
		while(!replayStack.empty()){
			Command c = replayStack.pop();
			c.execute();
		}
	}
}
