package hamhamdash.states;

import hamhamdash.*;
import jgame.JGPoint;

/**
 *
 * @author Cornel Alders
 */
public class StatePlayerSelect extends State
{
	private boolean started = false; //lowsy boolean for start() method
	private JGPoint psPoint = new JGPoint(game.viewWidth() / 2, 60);
	private String playerOneButtonState;
	private String playerTwoButtonState;
	private int playerAmount = 1;

	public StatePlayerSelect()
	{
	}

	@Override
	public void start()
	{
		game.clearPlayerList();
		playerOneButtonState = "rollover"; // 'player one' is highlighted as default
		playerTwoButtonState = "normal"; // 'player two' is not
		started = true;
	}

	@Override
	public void doFrame()
	{
		if(game.isDebug())
		{
			game.dbgPrint("PlayerAmount = " + playerAmount);
		}
		if(game.getKey(game.KeyEnter))
		{
			game.clearKey(game.KeyEnter);
			createPlayers();
			game.stateCounter = game.nextState();
		}
		else if(game.getKey(game.KeyEsc))
		{
			game.clearKey(game.KeyEsc);
			game.clearPlayerList();
			game.stateCounter = game.prevState();
		}

		if(game.getKey(Game.KeyLeft) || game.getKey(Game.KeyUp))
		{
			game.clearKey(Game.KeyLeft);
			game.clearKey(Game.KeyUp);
			togglePlayerSelect();
		}
		if(game.getKey(Game.KeyRight) || game.getKey(Game.KeyDown))
		{
			game.clearKey(Game.KeyRight);
			game.clearKey(Game.KeyDown);
			togglePlayerSelect();
		}
	}

	@Override
	public void paintFrame()
	{
		if(started)
		{
			game.drawString("Select amount of Player", psPoint.x, psPoint.y, 0);
			game.drawImage(psPoint.x - (75 / 2), psPoint.y + 30, "player1_button_" + playerOneButtonState);
			game.drawImage(psPoint.x - (75 / 2), psPoint.y + 65, "player2_button_" + playerTwoButtonState);
		}
	}

	/**
	 * Toggles button images
	 */
	private void togglePlayerSelect()
	{
		if(playerAmount == 1)
		{
			playerOneButtonState = "normal";
			playerTwoButtonState = "rollover";
			playerAmount = 2;
		}
		else if(playerAmount == 2)
		{
			playerOneButtonState = "rollover";
			playerTwoButtonState = "normal";
			playerAmount = 1;
		}
	}

	public void createPlayers()
	{
		game.addPlayer(new Player("Hamtaro"));
		if(playerAmount == 2)
		{
			game.addPlayer(new Player("Biyou"));
		}
		game.setActivePlayer(1);
	}
}
