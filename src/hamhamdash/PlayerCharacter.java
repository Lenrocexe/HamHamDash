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
	private Boolean isWalking = false;
	private int speed = 3;
	JGPoint occupied=null;
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

		if(!isWalking)
		{
			if (eng.getKey(eng.KeyUp) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyDown)))
			{
				setGraphic(getName() + "runup");
				yspeed = speed - 2*speed;
				ydir = 1;
				isWalking = true;

			}
			else if (eng.getKey(eng.KeyDown) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp)))
			{
				setGraphic(getName() + "rundown");
				yspeed = speed;
				ydir = 1;
				isWalking = true;
			}
			else if (eng.getKey(eng.KeyLeft) && stopWalking == false && !(eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
			{
				setGraphic(getName() + "runleft");
				xspeed = speed - 2*speed;
				xdir = 1;
				isWalking = true;
			}
			else if (eng.getKey(eng.KeyRight) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)))
			{
				setGraphic(getName() + "runright");
				xspeed = speed;
				xdir = 1;
				isWalking = true;
			}
			else
			{
				/*if(this.isLeftAligned(1) && this.isBottomAligned(1)){
					System.out.println("IsAligned");
					if(isAlive)
					{
						setGraphic(getName() + "idle");
					}
					xspeed = 0;
					yspeed = 0;
					xdir = 0;
					ydir = 0;
					eng.clearKey(eng.KeyUp);
					eng.clearKey(eng.KeyDown);
					eng.clearKey(eng.KeyLeft);
					eng.clearKey(eng.KeyRight);
				} else {

				}*/
				if(isAlive)
				{
					setGraphic(getName() + "still");
				}
				
			}
		}
		else
		{
			double margin = 1.5;
			if(isXAligned(margin) && isYAligned(margin))
			{
				xspeed = 0;
				yspeed = 0;
				xdir = 0;
				ydir = 0;
				isWalking = false;
			}
		}
	}

	//@Override
	public void hit_bg(int tilecid)
	{
		if (tilecid == 1)
		{


		}
		else if (tilecid == 2)
		{
			game.getCurrentLevel().digTile(getCenterTile().x, getCenterTile().y);
		}
		else if (tilecid == 3)
		{

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
		else if(obj.colid == 3)
		{
			System.out.println("STOP");
			//xspeed = 0;
			//yspeed = 0;
			//xdir = 0;
			//ydir = 0;
		}
	}
}
