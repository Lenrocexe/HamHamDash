package hamhamdash;

/**
 *
 * @author Cornel Alders
 */
public abstract class State
{
	public Game game = Game.getGame();

	public State()
	{
	}

	public abstract void start();

	public abstract void doFrame();

	public abstract void paintFrame();
}
