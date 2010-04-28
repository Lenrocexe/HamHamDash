package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	public MoveDirection Direction;
	public int speed = 1;
	public boolean isAligned = false;
	public boolean hitObject = false;

	/*
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public Enemy(String name, int x, int y)
	{
		super(name + "_" + x + "_" + y, false, x, y, 2, name + "still");
		setDirection(MoveDirection.RIGHT);
		setGraphic("spatAwalkright");
	}

	public void turnClockwise()
	{
		String[][] tile = game.getCurrentLevel().getSurroundingTiles(getCenterTile().x, getCenterTile().y);

		if(isAligned || hitObject)
		{
			if(getDirection() == MoveDirection.LEFT)
			{
				if(hitObject)
				{
					x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
					y = Math.round(y / game.getTileSize()) * game.getTileSize();
					setDirection(MoveDirection.RIGHT);
					setGraphic("spatAwalkright");
					hitObject = false;
				}
				else if(tile[1][0].equals("."))
				{
					setDirection(MoveDirection.UP);
					setGraphic("spatAwalkup");
				}
				else if(tile[3][0].equals("."))
				{
					//Just keep on movin'
				}
				else if(tile[6][0].equals("."))
				{
					setDirection(MoveDirection.DOWN);
					setGraphic("spatAwalkdown");
				}
				else if(tile[4][0].equals("."))
				{
					setDirection(MoveDirection.RIGHT);
					setGraphic("spatAwalkright");
				}
			}
			else if(getDirection() == MoveDirection.UP)
			{
				if(hitObject)
				{
					x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
					y = Math.round(y / game.getTileSize()) * game.getTileSize();
					setDirection(MoveDirection.DOWN);
					setGraphic("spatAwalkdown");
					hitObject = false;
				}
				else if(tile[4][0].equals("."))
				{
					setDirection(MoveDirection.RIGHT);
					setGraphic("spatAwalkright");
				}
				else if(tile[1][0].equals("."))
				{
					//Just keep on movin'
				}
				else if(tile[3][0].equals("."))
				{
					setDirection(MoveDirection.LEFT);
					setGraphic("spatAwalkleft");
				}
				else if(tile[6][0].equals("."))
				{
					setDirection(MoveDirection.DOWN);
					setGraphic("spatAwalkdown");
				}
			}
			else if(getDirection() == MoveDirection.RIGHT)
			{
				if(hitObject)
				{
					x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
					y = Math.round(y / game.getTileSize()) * game.getTileSize();
					setDirection(MoveDirection.LEFT);
					setGraphic("spatAwalkleft");
					hitObject = false;
				}
				else if(tile[6][0].equals("."))
				{
					setDirection(MoveDirection.DOWN);
					setGraphic("spatAwalkdown");
				}
				else if(tile[4][0].equals("."))
				{
					//Just keep on movin'
				}
				else if(tile[1][0].equals("."))
				{
					setDirection(MoveDirection.UP);
					setGraphic("spatAwalkup");
				}
				else if(tile[3][0].equals("."))
				{
					setDirection(MoveDirection.LEFT);
					setGraphic("spatAwalkleft");
				}
			}
			else if(getDirection() == MoveDirection.DOWN)
			{
				if(hitObject)
				{
					x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
					y = Math.round(y / game.getTileSize()) * game.getTileSize();
					setDirection(MoveDirection.UP);
					setGraphic("spatAwalkup");
					hitObject = false;
				}
				else if(tile[3][0].equals("."))
				{
					setDirection(MoveDirection.LEFT);
					setGraphic("spatAwalkleft");
				}
				else if(tile[6][0].equals("."))
				{
					//Just keep on movin'
				}
				else if(tile[4][0].equals("."))
				{
					setDirection(MoveDirection.RIGHT);
					setGraphic("spatAwalkright");
				}
				else if(tile[1][0].equals("."))
				{
					setDirection(MoveDirection.UP);
					setGraphic("spatAwalkup");
				}
			}
			isAligned = false;
		}
		else
		{
			double margin = 1;
			if(isXAligned(margin) && isYAligned(margin))
			{
				isAligned = true;
				x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
				y = Math.round(y / game.getTileSize()) * game.getTileSize();
			}
		}
	}

	public void turnLeft()
	{
		if(getDirection() == MoveDirection.LEFT)
		{
			setDirection(MoveDirection.DOWN);
			setGraphic("spatAwalkdown");
		}
		else if(getDirection() == MoveDirection.DOWN)
		{
			setDirection(MoveDirection.RIGHT);
			setGraphic("spatAwalkright");
		}
		else if(getDirection() == MoveDirection.RIGHT)
		{
			setDirection(MoveDirection.UP);
			setGraphic("spatAwalkup");
		}
		else if(getDirection() == MoveDirection.UP)
		{
			setDirection(MoveDirection.LEFT);
			setGraphic("spatAwalkleft");
		}
	}

	public void kill()
	{
		this.remove();
	}

	@Override
	public void move()
	{
		if(!isAligned)
		{
			double margin = 0;
			if(isXAligned(margin) && isYAligned(margin))
			{
				isAligned = true;
				x = Math.round(x / game.getTileSize()) * game.getTileSize(); // X and Y Correction
				y = Math.round(y / game.getTileSize()) * game.getTileSize();
				turnClockwise();
			}
			else
			{
				isAligned = false;
			}
		}

		switch(getDirection())
		{
			case LEFT:
			{
				yspeed = 0;
				xspeed = -speed;
				break;
			}
			case RIGHT:
			{
				yspeed = 0;
				xspeed = speed;
				break;
			}
			case UP:
			{
				xspeed = 0;
				yspeed = -speed;
				break;
			}
			case DOWN:
			{
				xspeed = 0;
				yspeed = speed;
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
	}

	@Override
	public void hit(JGObject obj)
	{
		if(obj.colid == 3 || obj.colid == 1 || obj.colid == 2 || obj.colid == 4)
		{
			hitObject = true;
			turnClockwise();
		}
	}
}
