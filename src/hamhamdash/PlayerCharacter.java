package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	/**
	 *
	 * @param x Starting x position
	 * @param y Starting y position
	 */
    public PlayerCharacter(int x, int y)
    {
        super("player", true, x, y, 1, "hidle");
    }

	@Override
	public void move(){};

	@Override
	public void hit_bg(int tilecid){};

	@Override
	public void hit(JGObject obj){};
}
