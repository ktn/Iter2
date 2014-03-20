import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

public class PublicInventoryView extends JPanel {
	/** This class will be represented in the main View as the public inventory
	 * and will keep track of communal items like 3 blocks, irrigation tiles,
	 * palace deck, palace tiles, etc.
	*/
	
	private JLabel numLandTiles;
	private JLabel numIrrigationTiles;
	private JLabel numTwoPalaceTiles;
	private JLabel numFourPalaceTiles;
	private JLabel numSixPalaceTiles;
	private JLabel numEightPalaceTiles;
	private JLabel numTenPalaceTiles;
	private JLabel festivalCard;
	
	public PublicInventoryView() {
		try {
			initialize();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		this.setLayout(new GridLayout(10,1));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel label = new JLabel("Communal Inventory");
		this.add(label);
		
		numLandTiles = new JLabel("Three Space Tiles: 56");
		this.add(numLandTiles);
		numIrrigationTiles = new JLabel("Irrigation Tiles: 16");
		this.add(numIrrigationTiles);
		numTwoPalaceTiles = new JLabel("Lv. 2 Palace Tiles: 6");
		this.add(numTwoPalaceTiles);
		numFourPalaceTiles = new JLabel("Lv. 4 Palace Tiles: 7");
		this.add(numFourPalaceTiles);
		numSixPalaceTiles = new JLabel("Lv. 6 Palace Tiles: 8");
		this.add(numSixPalaceTiles);
		numEightPalaceTiles = new JLabel("Lv. 8 Palace Tiles: 9");
		this.add(numEightPalaceTiles);
		numTenPalaceTiles = new JLabel("Lv. 10 Palace Tiles: 10");
		this.add(numTenPalaceTiles);
		JLabel festivalCardLabel = new JLabel("The Festival Card is... ");
		this.add(festivalCardLabel);		
		festivalCard = new JLabel("...");
		this.add(festivalCard);		
	}
	
	public void fullUpdate(BoardFacade b){
		displayThreeBlocks(b.threeBlocksLeft());
		displayIrrigationTiles(b.irrigationBlocksLeft());
		displayTwoPalaceTiles(b.communal.numPalaceTiles(2));
		displayFourPalaceTiles(b.communal.numPalaceTiles(4));
		displaySixPalaceTiles(b.communal.numPalaceTiles(6));
		displayEightPalaceTiles(b.communal.numPalaceTiles(8));
		displayTenPalaceTiles(b.communal.numPalaceTiles(10));
	}
	
	public void displayThreeBlocks(int numThreeBlocks) {
		numLandTiles.setText("Three Space Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displayIrrigationTiles(int numThreeBlocks) {
		numIrrigationTiles.setText("Irrigation Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displayTwoPalaceTiles(int numThreeBlocks) {
		numTwoPalaceTiles.setText("Lv. 2 Palace Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displayFourPalaceTiles(int numThreeBlocks) {
		numFourPalaceTiles.setText("Lv. 4 Palace Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displaySixPalaceTiles(int numThreeBlocks) {
		numSixPalaceTiles.setText("Lv. 6 Palace Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displayEightPalaceTiles(int numThreeBlocks) {
		numEightPalaceTiles.setText("Lv. 8 Palace Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	public void displayTenPalaceTiles(int numThreeBlocks) {
		numTenPalaceTiles.setText("Lv. 10 Palace Tiles: " + 
								  Integer.toString(numThreeBlocks));
	}
	
	 public void displayFestivalCard(int type) {
		try {
			Image img = ImageIO.read(new File("images/drumcard.png"));
		
			switch (type) {
				case 1: img = ImageIO.read(new File("images/drumcard.png"));
						break;
				case 2: img = ImageIO.read(new File("images/maskcard.png"));
						break;
				case 3: img = ImageIO.read(new File("images/puppetcard.png"));
						break;					
				case 4: img = ImageIO.read(new File("images/drummaskcard.png"));
						break;
				case 5: img = ImageIO.read(new File("images/drumpuppet.png"));
						break;
				case 6: img = ImageIO.read(new File("images/puppetmaskcard.png"));
						break;
			}
					
			festivalCard.setIcon(new ImageIcon(img));
			festivalCard.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
}