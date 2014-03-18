import javax.swing.*;
import java.awt.*;

public class PalaceFestivalView extends JFrame {

	/**
	 * Create the application.
	 */
	public PalaceFestivalView(String currentPlayer) {
        try {
            initialize(currentPlayer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
		//TODO:Set KeyListener for this to Controller
	}  
	
	private void initialize(String currentPlayer) {
		this.setTitle("Java - Palace Festival");
		this.setBounds(100, 100, 910, 650);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(3, 3));
		
			
	}
}