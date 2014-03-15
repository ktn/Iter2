

import java.io.IOException;

import javax.swing.JFrame;

public class ViewTester {
	
	public static void main(String[] args) throws IOException {
		
		Board b=new Board();
		
		BoardView boardView=new BoardView(b);
		
		JFrame mainFrame=new JFrame("Java");
		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.add(boardView);
		mainFrame.setVisible(true);
	}
}
