package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.HamButton;
import hamhamdash.State;

/**
 *
 * @author Cornel Alders
 */
public class StateStartGame extends State
{
	public StateStartGame()
	{
	}

	@Override
	public void start()
	{
		game.sgButtonWidth = 70;
		game.sgButtonHeight = game.psButtonWidth;
		game.sgPoint = game.psPoint;
		game.newGameState = 1;						// 'New Game' is highlighted as default
		game.loadGameState = 0;						// 'Load Game' is not
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyLeft) || game.getKey(Game.KeyUp))
		{
			game.clearKey(Game.KeyLeft);
			game.clearKey(Game.KeyUp);
			game.toggleLoadGame();
		}

		if(game.getKey(Game.KeyRight) || game.getKey(Game.KeyDown))
		{
			game.clearKey(Game.KeyRight);
			game.clearKey(Game.KeyDown);
			game.toggleLoadGame();
		}
	}

	@Override
	public void paintFrame()
	{
		game.setBlendMode(1, 0);
		game.drawString("Select your action", game.sgPoint.x, game.sgPoint.y, 0);

		HamButton b;
		b = new HamButton(game.sgPoint.x, game.sgPoint.y, "New Game", game.hamButtonLabelColor, game.newGameState);
		b.paint();
		b = new HamButton(game.sgPoint.x, game.sgPoint.y + (game.sgButtonHeight * 2), "Load Game", game.hamButtonLabelColor, game.loadGameState);
		b.paint();
	}
}
