package hamhamdash;

import jgame.*;
/**
 *
 * @author Cornel Alders
 */
public class Rock extends GObject
{
	public Rock(String name, boolean unique, int x, int y, String sprite)
	{
		super(name, unique, x, y, 4, "rock");
	}
	@Override
	public void hit(JGObject obj)
	{
//		System.out.println(obj.getName() +" COLLIDES WITH "+ this.getName());
	}
}
