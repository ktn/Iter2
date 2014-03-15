

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**mostly for markups as of yet.  need more model to view.*/
public class BoardView extends JPanel{

	Image dirt;
	Image tileSide;
	Image rice;
	
	int tileWidth;
	int tileHeight;
	
	Board target;
	
	BufferedImage cachedCanvas;
	
	public BoardView(Board board) throws IOException{
		dirt=ImageIO.read(new File("images/dirt.png"));
		rice=ImageIO.read(new File("images/rice.png"));
		target=board;
	}
	
	@Override
	/**draw this particular canvas*/
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		renderRow(g, target.get(0,0), 0);
		System.out.println("BoardView: row rendered");
	}
	
	//public void 
	
	private void renderRow(Graphics g, Space leftmost,int y){
		Space lefty=leftmost;
		int x=0;
		System.out.println("lefty:"+lefty);
		while (lefty!=null){
			renderSpace(g, lefty, x, y);
			x++;
			lefty=lefty.getRight();
			System.out.println("BoardView: space rendered");
		}
	}
	
	/**Renders a space at the given x & y board coordinates.*/
	private void renderSpace(Graphics g, Space s,int x, int y){
		Image tileFace=dirt;
		if (s.getHeight()>0&&s.getTile().getType()==Tile.TileType.RICE){
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
	
	private void renderTile(Graphics g, Image i, int x, int y, int height, int leftHeight, int topHeight,int rightHeight,int bottomHeight){

		
		int nuy = height*6;
		
		g.drawImage(tileSide, x*32, y*32, this);
		g.drawImage(i, x*32, y*32, this);
		
		g.setColor(new Color(0,0,0,0.25f));
		g.drawLine(x*32, y*32, x*32+31, y*32);
		g.drawLine(x*32, y*32+31, x*32+31, y*32+31);
		g.drawLine(x*32, y*32, x*32, y*32+31);
		g.drawLine(x*32+31, y*32, x*32+31, y*32+31);
		g.setColor(Color.white);
		
		int texx=16;
		int texy=16;
		
		/*
		g.setColor(Color.white);
		if (leftHeight!=height)g.drawLine(x*32, y*32, x*32, y*32+31);
		if (topHeight!=height)g.drawLine(x*32, y*32, x*32+31, y*32);
		if (rightHeight!=height)g.drawLine(x*32+31, y*32, x*32+31, y*32+31);
		if (bottomHeight!=height)g.drawLine(x*32, y*32+31, x*32+31, y*32+31);
		*/
	}
}
