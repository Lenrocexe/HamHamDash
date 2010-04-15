package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Rock extends GObject
{
	public Rock(String name, boolean unique, int x, int y, int cid, String sprite, Game game)
	{
		super(name, unique, x, y, cid, sprite, game);
	}

	@Override
	public void move(){};

	@Override
	public void hit_bg(int tilecid){};

	@Override
	public void hit(JGObject obj){};
}
