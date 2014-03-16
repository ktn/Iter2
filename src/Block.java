public abstract class Block {
	private Tile head;

	/*========================================
		The standard orientation will be


					head	-->		child
					
					  |
					  |
					  v

					child
	=========================================*/
	
	public Block()
	{
		head = new VillageTile();
	}
	
	public Block(TileType t)
	{
		if(t == TileType.VILLAGE)
			head = new VillageTile();
		else if(t == TileType.IRRIGATION)
			head = new IrrigationTile();
	}
	
	public Block(int num)
	{
		head = new VillageTile();
		if(num >= 2)
		{
			head.join(new RiceTile());
		}
		if(num >= 3)
		{
			head.join(new RiceTile());
		}
		
	}
	
	public void rotate(){
		head.rotate();
	}
	
	public Tile getTile(){
		return head;
	}
}