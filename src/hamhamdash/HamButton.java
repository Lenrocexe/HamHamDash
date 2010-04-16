package hamhamdash;

import jgame.JGColor;
import jgame.JGObject;

/**
 *
 * @author Serhan Uygur
 */
public class HamButton extends JGObject
{
	public final static int NORMAL = 0, OVER = 1;
	private String normalButton = "ham_button_normal";
	private String overButton = "ham_button_over";
	private Game game = Game.getGame();
	
	public HamButton(int x, int y, String label, JGColor labelColor, int state)
	{
		super("hambutton", true, x, y, 0, "ham_button_over");

		if(state == NORMAL)
		{
			setGraphic(normalButton);
		}else if(state == OVER)
		{
			setGraphic(overButton);
		}


		game.setColor(labelColor);
		game.drawString(
				label,
				x,
				y - 3,
				-1
				);

}
}
