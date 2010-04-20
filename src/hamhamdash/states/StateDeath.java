package hamhamdash.states;

import hamhamdash.State;
import jgame.JGTimer;

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
	}

	@Override
	public void doFrame()
	{
		// wait a bit for Hamtaro to finish dieng
		new JGTimer(50, true, "Death")
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				// remove Life cuz you diedz!
				game.getPlayer().removeLife();
				game.removeObjects("", 0); // Clear all objects from the field

				if (game.getPlayer().getLifes() > 0)
				{
					game.resetViewport();
					game.setCurrentState("Restart");
				}
				else
				{
					game.resetViewport();
					game.setCurrentState("GameOver");
				}
			}
		};
	}

	@Override
	public void paintFrame()
	{
	}
}
