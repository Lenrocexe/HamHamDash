package hamhamdash.states;

import hamhamdash.*;
import jgame.JGPoint;

/**
 *
 * @author Cornel Alders
 */
public class StatePlayerSelect extends State
{
    private JGPoint psPoint = new JGPoint(game.viewWidth() / 2, 60);
    private String playerOneButtonState;
    private String playerOneAltButtonState;
    private String playerTwoButtonState;
    private int playerAmount = 1;
    private String playerChar = "hamtaro";

    public StatePlayerSelect()
    {
        super("playerselect");
        game.clearPlayerList();
        playerOneButtonState = "rollover"; // 'player one' is highlighted as default
        playerOneAltButtonState = "normal"; // 'player one alt' is not
        playerTwoButtonState = "normal"; // 'player two' is not

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
            game.dbgPrint("PlayerAmount = " + playerAmount);
        }

        //Navigation
        if(game.getKey(game.KeyEnter))
        {
            game.clearKey(game.KeyEnter);
            createPlayers();
            game.setCurrentState("StartGame");
        }
        else if(game.getKey(game.KeyEsc))
        {
            game.clearKey(game.KeyEsc);
            game.clearPlayerList();
            game.setCurrentState("Title");
        }

        //Player select
        if(game.getKey(Game.KeyRight) || game.getKey(Game.KeyLeft))
        {
            game.clearKey(Game.KeyRight);
            game.clearKey(Game.KeyLeft);
            togglePlayerChar();
        }
        if(game.getKey(Game.KeyUp))
        {
            game.clearKey(Game.KeyUp);
            togglePlayerSelect();
        }
        if(game.getKey(Game.KeyDown))
        {
            game.clearKey(Game.KeyDown);
            togglePlayerSelect();
        }
    }

    @Override
    public void paintFrame()
    {
        if(started)
        {
            game.drawImage(0, 0, "menu_bg");
            game.drawString("Select amount of Player", psPoint.x, psPoint.y, 0);
            game.drawImage(psPoint.x - (75 / 2), psPoint.y + 30, "player1_button_" + playerOneButtonState);
            game.drawImage(psPoint.x, psPoint.y + 30, "player1alt_button_" + playerOneAltButtonState);
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
            playerOneAltButtonState = "normal";
            playerTwoButtonState = "rollover";
            playerAmount = 2;
        }
        else if(playerAmount == 2)
        {
            playerOneButtonState = "rollover";
            playerOneAltButtonState = "normal";
            playerTwoButtonState = "normal";
            playerAmount = 1;
        }
    }

    /**
     * Creates players based on the selection made.
     * Always creates Hamtaro as a player.
     */
    private void createPlayers()
    {
        game.addPlayer(new Player(playerChar));
        if(playerAmount == 2)
        {
            game.addPlayer(new Player("bijou"));
        }
        game.setActivePlayer(0);
    }

    private void togglePlayerChar()
    {
        if(playerChar.equals("hamtaro"))
        {
            playerOneButtonState = "normal";
            playerOneAltButtonState = "rollover";
            playerTwoButtonState = "normal";
            playerChar = "biyou";
        }
        else if(playerChar.equals("biyou"))
        {
            playerOneButtonState = "rollover";
            playerOneAltButtonState = "normal";
            playerTwoButtonState = "normal";
            playerChar = "hamtaro";
        }
    }
}
