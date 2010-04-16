package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Enemy extends GCharacter
{
	private String type = null;
	private String turnLeft;
	/*
	 * @param name Possible choices for name are "spat" and "spatAlt"
	 * @param x Starting x position
	 * @param y Starting y position
	 */
		public Enemy(String name, int x, int y)
	{
		super(name, false, x, y, 1, name + "Idle");
		this.type = name;
	}

	@Override
	public void move()
	{


		if (0 < xspeed)
		{
			setGraphic("SpatAIdle");
		}
		else
		{
			setGraphic("SpatAIdle");
		}
		if (0 < yspeed)
		{
			setGraphic("SpatAIdle");
		}
		else
		{
			setGraphic("SpatAIdle");
		}

//        if (getDirection == GCharacter.moveUp && isWalking()||
//            getDirection == GCharacter.moveDown && isWalking()||
//            getDirection == GCharacter.moveLeft && isWalking()||
//            getDirection == GCharacter.moveRight && isWalking())
		}
        
//	switch (EnemyDirection);
//	{
//		case MoveDirection:
//
//                        if (nextTile == EmptyTile)
//                        {
//                                GCharacter.stepInpixels;
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
//        }

//	public void directionview()
//    {
//            if (moveUp)
//            {
//                [LEFT,UP,RIGHT]
//            }
//            else if (moveDown)
//            {
//                [RIGHT,DOWN,LEFT]
//            }
//            else if (moveLeft)
//            {
//                [DOWN,LEFT,UP]
//            }
//            else if (moveRight)
//            {
//                [UP,RIGHT,DOWN]
//            }
//    }

	@Override
	public void hit_bg(int tilecid)
	{
//	if (nextToPlayer)
	
//		Player.removeLife();
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