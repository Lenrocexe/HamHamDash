package hamhamdash;

import jgame.*;
import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class Game extends JGEngine
{
	public Game(JGPoint dimension)
	{
		initEngine(dimension.x, dimension.y);
	}

	public static void main(String[] args)
	{
		new Game(new JGPoint(800, 640));
	}

	public void initCanvas()
	{
		setCanvasSettings(10, 10, 40, 40, JGColor.white, JGColor.black, null);
	}

	public void initGame()
	{
		setFrameRate(45, 2);
	}

	public void doFrame()
	{

	}

	int hex = 0xFF;

	public void paintFrame()
	{
		drawString(String.valueOf(hex), 150, 150, 0);
	}
}
