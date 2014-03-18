public interface Command{
	

	void execute();
	//this is to run the command

	void undo();
	//this is to undo the command

	void save();
	//to store the command for future use

	void load();
	//to load the command to use again
}