package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardView extends JPanel{

	Image dirt;
	Image tileSide;
	Image rice;
	
	int gridHeight;
	int gridWidth;
	
	public BoardView(int gridWidth, int gridHeight) throws IOException{
		dirt=ImageIO.read(new File("images/dirt.png"));
		rice=ImageIO.read(new File("images/rice.png"));
		tileSide=ImageIO.read(new File("images/dirtside.png"));
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
				}else if (r.nextBoolean()&&y<gridHeight-1){
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
				int bottomHeight=0;
				int leftHeight=0;
				if (x>0) leftHeight=heightMap[x-1][y];
				if (y>0) bottomHeight=heightMap[x][y-1];
				renderTile(g, randomTile, x, gridHeight-y, heightMap[x][y],leftHeight,bottomHeight);
			}
		}
	}
	
	private void renderTile(Graphics g, Image i, int x, int y, int height, int leftHeight, int bottomHeight){

		g.drawImage(tileSide, x*32, y*32, this);
		g.drawImage(i, x*32, y*32-height*8, this);
	}
}
