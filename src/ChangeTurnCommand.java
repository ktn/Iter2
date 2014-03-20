public class ChangeTurnCommand implements Command {
	private PlayerFacade player;

	public ChangeTurnCommand(PlayerFacade p) {
		this.player = p;
	}

	public void execute() {
		player.changeTurn();
		//ViewFacade.switchForwardActivePlayer();
		this.save();
		ViewFacade.getCurrentPlayerView().set(player);
		ViewFacade.getOtherPlayerView().set(player);
	}

	public void undo() {
		player.revertTurn();
		//ViewFacade.switchBackwardActivePlayer();

		ViewFacade.getCurrentPlayerView().set(player);
		ViewFacade.getOtherPlayerView().set(player);
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