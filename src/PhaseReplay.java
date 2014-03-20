
import java.util.Stack;


public class PhaseReplay {
	BoardFacade board;
	PlayerFacade player;
	Stack<Command> replayStack = new Stack<Command>();
	public PhaseReplay(BoardFacade b, PlayerFacade p) {
		this.board = b;
		this.player = p;
	}


	public void replay(){
		

		//get num of players
		Player[] temp = player.getPlayers();
		int numPlayers = temp.length;

		//loop until 4 player turns have been popped off or no commands left
		loop: while(numPlayers >= 0 && !CommandStack.empty()){
			Command c = CommandStack.popCommand();

			//someones turn ended so decrement counter
			if(c instanceof ChangeTurnCommand){
				numPlayers--;
				System.out.println(numPlayers);
			}

			//undo command
			c.undo();
			replayStack.push(c);

		}

		
	}

	public void doCommand(){
		//replay stack filled, replay commands
		if(!replayStack.empty()){
			Command c = replayStack.pop();
			c.execute();
		}
	}
}
