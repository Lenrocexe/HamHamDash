package hamhamdash;

import jgame.*;

/**
 * This is the superclass for game characters (Playable and NPC)
 * @author Cornel Alders
 */
public abstract class GCharacter extends JGObject
{
	private Game game = null;

	/**
	 *
	 * @param name The object name
	 * @param unique Set false if multiple chracters with the same name can exist (Like common enemies)
	 * @param x Starting X position
	 * @param y Starting Y position
	 * @param cid The collision ID
	 * @param sprite which sprite(animation) to use from the datasheet
	 */
	public GCharacter(String name, boolean unique, int x, int y, int cid, String sprite, Game game)
	{
		super(name, unique, x, y, cid, sprite);
		this.game = game;
	}

	@Override
	public abstract void move();

	@Override
	public abstract void hit_bg(int tilecid);

	@Override
	public abstract void hit(JGObject obj);
}
