public class DrawDeckCardCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;
	private PalaceCard card;

	DrawDeckCardCommand(BoardFacade b, PlayerFacade p){
		this.board = b;
		this.player = p;
		
	}

	public void	execute(){
		/*card = player.getFestivalCard;
		player.addCard(card);
		this.save();*/

	}

	public void undo(){
		//return player card
		//return festival cards

	}

	public void save(){

	}

	public void load(){
		
	}
}