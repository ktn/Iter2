

import java.io.IOException;

import javax.swing.JFrame;

public class ViewTester {
	
	public static class TestableBoard extends Board{
		public TestableBoard(){
			super();
		}
		
		public Space getOrigin(){
			return head;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		TestableBoard b=new TestableBoard();
		
		BoardView boardView=new BoardView(12,12);
		
		JFrame mainFrame=new JFrame("Java");
		mainFrame.setBounds(0, 0, 800, 600);
		mainFrame.add(boardView);
		boardView.renderNetwork(b.getOrigin(), 1, 1);
		mainFrame.setVisible(true);
	}
}
