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
	private int speed = 5;
	JGPoint occupied=null;
	private int counter = 0;
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
		super(name, true, x, y, 1, name + "still");
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
		String[][] tile = game.getCurrentLevel().getSurroundingTiles(this.getCenterTile().x, this.getCenterTile().y);
		if(!isWalking)
		{
			if (eng.getKey(eng.KeyUp) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyDown)) && !(tile[1][0].equals("X")))
			{
				setGraphic(getName() + "runup");
				yspeed = speed - 2*speed;
				ydir = 1;
				isWalking = true;
			}
			else if (eng.getKey(eng.KeyDown) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp)) && !(tile[6][0].equals("X")))
			{
				setGraphic(getName() + "rundown");
				yspeed = speed;
				ydir = 1;
				isWalking = true;
			}
			else if (eng.getKey(eng.KeyLeft) && stopWalking == false && !(eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)) && !(tile[3][0].equals("X")))
			{
				setGraphic(getName() + "runleft");
				xspeed = speed - 2*speed;
				xdir = 1;
				isWalking = true;
			}
			else if (eng.getKey(eng.KeyRight) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)) && !(tile[4][0].equals("X")))
			{
				setGraphic(getName() + "runright");
				xspeed = speed;
				xdir = 1;
				isWalking = true;
			}
			else
			{
				if(isAlive)
				{
					setGraphic(getName() + "still");
				}
			}
		}
		else
		{
			double margin = 1.9;
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
		if (tilecid == 2)
		{
			game.getCurrentLevel().digTile(getCenterTile().x, getCenterTile().y);
		}
		else if (tilecid == 7)
		{
			game.addState("Win");
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
			obj.remove();
			kill();
		}
		else if(obj.colid == 3)
		{
			if(obj.getCenterTile().x == game.player.getPc().getCenterTile().x)
			{
				game.getCurrentLevel().pickupDiamond(obj);
			}
		}
	}

	public void kill()
	{
		stopWalking = true;
		isAlive = false;
		setGraphic(getName() + "howdie");
		game.addState("Death");
	}
	public void setAlive(boolean b)
	{
		this.isAlive = b;
	}

	public void setWalking(boolean b)
	{
		this.stopWalking = b;
	}
}
