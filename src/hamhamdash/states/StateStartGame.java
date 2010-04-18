package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.HamButton;
import hamhamdash.State;
import jgame.JGColor;
import jgame.JGPoint;

/**
 *
 * @author Cornel Alders
 */
public class StateStartGame extends State
{
	private int sgButtonWidth = 70;
	private int sgButtonHeight = 30;
	private JGPoint sgPoint = new JGPoint(game.pfWidth() / 2, 60);
	private JGColor hamButtonLabelColor = new JGColor(180, 175, 150);
	private int newGameState = 1; // 'New Game' is highlighted as default
	private int loadGameState = 0; // 'Load Game' is not

	public StateStartGame()
	{
	}

	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyLeft) || game.getKey(Game.KeyUp))
		{
			game.clearKey(Game.KeyLeft);
			game.clearKey(Game.KeyUp);
			toggleLoadGame();
		}

		if(game.getKey(Game.KeyRight) || game.getKey(Game.KeyDown))
		{
			game.clearKey(Game.KeyRight);
			game.clearKey(Game.KeyDown);
			toggleLoadGame();
		}
	}

	@Override
	public void paintFrame()
	{
		game.setBlendMode(1, 0);
		game.drawString("Select your action", sgPoint.x, sgPoint.y, 0);

		HamButton b;
		b = new HamButton(sgPoint.x, sgPoint.y, "New Game", hamButtonLabelColor, newGameState);
		b.paint();
		b = new HamButton(sgPoint.x, sgPoint.y + (sgButtonHeight * 2), "Load Game", hamButtonLabelColor, loadGameState);
		b.paint();
	}

	// Player Select Methods
	public void toggleLoadGame()
	{
		if(game.loadGame)
		{
			newGameState = 1;
			loadGameState = 0;
			game.loadGame = false;
		}
		else
		{
			newGameState = 0;
			loadGameState = 1;
			game.loadGame = true;
		}
	}
}
