public enum TileType {
	VILLAGE("Village"), RICE("Rice"), IRRIGATION("Irrigation"), PALACE("Palace"), TWO(
			"Two-space"), THREE("Three-space"), EMPTY("Empty"), PALACE2(
			"Level 2 Palace"), PALACE4("Level 4 Palace"), PALACE6(
			"Level 6 Palace"), PALACE8("Level 8 Palace"), PALACE10(
			"Level 10 Palace");

	String s;

	private TileType(String s) {
		this.s = s;
	}

	public String toString() {
		return s;
	}
}