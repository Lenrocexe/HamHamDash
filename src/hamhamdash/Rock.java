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
        String[][] tile = game.getCurrentLevel().getSurroundingTiles(getCenterTile().x, getCenterTile().y);
        if(obj.colid == 1)
        {
            if(this.isFalling())// && this.getCenterTile().x == game.getPlayer().getPc().getCenterTile().x)
            {
                //System.out.println(this.isFalling() + " its falling");
                game.getPlayer().kill();
            }
            else
            {
                stopFalling();
            }
        }
        else if(obj.colid == 2)
        {
            Enemy collidEnemy = game.getObjLevels().getCurrentLevel().getEnemy(obj.getName());
            collidEnemy.kill();
            this.remove();
        }
        else if(obj.colid == 4 || obj.colid == 3)
        {
            stopFalling();
        }
    }
}
