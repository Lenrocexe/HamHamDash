package hamhamdash;

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
	public void hit_bg(int tilecid){

	}
}
