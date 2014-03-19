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

	public void currentPlayerPlaceBid(String[] cardSymbol) {
		playerFacade.play(cardSymbol);
		playerFacade.nextPFPlayer();
	}

	public void currentPlayerPassBid() {

	}
    
	public void endFestival() {
		
	}
    
}