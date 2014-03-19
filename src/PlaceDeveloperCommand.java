public class PlaceDeveloperCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;
	private Board.Coordinates coord;

	PlaceDeveloperCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c){
		this.board = b;
		this.player = p;
		coord = c;
	}

	public void	execute(){
		Developer dev = new Developer(p.getCurrentPlayer());
		board.placeDeveloper(coords, dev);
		player.placeDeveloper();

		this.save();

	}

	public void undo(){
		board.removeDeveloper(coords);
		player.removeDeveloper();
	}

	public void save(){

	}
}