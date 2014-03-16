public class PalaceFestival {
	// private PlayerFacade playerFacade;
	private PalaceCard festivalCard;

	public PalaceFestival(PalaceCard festivalCard) {
		this.setFestivalCard(festivalCard);
	}

	public void startFestival() {
		/*
		 * PalaceCard p = playerFacade.playPalaceCard(); if(p instanceof
		 * OnePointPalaceCard && this.festivalCard instanceof
		 * OnePointPalaceCard) {
		 * 
		 * } else if (p instanceof OnePointPalaceCard && this.festivalCard
		 * instanceof TwoPointPalaceCard) {
		 * 
		 * } else if (p instanceof TwoPointPalaceCard && this.festivalCard
		 * instanceof OnePointPalaceCard) {
		 * 
		 * } //else both are TwoPointPalaceCards else {
		 * 
		 * }
		 */
	}

	public void endFestival() {
		// Score fame points
	}

	public PalaceCard getFestivalCard() {
		return festivalCard;
	}

	public void setFestivalCard(PalaceCard festivalCard) {
		this.festivalCard = festivalCard;
	}
}