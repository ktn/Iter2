public class NotEnoughAPException extends Exception {
	int currentAP, costAP;
	
	public NotEnoughAPException() {
		super();
	}

	public NotEnoughAPException(String m) {
		super(m);
		currentAP = -1;
		costAP = -1;
	}
	
	public NotEnoughAPException(int currentAP, int costAP) {
		super();
		this.currentAP = currentAP;
		this.costAP = costAP;
	}
	
	public NotEnoughAPException(String m, int currentAP, int costAP) {
		super(m);
		this.currentAP = currentAP;
		this.costAP = costAP;
	}
	
	public String toString() {
		String result = super.toString();
		if(currentAP != -1 && costAP != -1)	result += " (" + currentAP + " - " + costAP + ")";
		return result;
	}
}