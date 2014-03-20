import java.util.ArrayList;

public class PlacePalaceTileCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int level;
	private int[] scoreChanges;

	PlacePalaceTileCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c,
			int l) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.level = l;
	}

	public void execute() {
		// assume checks have been made
		block = new OneBlock(new PalaceTile(level));
		player.playThreeBlock();
		board.placeBlock(coords, block);
		
		Player[] players = player.getPlayers();
		int[] oldScores = new int[players.length];
		for(int i = 0; i < players.length; i++)
			oldScores[i] = players[i].getScore();
		Scoring.palaceScoring(coords);
		
		scoreChanges = new int[players.length];
		for(int i = 0; i < players.length; i++)
			scoreChanges[i] = players[i].getScore()-oldScores[i];
			
		this.save();
		board.updateBoard();

		ViewFacade.getPublicInventoryView().fullUpdate(board);
	}

	public void undo() {
		board.removeBlock(coords);
		player.returnThreeBlock();
		
		Player[] players = player.getPlayers();
		for(int i = 0; i < players.length; i++)
			players[i].decrementScore(scoreChanges[i]);
		
		board.updateBoard();
		
		player.getCurrentPlayer().addScore(-1);

		ViewFacade.getPublicInventoryView().fullUpdate(board);
	}



	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "
				+ coords.y + " " + this.level);
		return result.toString();
	}
}