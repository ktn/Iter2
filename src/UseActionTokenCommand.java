public class UseActionTokenCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;

	MoveDeveloperCommand(BoardFacade b, PlayerFacade p)
		this.board = b;
		this.player = p;
		
	}

	public void	execute(){
		board.useActionToken();

		this.save();

	}

	public void undo(){
		board.returnActionToken();
	}

	public void save(){

	}
}