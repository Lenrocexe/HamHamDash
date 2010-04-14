package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	private String type = null;

	/*
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
	public Enemy(String spat, int x, int y)
	{
		super(spat, false, x, y, 1, spat + "Idle");
		this.type = spat;

	}

	@Override
	public void move()
	{
		if (0 < xspeed)
		{
			setGraphic("spatLeft");
		}
		else
		{
			setGraphic("spatRight");
		}
		if (0 < yspeed)
		{
			setGraphic("spatUp");
		}
		else
		{
			setGraphic("spatDown");
		}
//        if (getDirection == GCharacter.moveUp && isWalking()||
//            getDirection == GCharacter.moveDown && isWalking()||
//            getDirection == GCharacter.moveLeft && isWalking()||
//            getDirection == GCharacter.moveRight && isWalking())
//        switch (GCharacter.getDirection())
//	{
//		case GCharacter.moveLeft:
//
//                        if (nextTile == EmptyTile)
//                        {
//                                GCharacter.StepInPixel;
//                        }
//                        else
//                        {
//                            GCharacter.moveDown||GCharacter.moveUp;
//                        }
//
//                        break;
//
//                case GCharacter.moveUp:
//
//                        if (nextTile == EmptyTile)
//                        {
//                                GCharacter.StepInPixel;
//                        }
//                        else
//                        {
//                            GCharacter.moveLeft||GCharacter.moveRight;
//                        }
//
//                        break;
//
//                case GCharacter.moveRight:
//
//                        if (nextTile == EmptyTile)
//                        {
//                                GCharacter.StepInPixel;
//                        }
//                        else
//                        {
//                            GCharacter.moveUp||GCharacter.moveDown;
//                        }
//
//                        break;
//                case GCharacter.moveDown:
//
//                        if (nextTile == EmptyTile)
//                        {
//                                GCharacter.StepInPixel;
//                        }
//                        else
//                        {
//                            GCharacter.moveRight||GCharacter.moveLeft;
//                        }
//
//                        break;
//                default:
//                        break;
//                }
        }

	public void directionview()
    {
//            if (GCharacter.moveUp)
//            {
//                LEFT,UP,RIGHT
//            }
//            else if (GCharacter.moveDown)
//            {
//                RIGHT,DOWN,LEFT
//            }
//            else if (GCharacter.moveLeft)
//            {
//                DOWN,LEFT,UP
//            }
//            else if (GCharacter.moveRight)
//            {
//                UP,RIGHT,DOWN
//            }
    }

	@Override
	public void hit_bg(int tilecid)
	{

	if(!and(checkBGCollision(-xspeed, yspeed), 3))
	{
		xspeed = -xspeed;
	}
	else if(!and(checkBGCollision(xspeed, -yspeed), 3))
	{
		yspeed = -yspeed;
	}
	else if(!and(checkBGCollision(xspeed, -yspeed), 3)
			&& !and(checkBGCollision(-xspeed, -yspeed), 3))
	{
		xspeed = -xspeed;
		yspeed = -yspeed;
	}
	}

	@Override
	public void hit(JGObject obj)
	{
		if (checkCollision(1,xspeed,yspeed)==0)
		{
			// reverse direction
			xspeed = -xspeed;
			yspeed = -yspeed;
		}
	}

	public String getType()
	{
		return type;
	}
}
