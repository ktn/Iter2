import javax.swing.*;
import java.awt.*;

public class ControllerView extends JPanel {

	private static final long serialVersionUID = -2891745662059708730L;
	
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
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		gamePhase = new JLabel("Active Mode");
		this.add(gamePhase);
		message = new JLabel("Available Actions:");
		this.add(message);
	}
	 
	public void setMode(String mode) {
		gamePhase.setText(mode + " Mode");
	}
	
	public void setActions(String actions) {
		message.setText(actions);
	}

}

