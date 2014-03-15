
public class Board {
	private Space head;
	int xDim = 10;
	int yDim = 10;
	
	/*
		Head is in the top left corner just fyi
	*/
	
	
	Board(){
		Space[][] temp = new Space[10][10];
		head = temp[0][0];
		
		for(int x = 0; x < xDim; x++)
		{
			for(int y = 0; y < yDim; y++)
			{
				if(x != 0){
					temp[x][y].join(temp[x-1][y]);
				}
				
				if(y != 0){
					temp[x][y].join(temp[x][y-1]);
				}
				
				if(x != xDim - 1){
					temp[x][y].join(temp[x+1][y]);
				}
				
				if(y != xDim - 1){
					temp[x][y].join(temp[x][y+1]);
				}
				
			}
		}
	}
	
	public Space get(int x, int y){
		Space temp = null;
		
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
	
	public void placeBlock(Block b, int x, int y){
		Space target = this.get(x,y);
		target.placeTile(b.getTile());
		
		Grid<Tile> tiles = b.getTile().getGrid();
		Grid<Space> spaces = target.getGrid();
		
		//assumes error checked
		//can implement error checking though
		for(int i = 0; i < spaces.size(); i++)
		{
			if(spaces.get(i) != null){
				if(tiles.get(i) != null){
					spaces.get(i).placeTile(tiles.get(i));
				}
				else{
					//error stuff?
				}
			}
		}
		
	}
	
	
	public int getHeight(int x, int y){
		return this.get(x, y).getHeight();
	}
}
