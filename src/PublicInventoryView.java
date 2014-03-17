import javax.swing.*;

public class PublicInventoryView extends JPanel {
	/** This class will be represented in the main View as the public inventory
	 * and will keep track of communal items like 3 blocks, irrigation tiles,
	 * palace deck, palace tiles, etc.
	*/
	
	private int numLandTiles = 3;
	private int numIrrigationTiles = 16;
	private int numPalaceTiles = 40;
	
	public PublicInventoryView() {
		try {
			initialize();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	 
	
}