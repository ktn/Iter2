

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	static int tx=1;
	static int ty=1;
	
	public static void boardViewTester(){
		final TestableBoard b=new TestableBoard();
		 
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
		final BoardView boardView=new BoardView(12,12);
		JFrame mainFrame=new JFrame("BoardViewTest");
		mainFrame.setSize(768,768);
		mainFrame.add(boardView);
		//boardView.renderNetwork(b.getOrigin(), 1, 1);
		mainFrame.setVisible(true);
		boardView.renderBoard(b);
		boardView.hilightTile(3, 4, Color.red);
		mainFrame.repaint();
		
		final ThreeBlock temporary = new ThreeBlock();
		
		
		boardView.renderNetwork(temporary.getTile(), tx, ty);
		
		mainFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyChar() == 'w'&&ty>1){
					ty--;
				}
				if (arg0.getKeyChar() == 'a'&&tx>1) {
					tx--;
				}
				if (arg0.getKeyChar() == 's'&&ty<10) {
					ty++;
				}
				if (arg0.getKeyChar() == 'd'&&tx<10) {
					tx++;
				}
				boardView.renderBoard(b);
				boardView.renderNetwork(temporary.getTile(), tx, ty, new Color(1,0,0,0.5f));
				boardView.repaint();
			}
		});
	}
}
