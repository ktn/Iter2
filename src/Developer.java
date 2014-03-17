public class Developer{
	private Player player;
	private Space space;

	Developer(Player p){
		this.space = null;
		this.player  = p;
	}

	Developer(Player p, Space s){
		this.space = s;
		this.player  = p;
	}

	public Player getPlayer(){
		return player;
	}

	public Space getSpace(){
		return space;
	}

	public Space moveDeveloper(Space s){
		this.space = s;
	}
}