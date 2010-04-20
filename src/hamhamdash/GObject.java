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
		String[][] tile = game.getCurrentLevel().getSurroundingTiles(this.getCenterTile().x, this.getCenterTile().y);
		
		if(tile[5][0].contains(".") && !(game.player.getPc().getCenterTiles().y-1 == this.getCenterTile().y && game.player.getPc().getCenterTiles().x == this.getCenterTile().x))
		{
			startFalling();
			moveDown();
		}
		else if(tile[5][0].contains("#") || tile[5][0].contains("X") || tile[5][0].contains("C") || tile[5][0].contains("O"))
		{
			double margin = 1.9;
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
	
	}

	@Override
	public void hit(JGObject obj)
	{
		if(obj.colid == 1)
		{
			if(this.isFalling() && this.getCenterTile().x == game.player.getPc().getCenterTile().x){
				game.player.kill();
			} else {
				stopFalling();
			}
		}
		else if(obj.colid == 2)
		{
			if(this.isFalling())
			{
				obj.remove();
			}
		}
	}

	public void setPickable(boolean p)
	{
		pickable = p;
	}

	public void setPushable(boolean p)
	{
		pushable = p;
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

	}

	public void moveRight()
	{

	}

}
