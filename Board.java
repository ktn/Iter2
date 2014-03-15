
public class Board {
	private Space head;
	private int xDim = 10;
	private int yDim = 10;
	
	/*
		Head is in the top left corner just fyi
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
					//Bottom
					temp[x][y].join(1 , temp[x][y-1]);
				}
				
				if(x != xDim - 1){
					//Right
					temp[x][y].join(0 , temp[x+1][y]);
				}
				
				if(y != yDim - 1){
					//Top
					temp[x][y].join(3 , temp[x][y+1]);
				}
				
			}
		}
	}
	
	//ACCESSORS  =======================================================================
	//All methods from here down assume that inbounds indices are passed
	private Space get(int x, int y){
		Space temp = head;
		
		if(x < xDim){
			if(y < yDim){
				for(int i = 0; i < x; i++){
					temp = temp.getRight();
				}
				for(int j = 0; j < y; j++){
					temp = temp.getBottom();
				}
			}
		}
		
		return temp;
		
	}

	public int getHeight(int x, int y){
		return this.get(x, y).getHeight();
	}

	public Tile getTile(int x, int y){
		return this.get(x,y).getTile();
	}
	
	public TileType getTileType(int x, int y){
		Space temp = this.get(x, y);

		if(temp.getHeight() == 0)
			return temp.getTile().getType();
		else
			return TileType.EMPTY;
	}

	//BLOCK METHODS  =======================================================================

	public void placeBlock(Block b, int x, int y){
		Space target = this.get(x,y);
		target.placeTile(b.getTile());
		
	}

	public void removeBlock(int x, int y){
		Space target = this.get(x,y);
		target.removeTile();
	}


	//CHECKING METHODS  =======================================================================
	public boolean inBounds(int x, int y){
		return x <= xDim || y <= yDim;
	}
	
}
