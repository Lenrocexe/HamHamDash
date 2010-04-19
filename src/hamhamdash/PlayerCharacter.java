package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	private String name = null;
	private Boolean stop_walking = false;
	//private JGEngine game;
	//private GCharacter GCharacter;
	//private Player player;

	/**
	 *
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public PlayerCharacter(String name, int x, int y)//, Player player)
	{
		super(name+""+x+""+y, true, x, y, 1, name + "idle");
		this.name = name;
		//this.player = player;
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
		if (eng.getKey(eng.KeyUp) && stop_walking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
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
		else if (eng.getKey(eng.KeyDown) && stop_walking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
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
		else if (eng.getKey(eng.KeyLeft) && stop_walking == false && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
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
		else if (eng.getKey(eng.KeyRight) && stop_walking == false && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
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

	//@Override
	public void hit_bg(int tilecid)
	{
		if (tilecid == 1)
		{
			//destroy();
			//System.out.println("1");
			xspeed = 0;
			yspeed = 0;
			xdir = 0;
			ydir = 0;
		}
		else if (tilecid == 2)
		{
			//setGraphic(getName() + "runleft");
			//System.out.println("2");
			xspeed = 0;
			yspeed = 0;
			xdir = 0;
			ydir = 0;
		}
		else if (tilecid == 3)
		{
			//System.out.println("3");
			//remove();
		}
	}
	//@Override
	//public void destroy()
	//{
	//	setGraphic(getName() + "howdie");
//	}

	@Override
	public void hit(JGObject obj)
	{
		System.out.println("Bam");
		stop_walking = true;
		setGraphic(getName() + "howdie");
		new JGTimer(70, true)
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				remove();
				if (game.getPlayer().getLifes() > 0)
				{
					game.getPlayer().removeLife();
					game.setCurrentState("Death");
					System.out.println("Continue!!!");
				}
				else
				{
					game.setCurrentState("GameOver");
					System.out.println("Game over!!!");
				}
			}
		};

		//remove();
	}
}
