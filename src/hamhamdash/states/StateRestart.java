package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.State;
import jgame.JGColor;

/**
 *
 * @author Serhan Uygur
 */
public class StateRestart extends State
{
	public StateRestart()
	{
		
	}
	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(game.KeyEnter))
		{
			game.clearKey(game.KeyEnter);
			game.setCurrentState("InGame");
		}
	}

	@Override
	public void paintFrame()
	{
		int x = (game.viewWidth() / 2);
		int y = game.viewHeight() / 2;

		// Offset
		y -= 20;

		game.setColor(JGColor.white);
		game.drawImage(0, 0, "restart_bg");
		game.drawString("Too bad you did not survive that. Have another go by pressing <ENTER>", x, y, 0);
		game.drawImage(x - 40, y + 5, game.getPlayer().getIdentifier() + "still");
		game.drawString("x" + game.getPlayer().getLifes(), x, y  + 30, -1);
	}


}
