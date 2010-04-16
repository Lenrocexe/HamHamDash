package hamhamdash;

import jgame.JGColor;

/**
 *
 * @author Serhan Uygur
 */
public class HamButton
{
	public HamButton(String label, int x, int y, int width, int height, JGColor labelColor)
	{
		Game.getGame().drawRect(
				x,
				y,
				width,
				height,
				true,
				true
				);

		Game.getGame().setColor(labelColor);
		Game.getGame().drawString(
				label,
				x,
				y - 3,
				0
				);
	}
}
