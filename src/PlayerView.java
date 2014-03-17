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
	 
	private static OtherPlayersView otherPlayersView;
	private static CurrentPlayerView currentPlayerView;
	private static String currentPlayer;
	private static ArrayList<String> otherPlayers;
	
	public PlayerView(ArrayList<String> playerNames) {
		otherPlayers = new ArrayList<String>(playerNames.subList(1,playerNames.size()));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayer = playerNames.get(0);
		currentPlayerView = new CurrentPlayerView(playerNames.get(0));
	}
	
	//Copy of above for testing
	public static void initialize(ArrayList<String> playerNames) {
		otherPlayers = new ArrayList<String>(playerNames.subList(1,playerNames.size()));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayer = playerNames.get(0);
		currentPlayerView = new CurrentPlayerView(playerNames.get(0));
	}
	
	public static OtherPlayersView getOtherPlayerView() {
		return otherPlayersView;
	}
	
	public static CurrentPlayerView getCurrentPlayerView() {
		return currentPlayerView;
	}
	
	public static void displayPalaceInventory(int numMask, int numPuppet, int numDrum, 
									   int numMaskDrum, int numDrumPuppet, 
									   int numPuppetMask) {
		currentPlayerView.displayPalaceInventory(numMask, numPuppet, numDrum,
												 numMaskDrum, numDrumPuppet,
												 numPuppetMask);								
	}
	
	public static void switchActivePlayer() {
		String temp = otherPlayers.get(otherPlayers.size() - 1);
		for (int i = otherPlayers.size() - 1; i > 0; i--) {
			otherPlayers.set(i, otherPlayers.get(i-1));
		}
		otherPlayers.set(0,currentPlayer);
		currentPlayer = temp;
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayerView = new CurrentPlayerView(currentPlayer);
	}

}