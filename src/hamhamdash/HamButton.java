package hamhamdash;

import jgame.JGColor;

/**
 *
 * @author Serhan Uygur
 */
public class HamButton
{
	public HamButton(Game game, String label, int x, int y, int width, int height, JGColor labelColor)
	{
		game.drawRect(
				x,
				y,
				width,
				height,
				true,
				true
				);

		game.setColor(labelColor);
		game.drawString(
				label,
				x,
				y - 3,
				0
				);
	}
}
