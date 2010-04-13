package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	private String type = null;

	/**
	 *
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
    public Enemy(String name, int x, int y)
    {
        super(name, false, x, y, 1, name+"Idle");
		this.type = name;
    }

	@Override
	public void move(){};

	@Override
	public void hit_bg(int tilecid){};

	@Override
	public void hit(JGObject obj){};

	public String getType()
	{
		return type;
	}
}
