package hamhamdash.states;

import hamhamdash.*;
import hamhamdash.State;
import jgame.JGTimer;

/**
 *
 * @author Serhan Uygur
 */
public class StateDeath extends State
{
    public StateDeath()
    {
        super("death");
        game.stopAudio();
    }

    @Override
    public void start()
    {
    }

    @Override
    public void doFrame()
    {
        game.moveObjects(game.getPlayer().getIdentifier(), 0);
        // wait a bit for Hamtaro to finish dieing
//        new JGTimer(50, true, "Death")
//        {
//            //the alarm method is called when the timer ticks to zero
//            public void alarm()
//            {
                // remove Life cuz you diedz!
                game.getPlayer().removeLife();
                game.removeObjects("", 0); // Clear all objects from the field

                if(game.countPlayers() == 2)
                {
                    game.switchPlayers();
                }

                game.resetViewport();

                if(game.getPlayer().getLifes() > 0)
                {
                    game.setCurrentState("Restart");
                }
                else
                {
                    game.setCurrentState("GameOver");
                }
            }
//        };
//    }

    @Override
    public void paintFrame()
    {
    }
}
