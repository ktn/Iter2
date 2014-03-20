

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

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
		viewFacadeTester();
		boardViewTester();
	}
	
	public static void viewFacadeTester(){
		BoardFacade board = new BoardFacade();
		ArrayList<String> names = new ArrayList<String>();
		names.add("steve");
		names.add("martin");
		ViewFacade view = new ViewFacade(names, 10 , 10);
		View viewer=new View(names);
		ViewFacade.updateBoard(board.board);
	}
	
	public static void boardViewTester(){
		TestableBoard b=new TestableBoard();
		 
		b.placeDeveloper(b.new Coordinates(3, 4), new Developer(new Player("king dude", "red")));
		Block block = new OneBlock(new PalaceTile(2));
		b.placeBlock(b.new Coordinates(5,5), block);
		BoardView boardView=new BoardView(12,12);
		JFrame mainFrame=new JFrame("BoardViewTest");
		mainFrame.setSize(384, 384);
		mainFrame.add(boardView);
		//boardView.renderNetwork(b.getOrigin(), 1, 1);
		boardView.renderBoard(b);
		boardView.hilightTile(3, 4, Color.red);
		mainFrame.setVisible(true);
	}
}
