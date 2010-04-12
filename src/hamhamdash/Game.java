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
		new Game(new JGPoint(800, 800));
	}

	public void initCanvas()
	{
		setCanvasSettings(10, 10, 40, 40, JGColor.white, JGColor.black, null);
	}

	public void initGame()
	{
		setFrameRate(45, 2);
		defineMedia("datasheets/testdata.tbl");
	}

	public void doFrame()
	{
		moveObjects(null, 0);
	}

	public void paintFrame()
	{
		drawImage(0, 0, "splash_image");
		drawString("TOP LEFT", 0, 8, -1, true);
		drawString("BOTTOM LEFT", 0, pfHeight() - 20, -1, true);
		drawString("TOP RIGHT", pfWidth(), 8, 1, true);
		drawString("BOTTOM RIGHT", pfWidth(), pfHeight() - 20, 1, true);
	}

	public int getTileSize()
	{
		return 40;
	}

	public int getViewportWidth()
	{
		return 400;
	}

	public int getViewportHeight()
	{
		return 400;
	}
}
