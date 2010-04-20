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
		new JGTimer(50, true, "Death")
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				System.out.println("Making Timer!");

				// remove Life cuz you diedz!
				game.getPlayer().removeLife();

				if (game.getPlayer().getLifes() > 0)
				{
					game.resetViewport();
					game.setCurrentState("Restart");
					System.out.println("Continue with restart of game!!!");
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
