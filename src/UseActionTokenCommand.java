public class UseActionTokenCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;

	UseActionTokenCommand(BoardFacade b, PlayerFacade p){
		this.board = b;
		this.player = p;
		
	}

	public void	execute(){
		player.useActionToken();

		this.save();

	}

	public void undo(){
		player.returnActionToken();
	}

	public void save(){
		CommandStack.storeCommand(this);
	}

	public void load(){
		
	}
}