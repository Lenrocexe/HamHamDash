package hamhamdash.states;

import hamhamdash.*;
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
		game.stopAudio();
	}

	@Override
	public void doFrame()
	{
		game.moveObjects(game.getPlayer().getIdentifier(), 0);
		// wait a bit for Hamtaro to finish dieing
		new JGTimer(50, true, "Death")
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				// remove Life cuz you diedz!
				game.getPlayer().removeLife();
				game.removeObjects("", 0); // Clear all objects from the field
				game.switchPlayers();

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

	private boolean anyLifesLeft()
	{
		for(Player p : game.getPlayerList())
		{
			if(p.getLifes() > 0)
			{
				return true;
			}
		}
		return false;
	}
}
