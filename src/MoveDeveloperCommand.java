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

	}

	public void undo() {
		Developer dev = board.getDeveloper(newCoords);
		board.moveDeveloper(oldCoords, dev);
		board.updateBoard();
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