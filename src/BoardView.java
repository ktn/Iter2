package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardView extends JPanel{

	Image dirt;
	Image rice;
	
	public BoardView() throws IOException{
		dirt=ImageIO.read(new File("images/dirt.png"));
	}
	
	@Override
	/**draw this particular canvas*/
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(dirt,0,0,this);
	}
}
