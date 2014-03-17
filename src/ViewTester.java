//package view;

import java.io.IOException;

import javax.swing.JFrame;

public class ViewTester {
	
	public static void main(String[] args) throws IOException {
		BoardView boardView=new BoardView();
		
		JFrame mainFrame=new JFrame("Java");
		mainFrame.add(boardView);
		mainFrame.setVisible(true);
	}
}
