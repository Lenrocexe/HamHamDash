package hamhamdash;

import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class Levels
{
    private Level objLevel;
    private JGEngine game;

    public Levels(JGEngine Game)
    {
        this.game = Game;
    }
    public void startLevel(){
	objLevel = new Level(game);
	objLevel.createLevel();
    }
}
