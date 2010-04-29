package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class PlayerCharacter extends GCharacter
{
	private Player player = null;
	private String name = null;
	private Boolean stopWalking = false;
	private Boolean isAlive = true;
	private Boolean isWalking = false;
	private int speed = 5;
	JGPoint occupied = null;

	/**
	 *
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public PlayerCharacter(String name, int x, int y, Player player)
	{
		super(name, true, x, y, 1, name + "idle");
		this.player = player;
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
			if(eng.getKey(eng.KeyUp) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyDown)) && !(tile[1][0].equals("X")))
			{
				setGraphic(getName() + "runup");
				setDirection(MoveDirection.UP);
				if(checkCollision(4, 0, -speed) == 4)
					return;
				yspeed = -speed;
				ydir = 1;
				isWalking = true;
			}
			else if(eng.getKey(eng.KeyDown) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp)) && !(tile[6][0].equals("X")))
			{
				setGraphic(getName() + "rundown");
				setDirection(MoveDirection.DOWN);
				if(checkCollision(4, 0, speed) == 4)
					return;
				setDirSpeed(0, 1, 0, speed);
				isWalking = true;
			}
			else if(eng.getKey(eng.KeyLeft) && stopWalking == false && !(eng.getKey(eng.KeyRight) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)) && !(tile[3][0].equals("X")))
			{
				setGraphic(getName() + "runleft");
				setDirection(MoveDirection.LEFT);
				if(checkCollision(4, -speed, 0) == 4)
					return;
				setDirSpeed(1, 0, -speed, 0);
				isWalking = true;
			}
			else if(eng.getKey(eng.KeyRight) && stopWalking == false && !(eng.getKey(eng.KeyLeft) || eng.getKey(eng.KeyUp) || eng.getKey(eng.KeyDown)) && !(tile[4][0].equals("X")))
			{
				setGraphic(getName() + "runright");
				setDirection(MoveDirection.RIGHT);
				if(checkCollision(4, speed, 0) == 4)
					return;
				setDirSpeed(1, 0, speed, 0);
				isWalking = true;
			}
			else
			{
				if(isAlive)
				{
					setGraphic(getName() + "idle");
				}
			}
		}
		else
		{
			double margin = 1;
			if(isXAligned(margin) && isYAligned(margin))
			{
				xspeed = 0;
				yspeed = 0;
				xdir = 0;
				ydir = 0;

				isWalking = false;
				x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
				y = Math.round(y / game.getTileSize()) * game.getTileSize();
			}
		}
	}

	//@Override
	public void hit_bg(int tilecid)
	{
		if(tilecid == 2)
		{
			game.getCurrentLevel().digTile(getCenterTile().x, getCenterTile().y);
		}
		else if(tilecid == 7)
		{
			game.addState("Win");
		}
	}

	@Override
	public void hit(JGObject obj)
	{
		if(obj.colid == 4)
		{
			Rock collidRock = game.getObjLevels().getCurrentLevel().getRock(obj.getName());
			if(!collidRock.isFalling())
			{
				System.out.println("CANT MOVE!");
				xspeed = 0;
				yspeed = 0;
				xdir = 0;
				ydir = 0;

				if(getDirection() == MoveDirection.UP)
				{
					yspeed = speed;
					ydir = 1;
				}
				else if(getDirection() == MoveDirection.DOWN)
				{
					yspeed = -speed;
					ydir = 1;
				}
				else if(getDirection() == MoveDirection.LEFT)
				{
					xspeed = speed;
					xdir = 1;
				}
				else if(getDirection() == MoveDirection.RIGHT)
				{
					xspeed = -speed;
					xdir = 1;
				}
				setDirSpeed(xdir, ydir, xspeed, yspeed);

				double margin = 1;
				if(isXAligned(margin) && isYAligned(margin))
				{
					xspeed = 0;
					yspeed = 0;
					xdir = 0;
					ydir = 0;

					isWalking = false;
					x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
					y = Math.round(y / game.getTileSize()) * game.getTileSize();
				}
			}
		}
		else if(obj.colid == 2)
		{
			obj.remove();
			kill();
		}
		else if(obj.colid == 3)
		{
			if(obj.getCenterTile().x == getCenterTile().x)
			{
				game.getCurrentLevel().pickupDiamond(obj);
				player.addToLevelScore(10);
			}
		}
	}

	public void kill()
	{
		stopWalking = true;
		isAlive = false;
		player.resetLevelScore();
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
