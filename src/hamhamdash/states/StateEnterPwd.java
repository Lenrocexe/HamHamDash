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
	// Array with correct password chars
	public String[] goodNumbers =
	{
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
	};
	public int selectedPos, selectedNum;
	public JGColor selectedPosColor = JGColor.red;
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
		selectedPos = 0;
		selectedNum = 0;
		game.passPosList = new String[6];
		game.passPosList[0] = goodNumbers[0];
		game.passPosList[1] = goodNumbers[0];
		game.passPosList[2] = goodNumbers[0];
		game.passPosList[3] = goodNumbers[0];
		game.passPosList[4] = goodNumbers[0];
		game.passPosList[5] = goodNumbers[0];
		game.loadGame = false;
		started = true;
	}

	@Override
	public void doFrame()
	{
		if(game.debug)
		{
			game.dbgPrint("Password = " + game.passString);

			game.dbgPrint(selectedPos + "");
			game.dbgPrint(goodNumbers[Integer.parseInt(game.passPosList[selectedPos])]);
			game.dbgPrint(game.passPosList[selectedPos] + "");
			game.dbgPrint(selectedNum + "");
		}
		if(started)
		{
			selectedNum = Integer.parseInt(game.passPosList[selectedPos]);
			if(game.getKey(Game.KeyLeft))
			{
				game.clearKey(Game.KeyLeft);
				if(selectedPos > 0)
				{
					selectedPos--;
				}
			}
			if(game.getKey(Game.KeyUp))
			{
				game.clearKey(Game.KeyUp);
				if(selectedNum < goodNumbers.length - 1)
				{
					selectedNum++;
				}
				game.passPosList[selectedPos] = goodNumbers[selectedNum];

			}
			if(game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyRight);
				if(selectedPos < game.passPosList.length - 1)
				{
					selectedPos++;
				}
			}
			if(game.getKey(Game.KeyDown))
			{
				game.clearKey(Game.KeyDown);
				if(selectedNum > 0)
				{
					selectedNum--;
				}
				game.passPosList[selectedPos] = goodNumbers[selectedNum];
			}
		}
	}

	@Override
	public void paintFrame()
	{
		if(started)
		{
			game.drawString("Enter Password", epPoint.x, epPoint.y, 0);

			// Draw the individual passPos vars
			for(int i = 0; i < game.passPosList.length; i++)
			{
				if(selectedPos == i)
				{
					game.setColor(selectedPosColor);
				}
				else
				{
					game.setColor(JGColor.white);
				}
				game.drawString(game.passPosList[i], epPoint.x + (i * ppWidth) - (((game.passPosList.length - 1) * ppWidth) / 2), epPoint.y + 20, 1);
			}

			if(!game.passIsCorrect && game.passAttempt > 0)
			{
				game.setColor(JGColor.white);
				game.drawString("Password was wrong, please try again!", epPoint.x, epPoint.y + 50, 0);
			}
		}
	}
}
