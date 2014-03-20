

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
		//viewFacadeTester();
		boardViewTester();
	}
	
	public static void viewFacadeTester(){
		BoardFacade board = new BoardFacade();
		ArrayList<String> names = new ArrayList<String>();
		names.add("steve");
		names.add("martin");
		View viewer=new View(names);
		ViewFacade.updateBoard(board.board);
	}
	
	public static void boardViewTester(){
		TestableBoard b=new TestableBoard();
		 
		b.placeDeveloper(b.new Coordinates(4, 4), new Developer(new Player("king dude", Color.red)));
		Block block = new OneBlock(new PalaceTile(2));
		b.placeBlock(b.new Coordinates(5,5), block);
		block = new OneBlock(new PalaceTile(4));
		b.placeBlock(b.new Coordinates(6,5), block);
		block = new OneBlock(new PalaceTile(6));
		b.placeBlock(b.new Coordinates(7,5), block);
		block = new OneBlock(new PalaceTile(8));
		b.placeBlock(b.new Coordinates(8,5), block);
		block = new OneBlock(new PalaceTile(10));
		b.placeBlock(b.new Coordinates(9,5), block);
		block = new OneBlock(TileType.VILLAGE);
		b.placeBlock(b.new Coordinates(5,7), block);
		block = new ThreeBlock();
		b.placeBlock(b.new Coordinates(6,7), block);
		BoardView boardView=new BoardView(12,12);
		JFrame mainFrame=new JFrame("BoardViewTest");
		mainFrame.setSize(768,768);
		mainFrame.add(boardView);
		//boardView.renderNetwork(b.getOrigin(), 1, 1);
		mainFrame.setVisible(true);
		boardView.renderBoard(b);
		boardView.hilightTile(3, 4, Color.red);
		mainFrame.repaint();
	}
}
