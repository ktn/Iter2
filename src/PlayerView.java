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
	 
	private OtherPlayersView otherPlayers;
	private CurrentPlayerView currentPlayer;
	
	public PlayerView(ArrayList<String> playerNames) {
		otherPlayers = new OtherPlayersView(playerNames);
		currentPlayer = new CurrentPlayerView();
	}
	
	public OtherPlayersView getOtherPlayerView() {
		return otherPlayers;
	}
	
	public CurrentPlayerView getCurrentPlayerView() {
		return currentPlayer;
	}

}