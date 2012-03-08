package hamhamdash;

import jgame.JGObject;

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

    @Override
    public void hit(JGObject obj)
    {
        super.hit(obj);
        if(obj.colid == 2)
        {
            if(this.isFalling())
            {
                obj.remove();
            }
        }
    }

//    @Override
//    public void hit_bg(int tilecid)
//    {
//        System.out.println(tilecid + " wtf ...");
//    }
}
