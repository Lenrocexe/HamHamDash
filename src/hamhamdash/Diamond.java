package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Diamond extends GObject
{

	public int diamondPoint = 3;// Points for diamond
	public int diamondsLeft = 5;// Diamonds left for next level


	public Diamond(String name, boolean unique, int x, int y, String sprite)

	{
		super(name, unique, x, y, 3, "diamond");
	}
}
