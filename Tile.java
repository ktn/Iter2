public abstract class Tile {
	private Tile.TileType type;
	public enum TileType { PALACE, VILLAGE, RICE, IRRIGATION };
	protected Grid<Tile> joinedTiles;
	
	

	Tile(){
		joinedTiles = new Grid<Tile>(Tile.class);
	}

	public Tile.TileType getType()
	{
		return type;
	}

	public boolean isJoined(Tile t){
		boolean ret = false;

		for(Tile y : joinedTiles)
		{
			if(t == y)
				ret = true;
		}

		return ret;
	}

	public void join(Tile t){
		//check if tile is already joined
		if(joinedTiles.indexOf(t) == -1){
			joinedTiles.add(t);
			if(!t.isJoined(this))
				t.join(this);
		}
	}

	public ArrayList<int> getJoined(){
		ArrayList<int> joined = new ArrayList<int>();

		for(Tile y : joinedTiles)
		{
			if(y != null)
				joined.add(joinedTiles.indexOf(y));
		}
	}

	public Tile getJoined(int i){
		Tile temp = joinedTiles.get(i);

		if(temp != null){
			ret = temp 
		}
		else{
			//throw exception?
		}

		return temp;
	}


	public void remove(Tile t){
		joinedTiles.remove(t);
	}

	public void rotate(){
		joinedTiles.rotate();
	}
	
	public Grid<Tile> getGrid(){
		return joinedTiles;
	}
}