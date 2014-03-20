public class ChangeTurnCommand implements Command {
	private PlayerFacade player;

	public ChangeTurnCommand(PlayerFacade p) {
		this.player = p;
	}

	public void execute() {
		player.changeTurn();
		this.save();
		

	}

	public void undo() {
		player.revertTurn();

		// player.changeTurn(-1);

	}

	public void save() {
		CommandStack.storeCommand(this);
	}

	public void load() {

	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName());
		return result.toString();
	}
}