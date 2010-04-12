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
		setCanvasSettings(10, 10, 40, 40, JGColor.white, new JGColor(44, 44, 44), null);
	}

	public void initGame()
	{
		setFrameRate(45, 2);

		defineMedia("datasheets/testdata.tbl");

		// Start with the title screen
		setGameState("Title");
	}

	@Override
	public void doFrame()
	{
//		moveObjects(null, 0);
	}

	@Override
	public void paintFrame()
	{
//		drawString("TOP LEFT", 0, 8, -1, true);
//		drawString("BOTTOM LEFT", 0, pfHeight() - 20, -1, true);
//		drawString("TOP RIGHT", pfWidth(), 8, 1, true);
//		drawString("BOTTOM RIGHT", pfWidth(), pfHeight() - 20, 1, true);
	}



	/* Title Screen */
	public void startTitle()
	{
	}

	public void doFrameTitle()
	{
		if(getKey(' '))
		{
			// next step is player selection
			setGameState("PlayerSelect");
		}
	}

	public void paintFrameTitle()
	{
		drawImage(0, 0, "title_bg");
		drawString("Press <SPACE> to continue", pfWidth() / 2, pfHeight() - 50, 0);
	}

	/* Player Select */
	// Define player select vars
	private int pw = 30;
	private JGPoint p = new JGPoint(pfWidth() / 2, 100);
	private JGColor playerOneButton = JGColor.white, playerTwoButton = JGColor.white;
	private int playerAmount = 0;


	public void startPlayerSelect()
	{
		
	}

	public void doFramePlayerSelect()
	{

		if(getKey(KeyLeft))
		{
			clearKey(KeyLeft);
			playerOneButton = JGColor.red;
			playerTwoButton = JGColor.white;
			playerAmount = 1;
			dbgPrint("" + getKey(KeyLeft));
		}

		if(getKey(KeyRight))
		{
			clearKey(KeyRight);
			playerOneButton = JGColor.white;
			playerTwoButton = JGColor.red;
			playerAmount = 2;
			dbgPrint("" + getKey(KeyRight));
		}

		if(getKey(KeyEnter))
		{
			setGameState("StartGame");
		}



//		if(getMouseX())
	}

	public void paintFramePlayerSelect()
	{
		p = new JGPoint(pfWidth() / 2, 100);
		
		drawString("Select amount of Player", p.x, p.y, 0);

		setColor(playerOneButton);
		drawRect(
				(p.x - (pw / 2)) - pw,
				p.y + pw/2,
				pw,
				pw,
				true,
				false
				);

		setColor(JGColor.black);
		drawString(
				"1P",
				p.x - pw,
				p.y + pw,
				0
				);

		setColor(playerTwoButton);
		drawRect(
				(p.x + (pw / 2)),
				p.y + pw/2,
				pw,
				pw,
				true,
				false
				);

		setColor(JGColor.black);
		drawString(
				"2P",
				p.x + pw,
				p.y + pw,
				0
				);


	}

	// Getter(s) & Setter(s)
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
