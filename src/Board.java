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

	public Developer getDeveloper(Coordinates c){
		Space temp = this.get(c);

		Developer ret = null;
		for(Developer d : devs){
			if(d.getSpace() == temp)
				ret = d;
		}

		return ret;
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
