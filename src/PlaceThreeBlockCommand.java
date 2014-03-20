import java.util.ArrayList;

public class PlaceThreeBlockCommand implements Command {
	private BoardFacade board;
	private Board.Coordinates coords;
	private PlayerFacade player;
	private Block block;
	private int rotation;
	private int[] scoreChanges;

	PlaceThreeBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c, int r) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = r;
	}

	PlaceThreeBlockCommand(BoardFacade b, PlayerFacade p, Board.Coordinates c) {
		this.board = b;
		this.coords = c;
		this.player = p;
		this.rotation = 0;
	}

	public void execute() {
		// assume checks have been made
		block = board.getThreeBlock();
		player.playThreeBlock();

		for(int i = 0; i < rotation; i++)
		{
			block.rotate();
		}
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
		ViewFacade.getPublicInventoryView().displayThreeBlocks(board.threeBlocksLeft());
	}

	public void undo() {
		Player[] players = player.getPlayers();
		for(int i = 0; i < players.length; i++)
			players[i].decrementScore(scoreChanges[i]);
	
		board.removeBlock(coords);
		board.putBackThreeBlock(block);
		player.returnThreeBlock();
		board.updateBoard();
		ViewFacade.getPublicInventoryView().displayThreeBlocks(board.threeBlocksLeft());
	}

	public void load() {

	}	
	public void save(){
		CommandStack.storeCommand(this);
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(this.getClass().getName() + " " + coords.x + " "+ coords.y+" "+ rotation);
		return result.toString();
	}
}