import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

public class CurrentPlayerView extends JPanel {
/**This class, placed towards the bottom of the game window, will contain
 * information relevant to the player actively playing. This includes their
 * current inventory of score, AP remaining one blocks (village), two blocks
 * (village rice), action tokens, remaining off-board developers and palace 
 * cards */
 
	private JLabel numDrumCards;
	private JLabel numPuppetCards;
	private JLabel numMaskCards;
	private JLabel numMaskDrumCards;
	private JLabel numDrumPuppetCards;
	private JLabel numPuppetMaskCards;
	private JLabel numRiceBlocks;
	private JLabel numVillageBlocks;
	private JLabel numTwoBlocks;
	private JLabel numDevelopers;
	private JLabel score;
	private JLabel name;
	private JLabel AP;
	
	public CurrentPlayerView(String playerName) {
		try {
			initialize(playerName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize(String playerName) throws Exception {
		this.setLayout(new GridLayout(1, 4,1,1));
		//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel label = new  JLabel("Current (Active) Player", JLabel.CENTER);
		//label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(label);
		
		//Current Player basic info
		JPanel playerInfo = new JPanel();
		playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
		playerInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		name = new JLabel(playerName + "\n");
		score = new JLabel("Score: ");
		AP = new JLabel("AP: 6");
		playerInfo.add(name);
		playerInfo.add(score);
		playerInfo.add(AP);
		this.add(playerInfo);
		
		//Current player inventory
		JPanel playerInv = new JPanel();
		playerInv.setBorder(BorderFactory.createLineBorder(Color.black));
		playerInv.setLayout(new BoxLayout(playerInv, BoxLayout.PAGE_AXIS));
		JLabel riceBlocks = new JLabel("Rice Blocks: ");
		JLabel villageBlocks = new JLabel("Village Blocks: ");
		JLabel developers = new JLabel("Off-Board Developers: ");
		playerInv.add(riceBlocks);
		playerInv.add(villageBlocks);
		playerInv.add(developers);
		this.add(playerInv);
		
		//Current player palace card inventory
		JPanel playerCards = new JPanel();
		playerCards.setLayout(new BoxLayout(playerCards, BoxLayout.LINE_AXIS));
		//playerCards.setLayout(new GridLayout(1,6));
		
		//Drum cards first
		JPanel drumCards = new JPanel();
		drumCards.setLayout(new BoxLayout(drumCards,BoxLayout.PAGE_AXIS));
		Image img = ImageIO.read(new File("../images/drumcard.png"));
		JLabel drumPic = new JLabel(new ImageIcon(img));
		drumCards.add(drumPic);
		numDrumCards = new JLabel("0", JLabel.CENTER);
		drumCards.add(numDrumCards);
		playerCards.add(drumCards);
		
		//Puppet cards now 
		JPanel puppetCards = new JPanel();
		puppetCards.setLayout(new BoxLayout(puppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/puppetcard.png"));
		JLabel puppetPic = new JLabel(new ImageIcon(img));
		puppetCards.add(puppetPic);
		numPuppetCards = new JLabel("0", JLabel.CENTER);
		puppetCards.add(numPuppetCards);
		playerCards.add(puppetCards);

		//Mask cards now
		JPanel maskCards = new JPanel();
		maskCards.setLayout(new BoxLayout(maskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/maskcard.png"));
		JLabel maskPic = new JLabel(new ImageIcon(img));
		maskCards.add(maskPic);
		numMaskCards = new JLabel("0", JLabel.CENTER);
		maskCards.add(numMaskCards);
		playerCards.add(maskCards);

		//MaskDrum cards now
		JPanel maskDrumCards = new JPanel();
		maskDrumCards.setLayout(new BoxLayout(maskDrumCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/drummaskcard.png"));
		JLabel maskDrumPic = new JLabel(new ImageIcon(img));
		maskDrumCards.add(maskDrumPic);
		numMaskDrumCards = new JLabel("0", JLabel.CENTER);
		maskDrumCards.add(numMaskDrumCards);
		playerCards.add(maskDrumCards);

		//DrumPuppet cards now
		JPanel drumPuppetCards = new JPanel();
		drumPuppetCards.setLayout(new BoxLayout(drumPuppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/drumpuppet.png"));
		JLabel drumPuppetPic = new JLabel(new ImageIcon(img));
		drumPuppetCards.add(drumPuppetPic);
		numDrumPuppetCards = new JLabel("0", JLabel.CENTER);
		drumPuppetCards.add(numDrumPuppetCards);
		playerCards.add(drumPuppetCards);
		
		//PuppetMask cards now
		JPanel puppetMaskCards = new JPanel();
		puppetMaskCards.setLayout(new BoxLayout(puppetMaskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/puppetmaskcard.png"));
		JLabel puppetMaskPic = new JLabel(new ImageIcon(img));
		puppetMaskCards.add(puppetMaskPic);
		numPuppetMaskCards = new JLabel("0", JLabel.CENTER);
		puppetMaskCards.add(numPuppetMaskCards);
		playerCards.add(puppetMaskCards);
		
		//Add this whole panel in 
		this.add(playerCards);	
	}
	
	public void displayPalaceInventory(int numMask, int numPuppet, int numDrum, 
									   int numMaskDrum, int numDrumPuppet, 
									   int numPuppetMask) {
		numDrumCards.setText(Integer.toString(numDrum));
		numPuppetCards.setText(Integer.toString(numPuppet));
		numMaskCards.setText(Integer.toString(numMask));
		numMaskDrumCards.setText(Integer.toString(numMaskDrum));
		numDrumPuppetCards.setText(Integer.toString(numDrumPuppet));
		numPuppetMaskCards.setText(Integer.toString(numPuppetMask));
	}

	public void displayInventory(int numRice, int numVilage, int numTwoBlock, 
											int numDevel, int s, String n, int actionPoints)
	{
		numRiceBlocks.setText(Integer.toString(numRice));
		numVillageBlocks.setText(Integer.toString(numVillage));
		numTwoBlocks.setText(Integer.toString(numTwoBlock));
		numDevelopers.setText(Integer.toString(numDevel));
		score.setText(Integer.toString(s));
		name.setText(n);
		AP.setText(Integer.toString(actionPoints));
	}
}