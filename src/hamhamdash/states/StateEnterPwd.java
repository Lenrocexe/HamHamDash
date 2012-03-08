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
    private String[] goodNumbers =
    {
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    private int selectedPos, selectedNum;
    private JGColor selectedPosColor = JGColor.red;
    private boolean started = false;
    private JGPoint epPoint = new JGPoint(game.viewWidth() / 2, 60);
    // pp (passPos) draw attributes
    private int ppWidth = 10;
    private int ppHeight = 10;
    // 1 var for each password position, these vars will combine to be the passString
    private String[] passPosList;
    private boolean passIsCorrect = false;
    private int passAttempt = 0;
    private String passString;

    public StateEnterPwd()
    {
        super("enterpwd");
        passString = "";
        selectedPos = 0;
        selectedNum = 0;
        passPosList = new String[6];
        passPosList[0] = goodNumbers[0];
        passPosList[1] = goodNumbers[0];
        passPosList[2] = goodNumbers[0];
        passPosList[3] = goodNumbers[0];
        passPosList[4] = goodNumbers[0];
        passPosList[5] = goodNumbers[0];
        started = true;
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
            game.dbgPrint("Password = " + passString);

            game.dbgPrint(selectedPos + "");
            game.dbgPrint(goodNumbers[Integer.parseInt(passPosList[selectedPos])]);
            game.dbgPrint(passPosList[selectedPos] + "");
            game.dbgPrint(selectedNum + "");
        }
        if(started)
        {
            selectedNum = Integer.parseInt(passPosList[selectedPos]);

            if(game.getKey(game.KeyEnter))
            {
                game.clearKey(game.KeyEnter);
                passString = "";
                for(String perPass : passPosList)
                {
                    passString += perPass;
                }
                if(game.getObjLevels().checkPassword(passString))
                {
                    passIsCorrect = true;
                    game.setCurrentState("InGame");
                }
                else
                {
                    passIsCorrect = false;
                    passAttempt++;
                }
            }
            else if(game.getKey(game.KeyEsc))
            {
                game.clearKey(game.KeyEsc);
                game.setCurrentState("StartGame");
            }

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
                passPosList[selectedPos] = goodNumbers[selectedNum];

            }
            if(game.getKey(Game.KeyRight))
            {
                game.clearKey(Game.KeyRight);
                if(selectedPos < passPosList.length - 1)
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
                passPosList[selectedPos] = goodNumbers[selectedNum];
            }
        }
    }

    @Override
    public void paintFrame()
    {
        if(started)
        {
            game.drawImage(0, 0, "menu_bg");
            game.drawString("Enter Password", epPoint.x, epPoint.y, 0);

            // Draw the individual passPos vars
            for(int i = 0; i < passPosList.length; i++)
            {
                if(selectedPos == i)
                {
                    game.setColor(selectedPosColor);
                }
                else
                {
                    game.setColor(JGColor.white);
                }
                game.drawString(passPosList[i], epPoint.x + (i * ppWidth) - (((passPosList.length - 1) * ppWidth) / 2), epPoint.y + 20, 1);
            }

            if(!passIsCorrect && passAttempt > 0)
            {
                game.setColor(JGColor.white);
                game.drawString("Password was wrong, please try again!", epPoint.x, epPoint.y + 50, 0);
            }
        }
    }
}
