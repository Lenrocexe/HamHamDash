package hamhamdash;

/**
 * This class is the new implementation of State handling.
 * Every state must extends this class, there can be no exception.
 * With this implementation every state can be defined in it's own class.
 * @author Cornel Alders
 */
public abstract class State
{
	public Game game = Game.getGame();
	public boolean started = false; //lowsy boolean for start() method.
									//Needed because sometimes doFrame does not wait for start
									//to finish.

	public State(){}

	//Each of the methods below must be called in the respective game state method.
	public abstract void start();

	public abstract void doFrame();

	public abstract void paintFrame();
}
