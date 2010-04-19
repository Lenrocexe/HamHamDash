package hamhamdash.states;

import hamhamdash.State;

/**
 *
 * @author Serhan Uygur
 */
public class StateDeath extends State
{
	public StateDeath()
	{
	}

	@Override
	public void start()
	{
		System.out.println("I am Dead!!!!");
	}

	@Override
	public void doFrame()
	{
	}

	@Override
	public void paintFrame()
	{
//		game.drawImage(0, 0, "title_bg");
//		game.drawString("Press <ENTER> to continue", game.pfWidth() / 2, game.pfHeight() - 50, 0);
	}
}
