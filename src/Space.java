import java.util.*;

public class Space {
	
	private Grid<Space> neighbors  = new Grid(Space.class);
	private Stack<Tile> tiles;
	

	/*========================================
		The standard is as follows
		1st child = right child
		2nd child = bottom child
		3rd child = left child
		4th child = top child
	=========================================*/

	Space(){
		tiles = new Stack<Tile>();
		
	}

	//JOINED SPACES METHODS  =======================================================================
	//might delete
	public boolean isJoined(Space s){
		boolean ret = false;
				
		for(Space y : neighbors)
		{
			if(s == y)
				ret = true;
		}

		return ret;
	}

	public void join(Space s){
		//check if tile is already joined
		if(neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}

	public void join(int i, Space s){
		//check if tile is already joined
		if(neighbors.indexOf(s) == -1)
			neighbors.set(i, s);
	}

	public void remove(Space s){
		neighbors.remove(s);
	}


	//NEIGHBOR METHODS =======================================================================
	
	
	public void addNeighbor(Space s){
		if(neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}
	
	public Space getTop(){
		return neighbors.get(3);
	}
	
	public Space getBottom(){
		return neighbors.get(1);
	}
	
	public Space getRight(){
		return neighbors.get(0);
	}
	
	public Space getLeft(){
		return neighbors.get(2);
	}
	
	
	//TILE METHODS =========================================================================
	
	public int getHeight(){
		return tiles.size();
	}
	
	public Tile getTile(){
		return tiles.peek();
	}

	public void placeTile(Tile t){
		ArrayList<Integer> joined = t.getJoined();


		//dont add same tile again and again forever
		if(tiles.peek() != t){
			for(int i : joined){
				Space temp = neighbors.get(i);

				if(temp != null){
					temp.placeTile(t.getJoined(i));
				}
				else{
					//throw exception?
				}
			}
		}

		tiles.add(t);
	}
	
	public void removeTile(){
		Tile t = tiles.pop();
		ArrayList<Integer> joined = t.getJoined();


		for(int i : joined){
				Space temp = neighbors.get(i);

				if(temp != null){
					temp.removeTile();
				}
				else{
					//throw exception?
				}
			}
	}
}