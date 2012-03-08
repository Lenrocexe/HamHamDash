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
	private JGPoint sgPoint = new JGPoint(game.viewWidth() / 2, 60);
	private JGColor hamButtonLabelColor = new JGColor(180, 175, 150);
	private int newGameState = 1; // 'New Game' is highlighted as default
	private int loadGameState = 0; // 'Load Game' is not
	private boolean loadGame = false; // by default 'New Game' is selected

	public StateStartGame()
	{
		super("startgame");
	}

	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		if(game.isDebug())
		{
			game.dbgPrint("LoadGame = " + loadGame);
		}
		//Navigation
		if(game.getKey(game.KeyEnter))
		{
			game.clearKey(game.KeyEnter);
			if(loadGameState == 1)
				game.setCurrentState("EnterPwd");
			else
				game.setCurrentState("InGame");
		}
		else if(game.getKey(game.KeyEsc))
		{
			game.clearKey(game.KeyEsc);
			game.setCurrentState("PlayerSelect");
		}

		//Select new/load game
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
			game.drawImage(0, 0, "menu_bg");
			int x = game.viewWidth() - 30;
			game.drawString("<ESC>    -            Back", x, game.viewHeight() - 60, 1, true);
			game.drawString("<ENTER>    -             Next", x, game.viewHeight() - 40, 1, true);
			game.drawString("<ARROWS>    -   Navigation", x, game.viewHeight() - 20, 1, true);
		game.drawString("Select your action", sgPoint.x, sgPoint.y, 0);

		HamButton b;
		b = new HamButton(sgPoint.x, sgPoint.y, "New Game", hamButtonLabelColor, newGameState);
		b.paint();
		b = new HamButton(sgPoint.x, sgPoint.y + (sgButtonHeight * 2), "Load Game", hamButtonLabelColor, loadGameState);
		b.paint();
	}

	// Player Select Methods
	private void toggleLoadGame()
	{
		if(loadGame)
		{
			newGameState = 1;
			loadGameState = 0;
			loadGame = false;
		}
		else
		{
			newGameState = 0;
			loadGameState = 1;
			loadGame = true;
		}
	}
}
