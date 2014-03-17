import java.util.*;

public class Space {

	private Grid<Space> neighbors = new Grid<Space>(Space.class);
	private Stack<Tile> tiles;

	/*========================================
		The standard is as follows
		1st child = right child
		2nd child = top child
		3rd child = left child
		4th child = bottom child
	=========================================*/

	Space() {
		tiles = new Stack<Tile>();
	}

	// JOINED SPACES METHODS
	// =======================================================================
	// might delete
	public boolean isJoined(Space s) {
		boolean ret = false;

		for (Space y : neighbors) {
			if (s == y)
				ret = true;
		}

		return ret;
	}

	public void join(Space s) {
		// check if tile is already joined
		if (neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}

	public void join(int i, Space s) {
		// check if tile is already joined
		if (neighbors.indexOf(s) == -1)
			neighbors.set(i, s);
	}

	public void remove(Space s) {
		neighbors.remove(s);
	}

	// NEIGHBOR METHODS
	// =======================================================================

	public void addNeighbor(Space s) {
		if (neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}

	public Space getTop() {
		return neighbors.get(1);
	}

	public Space getBottom() {
		return neighbors.get(3);
	}

	public Space getRight() {
		return neighbors.get(0);
	}

	public Space getLeft() {
		return neighbors.get(2);
	}

	// TILE METHODS
	// =========================================================================

	public int getHeight() {
		if (tiles.empty())
			return 0;
		else
			return tiles.size();
	}

	public Tile getTile() {
		if (!tiles.empty()) {
			return tiles.peek();
		}
		return null;
	}

	public void placeTile(Tile t) {
		ArrayList<Integer> joined = t.getJoined();
		System.out.println(joined.size());

		// dont add same tile again and again forever
		// if (tiles.peek() != t) {
		for (int i : joined) {
			Space temp = neighbors.get(i);

			if (temp != null) {
				temp.addTile(t.getJoined(i));
			} else {
			}
		}

		tiles.add(t);
	}

	private void addTile(Tile t) {
		tiles.add(t);
	}

	public void removeTile() {
		Tile t = tiles.pop();
		ArrayList<Integer> joined = t.getJoined();

		for (int i : joined) {
			Space temp = neighbors.get(i);

			if (temp != null) {
				temp.popTile();
			} else {
				// throw exception?
			}
		}
	}

	private void popTile() {
		tiles.pop();
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(" Tile Stack Size: " + this.tiles.toString() + NEW_LINE);
		/*
		 * result.append(" Neighbors: "+ NEW_LINE); result.append("  Top: " +
		 * this.getTop() + NEW_LINE); result.append("  Left: " + this.getLeft()
		 * + NEW_LINE); result.append("  Bottom: " + this.getBottom() +
		 * NEW_LINE); result.append("  Right: " + this.getRight() + NEW_LINE);
		 */
		return result.toString();
	}
}