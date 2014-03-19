public class DrawPalaceCardCommand implements Command{
	private BoardFacade board;
	private PlayerFacade player;
	private PalaceCard card;

	DrawPalaceCardCommand(BoardFacade b, PlayerFacade p)
		this.board = b;
		this.player = p;
		
	}

	public void	execute(){
		card = player.getFestivalCard;
		player.addCard(card);
		this.save();

	}

	public void undo(){
		//return player card
		//return festival cards

	}

	public void save(){

	}
}