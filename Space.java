import java.util.*;

public class Space {
	
	private List<Space> joinedSpaces;
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
		joinedSpaces = new ArrayList<Space>();
	}

	//JOINED SPACES METHODS  =======================================================================
	public boolean isJoined(Space s){
		boolean ret = false;
				
		for(Space y : joinedSpaces)
		{
			if(s == y)
				ret = true;
		}

		return ret;
	}

	public void join(Space s){
		//check if tile is already joined
		if(joinedSpaces.indexOf(s) == -1)
			joinedSpaces.add(s);
	}


	public void remove(Space s){
		joinedSpaces.remove(s);
	}


	//NEIGHBOR METHODS =======================================================================
	public Grid<Space> getGrid(){
		return neighbors;
	}
	
	public void addNeighbor(Space s){
		if(neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}
	
	public Space getTop(){
		return neighbors.get(4);
	}
	
	public Space getBottom(){
		return neighbors.get(2);
	}
	
	public Space getRight(){
		return neighbors.get(1);
	}
	
	public Space getLeft(){
		return neighbors.get(3);
	}
	
	
	//TILE METHODS =========================================================================
	
	public int getHeight(){
		return tiles.size();
	}
	
	public void placeTile(Tile t){
		tiles.add(t);
	}
	
	public void removeTile(){
		tiles.pop();
	}
}