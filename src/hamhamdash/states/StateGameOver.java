package hamhamdash.states;

import hamhamdash.*;
import jgame.JGTimer;

/**
 *
 * @author Serhan Uygur
 */
public class StateGameOver extends State
{
	public StateGameOver()
	{
	}

	@Override
	public void start()
	{
		System.out.println("Starting Game Over!");
		game.resetViewport();


		// wait a bit so the pain sinks in
		new JGTimer(500, true, "GameOver")
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				System.out.println("Go to Title");
				game.setCurrentState("Title");
			}
		};
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyEnter))
		{
			game.clearKey(Game.KeyEnter);
			game.setCurrentState("Title");
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(0, 0, "gameover_bg");
	}
}
