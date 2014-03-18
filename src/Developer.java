public class Developer {
	private Player player;
	private Space space;

	Developer(Player p) {
		this.space = null;
		this.player = p;
	}

	Developer(Player p, Space s) {
		this.space = s;
		this.player = p;
	}

	public Player getPlayer() {
		return player;
	}

	public Space getSpace() {
		return space;
	}

	public void moveDeveloper(Space s) {
		this.space = s;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" Player Name: " + this.getPlayer());
		result.append(" Space: " + this.getSpace() + NEW_LINE);
		result.append("}" + NEW_LINE);
		return result.toString();
	}
}