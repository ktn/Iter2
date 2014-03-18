public class PlaceOneBlockCommand implements Command{
	private Board boad;
	private Board.Coordinates coords;
	private Block block;

	PlaceOneBlockCommmand(Board b, Board.Coordinates c){
		this.board = b;
		this.coords = c;
	}
}