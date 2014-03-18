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
		palaceTiles = new ArrayList<Integer>();
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

	public ThreeBlock getThreeBlock() {
		if (!threes.isEmpty())
			throw new NoBlocksLeftException("No Three-Tile Blocks left.");
		return threes.remove(0);
	}

	public OneBlock getIrrigationTile() {
		
		if (!irrigations.isEmpty())
			throw new NoBlocksLeftException("No Irrigation Tiles left.");
		return irrigations.remove(0);
	}

	public OneBlock getPalaceTile(int level) {
		//Makes no sense
		if (palaceTiles.get(level) != 0)
			palaceTiles.get(level) -= 1;
		return new OneBlock(TileType.PALACE);
	}

	public void putBackPalaceTile(Block b) {
		//Makes no sense
		if (b.getType() == TileType.PALACE)
			palaceTiles.get(((PalaceTile) b.getTile()).getLevel()) += 1;
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
