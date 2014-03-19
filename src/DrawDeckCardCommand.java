public class DrawDeckCardCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;
	private PalaceCard card;

	DrawDeckCardCommand(BoardFacade b, PlayerFacade p){
		this.board = b;
		this.player = p;
		
	}

	public void	execute(){
		card = player.topCard();
		player.addCard(card);
		this.save();

	}

	public void undo(){
		//return player card
		//return festival cards
		player.returnTopCard(card);
		player.removeCard(card);

	}

	public void save(){
		CommandStack.storeCommand(this);
	}

	public void load(){

	}
}