import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**mostly for mark-ups as of yet.  need more model to view.
 * needs work to create buffer.*/
public class BoardView extends JPanel{
	
	private static final long serialVersionUID = 5165000078001880297L;
	
	Image dirt;
	Image tileSide;
	Image rice;
	
	int tileWidth=32;
	int tileHeight=32;
	
	Board target;
	
	BufferedImage cachedCanvas;
	
	/**creates a new board view with the given height and width (in Tiles)*/
	public BoardView(int boardWidth, int boardHeight) throws IOException{
		dirt=ImageIO.read(new File("images/dirt.png"));
		rice=ImageIO.read(new File("images/rice.png"));
		cachedCanvas=new BufferedImage(dirt.getWidth(this)*boardWidth, dirt.getHeight(this)*boardHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	/**draw this particular canvas*/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cachedCanvas, 0, 0, this);
	}
	
	/**recursively renders the given space at it's given location, 
	 * along with all of the spaces connected to it.*/
	public void renderNetwork(Space origin, int x, int y){
		Graphics2D g = cachedCanvas.createGraphics();
		ArrayList<Space> alreadyRendered=new ArrayList<Space>();
		renderNetworkRecursive(g,alreadyRendered, origin, x, y);
	}
	
	protected void renderNetworkRecursive(Graphics g, AbstractCollection<Space> finished, Space origin, int x, int y){
		renderSpace(g, origin, x, y);
		if (origin.getLeft()!=null&&!finished.contains(origin.getLeft())){
			finished.add(origin.getLeft());
			renderNetworkRecursive(g,finished,origin.getLeft(),x-1,y);
		}
		if (origin.getRight()!=null&&!finished.contains(origin.getRight())){
			finished.add(origin.getRight());
			renderNetworkRecursive(g,finished,origin.getRight(),x+1,y);
		}
		if (origin.getTop()!=null&&!finished.contains(origin.getTop())){
			finished.add(origin.getTop());
			renderNetworkRecursive(g,finished,origin.getTop(),x,y-1);
		}
		if (origin.getBottom()!=null&&!finished.contains(origin.getBottom())){
			finished.add(origin.getBottom());
			renderNetworkRecursive(g,finished,origin.getBottom(),x,y+1);
		}
	}
	
	/**renders a single space.  To render a network of connected spaces, 
	 * use renderNetwork.*/
	public void renderSpace(Space s, int x, int y){
		Graphics2D g = cachedCanvas.createGraphics();
		renderSpace(g,s, x, y);
	}
	
	/**Renders a space at the given x & y board coordinates.*/
	protected void renderSpace(Graphics g, Space s,int x, int y){
		Image tileFace=dirt;
		if (s.getHeight()>0&&s.getTile().getType()==TileType.RICE){
			tileFace=rice;
		}
		g.setColor(Color.white);
		g.drawImage(tileFace, x*32, y*32, this);
		renderText(g, ""+s.getHeight(), x*32+12, y*32+12);
	}
	
	/**calls g.drawstring, but gives a small black outline*/
	private void renderText(Graphics g, String s, int x, int y){
		g.setColor(Color.black);
		g.drawString(s, x-1, y);
		g.drawString(s, x+1, y);
		g.drawString(s, x, y-1);
		g.drawString(s, x, y+1);
		
		g.setColor(Color.white);
		g.drawString(s, x, y);
	}
	
	public void hilightPath(ArrayList<Integer> x,ArrayList<Integer> y, Color c){
		Graphics2D g = cachedCanvas.createGraphics();
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		for (int i=0;i<x.size();i++){
			g.setColor(transparentColor);
			g.drawRect(x.get(i)*tileWidth, y.get(i)*tileHeight, tileWidth, tileHeight);
			g.setColor(c);
			g.drawRect(x.get(i)*tileWidth, y.get(i)*tileHeight, tileWidth, tileHeight);
		}
	}
	
	public void hilightTile(int x, int y, Color c){
		Graphics2D g = cachedCanvas.createGraphics();
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		g.setColor(transparentColor);
		g.fillRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
		g.setColor(c);
		g.drawRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
	}

}
