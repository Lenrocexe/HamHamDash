package hamhamdash;

import jgame.*;
import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	//private JGEngine game;
	//private GCharacter GCharacter;

	/**
	 *
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public PlayerCharacter(int x, int y, Game game)
	{
		super("player", true, x, y, 1, "hidle", game);
	}

	@Override
	public void move()
	{
		xspeed = 0;
		yspeed = 0;
		xdir = 0;
		ydir = 0;
		if (eng.getKey(eng.KeyUp) && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
			if (y < game.pfHeight() - 230)
			{
				setGraphic("hrunup");
				yspeed = 0;
				ydir = 0;
				System.out.println(x);
				System.out.println(y);
				System.out.println(game.pfHeight());
			}
			else
			{
				setGraphic("hrunup");
				yspeed = -6;
				ydir = 1;
				System.out.println(x);
			}
		}
		else if (eng.getKey(eng.KeyDown) && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
			if (y > game.pfHeight() - 56)
			{
				setGraphic("hrundown");
				yspeed = 0;
				ydir = 0;
			}
			else
			{
				setGraphic("hrundown");
				yspeed = 6;
				ydir = 1;
			}
		}
		else if (eng.getKey(eng.KeyLeft) && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
			if (x < game.pfWidth() - 300)
			{
				setGraphic("hrunleft");
				xspeed = 0;
				xdir = 0;
			}
			else
			{
				setGraphic("hrunleft");
				xspeed = -6;
				xdir = 1;
			}
		}
		else if (eng.getKey(eng.KeyRight) && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
			if (x > game.pfWidth() - 60)
			{
				setGraphic("hrunright");
				xspeed = 0;
				xdir = 0;
			}
			else
			{
				setGraphic("hrunright");
				xspeed = 6;
				xdir = 1;
			}
		}
		else
		{
			setGraphic("hidle");
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

	;

	@Override
	public void hit(JGObject obj)
	{
	}

	;
}
