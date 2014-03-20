import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
	Image palace;
	
	protected int TILE_WIDTH=64;
	protected int TILE_HEIGHT=64;
	
	protected int boardWidth;
	protected int boardHeight;
	
	BufferedImage cachedCanvas;
	Graphics2D cachedGraphics;
	
	/**creates a new board view with the given height and width (in Tiles)
	 * Obeys LOD (given that createGraphics does not return an attribute of BufferedImage,
	 * but rather, and interface to it)*/
	public BoardView(int boardWidth, int boardHeight){
		createDefaultTexture();
		dirt=getTexture("images/dirt.png");
		rice=getTexture("images/rice.png");
		village=getTexture("images/village.png");
		palace=getTexture("images/palace.png");
		this.boardWidth=boardWidth;
		this.boardHeight=boardHeight;
		initGraphics();
	}
	
	private void initGraphics(){
		cachedCanvas=new BufferedImage(TILE_WIDTH*boardWidth, TILE_HEIGHT*boardHeight, BufferedImage.TYPE_INT_RGB);
		cachedGraphics=cachedCanvas.createGraphics();
		cachedGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font=new Font(Font.SANS_SERIF, Font.PLAIN, TILE_HEIGHT*3/8);
		cachedGraphics.setFont(font);
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
		defaultTexture=new BufferedImage(TILE_WIDTH, TILE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D defaultGraphics = defaultTexture.createGraphics();
		defaultGraphics.setColor(Color.black);
		defaultGraphics.drawRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
		defaultGraphics.setColor(Color.magenta);
		defaultGraphics.drawRect(0, 0, TILE_WIDTH/2, TILE_HEIGHT/2);
		defaultGraphics.drawRect(TILE_WIDTH/2, TILE_HEIGHT/2, TILE_WIDTH/2, TILE_HEIGHT/2);
	}
	
	@Override
	/**draw this particular canvas
	 * Obeys LOD*/
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(cachedCanvas, 0, 0,this.getWidth(),this.getHeight(), this);
	}
	
	
	public void renderBoard(Board b){
		if (this.getWidth()!=cachedCanvas.getWidth()||
				this.getHeight()!=cachedCanvas.getHeight()){
			TILE_WIDTH=this.getWidth()/boardWidth;
			TILE_HEIGHT=this.getWidth()/boardHeight;
			initGraphics();
		}
		renderFullBoard(b.head, b);
	}
	
	public void renderFullBoard(Space origin, Board b){
		ArrayList<Space> alreadyRendered=new ArrayList<Space>();
		renderFullBoardRecursive(cachedGraphics,alreadyRendered, origin, 1, 1, b);
	}
	
	public void renderFullBoardRecursive(Graphics g, AbstractList<Space> finished, Space origin, int x, int y, Board b){
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
		int givenHeight=origin.getHeight();
		if (origin.getHeight()>0&&origin.getTile().getType()==TileType.PALACE){
			givenHeight=((PalaceTile)origin.getTile()).getLevel();
		}
		renderText(g, ""+givenHeight, x*TILE_WIDTH, y*TILE_HEIGHT+g.getFont().getSize());
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
	
	protected void renderSpace(Graphics g, Tile t, int height, int x, int y){
		Image tileFace=dirt;
		
		if (height>0&&t.getType()==TileType.RICE){
			tileFace=rice;
		}
		
		g.setColor(Color.white);
		g.drawImage(tileFace, x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH,TILE_HEIGHT, this);
		
		int givenHeight=height;
		
		if (height>0){
			g.setColor(Color.black);
			if (t.getJoined(Grid.TOP)==null){
				g.drawLine((x)*TILE_WIDTH, (y)*TILE_HEIGHT, (x+1)*TILE_WIDTH, (y)*TILE_HEIGHT);
			}
			if (t.getJoined(Grid.BOTTOM)==null){
				g.drawLine((x)*TILE_WIDTH, (y+1)*TILE_HEIGHT, (x+1)*TILE_WIDTH, (y+1)*TILE_HEIGHT);
			}
			if (t.getJoined(Grid.TOP)==null){
				g.drawLine((x)*TILE_WIDTH, (y)*TILE_HEIGHT, (x)*TILE_WIDTH, (y+1)*TILE_HEIGHT);
			}
			if (t.getJoined(Grid.TOP)==null){
				g.drawLine((x+1)*TILE_WIDTH, (y)*TILE_HEIGHT, (x+1)*TILE_WIDTH, (y+1)*TILE_HEIGHT);
			}
		}
		
		if (height>0&&t.getType()==TileType.PALACE){
			givenHeight=((PalaceTile)t).getLevel();
			renderPalaceRecursive(g, givenHeight, x*TILE_WIDTH, y*TILE_WIDTH, TILE_WIDTH, TILE_HEIGHT);
			if (!((PalaceTile)t).isHeadsUp()){
				g.setColor(new Color(0,0,0,0.5f));
				g.fillRect(x*TILE_WIDTH, y*TILE_WIDTH, TILE_WIDTH, TILE_HEIGHT);
			}
			
		}
	}
	
	public void renderPalaceRecursive(Graphics g, int layers, double x, double y, double width, double height){
		if (layers>0){
			g.drawImage(palace, (int)x, (int)y, (int)width, (int)height, this);
			renderPalaceRecursive(g,layers-1,x+width*0.1,y,width*0.8,height*0.7);
		}
	}
	
	/**Renders a space at the given x & y board coordinates.
	 * Obeys LOD*/
	protected void renderSpace(Graphics g, Space s,int x, int y){
		renderSpace(g, s.getTile(), s.getHeight(), x, y);
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
			cachedGraphics.fillRect(x.get(i)*TILE_WIDTH, y.get(i)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
			cachedGraphics.setColor(c);
			cachedGraphics.drawRect(x.get(i)*TILE_WIDTH, y.get(i)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		}
	}
	
	/**Obeys LOD*/
	public void hilightTile(int x, int y, Color c){
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		cachedGraphics.setColor(transparentColor);
		cachedGraphics.fillRect(x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		cachedGraphics.setColor(c);
		cachedGraphics.drawRect(x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}

	public void renderDeveloper(Developer dev, int x, int y){
		cachedGraphics.setColor(Color.red);
		cachedGraphics.fillOval(x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}
}
