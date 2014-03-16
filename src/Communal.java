import Java.util.ArrayList;

public class Communal
{
	ArrayList<ThreeBlock> threes;
	ArrayList<OneBlock> irrigations;
	
	public Communal()
	{
		threes = new ArrayList<ThreeBlock>;
		for(int i = 0; i < 56; i++)
			threes.add(new ThreeBlock());
		irrigations = new ArrayList<OneBlock>;
		for(int i = 0; i < 16; i++)
			threes.add(new OneBlock(TileType.IRRIGATION));
	}
	public Communal(int thr, int irr)
	{
		threes = new ArrayList<ThreeBlock>;
		for(int i = 0; i < thr; i++)
			threes.add(new ThreeBlock());
		irrigations = new ArrayList<OneBlock>;
		for(int i = 0; i < irr; i++)
			threes.add(new OneBlock(TileType.IRRIGATION));
	}
	
	public ThreeBlock getThreeBlock()
	{
		if(!threes.empty())
			throw new NoBlocksLeftException("No Three-Tile Blocks left.");
		return threes.pop();
	}
	public OneBlock getIrrigationTile()
	{
		if(!irrigations.empty())
			throw new NoBlocksLeftException("No Irrigation Tiles left.");
		return irrigations.pop();
	}
	public void putBackThreeBlock(Block b)
	{
		if(b.getType() = TileType.THREE)
			threes.add(b);
	}
	public void putBackIrrigationTile(Block b)
	{
		if(b.getType() = TileType.IRRIGATION)
			threes.add(b);
	}
	public int numThreeBlocks()
	{
		return threes.size();
	}
	public int numIrrigationTiles()
	{
		return irrigations.size();
	}
}