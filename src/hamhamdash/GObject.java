package hamhamdash;

import jgame.*;

/**
 * This is the superclass for game objects (Diamond and Rocks)
 * @author Cornel Alders
 */
public abstract class GObject extends JGObject
{
	private boolean pickable, pushable, falling;
	private Game game;
	/**
	 *
	 * @param name The object name
	 * @param unique Set false if multiple objects with the same name can exist
	 * @param x Starting X position
	 * @param y Starting Y position
	 * @param cid The collision ID
	 * @param sprite which sprite(animation) to use from the datasheet
	 */
    public GObject(String name, boolean unique, int x, int y, int cid, String sprite)
    {
        super(name, unique, x, y, cid, sprite);
    }

	@Override
	public void move()
	{
		String tile = getTileBelow(this);
		if (tile == ".")
		{
				moveDown();
		}
		if (tile == "R" || "D" || "X" || "#")
		{
			tile = getTileBelowLeft(this);
			if (tile == ".")
			{
				for (int i = 0; i <= 19; i++)
				{
					xspeed = -2;
				}
				stopFalling();
				
				for (int i = 0; i <= 19; i++)
				{
					yspeed = 2;
					
				}
				
				stopFalling();
					
			}
			if (tile == "R" || "D" || "X" || "#")
			{
				tile = getTileBelowRight(this);
				if (tile == ".")
				{
					for (int i = 0; i <= 19; i++)
					{
						xspeed = 2;
					}
					stopFalling();
				    moveDown();
				}
				if (tile == "R" || "D" || "X" || "#")
				{
						return;
				}
			}
		}
	}
	
	/* 	muur = X
		grond = #
		leeg = .
	 */

	@Override
	public abstract void hit_bg(int tilecid);

	@Override
	public abstract void hit(JGObject obj);
	
	public void setPickable(boolean p)
	{
		pickable = p;
	}
	public void setPushable(boolean p)
	{
		pushable = p;
	}
	public void setFalling (boolean f)
	{
		falling = f;
	}
	
	public void moveDown()
	{
		for (int i = 0; i <= 19; i++)
		{
			yspeed = 2;	
		}
		stopFalling();
	}
	public boolean isFalling()
	{
		return falling;
	}
	
}
