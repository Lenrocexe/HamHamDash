package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	private String name = null;
	private Boolean stopWalking = false;
	private Boolean isAlive = true;
	private int speed = 3;
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
		super(name, true, x, y, 1, name + "idle");
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
		if (eng.getKey(eng.KeyUp) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
//			if (y < game.viewHeight() - 230)
//			{
//				setGraphic(getName() + "runup");
//				yspeed = 0;
//				ydir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runup");
				yspeed = speed - 2*speed;
				ydir = 1;
			}
		}
		else if (eng.getKey(eng.KeyDown) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight)))
		{
//			if (y > game.viewHeight()- 57)
//			{
//				setGraphic(getName() + "rundown");
//				yspeed = 0;
//				ydir = 0;
//			}
//			else
			{
				setGraphic(getName() + "rundown");
				yspeed = speed;
				ydir = 1;
			}
		}
		else if (eng.getKey(eng.KeyLeft) && stopWalking == false && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
//			if (x < game.viewWidth() - 300)
//			{
//				setGraphic(getName() + "runleft");
//				xspeed = 0;
//				xdir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runleft");
				xspeed = speed - 2*speed;
				xdir = 1;
			}
		}
		else if (eng.getKey(eng.KeyRight) && stopWalking == false && !(eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
		{
//			if (x > game.viewWidth() - 60)
//			{
//				setGraphic(getName() + "runright");
//				xspeed = 0;
//				xdir = 0;
//			}
//			else
			{
				setGraphic(getName() + "runright");
				xspeed = speed;
				xdir = 1;
			}
		}
		else
		{
			if(isAlive)
			{	
				setGraphic(getName() + "still");
			}
			xspeed = 0;
			yspeed = 0;
			xdir = 0;
			ydir = 0;
			eng.clearKey(eng.KeyUp);
			eng.clearKey(eng.KeyDown);
			eng.clearKey(eng.KeyLeft);
			eng.clearKey(eng.KeyRight);
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

			game.getCurrentLevel().digTile(getCenterTile().x, getCenterTile().y);
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
		if(obj.colid == 2)
		{
			stopWalking = true;
			isAlive = false;
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
						game.resetViewport();
						game.setCurrentState("GameOver");
					}
				}
			};
		}

		//remove();
	}
}
