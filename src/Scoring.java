import java.util.*;

public class Scoring {
	private PlayerFacade p;
	private BoardFacade b;

	public Scoring(PlayerFacade p, BoardFacade b){
		//this should take in whatever
		this.p=p;
		this.b=b;
	}
	
	public Scoring(PlayerFacade p){
		//this should take in whatever
		this.p=p;
		this.b=null;
	}

	public Scoring(BoardFacade b){
		//this should take in whatever
		this.p=null;
		this.b=b;
	}
	//THIS METHOD ASSUMES THAT THE PALACE PLACEMENT WAS LEGAL
	public void palaceScoring(Board.Coordinates c) {
		//This method is called when a player expands or builds a palace
		p.addScore(b.getPalaceLevel(c)/2);
	}

	//THIS METHOD ASSUMES THAT THE IRRIGATION PLACEMENT WAS LEGAL
	public void irrigationScoring(Board.Coordinates c) {
		//Called when any player lays down an irrigation tile AND that irrigation tile (and irrigation tiles connected to it)
		//is COMPLETELY enclosed by land tiles.
		ArrayList<Developer> d = b.findHighestDev(c);
		if(d.size()==1){
			p.addPlayerScore(d.get(0).getPlayer(), b.numIrrigationTiles(c)*3);
		}
		//if theres a tie for highest developer no points scored
	}

	public void partyScoring(ArrayList<Player> victors, Board.Coordinates palaceCoordinates){
		//this method is called when a festival ends

		//Boardfacade class has a method which returns the level of a palace if you pass it the coordinates of a palace
		//need a away to get the level of the palace
		int level = b.getPalaceLevel(palaceCoordinates);
		//If the number of victors is 1, then they get famepoints equal to the palace level/2
		if(victors.size()==1){
			p.addPlayerScore(victors.get(0), level/2);
		}
		else{
			for(int i=0; i<victors.size(); i++){
				switch (level) {
					case 2: p.addPlayerScore(victors.get(i), 0);
							break;
					case 4: p.addPlayerScore(victors.get(i), 1);
							break;
					case 6: p.addPlayerScore(victors.get(i), 2);
							break;
					case 8: p.addPlayerScore(victors.get(i), 3);
							break;
					case 10:p.addPlayerScore(victors.get(i), 4);
							break;
					default:p.addPlayerScore(victors.get(i), 0);
							break;
				}
			}		
		}
	}

	public void greatFinalCount(){
		//this happens at THE END of each player's final turn!!!

		//first thing we need to do is iterate over all palace tiles to te
		ArrayList<Board.Coordinates> l = b.allPalaceTiles();
		for(int i = 0; i<l.size();i++){
			boolean inD1 = false;
			boolean inD2 = false;
			ArrayList<Developer> d1 = b.findHighestDev(l.get(i));
			ArrayList<Developer> d2 = b.findSecondHighestDev(l.get(i));
			for(int j = 0; j<d1.size();j++){
				//checks to see if current player is tied for the highest developer in the city
				if(d1.get(j).getPlayer() == p.getCurrentPlayer()){
					inD1 = true;
					break;
				}
			}
			for(int j = 0; j<d2.size();j++){
				if(d2.get(j).getPlayer() == p.getCurrentPlayer()){
					inD2 = true;
					break;
				}
			}
			if(inD1 == true){
				//if current player is tied for highest dev in the city
				p.addScore(b.getPalaceLevel(l.get(i)));
			}
			if(inD2 == true){
				//
				p.addScore(b.getPalaceLevel(l.get(i))/2);
			}
		}
	}	
	//if p has the highest game piece	
}

/*Things that score you points
has access to "boardfacade" and "buildingfacade"
Build one palace
	The player who builds or extends a palace immediately advances their counter on the 
	counting scale by half the value of the new palace. If the value of a palace changes from, 
	for example, 2 to 8 points, the player advances their counter by 4 points. 

Expand one palace
	Same as building a palace

	Ask palace level from the tile

Place Irrigation fields
	If an irrigation field (composed of one or more juxtaposed hexagons) is entirely 
	surrounded by terrains, it is established who occupies the highest position on the fields 
	surrounding it (be it rice terraces, villages or towns). The rule about the position is the 
	same as for the construction and extension of palaces. For each field of an irrigation field 
	surrounded in this way, the player scores 3 points. If no player has the ad vantage or if no 
	game piece is around the irrigation field, no one scores any points. 

	ask who highest player is in the board

Organize a party in the palace
	 
To determine who will organise the party, the players duel with their party cards. On 
these cards, 1 or 2 symbols are represented. The party card that lies openly next to the 
pile indicates what each player must possess to participate in the organisation of the 
party: 
· If the returned card indicates only 1 symbol, the players can play the cards that also 
have that symbol. The other symbols represented on their cards are then not taken 
into account. Each of these cards counts for 1 point. 
· If the returned card indicates 2 symbols, the players can play all cards that have 
either: 
o One of these symbols. These cards count for 1 point 
o Both of these symbols. These cards count for 2 points

public void partyScoring(Arraylist victors, Coordinates palaceCoordinates){
	//Boardfacade class has a method which returns the level of a palace if you pass it the coordinates of a palace
	//need a away to get the level of the palace
	int level = B.getPalaceLevel(palaceCoordinates);
	if(victors.size()==1){
		P.addPlayerScore(victors.get(0), level/2))
	}
	else{
		for(int i, i<victors.size(), i++){
			switch (level) {
				case 2: P.addPlayerScore(victors.get(i), 0);
						break;
				case 4: P.addPlayerScore(victors.get(i), 1);
						break;
				case 6: P.addPlayerScore(victors.get(i), 2);
						break;
				case 8: P.addPlayerScore(victors.get(i), 3);
						break;
				case 10:P.addPlayerScore(victors.get(i), 4);
						break;
				default: P.addPlayerScore(victors.get(i), 0));
						break;
			}
	}
}

Lol wtf is this shit

End of the game
	Great final Count!

only score points if they occupy the highest or the second highest place in a town. 
 
	If one of their game pieces occupies the highest position in a town, they score a number 
	of points equal to the value of the palace. 
	If their game piece is at the same level as another player’s game piece, the second 
	highest game piece is considered (see rules for construction and extension of palaces). 
	In case of a tie, the player still scores all the palace’s points. 
 
	If a player is only second with their game piece, they score half the palace’s points. 
	In case of a tie with another player for second position, the player still scores half the 
	palace’s points. 
 
	To indicate that the points for a palace have already been counted, an action card is 
	placed on the palace. Once a player has finished their GFC, these action cards are 
	removed. This helps to make sure that no palace is forgotten or counted more than once. 

	loop through palaces seeing if you are the highest

public void greatFinalCount(Player p){
	ArrayList L = B.getListOfPalaces();\
	//if p has the highest game piece
	P.addPlayerScore(p, L.get(i))
}
*/
