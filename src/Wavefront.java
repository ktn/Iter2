import java.util.ArrayList;


public class Wavefront {

	public int totalCost;
	
	public ArrayList<Board.Coordinates> wavefront(Board.Coordinates start,Board.Coordinates end, Board b){
		int width = b.getLargest().x+1;
		int height = b.getLargest().y+1;
		
		int [][] costs= new int[width][height];
		

		for (int x=0;x<width;x++){
			for (int y=0;y<height;y++){
				costs[x][y]=Integer.MAX_VALUE;
			}
		}
		
		costs[end.x][end.y]=1;
		
		boolean searching = true;
		
		//populate wavefront
		
		while (searching){
			searching=false;
			for (int x=0;x<width;x++){
				for (int y=0;y<height;y++){
					searching = searching | spreadCost(b.new Coordinates(x, y), 0, 1, b, costs);
					searching = searching | spreadCost(b.new Coordinates(x, y), 0, -1, b, costs);
					searching = searching | spreadCost(b.new Coordinates(x, y), 1, 0, b, costs);
					searching = searching | spreadCost(b.new Coordinates(x, y), -1, 0, b, costs);
				}
			}
		}
		
		ArrayList<Board.Coordinates> coords=new ArrayList<Board.Coordinates>();
		coords.add(start);
		totalCost=costs[start.x][start.y];
		
		//rebuild
		boolean ended=false;
		while (!ended){
			int startx=coords.get(coords.size()-1).x;
			int starty=coords.get(coords.size()-1).y;
			Board.Coordinates bestTarget=null;
			int lowestValue=Integer.MAX_VALUE;
			if (b.inBounds(startx+1, starty)&&costs[startx+1][starty]<lowestValue){
				lowestValue=costs[startx+1][starty];
				bestTarget=b.new Coordinates(startx+1, starty);
			}
			if (b.inBounds(startx-1, starty)&&costs[startx-1][starty]<lowestValue){
				lowestValue=costs[startx-1][starty];
				bestTarget=b.new Coordinates(startx-1, starty);
			}
			if (b.inBounds(startx, starty+1)&&costs[startx][starty+1]<lowestValue){
				lowestValue=costs[startx][starty+1];
				bestTarget=b.new Coordinates(startx, starty+1);
			}
			if (b.inBounds(startx, starty-1)&&costs[startx][starty-1]<lowestValue){
				lowestValue=costs[startx][starty-1];
				bestTarget=b.new Coordinates(startx, starty-1);
			}
			coords.add(bestTarget);
			if (bestTarget.x==end.x&&bestTarget.y==end.y){
				ended=true;
			}
		}
		return coords;
	}
	
	public static boolean spreadCost(Board.Coordinates start, int offx, int offy, Board b, int[][] costs){
		if (b.inBounds(start.x+offx, start.y+offy)){
			Space sp1=b.get(b.new Coordinates(start.x+offx, start.y+offy));
			Space sp2=b.get(start);
			int cost=costs[start.x][start.y];
			if (sp1.getTile().getType()!=sp2.getTile().getType()){
				cost += 1;
			}
			if (costs[start.x+offx][start.y+offy]>cost){
				costs[start.x+offx][start.y+offy]=cost;
				return true;
			}
		}
		return false;
	}
}
