public class PhaseFestival {
	private PlayerFacade playerFacade;
	private Sanitation sanitation;
	private boolean isPlayerBidding;
	private boolean lastPlayerDone;
	private boolean isTied;
    
	public PhaseFestival(PlayerFacade playerFacade, Sanitation sanitation) {
		this.playerFacade = playerFacade;
		this.sanitation = sanitation;
	}
    
	public void startFestival() {
		playerFacade.createPalaceFestival();
		playerFacade.startFestival(playerFacade.getPlayers());
	}

	/*public void currentPlayerPlaceBid(String[] cardSymbol) {
		//playerFacade.play(cardSymbol);
		playerFacade.nextPFPlayer();
	}*/

	public void currentPlayerPassBid() {
		playerFacade.freezePlayer();
		playerFacade.nextPFPlayer();
	}
    
	public boolean endFestival() {
		//If this is true, the festival is over
		if(playerFacade.checkEnd())
		{
			return true;
		}
		return false;
	}

	public void playDrumCard()
	{
		String[] type = new String[1];
		type[0] = "DRUM";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}

	public void playPuppetCard()
	{
		String[] type = new String[1];
		type[0] = "PUPPET";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}

	public void playMaskCard()
	{
		String[] type = new String[1];
		type[0] = "MASK";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}

	public void playMaskDrumCard()
	{
		String[] type = new String[2];
		type[0] = "MASK";
		type[1] = "DRUM";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}

	public void playDrumPuppetCard()
	{
		String[] type = new String[2];
		type[0] = "DRUM";
		type[1] = "PUPPET";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}

	public void playPuppetMaskCard()
	{
		String[] type = new String[2];
		type[0] = "PUPPET";
		type[1] = "MASK";
		PlayPalaceCardCommand p = new PlayPalaceCardCommand(playerFacade, type);
		p.execute();
	}






	
    
}