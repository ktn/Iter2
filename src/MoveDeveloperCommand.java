public class MoveDeveloperCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;
	private Board.Coordinates oldCoords;
	private Board.Coordinates newCoords;

	MoveDeveloperCommand(BoardFacade b, PlayerFacade p, Board.Coordinates olds, Board.Coordinates news)
		this.board = b;
		this.player = p;
		this.oldCoords = olds;
		this.newCoords = news;
	}

	public void	execute(){
		Developer dev = board.getDeveloper(oldCoords);
		board.moveDeveloper(newCoords, dev);

		this.save();

	}

	public void undo(){
		board.moveDeveloper(oldCoords, dev);
	}

	public void save(){

	}
}