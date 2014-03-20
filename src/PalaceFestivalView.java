import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;

public class PalaceFestivalView extends JFrame {

	private JLabel playerName;
	private JLabel numDrumCards;
	private JLabel numPuppetCards;
	private JLabel numMaskCards;
	private JLabel numMaskDrumCards;
	private JLabel numDrumPuppetCards;
	private JLabel numPuppetMaskCards;
	private JLabel festivalCard;
	private JLabel topOfPileCard;

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
	
	private void initialize(String currentPlayer) throws Exception{
		this.setTitle("Java - Palace Festival");
		this.setBounds(100, 100, 800, 350);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(2,1));
		
		//Set up the top panel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Festival card portion 
		JPanel festCard = new JPanel();
		festCard.setLayout(new BoxLayout(festCard, BoxLayout.PAGE_AXIS));
		JLabel festCardLabel = new JLabel("Festival Card: ");
		festCard.add(festCardLabel);
		Image img = ImageIO.read(new File("../images/drumcard.png"));
		festivalCard = new JLabel(new ImageIcon(img));
		festCard.add(festivalCard);
		topPanel.add(festCard);
		
		//Pile card portion 
		JPanel pileCard = new JPanel();
		pileCard.setLayout(new BoxLayout(pileCard, BoxLayout.PAGE_AXIS));
		JLabel pileCardLabel = new JLabel("Top of Pile: ");
		pileCard.add(pileCardLabel);
		topOfPileCard = new JLabel("Empty Pile");
		pileCard.add(topOfPileCard);
		topPanel.add(pileCard);
		
		//Instructions portion
		String instructions = "<html>Instructions:<br>" +
							  "0 - Pass<br>" + 
							  "1 - Play Drum Card<br>" +
							  "2 - Play Puppet Card<br>" +
							  "3 - Play Mask Card<br>" +
							  "4 - Play Mask/Drum Card<br>" +
							  "5 - Play Drum/Puppet Card<br>" +
							  "6 - Play Puppet/Mask Card<br>" +
							  "7 - Finish Turn";
		JLabel instrs = new JLabel(instructions);
		topPanel.add(instrs);
		this.add(topPanel);
		
		//Set up the bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,7));
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//Player name
		playerName = new JLabel(currentPlayer + "'s Cards");
		bottomPanel.add(playerName);
		
		//Drum cards first
		JPanel drumCards = new JPanel();
		drumCards.setLayout(new BoxLayout(drumCards,BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/drumcard.png"));
		JLabel drumPic = new JLabel(new ImageIcon(img));
		drumCards.add(drumPic);
		numDrumCards = new JLabel("0", JLabel.CENTER);
		drumCards.add(numDrumCards);
		bottomPanel.add(drumCards);
		
		//Puppet cards now 
		JPanel puppetCards = new JPanel();
		puppetCards.setLayout(new BoxLayout(puppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/puppetcard.png"));
		JLabel puppetPic = new JLabel(new ImageIcon(img));
		puppetCards.add(puppetPic);
		numPuppetCards = new JLabel("0", JLabel.CENTER);
		puppetCards.add(numPuppetCards);
		bottomPanel.add(puppetCards);

		//Mask cards now
		JPanel maskCards = new JPanel();
		maskCards.setLayout(new BoxLayout(maskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/maskcard.png"));
		JLabel maskPic = new JLabel(new ImageIcon(img));
		maskCards.add(maskPic);
		numMaskCards = new JLabel("0", JLabel.CENTER);
		maskCards.add(numMaskCards);
		bottomPanel.add(maskCards);

		//MaskDrum cards now
		JPanel maskDrumCards = new JPanel();
		maskDrumCards.setLayout(new BoxLayout(maskDrumCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/drummaskcard.png"));
		JLabel maskDrumPic = new JLabel(new ImageIcon(img));
		maskDrumCards.add(maskDrumPic);
		numMaskDrumCards = new JLabel("0", JLabel.CENTER);
		maskDrumCards.add(numMaskDrumCards);
		bottomPanel.add(maskDrumCards);

		//DrumPuppet cards now
		JPanel drumPuppetCards = new JPanel();
		drumPuppetCards.setLayout(new BoxLayout(drumPuppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/drumpuppet.png"));
		JLabel drumPuppetPic = new JLabel(new ImageIcon(img));
		drumPuppetCards.add(drumPuppetPic);
		numDrumPuppetCards = new JLabel("0", JLabel.CENTER);
		drumPuppetCards.add(numDrumPuppetCards);
		bottomPanel.add(drumPuppetCards);
		
		//PuppetMask cards now
		JPanel puppetMaskCards = new JPanel();
		puppetMaskCards.setLayout(new BoxLayout(puppetMaskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("images/puppetmaskcard.png"));
		JLabel puppetMaskPic = new JLabel(new ImageIcon(img));
		puppetMaskCards.add(puppetMaskPic);
		numPuppetMaskCards = new JLabel("0", JLabel.CENTER);
		puppetMaskCards.add(numPuppetMaskCards);
		bottomPanel.add(puppetMaskCards);
		
		this.add(bottomPanel);
	}
	
	public void setFestivalCard(String cardType) throws Exception {
		Image img;
		switch (cardType) {
			case "puppet": 
				img = ImageIO.read(new File("images/puppet.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
			case "drum": 
				img = ImageIO.read(new File("images/drum.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
			case "mask": 
				img = ImageIO.read(new File("images/mask.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
			case "puppetmask": 
				img = ImageIO.read(new File("images/puppetmaskcard.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
			case "maskdrum": 
				img = ImageIO.read(new File("images/drummaskcard.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
			case "drumpuppet": 
				img = ImageIO.read(new File("images/drumpuppet.png"));
				festivalCard.setIcon(new ImageIcon(img));
				break;
		}
	}
	
	public boolean promptPlayerJoin(String name) {
		String message = "Would you, " + name + ", like to join this palace " +
						 " festival?";
		int n = JOptionPane.showConfirmDialog(this, message, 
											  "Player Prompt - Join Palace Fest"
											  , JOptionPane.YES_NO_OPTION);
		if (n == 0)
			return true;
		else if (n == 1)
			return false;
		else
			return false;
	}
	
	public void displayPalaceCard(int numMask, int numPuppet, int numDrum, int numMaskDrum, int numDrumPuppet, int numPuppetMask) {
		numDrumCards.setText(Integer.toString(numDrum));
		numPuppetCards.setText(Integer.toString(numPuppet));
		numMaskCards.setText(Integer.toString(numMask));
		numMaskDrumCards.setText(Integer.toString(numMaskDrum));
		numDrumPuppetCards.setText(Integer.toString(numDrumPuppet));
		numPuppetMaskCards.setText(Integer.toString(numPuppetMask));
	}
	
	public boolean promptSharePoints(String name) {
		String message = "Would you, " + name + ", like to share the points?";
		int n = JOptionPane.showConfirmDialog(this, message, 
											  "Player Prompt - Tie Breaker",
											  JOptionPane.YES_NO_OPTION);
		if (n == 0)
			return true;
		else if (n == 1)
			return false;
		else
			return false;
	}
	
	public void cannotPlayCard() {
		String message = "You cannot place that card.";
		int n = JOptionPane.showConfirmDialog(this, message, 
											  "Player Prompt - Palace Fest",
											  JOptionPane.DEFAULT_OPTION);
	}
}