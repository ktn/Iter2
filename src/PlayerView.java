import java.util.ArrayList;

public class PlayerView {
	/**This class represents the facade for two portions
	 * of the final view, the current player and other player
	 * components. This class serves as a facade to interface
	 * both with outside systems, as well as with the central 
	 * view. Primarily, this handles method calls related
	 * to PlayerView and rotates the current player and inactive
	 * players.
	 */
	 
	private OtherPlayersView otherPlayersView;
	private CurrentPlayerView currentPlayerView;
	
	public PlayerView(ArrayList<String> playerNames) {
		ArrayList<String> otherPlayers = new ArrayList<String>(playerNames.subList(1,4));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayerView = new CurrentPlayerView(playerNames.get(0));
	}
	
	public OtherPlayersView getOtherPlayerView() {
		return otherPlayersView;
	}
	
	public CurrentPlayerView getCurrentPlayerView() {
		return currentPlayerView;
	}
	
	public void displayPalaceInventory(int numMask, int numPuppet, int numDrum, 
									   int numMaskDrum, int numDrumPuppet, 
									   int numPuppetMask) {
		currentPlayerView.displayPalaceInventory(numMask, numPuppet, numDrum,
												 numMaskDrum, numDrumPuppet,
												 numPuppetMask);								
	}

}