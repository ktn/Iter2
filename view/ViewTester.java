package view;

import java.io.IOException;

import javax.swing.JFrame;

public class ViewTester {
	
	public static void main(String[] args) throws IOException {
		BoardView boardView=new BoardView(16,16);
		
		JFrame mainFrame=new JFrame("Java");
		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.add(boardView);
		mainFrame.setVisible(true);
	}
}
