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
	public StateEnterPwd()
	{
	}

	@Override
	public void start()
	{
		game.epPoint = new JGPoint(game.pfWidth() / 2, 60);
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
	}

	@Override
	public void doFrame()
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

	@Override
	public void paintFrame()
	{
		game.drawString("Enter Password", game.epPoint.x, game.epPoint.y, 0);

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
			game.drawString(game.passPosList[i], game.epPoint.x + (i * game.ppWidth) - (((game.passPosList.length - 1) * game.ppWidth) / 2), game.epPoint.y + 20, 1);
		}

		if(!game.passIsCorrect && game.passAttempt > 0)
		{
			game.drawString("Password was wrong, please try again!", game.epPoint.x, game.epPoint.y + 50, 0);
		}
	}
}
