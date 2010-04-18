package hamhamdash.states;

import hamhamdash.State;

/**
 *
 * @author Cornel Alders
 */
public class StateTitle extends State
{
	public StateTitle()
	{
	}

	@Override
	public void start()
	{

	}

	@Override
	public void doFrame()
	{
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(0, 0, "title_bg");
		game.drawString("Press <ENTER> to continue", game.pfWidth() / 2, game.pfHeight() - 50, 0);
	}
}
