import java.util.*;


public class Communal {
	ArrayList<ThreeBlock> threes;
	ArrayList<OneBlock> irrigations;
	ArrayList<Integer> palaceTiles;

	public Communal() {
		threes = new ArrayList<ThreeBlock>();
		irrigations = new ArrayList<OneBlock>();
		palaceTiles = new ArrayList<Integer>();

		for (int i = 0; i < 56; i++)
			threes.add(new ThreeBlock());
		irrigations = new ArrayList<OneBlock>();

		for (int i = 0; i < 16; i++)
			irrigations.add(new OneBlock(TileType.IRRIGATION));

		palaceTiles.add(0);
		palaceTiles.add(0);
		palaceTiles.add(6);
		palaceTiles.add(0);
		palaceTiles.add(7);
		palaceTiles.add(0);
		palaceTiles.add(8);
		palaceTiles.add(0);
		palaceTiles.add(9);
		palaceTiles.add(0);
		palaceTiles.add(10);
	}

	public Communal(int thr, int irr, int[] pt) {
		threes = new ArrayList<ThreeBlock>();
		irrigations = new ArrayList<OneBlock>();
		palaceTiles = new ArrayList<Integer>();

		for (int i = 0; i < thr; i++)
			threes.add(new ThreeBlock());

		for (int i = 0; i < irr; i++)
			irrigations.add(new OneBlock(TileType.IRRIGATION));

		for (int i = 0; i < pt.length; i++)
			palaceTiles.add(pt[i]);
	}

	public ThreeBlock getThreeBlock() throws NoBlocksLeftException {
		if (threes.isEmpty())
			throw new NoBlocksLeftException("No Three-Tile Blocks left.");
		return threes.remove(0);
	}

	public OneBlock getIrrigationTile() throws NoBlocksLeftException {

		if (irrigations.isEmpty())
			throw new NoBlocksLeftException("No Irrigation Tiles left.");
		return irrigations.remove(0);
	}

	public OneBlock getPalaceTile(int level) {
		if (palaceTiles.get(level).compareTo(0) != 0) {
			palaceTiles.set(level, palaceTiles.get(level) - 1);

			return new OneBlock(TileType.PALACE);
		}
		return null;
	}

	public void putBackPalaceTile(Block b) {
		if (b.getType() == TileType.PALACE)
		{
			int level = (((PalaceTile) b.getTile()).getLevel());
			palaceTiles.set(level, palaceTiles.get(level) +1 );
		}
	}

	public void putBackThreeBlock(ThreeBlock b) {
		if (b.getType() == TileType.THREE)
			threes.add(b);
	}

	public void putBackIrrigationTile(OneBlock b) {
		if (b.getType() == TileType.IRRIGATION)
			irrigations.add(b);
	}

	public int numThreeBlocks() {
		return threes.size();
	}

	public int numIrrigationTiles() {
		return irrigations.size();
	}

	public int numPalaceTiles(int level) {
		return palaceTiles.get(level);
	}
}
