import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
	/**This class represents the central view which will be the actual game 
	 * window. It will grab the other components of view and place them 
	 * in a single window according to a specific layout scheme. */
	 
	//TEMPORARY METHOD
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Do");
		names.add("You");
		names.add("See");
		names.add("Right");
		View view = new View(names);
	}
	 
	/**
	 * Create the application.
	 */
	public View(ArrayList<String> playerNames) {
        try {
            initialize(playerNames);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
	}  
	
	private void initialize(ArrayList<String> playerNames) {
		this.setTitle("Java");
		//this.setBounds(100, 100, 910, 650);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		this.setSize(xSize,ySize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(3, 3));
		
		PlayerView playerView = new PlayerView(playerNames);
		
		this.getContentPane().add(playerView.getOtherPlayerView(),							BorderLayout.NORTH);
		this.getContentPane().add(playerView.getCurrentPlayerView(),
								  BorderLayout.SOUTH);
		try {
			BoardView boardView = new BoardView(); 
			this.getContentPane().add(boardView, BorderLayout.CENTER);
		}
		catch (Exception e) { e.printStackTrace(); }						  
								  
		//TODO: Add public inventory view to east and player instructoins
		//to left.
	}
}