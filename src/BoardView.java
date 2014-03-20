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
	Image developer;
	Image mountians;
	Image lowlands;
	
	protected int TILE_WIDTH=64;
	protected int TILE_HEIGHT=64;
	
	protected int boardWidth;
	protected int boardHeight;
	
	BufferedImage cachedCanvas;
	Graphics2D cachedGraphics;
	
	/**a cached copy of the heights of the spaces*/
	int[][] spaceHeights;
	
	/**creates a new board view with the given height and width (in Tiles)
	 * Obeys LOD (given that createGraphics does not return an attribute of BufferedImage,
	 * but rather, and interface to it)*/
	public BoardView(int boardWidth, int boardHeight){
		spaceHeights=new int[boardWidth][boardHeight];
		createDefaultTexture();
		dirt=getTexture("images/dirt.png");
		rice=getTexture("images/rice.png");
		village=getTexture("images/village.png");
		palace=getTexture("images/palace.png");
		developer = getTexture("images/developer.png");
		lowlands=getTexture("images/dirtside.png");
		mountians=getTexture("images/Mountain.png");
		this.boardWidth=boardWidth;
		this.boardHeight=boardHeight;
		initGraphics();
	}
	
	private void initGraphics(){
		cachedCanvas=new BufferedImage(TILE_WIDTH*(boardWidth+2), TILE_HEIGHT*(boardHeight+2), BufferedImage.TYPE_INT_RGB);
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
		defaultGraphics.fillRect(0, 0, TILE_WIDTH, TILE_HEIGHT);
		defaultGraphics.setColor(Color.magenta);
		defaultGraphics.fillRect(0, 0, TILE_WIDTH/2, TILE_HEIGHT/2);
		defaultGraphics.fillRect(TILE_WIDTH/2, TILE_HEIGHT/2, TILE_WIDTH/2, TILE_HEIGHT/2);
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
			TILE_WIDTH=this.getWidth()/(boardWidth+2);
			TILE_HEIGHT=this.getWidth()/(boardHeight+2);
			initGraphics();
		}
		renderFullBoard(b.head, b);
		getParent().repaint();
	}
	
	public void renderFullBoard(Space origin, Board b){
		for (int x = 0; x<boardWidth;x++){
			if (x==0){
				for (int y = 0; y<boardHeight;y++){
					
				}
			}else if (x==boardWidth-1){
				for (int y = 0; y<boardHeight;y++){
					
				}
			}else{
				
			}
		}
		ArrayList<Space> alreadyRendered=new ArrayList<Space>();
		renderFullBoardRecursive(cachedGraphics,alreadyRendered, origin, 0, 0, b);
	}
	
	private void renderFullBoardRecursive(Graphics g, AbstractList<Space> finished, Space origin, int x, int y, Board b){
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
		spaceHeights[x][y]=givenHeight;
		renderText(g, ""+givenHeight, (x+1)*TILE_WIDTH, (y+1)*TILE_HEIGHT+g.getFont().getSize());
	}
	
	
	/**recursively renders the given space at it's given location, 
	 * along with all of the spaces connected to it.*/
	public void renderNetwork(Tile origin, int x, int y){
		renderNetwork(origin, x, y, new Color(0,0,0,0));
	}
	
	/**recursively renders the given space at it's given location, 
	 * along with all of the spaces connected to it.*/
	public void renderNetwork(Tile origin, int x, int y, Color hilight){
		ArrayList<Tile> alreadyRendered=new ArrayList<Tile>();
		renderNetworkRecursive(cachedGraphics,alreadyRendered, origin, x, y, hilight);
	}
	
	/**recursively renders a network of spaces*/
	protected void renderNetworkRecursive(Graphics g, AbstractCollection<Tile> finished, Tile origin, int x, int y, Color hilight){
		int dummyHeight=0;
		
		if (x>=0&&x<boardWidth&&y>=0&&y<boardHeight){
			dummyHeight=spaceHeights[x][y];
		}
		renderSpace(g, origin, dummyHeight, x, y);
		g.setColor(hilight);
		g.fillRect((x+1)*TILE_WIDTH, (y+1)*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		if (x>=0&&x<boardWidth&&y>=0&&y<boardHeight){
			renderText(g, Integer.toString(spaceHeights[x][y]), (x+1)*TILE_WIDTH, (y+1)*TILE_HEIGHT+g.getFont().getSize());
		}
		if (origin.getJoined(Grid.LEFT)!=null&&!finished.contains(origin.getJoined(Grid.LEFT))){
			finished.add(origin.getJoined(Grid.LEFT));
			renderNetworkRecursive(g,finished,origin.getJoined(Grid.LEFT),x-1,y, hilight);
		}
		if (origin.getJoined(Grid.RIGHT)!=null&&!finished.contains(origin.getJoined(Grid.RIGHT))){
			finished.add(origin.getJoined(Grid.RIGHT));
			renderNetworkRecursive(g,finished,origin.getJoined(Grid.RIGHT),x+1,y, hilight);
		}
		if (origin.getJoined(Grid.TOP)!=null&&!finished.contains(origin.getJoined(Grid.TOP))){
			finished.add(origin.getJoined(Grid.TOP));
			renderNetworkRecursive(g,finished,origin.getJoined(Grid.TOP),x,y-1, hilight);
		}
		if (origin.getJoined(Grid.BOTTOM)!=null&&!finished.contains(origin.getJoined(Grid.BOTTOM))){
			finished.add(origin.getJoined(Grid.BOTTOM));
			renderNetworkRecursive(g,finished,origin.getJoined(Grid.BOTTOM),x,y+1, hilight);
		}
	}
	
	/**renders a single space.  To render a network of connected spaces, 
	 * use renderNetwork. Obeys LOD*/
	public void renderSpace(Space s, int x, int y){
		renderSpace(cachedGraphics,s, x, y);
	}
	
	protected void renderSpace(Graphics g, Tile t, int height, int inx, int iny){
		int x = inx+1;
		int y = iny+1;
		
		Image tileFace=dirt;
		
		if (t!=null){
			if (t.getType()==TileType.RICE){
				tileFace=rice;
			}
			if (t.getType()==TileType.VILLAGE){
				tileFace=village;
			}
		}
		
		g.setColor(Color.white);
		g.drawImage(tileFace, x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH,TILE_HEIGHT, this);
		
		int givenHeight=height;
		
		if (t!=null){
			g.setColor(Color.black);
			if (t.getJoined(Grid.TOP)==null){
				g.drawLine((x)*TILE_WIDTH, (y)*TILE_HEIGHT, (x+1)*TILE_WIDTH-1, (y)*TILE_HEIGHT);
				g.drawLine((x)*TILE_WIDTH, (y)*TILE_HEIGHT+1, (x+1)*TILE_WIDTH-1, (y)*TILE_HEIGHT+1);
			}
			if (t.getJoined(Grid.BOTTOM)==null){
				g.drawLine((x)*TILE_WIDTH, (y+1)*TILE_HEIGHT-1, (x+1)*TILE_WIDTH-1, (y+1)*TILE_HEIGHT-1);
				g.drawLine((x)*TILE_WIDTH, (y+1)*TILE_HEIGHT-2, (x+1)*TILE_WIDTH-1, (y+1)*TILE_HEIGHT-2);
			}
			if (t.getJoined(Grid.LEFT)==null){
				g.drawLine((x)*TILE_WIDTH, (y)*TILE_HEIGHT, (x)*TILE_WIDTH, (y+1)*TILE_HEIGHT-1);
				g.drawLine((x)*TILE_WIDTH+1, (y)*TILE_HEIGHT, (x)*TILE_WIDTH+1, (y+1)*TILE_HEIGHT-1);
			}
			if (t.getJoined(Grid.RIGHT)==null){
				g.drawLine((x+1)*TILE_WIDTH-1, (y)*TILE_HEIGHT, (x+1)*TILE_WIDTH-1, (y+1)*TILE_HEIGHT-1);
				g.drawLine((x+1)*TILE_WIDTH-2, (y)*TILE_HEIGHT, (x+1)*TILE_WIDTH-2, (y+1)*TILE_HEIGHT-1);
			}
		}
		
		if (height>0&&t.getType()==TileType.PALACE){
			givenHeight=((PalaceTile)t).getLevel();
			renderPalaceRecursive(g, givenHeight/2, x*TILE_WIDTH, y*TILE_WIDTH, TILE_WIDTH, TILE_HEIGHT);
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
		//offset for edge
		x++;
		y++;
		Color transparentColor=new Color(c.getRed(),c.getGreen(),c.getBlue(),63);
		cachedGraphics.setColor(transparentColor);
		cachedGraphics.fillRect(x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
		cachedGraphics.setColor(c);
		cachedGraphics.drawRect(x*TILE_WIDTH, y*TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
	}

	public void renderDeveloper(Developer dev, int x, int y){
		//offset for edge
		x++;
		y++;
		cachedGraphics.setColor(Color.red);
		cachedGraphics.fillOval(x*TILE_WIDTH+TILE_WIDTH/4, y*TILE_HEIGHT, TILE_WIDTH/2, TILE_HEIGHT/4);
		cachedGraphics.fillRect(x*TILE_WIDTH+TILE_WIDTH/4, y*TILE_HEIGHT+TILE_HEIGHT/8, TILE_WIDTH/2, TILE_HEIGHT*3/4);
		cachedGraphics.fillOval(x*TILE_WIDTH+TILE_WIDTH/4, y*TILE_HEIGHT+TILE_HEIGHT*3/4, TILE_WIDTH/2, TILE_HEIGHT/4);
		cachedGraphics.drawImage(developer, x*TILE_WIDTH-1, y*TILE_HEIGHT-1, TILE_WIDTH,TILE_HEIGHT, this);
	}
	
	public Board.Coordinates coordFromPixel(int x, int y, Board b){
		int x2=x/(getWidth()/(boardWidth+2))-1;
		int y2=y/(getHeight()/(boardHeight+2))-1;
		if (b.inBounds(x2, y2)){
			return b.new Coordinates(x2,y2);
		}else {
			return null;
		}
	}
}
