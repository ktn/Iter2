public class MoveDeveloperCommand implements Command {
	private BoardFacade board;
	private PlayerFacade player;
	private Board.Coordinates oldCoords;
	private Board.Coordinates newCoords;

	MoveDeveloperCommand(BoardFacade b, PlayerFacade p, Board.Coordinates olds,
			Board.Coordinates news) {
		this.board = b;
		this.player = p;
		this.oldCoords = olds;
		this.newCoords = news;
	}

	public void execute() {
		Developer dev = board.getDeveloper(oldCoords);
		board.moveDeveloper(newCoords, dev);
		this.save();
		board.updateBoard();

		Wavefront wavefront=new Wavefront();
		wavefront.wavefront(oldCoords, newCoords, board.board);
		int requiredAP = wavefront.totalCost-1;
		for (int i=0;i<requiredAP;i++)
			player.playThreeBlock();
		
		ViewFacade.getCurrentPlayerView().set(player);
	}

	public void undo() {
		Developer dev = board.getDeveloper(newCoords);
		board.moveDeveloper(oldCoords, dev);
		board.updateBoard();
		Wavefront wavefront=new Wavefront();
		wavefront.wavefront(oldCoords, newCoords, board.board);
		int requiredAP = wavefront.totalCost-1;
		player.playerTurn.addToActionPoints(requiredAP);
		ViewFacade.getCurrentPlayerView().set(player);
	}

	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + oldCoords.x + " "
				+ oldCoords.y + " " + newCoords.x + " " + newCoords.y
				+ player.getName());
		return result.toString();
	}
}