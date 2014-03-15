package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
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
	
	int gridHeight;
	int gridWidth;
	
	public BoardView(int gridWidth, int gridHeight) throws IOException{
		dirt=ImageIO.read(new File("images/dirt.png"));
		rice=ImageIO.read(new File("images/rice.png"));
		this.gridWidth=gridWidth;
		this.gridHeight=gridHeight;
	}
	
	@Override
	/**draw this particular canvas*/
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(dirt,0,0,this);
		
		int heightMap[][]=new int[gridWidth][gridHeight];
		
		Random r=new Random();
		for (int y=gridHeight-1;y>=0;y--){
			for (int x=0;x<gridWidth;x++){
				int height = r.nextInt(3);
				if (r.nextBoolean()&&x>0){
					height=heightMap[x-1][y];
				}else if ((r.nextBoolean()||r.nextBoolean())&&y<gridHeight-1){
					height=heightMap[x][y+1];
				}
				heightMap[x][y]=height;
			}
		}
		

		for (int y=gridHeight-1;y>=0;y--){
			for (int x=0;x<gridWidth;x++){
				Image randomTile=rice;
				if (r.nextBoolean()){
					randomTile=dirt;
				}
				int topHeight=0;
				int bottomHeight=0;
				int leftHeight=0;
				int rightHeight=0;
				if (x>0) leftHeight=heightMap[x-1][y];
				if (x<gridWidth-1) rightHeight = heightMap[x+1][y];
				if (y<gridHeight-1) topHeight=heightMap[x][y+1];
				if (y>0)bottomHeight=heightMap[x][y-1];
				renderTile(g, randomTile, x, gridHeight-y, heightMap[x][y],leftHeight,topHeight,rightHeight,bottomHeight);
			}
		}
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
		g.setColor(Color.black);
		g.drawString(""+height, x*32+texx-1, y*32+texy);
		g.drawString(""+height, x*32+texx+1, y*32+texy);
		g.drawString(""+height, x*32+texx, y*32+texy-1);
		g.drawString(""+height, x*32+texx, y*32+texy+1);
		
		g.setColor(Color.white);
		g.drawString(""+height, x*32+texx, y*32+texy);
		/*
		g.setColor(Color.white);
		if (leftHeight!=height)g.drawLine(x*32, y*32, x*32, y*32+31);
		if (topHeight!=height)g.drawLine(x*32, y*32, x*32+31, y*32);
		if (rightHeight!=height)g.drawLine(x*32+31, y*32, x*32+31, y*32+31);
		if (bottomHeight!=height)g.drawLine(x*32, y*32+31, x*32+31, y*32+31);
		*/
	}
}
