import java.util.*;

public abstract class Tile {
	private TileType type;
	protected Grid<Tile> joinedTiles;
	
	

	Tile(){
		joinedTiles = new Grid<Tile>(Tile.class);
	}

	public TileType getType()
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

	public ArrayList<Integer> getJoined(){
		ArrayList<Integer> joined = new ArrayList<Integer>();

		for(Tile y : joinedTiles)
		{
			if(y != null)
				joined.add(joinedTiles.indexOf(y));
		}
		return joined;
	}

	public Tile getJoined(int i){
		Tile temp = joinedTiles.get(i);
		Tile ret;

		if(temp != null){
			ret = temp;
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