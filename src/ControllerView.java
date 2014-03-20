import javax.swing.*;
import java.awt.*;

public class ControllerView extends JPanel {
	/** This class represents an interface between the player and the 
	 * controller. 
	 */
	 
	private JLabel gamePhase;
	private JLabel message;
	
	public ControllerView() {
		try {
			initialize();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		setLayout(new GridLayout(2,1));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		gamePhase = new JLabel("Active Mode");
		this.add(gamePhase);
		message = new JLabel("Available Actions:");
		this.add(message);
	}
	 
	public void setMode(String mode) {
		gamePhase = new JLabel(mode + " Mode");
	}
	
	public void setActions(String actions) {
		message = new JLabel(actions);
	}

}

