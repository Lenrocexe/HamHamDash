package hamhamdash;

import jgame.JGObject;

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
		super.hit(obj);
		if(obj.colid == 1)
		{
			if(this.isFalling() && this.getCenterTile().x == game.player.getPc().getCenterTile().x){
				game.player.kill();
			} else {
				stopFalling();
			}
		}
	}

}
