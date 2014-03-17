/*
 ******************************************************************************
PlayerTurn is an object that keeps track of whose turn it is
 ******************************************************************************
 */

public class PlayerTurn {
	Player[] players;
	int currentPlayer;
	int numPlayers;
	boolean tokenUsed;
	int actionPoints;
	boolean blockPlayed;

	// initialization requires names of players, assumes acceptable size
	public PlayerTurn(String[] n) {
		numPlayers = n.length;

		if (numPlayers > 4) {
			numPlayers = 4;
		}

		players = new Player[numPlayers];
		currentPlayer = 0;

		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(n[i]);
		}
		tokenUsed = false;
		actionPoints = 6;
		blockPlayed = false;
	}

	// change player turn
	public void changeTurn() {
		currentPlayer++;
		if (currentPlayer >= numPlayers) {
			currentPlayer = 0;
		}
		tokenUsed = false;
		actionPoints = 6;
		blockPlayed = false;
	}

	public Player getCurrentPlayer() {
		return players[currentPlayer].getPlayer();
	}

	public Player getPlayer(int p) {
		return players[p];
	}
	
	public int getActionPoints() {
		return actionPoints;
	}

	// checks, placements, and returns
	public boolean actionTokenUsable() {
		if (players[currentPlayer].getActionTokens() > 0 && !tokenUsed) {
			return true;
		} else {
			return false;
		}
	}

	public void useActionToken() {
		players[currentPlayer].useActionToken();
		tokenUsed = true;
		actionPoints++;
	}

	public boolean blockPlayed() {
		return blockPlayed;
	}

	public void playedBlock() {
		blockPlayed = true;
	}

	public void returnActionToken()
	{
		players[currentPlayer].returnActionToken();
	}

}
