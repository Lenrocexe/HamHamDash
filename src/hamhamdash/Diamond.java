package hamhamdash;

import jgame.*;

/**
 *
 * @author Cornel Alders
 */
public class Diamond extends GObject
{

	public int diamondPoint = 3; // Points for diamond


	public Diamond(String name, boolean unique, int x, int y, String sprite)

	{
		super(name, unique, x, y, 3, "diamond");
	}


	@Override
	public void move(){};


	@Override
	public void hit_bg(int tilecid){};


	@Override
	public void hit(JGObject obj){};

//		pickupDiamond();

	public void pickupDiamond()
	{
//		if (tile.contains("P"))
//		{
//			destroy();
//		}
	}
}
