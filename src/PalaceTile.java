public class PalaceTile extends Tile {
	private int level;
	private boolean headsUp;

	PalaceTile(int level) {
		type = TileType.PALACE;
		headsUp = true;

		if (level % 2 == 0 && level <= 10) {
			this.level = level;
		} else
			throw new IllegalArgumentException("Illegal Palace level");
	}

	public void flip() {
		this.headsUp = !this.headsUp;
	}

	public boolean isHeadsUp() {
		return this.headsUp;
	}

	public int getLevel() {
		return level;
	}
}