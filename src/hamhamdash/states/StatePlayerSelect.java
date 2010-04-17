package hamhamdash.states;

import hamhamdash.*;
import jgame.JGPoint;

/**
 *
 * @author Cornel Alders
 */
public class StatePlayerSelect extends State
{
	public StatePlayerSelect()
	{
	}

	@Override
	public void start()
	{
		game.psButtonWidth = 30;
		game.playerOneButtonState = "rollover";				// 'player one' is highlighted as default
		game.playerTwoButtonState = "normal";			// 'player tow' is not
		game.psPoint = new JGPoint(game.pfWidth() / 2, 60);
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyLeft) || game.getKey(Game.KeyUp))
		{
			game.clearKey(Game.KeyLeft);
			game.clearKey(Game.KeyUp);
			game.togglePlayerSelect();
		}
		if(game.getKey(Game.KeyRight) || game.getKey(Game.KeyDown))
		{
			game.clearKey(Game.KeyRight);
			game.clearKey(Game.KeyDown);
			game.togglePlayerSelect();
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawString("Select amount of Player", game.psPoint.x, game.psPoint.y, 0);
		game.drawImage(game.psPoint.x - (75 / 2), game.psPoint.y + 30, "player1_button_" + game.playerOneButtonState);
		game.drawImage(game.psPoint.x - (75 / 2), game.psPoint.y + 65, "player2_button_" + game.playerTwoButtonState);
	}
}
