import java.util.*;

public class Board {
	private Space head;
	private int xDim = 10;
	private int yDim = 10;
	private ArrayList<Developer> devs;

	/*
		Head is in the bottom left corner just fyi
	*/

	//CONSTRUCTOR  =======================================================================

	Board(){
		Space[][] temp = new Space[xDim][yDim];
		for(int i = 0; i < xDim; i++)
		{
			for(int j = 0; j < yDim; j++)
			{

				temp[i][j] = new Space();
			}
		}

		head = temp[0][0];

		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				if(x != 0){
					//Left
					temp[x][y].join(2 , temp[x-1][y]);
				}

				if(y != 0){
					//Top
					temp[x][y].join(1 , temp[x][y-1]);
				}

				if(x != xDim - 1){
					//Right
					temp[x][y].join(0 , temp[x+1][y]);
				}

				if(y != yDim - 1){
					//Bottom
					temp[x][y].join(3 , temp[x][y+1]);

				}

			}
		}

		devs = new ArrayList<Developer>();
	}


	// ACCESSORS
	// =======================================================================
	// All methods from here down assume that inbounds indices are passed
	private Space get(Coordinates c) {
		Space temp = head;

		if (c.x < xDim) {
			if (c.y < yDim) {
				for (int i = 0; i < c.x; i++) {
					temp = temp.getRight();
				}
				for (int j = 0; j < c.y; j++) {
					temp = temp.getBottom();
				}
			}
		}
		return temp;

	}

	public int getHeight(Coordinates c) {
		return this.get(c).getHeight();
	}

	public Tile getTile(Coordinates c) {
		return get(c).getTile();
	}

	public TileType getTileType(Coordinates c) {
		Space temp = this.get(c);

		if (temp == null)
			System.out.print("fjakshdflkjahsfd");
		if (temp.getHeight() != 0)
			return temp.getTile().getType();
		else
			return TileType.EMPTY;
	}

	public Coordinates getLargest() {
		return new Coordinates(xDim - 1, yDim - 1);
	}

	// BLOCK METHODS
	// =======================================================================

	public void placeBlock(Block b, Coordinates c) {
		Space target = this.get(c);
		//System.out.println("Board placement");
		target.placeTile(b.getTile());

	}


	public void removeBlock(Coordinates c) {
		Space target = this.get(c);
		target.removeTile();
	}

	// CHECKING METHODS  
	// =======================================================================
	public boolean inBounds(int x, int y){
		return x <= xDim || y <= yDim;
	}

	public boolean inBounds(Coordinates c){
		return c.x <= xDim || c.y <= yDim;
	}

	public boolean validPlacement(Block b, Coordinates c){
		boolean ret = true;
		Space target = this.get(c);
		

		if(target.getTileType() == IRRIGATION || target.getTileType() == PALACE){
			ret = false;
		}
		else{
			//indices of joined tiles in given block
			ArrayList<Integer> adjacentIndices = b.getTile().getJoined();
			//spaces that you are trying to also place blocks
			ArrayList<Space> adjacentSpaces = new ArrayList<Space>();

			//check for level spaces
			for(Integer i : adjacentIndices){
				if(i == 0){
					//check for out of bounds
					if(c.x < xDim - 1)
						adjacentSpaces.add(this.get(c.x + 1, c.y));
					else
						throw new IllegalBlockPlacementException("Block out of bounds");
				}
				else if(i == 1){
					//check for out of bounds
					if(c.y < yDim - 1)
						adjacentSpaces.add(this.get(c.x, c.y + 1));
					else
						throw new IllegalBlockPlacementException("Block out of bounds");
				}
				else if(i == 2){
					//check for out of bounds
					if(c.x > 0)
						adjacentSpaces.add(this.get(c.x - 1, c.y));
					else
						throw new IllegalBlockPlacementException("Block out of bounds");
				}
				else if(i == 3){
					//check for out of bounds
					if(c.y > 0)
						adjacentSpaces.add(this.get(c.x, c.y - 1));
					else
						throw new IllegalBlockPlacementException("Block out of bounds");
				}
			}

			//adjacent spaces now filled

			//check for if it is a OneBlock
			if(!adjacentSpaces.size() == 0){
				//check for level spaces now
				//initialize new boolean for testing

				boolean levelSpaces = true;
				int height = adjacentSpaces.get(0).getHeight();
				for(Space s : adjacentSpaces){
					levelSpaces = levelSpaces && (s.getHeight() == height);
				}
				ret = levelSpaces && ret;
			}
			
			//check for same type of block
			if(adjacentSpaces.size() == target.getTile().getJoined()){
				//same type of tile
				//check for same rotation

				boolean diffRotation = false;
				//indices of block already places
				ArrayList<Integer> checkIndices = target.getTile.getJoined();
				for(int j = 0; j < adjacentSpaces.size(); j++){
					//if they dont match even once then placement is fine
					if(checkIndices.get(i) != adjacentIndices.get(i)){
						diffRotation = true;
					}
				}

				ret = ret && diffRotation;
			}
			
		}

		return ret;
	}

	public class Coordinates{
		public int x;
		public int y;

		Coordinates(int x , int y){
			if(!inBounds(x, y))
				throw new IllegalArgumentException("Out of Bounds arguements");

			this.x = x;
			this.y = y;
		}
	}

	// DEVELOPER METHODS  
	// =======================================================================

	public void placeDeveloper(Coordinates c, Developer d){
		Space temp = this.get(c);

		d.moveDeveloper(temp);

		devs.add(d);
	}

	public void moveDeveloper(Coordinates c, Developer d){
		Space temp = this.get(c);

		d.moveDeveloper(temp);
	}

	// HELPER METHODS  
	// =======================================================================

	public String toString() {
		StringBuilder result = new StringBuilder(100);
		String NEW_LINE = System.getProperty("line.separator");
		for (int i = 0; i < xDim; i++) {
			for (int j = 0; j < yDim; j++) {
				if (this.getTileType(new Coordinates(i, j)) == null) {
					result.append("N ");
				} else {
					result.append(this.getTileType(new Coordinates(i, j)) + "\t");
				}
			}
			result.append(NEW_LINE);
		}
		return result.toString();
	}
}
