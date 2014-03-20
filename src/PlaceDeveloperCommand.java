public class PlaceDeveloperCommand implements Command {
	private BoardFacade board;
	private PlayerFacade player;
	private Board.Coordinates coord;

	PlaceDeveloperCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.player = p;
		coord = c;
	}

	public void execute() {
		Developer dev = new Developer(player.getCurrentPlayer());
		board.placeDeveloper(coord, dev);
		player.placeDeveloper();
		player.playerTurn.addToActionPoints(-1);
		if (board.isMountains(coord))
			player.playerTurn.addToActionPoints(-1);
		this.save();
		board.updateBoard();
		ViewFacade.getCurrentPlayerView().displayDevelopers(player.getCurrentPlayer().getDevelsOff());
	}

	public void undo() {
		board.removeDeveloper(coord);
		player.removeDeveloper();
		board.updateBoard();
		player.playerTurn.addToActionPoints(1);
		if (board.isMountains(coord))
			player.playerTurn.addToActionPoints(1);
		ViewFacade.getCurrentPlayerView().displayDevelopers(player.getCurrentPlayer().getDevelsOff());
	}



	public void load() {
	}

	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coord.x + " " + coord.y);
		return result.toString();
	}
}