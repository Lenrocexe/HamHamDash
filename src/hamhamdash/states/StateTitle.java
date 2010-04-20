package hamhamdash.states;

import hamhamdash.Player;
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
		game.setViewOffset(0, 0, true);
		game.switchMusic("titlebg1");
	}

	@Override
	public void doFrame()
	{
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(0, 0, "title_bg");
		game.drawString("Press <ENTER> to continue", game.viewWidth()/ 2, game.viewHeight() - 50, 0);
	}
}
