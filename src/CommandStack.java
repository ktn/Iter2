public final class CommandStack{
	Stack<Command> commands;

	private CommandStack(){
		commands = new Stack<Tiles>();
	}


	public static void storeCommand(Command c){
		commands.push(c);
	}

	public void save(){
		//Lucas save stuff here
	}
}