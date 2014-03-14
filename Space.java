public class Space {
	
	private Grid<Space> joinedSpaces = new ArrayList<Space>();
	private List<Space> neighbors;
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
		neighbors = new Grid<Space>();
	}

	//joined spaces methods
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


	public boolean remove(Space s){
		joinedSpaces.remove(s);
	}


	//neighbor methods
	public void addNeighbor(Space s){
		if(neighbors.indexOf(s) == -1)
			neighbors.add(s);
	}

	//discuss how to implement neighbor traversal this might not be best
	public List<Space> getNeighbors(){
		return neighbors;
	}
}