package hamhamdash;

import jgame.*;

/**
 * This is the superclass for game objects (Diamond and Rocks)
 * @author Cornel Alders
 */
public abstract class GObject extends JGObject
{
	public boolean pickable, pushable, falling = false;
	public Game game = Game.getGame();
	private int speed = 3;

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
		if(isFalling())
		{
			startFalling();
			moveDown();
		}
		else
		{
			double margin = 1;
			if(isXAligned(margin) && isYAligned(margin))
			{
				stopFalling();
			}
		}

		/*else if(tile[5][0].contains("P") || tile[5][0].contains("E") && isFalling())
		{
			//explode();
		}
		else if(tile[5][0].contains("R")
				|| tile[5][0].contains("D")
				|| tile[5][0].contains("X")
				|| tile[5][0].contains("#"))
		{
			// left and down
			if(tile[6][0].contains(".") && tile[7][0].contains("."))
			{
				startFalling();
				moveLeft();
				startFalling();
				moveDown();
			}
			else if(tile[6][0].contains("P") || tile[5][0].contains("E") && isFalling())
			{
				//explode();
			}
			else if(tile[6][0].contains("R") && tile[5][0].contains("R")
					|| tile[6][0].contains("D") && tile[5][0].contains("D")
					|| tile[6][0].contains("X") && tile[5][0].contains("X")
					|| tile[6][0].contains("#") && tile[5][0].contains("#"))
			{
				// right and down
				if(tile[6][0].contains(".") && tile[5][0].contains("."))
				{
					startFalling();
					moveRight();
					startFalling();
					moveDown();
				}
				else if(tile[6][0].contains("P") || tile[6][0].contains("E") && isFalling())
				{
					//explode();
				}
			}
			else if(tile[6][0].contains("R") && tile[6][0].contains("R")
					|| tile[6][0].contains("D") && tile[6][0].contains("D")
					|| tile[6][0].contains("X") && tile[6][0].contains("X")
					|| tile[6][0].contains("#") && tile[6][0].contains("#"))
			{
				return;
			}
		}*/
	}

	@Override
	public void hit_bg(int tilecid)
	{
		String[][] tile = game.getCurrentLevel().getSurroundingTiles(this.getCenterTile().x, this.getCenterTile().y);

		if(tile[6][0].contains(".") && !(game.getPlayer().getPc().getCenterTiles().y - 1 == this.getCenterTile().y && game.getPlayer().getPc().getCenterTiles().x == this.getCenterTile().x))
		{
			setFalling(true);
		}
		else if(tile[6][0].contains("#") || tile[6][0].contains("X") || tile[6][0].contains("C") || tile[6][0].contains("O"))
		{
			setFalling(false);
		}
	}

	@Override
	public void hit(JGObject obj)
	{
		stopFalling();
	}

	public void setFalling(boolean falls)
	{
		falling = falls;
	}

	public void setPickable(boolean pickedUp)
	{
		pickable = pickedUp;
	}

	public void setPushable(boolean pushed)
	{
		pushable = pushed;
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
		xspeed = 0;
		yspeed = 0;
		xdir = 0;
		ydir = 0;
		falling = false;
		x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
		y = Math.round(y / game.getTileSize()) * game.getTileSize();
	}

	/**
	 * Moves the object 1 tile (exactly 40 pixels) down
	 */
	public void moveDown()
	{
		yspeed = speed;
		ydir = 1;
	}

	public void moveLeft()
	{
		xspeed = -speed;
		xdir = 1;
	}

	public void moveRight()
	{
		xspeed = speed;
		xdir = 1;
	}
}
