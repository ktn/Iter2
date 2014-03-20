import java.util.ArrayList;


public class Wavefront {

	public int totalCost;
	
	public ArrayList<Board.Coordinates> wavefront(Board.Coordinates start,Board.Coordinates end, Board b){
		int width = b.getLargest().x+1;
		int height = b.getLargest().y+1;
		
		if (end.x==start.x&&end.y==start.y){
			return new ArrayList<Board.Coordinates>();
		}
		
		int [][] costs= new int[width][height];
		

		for (int x=0;x<width;x++){
			for (int y=0;y<height;y++){
				costs[x][y]=99;
			}
		}
		
		costs[end.x][end.y]=1;
		
		boolean searching = true;
		
		//populate wavefront
		
		while (searching){
			searching=false;
			for (int x=0;x<width;x++){
				for (int y=0;y<height;y++){
					searching = searching | spreadCost(b.new Coordinates(x, y), 0, 1, b, costs)==1;
					searching = searching | spreadCost(b.new Coordinates(x, y), 0, -1, b, costs)==1;
					searching = searching | spreadCost(b.new Coordinates(x, y), 1, 0, b, costs)==1;
					searching = searching | spreadCost(b.new Coordinates(x, y), -1, 0, b, costs)==1;
				}
			}
		}
		
		ArrayList<Board.Coordinates> coords=new ArrayList<Board.Coordinates>();
		coords.add(start);
		totalCost=costs[start.x][start.y];
		
		printWaveFront(costs);
		
		//rebuild
		boolean ended=false;
		while (!ended){
			int startx=coords.get(coords.size()-1).x;
			int starty=coords.get(coords.size()-1).y;
			Board.Coordinates bestTarget=coords.get(coords.size()-1);
			int lowestValue=costs[bestTarget.x][bestTarget.y];
			if (inBounds(startx+1, starty, costs)&&costs[startx+1][starty]<lowestValue){
				lowestValue=costs[startx+1][starty];
				bestTarget=b.new Coordinates(startx+1, starty);
			}
			if (inBounds(startx-1, starty, costs)&&costs[startx-1][starty]<lowestValue){
				lowestValue=costs[startx-1][starty];
				bestTarget=b.new Coordinates(startx-1, starty);
			}
			if (inBounds(startx, starty+1, costs)&&costs[startx][starty+1]<lowestValue){
				lowestValue=costs[startx][starty+1];
				bestTarget=b.new Coordinates(startx, starty+1);
			}
			if (inBounds(startx, starty-1, costs)&&costs[startx][starty-1]<lowestValue){
				lowestValue=costs[startx][starty-1];
				bestTarget=b.new Coordinates(startx, starty-1);
			}
			if (bestTarget==coords.get(coords.size()-1)){
				return coords;
			}
			coords.add(bestTarget);
			if (bestTarget.x==end.x&&bestTarget.y==end.y){
				ended=true;
			}
		}
		return coords;
	}
	
	public static boolean inBounds(int x, int y, int[][] costs){
		return x>=0&&x<costs.length
				&&y>=0&&y<costs[0].length;
	}
	
	public static int spreadCost(Board.Coordinates start, int offx, int offy, Board b, int[][] costs){
		if (start.x+offx>=0&&start.x+offx<costs.length
				&&start.y+offy>=0&&start.y+offy<costs[0].length){
			Space sp1=b.get(b.new Coordinates(start.x+offx, start.y+offy));
			Space sp2=b.get(start);
			int cost=costs[start.x][start.y];
			if (sp1==null||sp2==null||sp1.getTile()==null||sp2.getTile()==null){
				return -1;
			}else if (sp1.getTile().getType()!=sp2.getTile().getType()){
				cost += 1;
			}
			if (costs[start.x+offx][start.y+offy]>cost){
				costs[start.x+offx][start.y+offy]=cost;
				return 1;
			}
		}
		return 0;
	}
	
	public void printWaveFront(int[][] costs){
		for (int y=0; y<costs[0].length;y++ ){
			for (int x=0;x<costs.length;x++){
				System.out.print("\t"+costs[x][y]);
			}
			System.out.println();
		}
	}
}
