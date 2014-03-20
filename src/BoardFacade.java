import java.util.ArrayList;

public class BoardFacade {
	Board board;
	Pathfinding path;
	Communal communal;
	Pathfinding pathfinding;
	Traversal traversal;

	public BoardFacade() {
		board = new Board();
		path = new Pathfinding(board);
		communal = new Communal();
		pathfinding = new Pathfinding(board);
		traversal = new Traversal(board);
	}

	public Board.Coordinates getCoordinates(int x, int y){
		return board.new Coordinates(x, y);
	}

	public int getHeight(Board.Coordinates c) {
		return board.getHeight(c);
	}

	public Tile getTile(Board.Coordinates c) {
		return board.getTile(c);
	}

	public TileType getTileType(Board.Coordinates c) {
		return board.getTileType(c);
	}

	public Board.Coordinates getLargest() {
		return board.getLargest();
	}

	public ThreeBlock getThreeBlock() {
		try{
			return communal.getThreeBlock();
		} catch (NoBlocksLeftException e){
			return null;
		}
		
	}

	public OneBlock getIrrigationTile() {
		try{
			return communal.getIrrigationTile();
		} catch (NoBlocksLeftException e){
			return null;
		}
	}

	public int getPalaceLevel(Board.Coordinates c) {
		int level = ((PalaceTile) board.getTile(c)).getLevel();
		return level;
	}

	public Developer getDeveloper(Board.Coordinates c) {
		return board.getDeveloper(c);
	}
	
	public String toString()
	{
		return board.toString();
	}

	// BLOCK METHODS
	// =======================================================================

	public void placeBlock(Board.Coordinates c, Block b) {
		board.placeBlock(c, b);
	}

	public void removeBlock(Board.Coordinates c) {
		board.removeBlock(c);
	}

	public void putBackThreeBlock(Block b) {
		communal.putBackThreeBlock((ThreeBlock)b);
	}

	public void putBackIrrigationTile(Block b) {
		communal.putBackIrrigationTile((OneBlock)b);
	}

	// CHECKING METHODS  
	// =======================================================================
	public boolean inBounds(int x, int y){
		return board.inBounds(x, y);
	}

	public boolean inBounds(Board.Coordinates c){
		return board.inBounds(c);
	}

	public int threeBlocksLeft() {
		return communal.numThreeBlocks();
	}

	public int irrigationBlocksLeft() {
		return communal.numIrrigationTiles();
	}

	public boolean validPlacement(Board.Coordinates c, Block b) throws IllegalBlockPlacementException {
		return board.validPlacement(c,b);
	}

	public boolean isMountains(Board.Coordinates c){
		return board.isMountainSpace(c);
	}
	
	public boolean checkDeveloperPlacement(Board.Coordinates c)
	{
		return board.checkDeveloperPlacement(c);
	}

	public boolean checkEdgePlacement(Board.Coordinates c){
		return board.checkEdgePlacement(c);
	}

	// DEVELOPER METHODS  
	// =======================================================================

	public void placeDeveloper(Board.Coordinates c, Developer d){
		board.placeDeveloper(c, d);
	}

	public void moveDeveloper(Board.Coordinates c, Developer d){
		board.moveDeveloper(c, d);
	}

	public void removeDeveloper(Board.Coordinates c){
		board.removeDeveloper(c);
	}

	public int findShortestPath(Board.Coordinates oldPos, Board.Coordinates newPos) {
		return pathfinding.findShortestPath(oldPos.x, oldPos.y, newPos.x, newPos.y);
	}

	public ArrayList<Board.Coordinates> getShortestPath() {
		return pathfinding.getShortestPath();
	}

	// TRAVERSAL METHODS
	// =======================================================================================

	public ArrayList<Developer> findHighestDev(Board.Coordinates c)
	{
		return traversal.findHighestDev(c);
	}
	public ArrayList<Developer> findSecondHighestDev(Board.Coordinates c)
	{
		return traversal.findSecondHighestDev(c);
	}

	public boolean playerInCity(Player player, Board.Coordinates aCityTile)
	{
		return traversal.playerInCity(player, aCityTile);
	}

	public int numIrrigationTiles(Board.Coordinates anIrrigTile)
	{
		return traversal.numIrrigationTiles(anIrrigTile);
	}

	public ArrayList<Board.Coordinates> allPalaceTiles()
	{
		return traversal.allPalaceTiles();
	}
	
	public Board.Coordinates getDeveloper(Player p){
		return board.getDeveloper(p);
	}

	public Board.Coordinates nextDeveloper(Board.Coordinates c){
		return board.nextDeveloper(c);
	}
}		