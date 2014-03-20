import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**mostly for mark-ups as of yet.  need more model to view.
 * needs work to create buffer.*/
public class BoardView extends JPanel{
	
	private static final long serialVersionUID = 5165000078001880297L;
	
	BufferedImage defaultTexture;
	Image dirt;
	Image rice;
	Image village;
	
	int tileWidth=32;
	int tileHeight=32;
	
	BufferedImage cachedCanvas;
	Graphics2D cachedGraphics;
	
	/**creates a new board view with the given height and width (in Tiles)
	 * Obeys LOD (given that createGraphics does not return an attribute of BufferedImage,
	 * but rather, and interface to it)*/
	public BoardView(int boardWidth, int boardHeight){
		createDefaultTexture();
		dirt=getTexture("images/dirt.png");
		rice=getTexture("images/rice.png");
		rice=getTexture("images/village.png");
		cachedCanvas=new BufferedImage(dirt.getWidth(this)*boardWidth, dirt.getHeight(this)*boardHeight, BufferedImage.TYPE_INT_RGB);
		cachedGraphics=cachedCanvas.createGraphics();
	}
	
	private Image getTexture(String texturePath){
		Image returned=defaultTexture;
		try{
			returned = ImageIO.read(new File(texturePath));
		} catch (IOException e){
			e.printStackTrace();
		}
		return returned;
	}
	
	private void createDefaultTexture(){
		defaultTexture=new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D defaultGraphics = defaultTexture.createGraphics();
		defaultGraphics.setColor(Color.black);
		defaultGraphics.drawRect(0, 0, tileWidth, tileHeight);
	}
	
	@Override
	/**draw this particular canvas
	 * Obeys LOD*/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cachedCanvas, 0, 0, this);
	}
	
	
	public void renderBoard(Board b){
		System.out.println("rendering board");
		renderFullBoard(b.head, b);
	}
	
	public void renderFullBoard(Space origin, Board b){
		ArrayList<Space> alreadyRendered=new ArrayList<Space>();
		renderFullBoardRecursive(cachedGraphics,alreadyRendered, origin, 1, 1, b);
	}
	
	public void renderFullBoardRecursive(Graphics g, AbstractList<Space> finished, Space origin, int x, int y, Board b){
		System.out.println("render tile");
		renderSpace(g, origin, x, y);
		Board.Coordinates coord = b.new Coordinates(x, y);
		Developer dev = b.getDeveloper(coord);
		if (dev!=null){
			renderDeveloper(dev, x, y);
		}
		if (origin.getTop()!=null&&!finished.contains(origin.getTop())){
			finished.add(origin.getTop());
			renderFullBoardRecursive(g,finished,origin.getTop(),x,y-1,b);
		}
		if (origin.getLeft()!=null&&!finished.contains(origin.getLeft())){
			finished.add(origin.getLeft());
			renderFullBoardRecursive(g,finished,origin.getLeft(),x-1,y,b);
		}
		if (origin.getRight()!=null&&!finished.contains(origin.getRight())){
			finished.add(origin.getRight());
			renderFullBoardRecursive(g,finished,origin.getRight(),x+1,y,b);
		}
		if (origin.getBottom()!=null&&!finished.contains(origin.getBottom())){
			finished.add(origin.getBottom());
			renderFullBoardRecursive(g,finished,origin.getBottom(),x,y+1,b);
		}
	}
	
	/**recursively renders the given space at it's given location, 
	 * along with all of the spaces connected to it.*/
	public void renderNetwork(Space origin, int x, int y){
		ArrayList<Space> alreadyRendered=new ArrayList<Space>();
		renderNetworkRecursive(cachedGraphics,alreadyRendered, origin, x, y);
	}
	
	/**recursively renders a network of spaces*/
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
	 * use renderNetwork. Obeys LOD*/
	public void renderSpace(Space s, int x, int y){
		renderSpace(cachedGraphics,s, x, y);
	}
	
	protected void renderSpace(Graphics g, Tile t, int height, int x, int y, boolean[] directions){
		Image tileFace=dirt;
		if (height>0&&t.getType()==TileType.RICE){
			tileFace=rice;
		}
		
		g.setColor(Color.white);
		g.drawImage(tileFace, x*tileWidth, y*tileHeight, this);
		renderText(g, ""+height, x*tileWidth+12, y*tileHeight+12);
	}
	
	/**Renders a space at the given x & y board coordinates.
	 * Obeys LOD*/
	protected void renderSpace(Graphics g, Space s,int x, int y){
		Image tileFace=dirt;
		if (s.getHeight()>0&&s.getTile().getType()==TileType.RICE){
			tileFace=rice;
		}
		
		g.setColor(Color.white);
		g.drawImage(tileFace, x*tileWidth, y*tileHeight, this);
		renderText(g, ""+s.getHeight(), x*tileWidth+12, y*tileHeight+12);
	}
	
	/**calls g.drawstring, but gives a small black outline
	 * Obeys LOD*/
	private void renderText(Graphics g, String s, int x, int y){
		g.setColor(Color.black);
		g.drawString(s, x-1, y);
		g.drawString(s, x+1, y);
		g.drawString(s, x, y-1);
		g.drawString(s, x, y+1);
		
		g.setColor(Color.white);
		g.drawString(s, x, y);
	}
	
	/**Obeys LOD*/
	public void hilightPath(ArrayList<Integer> x,ArrayList<Integer> y, Color c){
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		for (int i=0;i<x.size();i++){
			cachedGraphics.setColor(transparentColor);
			cachedGraphics.drawRect(x.get(i)*tileWidth, y.get(i)*tileHeight, tileWidth, tileHeight);
			cachedGraphics.setColor(c);
			cachedGraphics.drawRect(x.get(i)*tileWidth, y.get(i)*tileHeight, tileWidth, tileHeight);
		}
	}
	
	/**Obeys LOD*/
	public void hilightTile(int x, int y, Color c){
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		cachedGraphics.setColor(transparentColor);
		cachedGraphics.fillRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
		cachedGraphics.setColor(c);
		cachedGraphics.drawRect(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
	}

	public void renderDeveloper(Developer dev, int x, int y){
		cachedGraphics.setColor(Color.red);
		cachedGraphics.fillOval(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
	}
}
