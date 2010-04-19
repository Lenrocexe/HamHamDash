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
		super(name, unique, x, y, 4, sprite);
	}

	@Override
	public void move(){};

	@Override
	public void hit_bg(int tilecid){
//        String duw = getPushSource();
//		if(duw == left)
//		{
//			moveRight();
//		}
//		if(duw == right)
//		{
//			moveLeft();
//		}
//		if(duw == down)
//		{
//			return;
//		}
	}

	@Override
	public void hit(JGObject obj){};
}
