import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class View extends JFrame {
	/**This class represents the central view which will be the actual game 
	 * window. It will grab the other components of view and place them 
	 * in a single window according to a specific layout scheme. */
	 
	private static final long serialVersionUID = -2507182043735130183L;

	JPanel boardPanel;
	
	/**
	 * Create the application.
	 */
	public View(ArrayList<String> playerNames, KeyListener inputListener) {
        try {
            initialize(playerNames);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
		//TODO:Set KeyListener for this to Controller
        this.addKeyListener(inputListener);
        
		int minDimension = Math.min(boardPanel.getSize().width,boardPanel.getSize().height);
		ViewFacade.getBoardView().setPreferredSize(new Dimension(minDimension,minDimension));
		ViewFacade.getBoardView().setSize(new Dimension(minDimension,minDimension));
		this.repaint();
	}  
	
	private void initialize(ArrayList<String> playerNames) {
		this.setTitle("Java");
		this.getContentPane().setBackground(Color.BLACK);
		//this.setBounds(100, 100, 910, 650);
        //this.setExtendedState(Frame.MAXIMIZED_BOTH);
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) (tk.getScreenSize().getWidth()));  
		int ySize = ((int) (tk.getScreenSize().getHeight()));  
		this.setSize(xSize,ySize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(3, 3));
		
		//PlayerView playerView = new PlayerView();
		ViewFacade.initialize(playerNames, 10, 10);
		
		ViewFacade.getOtherPlayerView().setBackground(new Color(220,220,220));
		this.getContentPane().add(ViewFacade.getOtherPlayerView(), 
								  BorderLayout.NORTH);
		ViewFacade.getCurrentPlayerView().setBackground(new Color(220,220,220));
		this.getContentPane().add(ViewFacade.getCurrentPlayerView(),
								  BorderLayout.SOUTH);
		ViewFacade.getPublicInventoryView().setBackground(new Color(220,220,220));
		this.getContentPane().add(ViewFacade.getPublicInventoryView(),
								  BorderLayout.EAST);
		ViewFacade.getControllerView().setBackground(new Color(220,220,220));
		this.getContentPane().add(ViewFacade.getControllerView(), 
							  BorderLayout.WEST);
		boardPanel = new JPanel();
		boardPanel.setBackground(new Color(112,128,144));
		boardPanel = new JPanel();
		boardPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		boardPanel.add(ViewFacade.getBoardView());
		this.getContentPane().add(boardPanel, BorderLayout.CENTER);
	}
}