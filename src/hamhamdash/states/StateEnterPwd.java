package hamhamdash.states;

import hamhamdash.*;
import jgame.JGColor;
import jgame.JGPoint;

/**
 *
 * @author Cornel Alders
 */
public class StateEnterPwd extends State
{
	private boolean started = false;
	private JGPoint epPoint = new JGPoint(game.viewWidth() / 2, 60);
	// pp (passPos) draw attributes
	private int ppWidth = 10;
	private int ppHeight = 10;

	public StateEnterPwd()
	{
	}

	@Override
	public void start()
	{
		game.passString = "";
		game.selectedPos = 0;
		game.selectedNum = 0;
		game.passPosList = new String[6];
		game.passPosList[0] = game.goodNumbers[0];
		game.passPosList[1] = game.goodNumbers[0];
		game.passPosList[2] = game.goodNumbers[0];
		game.passPosList[3] = game.goodNumbers[0];
		game.passPosList[4] = game.goodNumbers[0];
		game.passPosList[5] = game.goodNumbers[0];
		started = true;
		game.loadGame = false;
	}

	@Override
	public void doFrame()
	{
		if(game.isDebug())
		{
			game.dbgPrint("Password = " + game.passString);

			game.dbgPrint(game.selectedPos + "");
			game.dbgPrint(game.goodNumbers[Integer.parseInt(game.passPosList[game.selectedPos])]);
			game.dbgPrint(game.passPosList[game.selectedPos] + "");
			game.dbgPrint(game.selectedNum + "");
		}
		if(started)
		{
			game.selectedNum = Integer.parseInt(game.passPosList[game.selectedPos]);
			if(game.getKey(Game.KeyLeft))
			{
				game.clearKey(Game.KeyLeft);
				if(game.selectedPos > 0)
				{
					game.selectedPos--;
				}
			}
			if(game.getKey(Game.KeyUp))
			{
				game.clearKey(Game.KeyUp);
				if(game.selectedNum < game.goodNumbers.length - 1)
				{
					game.selectedNum++;
				}
				game.passPosList[game.selectedPos] = game.goodNumbers[game.selectedNum];

			}
			if(game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyRight);
				if(game.selectedPos < game.passPosList.length - 1)
				{
					game.selectedPos++;
				}
			}
			if(game.getKey(Game.KeyDown))
			{
				game.clearKey(Game.KeyDown);
				if(game.selectedNum > 0)
				{
					game.selectedNum--;
				}
				game.passPosList[game.selectedPos] = game.goodNumbers[game.selectedNum];
			}
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawString("Enter Password", epPoint.x, epPoint.y, 0);

		// Draw the individual passPos vars
		for(int i = 0; i < game.passPosList.length; i++)
		{
			if(game.selectedPos == i)
			{
				game.setColor(game.selectedPosColor);
			}
			else
			{
				game.setColor(JGColor.white);
			}
			game.drawString(game.passPosList[i], epPoint.x + (i * ppWidth) - (((game.passPosList.length - 1) * ppWidth) / 2), epPoint.y + 20, 1);
		}

		if(!game.passIsCorrect && game.passAttempt > 0)
		{
			game.drawString("Password was wrong, please try again!", epPoint.x, epPoint.y + 50, 0);
		}
	}
}
