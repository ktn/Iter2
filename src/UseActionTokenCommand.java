public class UseActionTokenCommand implements Command {
	private BoardFacade board;
	private PlayerFacade player;

	UseActionTokenCommand(BoardFacade b, PlayerFacade p) {
		this.board = b;
		this.player = p;

	}

	public void execute() {
		player.useActionToken();

		this.save();

	}

	public void undo() {
		player.returnActionToken();
	}


	public void save(){
		CommandStack.storeCommand(this);
	}

	public void load(){
		
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " " + player.getName());
		return result.toString();
	}
}