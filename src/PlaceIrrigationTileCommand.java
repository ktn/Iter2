import java.util.ArrayList;

public class PlaceIrrigationTileCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int[] scoreChanges;

	PlaceIrrigationTileCommand(BoardFacade b, PlayerFacade p,
			Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
	}

	public void execute() {
		// assume checks have been made
		block = board.getIrrigationTile();
		player.playThreeBlock();
		// if(board.validPlacement(coors, b)
		board.placeBlock(coords, block);
		
		Player[] players = player.getPlayers();
		int[] oldScores = new int[players.length];
		for(int i = 0; i < players.length; i++)
			oldScores[i] = players[i].getScore();
			
		ArrayList<Board.Coordinates> irrCoords = board.checkIrrigationsSurrounded();
		for(Board.Coordinates irr : irrCoords)
			Scoring.irrigationScoring(irr);
			
		scoreChanges = new int[players.length];
		for(int i = 0; i < players.length; i++)
			scoreChanges[i] = players[i].getScore()-oldScores[i];
		
		
		
		this.save();		
		board.updateBoard();
		ViewFacade.getPublicInventoryView().displayIrrigationTiles(board.irrigationBlocksLeft());
	}

	public void undo() {
		board.removeBlock(coords);
		
		Player[] players = player.getPlayers();
		for(int i = 0; i < players.length; i++)
			players[i].decrementScore(scoreChanges[i]);
		
		player.returnThreeBlock();
		board.updateBoard();

		ViewFacade.getPublicInventoryView().displayIrrigationTiles(board.irrigationBlocksLeft());
	}


	public void save(){
		CommandStack.storeCommand(this);
	}

	public void load() {

	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y);
		return result.toString();
	}
}
