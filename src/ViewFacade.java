import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public final class ViewFacade {
	/**This class represents the facade for three portions
	 * of the final view, the current player other player
	 * components, and public inventory. This class serves as a facade to
	 * interface both with outside systems, as well as with the central 
	 * view. Primarily, this handles method calls related
	 * to PlayerView and rotates the current player and inactive
	 * players.
	 */
	 
	private static OtherPlayersView otherPlayersView;
	private static CurrentPlayerView currentPlayerView;
	private static String currentPlayer;
	private static ArrayList<String> otherPlayers;
	private static PublicInventoryView publicInventoryView;
	private static ControllerView controllerView;
	private static BoardView boardView;
	
	private ViewFacade(ArrayList<String> playerNames, int boardWidth, int boardHeight) {
		otherPlayers = new ArrayList<String>(playerNames.subList(1,playerNames.size()));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayer = playerNames.get(0);
		currentPlayerView = new CurrentPlayerView(playerNames.get(0));
		publicInventoryView = new PublicInventoryView();
		controllerView = new ControllerView();
		//@ TODO: don't use magic  numbers
		boardView=new BoardView(boardWidth, boardHeight);
	}
	
	//Copy of above for testing
	public static void initialize(ArrayList<String> playerNames, int boardWidth, int boardHeight) {
		otherPlayers = new ArrayList<String>(playerNames.subList(1,playerNames.size()));
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayer = playerNames.get(0);
		currentPlayerView = new CurrentPlayerView(playerNames.get(0));
		publicInventoryView = new PublicInventoryView();
		controllerView = new ControllerView();
		//@ TODO: don't use magic  numbers
		boardView=new BoardView(boardWidth, boardHeight);
	}
	
	public static OtherPlayersView getOtherPlayerView() {
		return otherPlayersView;
	}
	
	public static CurrentPlayerView getCurrentPlayerView() {
		return currentPlayerView;
	}
	
	public static PublicInventoryView getPublicInventoryView() {
		return publicInventoryView;
	}
	
	public static ControllerView getControllerView() {
		return controllerView;
	}
	
	public static BoardView getBoardView(){
		return boardView;
	}
	
	public static void displayPalaceInventory(int[] inventory) {
		currentPlayerView.displayPalaceInventory(inventory[0], inventory[1], inventory[2],
												 inventory[3], inventory[4],
												 inventory[5]);								
	}
	
	public static void switchForwardActivePlayer() {
		String temp = otherPlayers.get(otherPlayers.size() - 1);
		for (int i = otherPlayers.size() - 1; i > 0; i--) {
			otherPlayers.set(i, otherPlayers.get(i-1));
		}
		otherPlayers.set(0,currentPlayer);
		currentPlayer = temp;
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayerView = new CurrentPlayerView(currentPlayer);
	}
	
	public static void switchBackwardActivePlayer() {
		String temp = otherPlayers.get(0);
		for (int i = 0; i < otherPlayers.size() - 1; i++) {
			otherPlayers.set(i, otherPlayers.get(i+1));
		}
		otherPlayers.set(otherPlayers.size() - 1,currentPlayer);
		currentPlayer = temp;
		otherPlayersView = new OtherPlayersView(otherPlayers);
		currentPlayerView = new CurrentPlayerView(currentPlayer);
	}
	
	public static void displayScore(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayScore(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayScore(score);
	}
	
	public static void displayActionTokens(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayActionTokens(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayActionTokens(score);
	}
	
	public static void displayRiceBlocks(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayRiceBlocks(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayRiceBlocks(score);
	}
	
	public static void displayVillageBlocks(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayVillageBlocks(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayVillageBlocks(score);
	}
	
	public static void displayTwoBlocks(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayTwoBlocks(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayTwoBlocks(score);
	}
	
	public static void displayActionPoints(int score) {
			currentPlayerView.displayActionPoints(score);
	}
	
	public static void displayDevelopers(String playerName, int score) {
		if (otherPlayers.contains(playerName)) 
			otherPlayersView.displayTwoBlocks(playerName, score);
		else if (currentPlayer.equals(playerName)) 
			currentPlayerView.displayTwoBlocks(score);
	}
	
	public static boolean promptPlayer(String message) {
		int n = JOptionPane.showConfirmDialog(otherPlayersView, message, "Player Prompt", JOptionPane.YES_NO_OPTION);
		if (n == 0)
			return true;
		else if (n == 1)
			return false;
		else
			return false;
	}
	
	public static void warnPlayer(String message) {
		int n = JOptionPane.showConfirmDialog(otherPlayersView, message, "Player Warning", JOptionPane.DEFAULT_OPTION);
	}
	
	public static void displayThreeBlocks(int numThreeBlocks) {
		publicInventoryView.displayThreeBlocks(numThreeBlocks);
	}
	
	public static void displayIrrigationTiles(int numIrrigationTiles) {
		publicInventoryView.displayIrrigationTiles(numIrrigationTiles);
	}
	
	public static void displayTwoPalaceTiles(int numTwoPalace) {
		publicInventoryView.displayTwoPalaceTiles(numTwoPalace);
	}
	
	public static void displayFourPalaceTiles(int numFourPalace) {
		publicInventoryView.displayFourPalaceTiles(numFourPalace);
	}
	
	public static void displaySixPalaceTiles(int numSixPalace) {
		publicInventoryView.displaySixPalaceTiles(numSixPalace);
	}
	
	public static void displayEightPalaceTiles(int numEightPalace) {
		publicInventoryView.displaySixPalaceTiles(numEightPalace);
	}
	
	public static void displayTenPalaceTiles(int numTenPalace) {
		publicInventoryView.displaySixPalaceTiles(numTenPalace);
	}
	
	public static void displayFestivalCard(int type) {
		publicInventoryView.displayFestivalCard(type);
	}
	
	public static void setGameMode(String mode) {
		controllerView.setMode(mode);
	}
	
	public static void setAvailableActions(String actions) {
		controllerView.setActions(actions);
	}
	
	public static void startPalaceFestival(String currentPlayer) {
		PalaceFestivalViewFacade.initialize(currentPlayer);
	}
	
	public static void updateBoard(Board board){
		boardView.renderBoard(board);
	}
	
	public static void renderNetwork(Tile origin, int x, int y){
		boardView.renderNetwork(origin, x, y);
	}
	
	public static void renderNetwork(Tile origin, int x, int y, Color c){
		boardView.renderNetwork(origin, x, y, c);
	}
}