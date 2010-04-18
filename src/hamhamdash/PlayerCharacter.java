package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	private String name = null;
	//private JGEngine game;
	//private GCharacter GCharacter;

	/**
	 *
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public PlayerCharacter(String name, int x, int y)
	{
		super(name, true, x, y, 1, name + "idle");
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void move()
	{
		xspeed = 0;
		yspeed = 0;
		xdir = 0;
		ydir = 0;
		if(eng.getKey(eng.KeyUp) && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
//			if (y < game.pfHeight() - 230)
//			{
//				setGraphic(getName() + "runup");
//				yspeed = 0;
//				ydir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runup");
				yspeed = -6;
				ydir = 1;
			}
		}
		else if(eng.getKey(eng.KeyDown) && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
//			if (y > game.pfHeight() - 57)
//			{
//				setGraphic(getName() + "rundown");
//				yspeed = 0;
//				ydir = 0;
//			}
//			else
			{
				setGraphic(getName() + "rundown");
				yspeed = 6;
				ydir = 1;
			}
		}
		else if(eng.getKey(eng.KeyLeft) && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
//			if (x < game.pfWidth() - 300)
//			{
//				setGraphic(getName() + "runleft");
//				xspeed = 0;
//				xdir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runleft");
				xspeed = -6;
				xdir = 1;
			}
		}
		else if(eng.getKey(eng.KeyRight) && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
//			if (x > game.pfWidth() - 60)
//			{
//				setGraphic(getName() + "runright");
//				xspeed = 0;
//				xdir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runright");
				xspeed = 6;
				xdir = 1;
			}
		}
		else
		{
//			setGraphic(getName() + "idle");
			xspeed = 0;
			yspeed = 0;
			xdir = 0;
			ydir = 0;
		}
	}

	@Override
	public void hit_bg(int tilecid)
	{
	}

	@Override
	public void hit(JGObject obj)
	{
	}
}
