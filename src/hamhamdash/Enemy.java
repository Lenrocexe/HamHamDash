package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	private String type = null;
	public MoveDirection Direction;

	/*
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public Enemy(String name, int x, int y)
	{
		super(name+""+x+""+y, false, x, y, 2, name + "Idle");


		this.type = name;
		setDirection(MoveDirection.LEFT);
	}

	public MoveDirection getDirection()
	{
		return Direction;
	}

	public void setDirection(MoveDirection newDirection)
	{
		Direction = newDirection;
	}

	private void turnRight()
	{
		if(getDirection() == MoveDirection.LEFT)
		{
			setDirection(MoveDirection.UP);
			setGraphic("SpatAIdle");
		}
		else if(getDirection() == MoveDirection.UP)
		{
			setDirection(MoveDirection.RIGHT);
			setGraphic("SpatAIdle");
		}
		else if(getDirection() == MoveDirection.RIGHT)
		{
			setDirection(MoveDirection.DOWN);
			setGraphic("SpatAIdle");
		}
		else if(getDirection() == MoveDirection.DOWN)
		{
			setDirection(MoveDirection.LEFT);
			setGraphic("SpatAIdle");
		}
	}

	private void turnLeft()
	{
		if(getDirection() == MoveDirection.LEFT)
		{
			setDirection(MoveDirection.DOWN);
		}
		else if(getDirection() == MoveDirection.DOWN)
		{
			setDirection(MoveDirection.RIGHT);
		}
		else if(getDirection() == MoveDirection.RIGHT)
		{
			setDirection(MoveDirection.UP);
		}
		else if(getDirection() == MoveDirection.UP)
		{
			setDirection(MoveDirection.LEFT);
		}
	}

	@Override
	public void move()
	{
//		System.out.println(getDirection());
		switch(getDirection())
		{
			case LEFT:
			{
				xspeed = -2;
				break;
			}
			case RIGHT:
			{
				xspeed = 2;
				break;
			}
			case UP:
			{
				yspeed = -2;
				break;
			}
			case DOWN:
			{
				yspeed = 2;
				break;
			}
			default:
			{
			}
		}
	}

	@Override
	public void hit_bg(int tilecid)
	{
		if(tilecid == 3)
		{
			yspeed = 0;
			xspeed = 0;
			turnRight();
		}
		if(tilecid == 2)
		{
			yspeed = 0;
			xspeed = 0;
			turnRight();
		}
		if(tilecid == 1)
		{
			yspeed = 0;
			xspeed = 0;
			turnRight();
		}
	}

	@Override
	public void hit(JGObject obj)
	{
	}

	public String getType()
	{
		return type;
	}
}
