package hamhamdash;

import jgame.*;

/**
 * This is the superclass for game characters (Playable and NPC)
 * @author Cornel Alders
 */
public abstract class GCharacter extends JGObject
{
	private Boolean doWalking;
	private int stepInpixels;
	private MoveDirection direction;
	public static Boolean moveUp = false, moveDown = false,
			moveLeft = false, moveRight = false;
	public Game game = Game.getGame();
	private Boolean alive;

	/**
	 *
	 * @param name The object name
	 * @param unique Set false if multiple chracters with the same name can exist (Like common enemies)
	 * @param x Starting X position
	 * @param y Starting Y position
	 * @param cid The collision ID
	 * @param sprite which sprite(animation) to use from the datasheet
	 */

//	public GCharacter(String name, boolean unique, int x, int y, int cid, String sprite)
//	{
//		super(name, unique, x, y, cid, sprite);

	public GCharacter(String name, boolean unique, int x, int y, int cid, String sprite)
	{
		super(name, unique, x, y, cid, sprite);
	}

	public Boolean isWalking()
	{
		return doWalking;
	}

	public MoveDirection getDirection()
	{
		return direction;
	}

	public void setDirection(MoveDirection direction)
	{
		this.direction = direction;
	}

	public void setStepInpixels(int stepInpixels)
	{
		this.stepInpixels = stepInpixels;
	}

	public void stopWalking()
	{
		moveUp = false;
		moveDown = false;
		moveLeft = false;
		moveRight = false;
	}

	public void startWalking()
	{
	}

	@Override
	public abstract void move();

	@Override
	public abstract void hit_bg(int tilecid);

	@Override
	public abstract void hit(JGObject obj);
}
