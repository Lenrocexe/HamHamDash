package hamhamdash;

import java.util.ArrayList;
import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	private String type = null;
	public MoveDirection Direction;
	private ArrayList<Integer> cids = new ArrayList<Integer>();

	/*
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public Enemy(String name, int x, int y)
	{
		super(name+"_"+x+"_"+y, false, x, y, 2, name + "idle");
		this.type = name;
		setDirection(MoveDirection.RIGHT);
		cids.add(1);
		cids.add(2);
		cids.add(3);
	}

	@Override
	public MoveDirection getDirection()
	{
		return Direction;
	}

	@Override
	public void setDirection(MoveDirection newDirection)
	{
		Direction = newDirection;
	}

	private void turnRight()
	{
		if(getDirection() == MoveDirection.LEFT)
		{
			setDirection(MoveDirection.UP);
			setGraphic("spatAwalkup");
		}
		else if(getDirection() == MoveDirection.UP)
		{
			setDirection(MoveDirection.RIGHT);
			setGraphic("spatAwalkright");
		}
		else if(getDirection() == MoveDirection.RIGHT)
		{
			setDirection(MoveDirection.DOWN);
			setGraphic("spatAwalkdown");
		}
		else if(getDirection() == MoveDirection.DOWN)
		{
			setDirection(MoveDirection.LEFT);
			setGraphic("spatAwalkleft");
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
		switch(getDirection())
		{
			case LEFT:
			{
				yspeed = 0;
				xspeed = -xspeed;
				break;
			}
			case RIGHT:
			{
				yspeed = 0;
				xspeed = 2;
				break;
			}
			case UP:
			{
				xspeed = 0;
				yspeed = -yspeed;
				break;
			}
			case DOWN:
			{
				xspeed = 0;
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
		if(getDirection() == Direction.UP && !and(checkBGCollision(-1, -1), 0))
		{
			doMove();
		}
		else if(getDirection() == Direction.DOWN && !and(checkBGCollision(1, 1), 0))
		{
			doMove();
		}
		else if(getDirection() == Direction.LEFT && !and(checkBGCollision(-1, 0), 0))
		{
			doMove();
		}
		else if(getDirection() == Direction.RIGHT && !and(checkBGCollision(1, 0), 0))
		{
			doMove();
		}
		clearBBox();
		clearTileBBox();
	}

	@Override
	public void hit(JGObject obj)
	{
		if(obj.colid == 3)
		{
			yspeed = 0;
			xspeed = 0;
			turnRight();
		}
	}

	public String getType()
	{
		return type;
	}

	public void doMove()
	{
		if(getType().equals("spatA"))
			turnRight();
		else
			turnLeft();
	}
}
