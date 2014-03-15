class Developer
{
	private Space currentSpace;
	private boolean onBoard;
	
	public Developer()
	{
		currentSpace = null;
		onBoard = false;
	}
	
	public void playDeveloper(Space s)
	{
		currentSpace = s;
		onBoard = true;
	}
	
	public void removeFromBoard()
	{
		currentSpace = null;
		onBoard = false;
	}
	
	public void moveDeveloper(Space s)
	{
		currentSpace = s;
	}
	
	public Space getSpace()
	{
		return currentSpace;
	}
	}
}