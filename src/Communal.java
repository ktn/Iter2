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
		return threes.pop();
	}
	public OneBlock getIrrigationTile()
	{
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
}