package hamhamdash;

import jgame.*;

/**
 * This is the superclass for game objects (Diamond and Rocks)
 * @author Cornel Alders
 */
public abstract class GObject extends JGObject
{
	private boolean pickable, pushable, falling = false;
	private Game game = Game.getGame();

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
//		String tile = getTileBelow(this);
//		if(tile.contains("."))
//		{
//			moveDown();
//		}
//		else if(tile.contains("R") ||
//				tile.contains("D") ||
//				tile.contains("X") ||
//				tile.contains("#"))
//		{
//			tile = getTileBelowLeft(this);
//			if(tile.contains("."))
//			{
//				moveLeft();
//				moveDown();
//
//			}
//			else if(tile.contains("R") ||
//					tile.contains("D") ||
//					tile.contains("X") ||
//					tile.contains("#"))
//			{
//				tile = getTileBelowRight(this);
//				if(tile.contains("."))
//				{
//					moveRight();
//					moveDown();
//				}
//				else if(tile.contains("R") ||
//						tile.contains("D") ||
//						tile.contains("X") ||
//						tile.contains("#"))
//				{
//					return;
//				}
//			}
//		}
	}

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

	public void setFalling(boolean f)
	{
		falling = f;
	}

	public boolean isFalling()
	{
		return falling;
	}

	/**
	 * Prepares the object to fall
	 */
	public void startFalling()
	{
		falling = true;
	}

	/**
	 * Stops the current object
	 */
	public void stopFalling()
	{
		yspeed = 0;
		xspeed = 0;
		falling = false;
	}

	/**
	 * Moves the object 1 tile (exactly 40 pixels) down
	 */
	public void moveDown()
	{
		for (int i = 0; i <= 19; i++)
		{
			yspeed = 2;
		}
		stopFalling();
	}

	public void moveLeft()
	{
		for (int i = 0; i <= 19; i++)
		{
			xspeed = -2;
		}
		stopFalling();
	}

	public void moveRight()
	{
		for (int i = 0; i <= 19; i++)
		{
			xspeed = 2;
		}
		stopFalling();
	}
}
