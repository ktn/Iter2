public class PlaceOneBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private TileType type;

	PlaceOneBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c,
			TileType type) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.type = type;
	}

	public void execute() {

	}

	public void undo() {

	}

	public void save() {

	}

	public void load() {

	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y + NEW_LINE);
		result.append(NEW_LINE);
		return result.toString();
	}
}