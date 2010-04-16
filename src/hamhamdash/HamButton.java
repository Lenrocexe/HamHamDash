package hamhamdash;

import jgame.JGColor;
import jgame.JGPoint;

/**
 *
 * @author Serhan Uygur
 */
public class HamButton
{
	public final static int NORMAL = 0, OVER = 1;
	private String normalButton = "ham_button_normal";
	private String overButton = "ham_button_rollover1";
	private Game game = Game.getGame();
	private JGColor labelColor;
	private int state;
	private String label;
	private int hamButtonWidth = 100;
	private int hamMargin = 30;
	private JGPoint point;

	public HamButton(int x, int y, String label, JGColor labelColor, int state)
	{
		this.labelColor = labelColor;
		this.state = state;
		this.label = label;
		this.point = new JGPoint(x, y + 10);
	}

	public void paint()
	{
		if(state == NORMAL)
		{
			game.drawImage(point.x - 50, point.y, normalButton);
			System.out.println("im in normal");
		}
		else if(state == OVER)
		{
			game.drawImage(point.x - 50, point.y, overButton);
		}

		game.setColor(labelColor);
		game.drawString(
				label,
				point.x,
				point.y + hamMargin + (hamMargin / 2) - 3,
				0
				);
	}
}
