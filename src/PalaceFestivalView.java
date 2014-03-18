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
		Image img = ImageIO.read(new File("../images/drumcard.png"));
		JLabel drumPic = new JLabel(new ImageIcon(img));
		drumCards.add(drumPic);
		numDrumCards = new JLabel("0", JLabel.CENTER);
		drumCards.add(numDrumCards);
		bottomPanel.add(drumCards);
		
		//Puppet cards now 
		JPanel puppetCards = new JPanel();
		puppetCards.setLayout(new BoxLayout(puppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/puppetcard.png"));
		JLabel puppetPic = new JLabel(new ImageIcon(img));
		puppetCards.add(puppetPic);
		numPuppetCards = new JLabel("0", JLabel.CENTER);
		puppetCards.add(numPuppetCards);
		bottomPanel.add(puppetCards);

		//Mask cards now
		JPanel maskCards = new JPanel();
		maskCards.setLayout(new BoxLayout(maskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/maskcard.png"));
		JLabel maskPic = new JLabel(new ImageIcon(img));
		maskCards.add(maskPic);
		numMaskCards = new JLabel("0", JLabel.CENTER);
		maskCards.add(numMaskCards);
		bottomPanel.add(maskCards);

		//MaskDrum cards now
		JPanel maskDrumCards = new JPanel();
		maskDrumCards.setLayout(new BoxLayout(maskDrumCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/drummaskcard.png"));
		JLabel maskDrumPic = new JLabel(new ImageIcon(img));
		maskDrumCards.add(maskDrumPic);
		numMaskDrumCards = new JLabel("0", JLabel.CENTER);
		maskDrumCards.add(numMaskDrumCards);
		bottomPanel.add(maskDrumCards);

		//DrumPuppet cards now
		JPanel drumPuppetCards = new JPanel();
		drumPuppetCards.setLayout(new BoxLayout(drumPuppetCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/drumpuppet.png"));
		JLabel drumPuppetPic = new JLabel(new ImageIcon(img));
		drumPuppetCards.add(drumPuppetPic);
		numDrumPuppetCards = new JLabel("0", JLabel.CENTER);
		drumPuppetCards.add(numDrumPuppetCards);
		bottomPanel.add(drumPuppetCards);
		
		//PuppetMask cards now
		JPanel puppetMaskCards = new JPanel();
		puppetMaskCards.setLayout(new BoxLayout(puppetMaskCards, BoxLayout.PAGE_AXIS));
		img = ImageIO.read(new File("../images/puppetmaskcard.png"));
		JLabel puppetMaskPic = new JLabel(new ImageIcon(img));
		puppetMaskCards.add(puppetMaskPic);
		numPuppetMaskCards = new JLabel("0", JLabel.CENTER);
		puppetMaskCards.add(numPuppetMaskCards);
		bottomPanel.add(puppetMaskCards);
		
		this.add(bottomPanel);
	}
}