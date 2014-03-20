import java.util.Stack;

public final class CommandStack
{
	static Stack<Command> commands;

	private CommandStack(){
		commands = new Stack<Command>();
	}


	public static void storeCommand(Command c){
		commands.push(c);
	}

	public void save(){
		//Lucas save stuff here
	}
}