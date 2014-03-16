import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.nio.file.*;

public class CurrentPlayerView extends JPanel {
/**This class, placed towards the bottom of the game window, will contain
 * information relevant to the player actively playing. This includes their
 * current inventory of score, AP remaining one blocks (village), two blocks
 * (village rice), action tokens, remaining off-board developers and palace 
 * cards */
 
	public CurrentPlayerView(String playerName) {
		try {
			initialize(playerName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize(String playerName) throws Exception {
		this.setLayout(new GridLayout(1, 4, 1 , 1));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel label = new  JLabel("Current (Active) Player", JLabel.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(label);
		
		//Current Player basic info
		JPanel playerInfo = new JPanel();
		playerInfo.setLayout(new GridLayout(3,1,1,1));
		playerInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel name = new JLabel(playerName + "\n");
		JLabel score = new JLabel("Score: ");
		JLabel AP = new JLabel("AP: 6");
		playerInfo.add(name);
		playerInfo.add(score);
		playerInfo.add(AP);
		this.add(playerInfo);
		
		//Current player inventory
		JPanel playerInv = new JPanel();
		playerInv.setBorder(BorderFactory.createLineBorder(Color.black));
		playerInv.setLayout(new GridLayout(3,1,1,1));
		JLabel riceBlocks = new JLabel("Rice Blocks: ");
		JLabel villageBlocks = new JLabel("Village Blocks: ");
		JLabel developers = new JLabel("Off-Board Developers: ");
		playerInv.add(riceBlocks);
		playerInv.add(villageBlocks);
		playerInv.add(developers);
		this.add(playerInv);
		
		//TODO: Add fourth grid component with palace cards
		//TIP: Use java.io.File to get file path
		//Current player palace card inventory
		// JPanel playerCards = new JPanel();
		// JPanel drumCards = new JPanel();
		// drumCards.setLayout(new GridLayout(2,1,1,1));
		// Image img = ImageIO.read(getClass().getResource(path.toString()));
		// JLabel drumPic = new JLabel(new ImageIcon(img));
		// drumCards.add(drumPic);
		// JLabel numDrumCards = new JLabel("0", JLabel.CENTER);
		// drumCards.add(numDrumCards);
		// playerCards.add(drumCards);
		// this.add(playerCards);
	}
}