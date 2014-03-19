public class PalaceFestivalViewFacade {

	static PalaceFestivalView festView;

	public static void initialize(String currentPlayer) {
		festView = new PalaceFestivalView(currentPlayer);
	}
	
	public static void setFestivalCard(String cardType) {
		festView.setFestivalCard(cardType);
	}

}