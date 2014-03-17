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
	private String currentPlayer;
	private ArrayList<String> otherPlayers;
	
	public PlayerView(ArrayList<String> playerNames) {
		otherPlayers = new ArrayList<String>(playerNames.subList(1,playerNames.size()));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayer = playerNames.get(0);
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
	
	public void switchPlayer() {
			String temp = otherPlayers.get(otherPlayers.size());
			for (int i = 1; i < otherPlayers.size(); i++)
				otherPlayers.add(i, otherPlayers.get(i-1));
			otherPlayers.add(0,currentPlayer);
			currentPlayer = temp;
	}

}