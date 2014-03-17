import java.util.*;

public abstract class Tile {
	protected TileType type;
	private Grid<Tile> joinedTiles;

	Tile() {
		joinedTiles = new Grid<Tile>(Tile.class);
	}

	public TileType getType() {
		return type;
	}

	public boolean isJoined(Tile t) {
		boolean ret = false;

		for (Tile y : joinedTiles) {
			if (t == y)
				ret = true;
		}

		return ret;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		result.append(type.toString());
		for (Tile y : joinedTiles) {
			if (y != null)
				result.append(y.getType().toString());
			/*else
				result.append("EMPTY ");*/

		}

		return result.toString();
	}

	public void join(Tile t) {
		// check if tile is already joined
		// if (joinedTiles.indexOf(t) == -1)
		joinedTiles.add(t);
	}

	private void joinTile(Tile t) {
		if (!this.isJoined(t))
			joinedTiles.add(t);
	}

	public ArrayList<Integer> getJoined() {
		ArrayList<Integer> joined = new ArrayList<Integer>();

		for (Tile y : joinedTiles) {
			if (y != null) {
				joined.add(joinedTiles.indexOf(y));
				//System.out.println(y + "is joined");
			}
		}
		return joined;
	}

	public Tile getJoined(int i) {
		Tile temp = joinedTiles.get(i);
		Tile ret;

		if (temp != null) {
			ret = temp;
		} else {
			// throw exception?
		}

		return temp;
	}

	public void remove(Tile t) {
		joinedTiles.remove(t);
	}

	public void rotate() {
		joinedTiles.rotate();
	}

	public Grid<Tile> getGrid() {
		return joinedTiles;
	}

	public void set(int i, Tile t) {
		joinedTiles.set(i, t);
	}
}