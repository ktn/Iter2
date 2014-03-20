import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
	/**This class represents the central view which will be the actual game 
	 * window. It will grab the other components of view and place them 
	 * in a single window according to a specific layout scheme. */
	 
	private static final long serialVersionUID = -2507182043735130183L;

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
		//TODO:Set KeyListener for this to Controller
	}  
	
	private void initialize(ArrayList<String> playerNames) {
		this.setTitle("Java");
		//this.setBounds(100, 100, 910, 650);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) (tk.getScreenSize().getWidth()*0.75f));  
		int ySize = ((int) (tk.getScreenSize().getHeight()*0.75f));  
		this.setSize(xSize,ySize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(3, 3));
		
		//PlayerView playerView = new PlayerView();
		ViewFacade.initialize(playerNames);
		
		this.getContentPane().add(ViewFacade.getOtherPlayerView(), 
								  BorderLayout.NORTH);
		this.getContentPane().add(ViewFacade.getCurrentPlayerView(),
								  BorderLayout.SOUTH);
		this.getContentPane().add(ViewFacade.getPublicInventoryView(),
								  BorderLayout.EAST);
		this.getContentPane().add(ViewFacade.getControllerView(), 
								  BorderLayout.WEST);
		this.getContentPane().add(ViewFacade.getBoardView(), BorderLayout.CENTER);
	}
}