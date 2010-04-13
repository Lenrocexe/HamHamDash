package hamhamdash;

/**
 *
 * @author Cornel Alders
 */
public abstract class Tile
{
	private int x = 0;
	private int y = 0;
	private String identifier = "";

	/**
	 *
	 * @param x The "true" x position
	 * @param y The "true" y position
	 * @param i The textual representation of the tile
	 */
	public Tile(int x, int y, String i)
	{
		this.x = x;
		this.y = y;
		this.identifier = i;
	}

	public String getIdentifier()
	{
		return identifier;
	}

	public int getX()
	{
		return y;
	}

	public int getY()
	{
		return y;
	}
}
