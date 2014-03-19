public class PalaceFestivalViewFacade {

	static PalaceFestivalView festView;

	public static void initialize(String currentPlayer) {
		festView = new PalaceFestivalView(currentPlayer);
	}
	
	public static void setFestivalCard(String cardType) {
		try {
			festView.setFestivalCard(cardType);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean promptPlayerJoin(String name) {
		return festView.promptPlayerJoin(name);
	}
	
	public static void displayPalaceCard(int numMask, int numPuppet, int numDrum
									     , int numMaskDrum, int numDrumPuppet,
										 int numPuppetMask) {
		festView.displayPalaceCard(numMask, numPuppet, numDrum, numMaskDrum,
								   numDrumPuppet, numPuppetMask);
	}
	
	public static boolean promptSharePoints(String name) {
		return festView.promptSharePoints(name);
	}
	
	public static void cannotPlayCardNotice() {
		festView.cannotPlayCard();
	}
}