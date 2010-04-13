package hamhamdash;

import jgame.*;
import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class Game extends JGEngine
{
	private Levels objLevels;
	static int currentLevelId = 1;

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

		objLevels = new Levels(this);

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
	private int pw;
	private JGPoint p;
	private int playerAmount;
	private JGColor playerOneButtonBG;
	private JGColor playerTwoButtonBG;

	// Player Select Main Methods
	public void startPlayerSelect()
	{
		pw = 30;
		p = new JGPoint(pfWidth() / 2, 100);
		playerAmount = 1;						// default '1P' is selected
		playerOneButtonBG = JGColor.red;		// '1P' is highlighted as default
		playerTwoButtonBG = JGColor.white;		// '2P' is not
	}

	public void doFramePlayerSelect()
	{

		if(getKey(KeyLeft))
		{
			clearKey(KeyLeft);
			togglePlayerSelect();
		}

		if(getKey(KeyRight))
		{
			clearKey(KeyRight);
			togglePlayerSelect();
		}

		if(getKey(KeyEnter))
		{
			setGameState("StartGame");
		}

		dbgPrint("" + playerAmount);


	}

	public void paintFramePlayerSelect()
	{		
		drawString("Select amount of Player", p.x, p.y, 0);

		setColor(playerOneButtonBG);
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

		setColor(playerTwoButtonBG);
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

	// Player Select Methods
	public void togglePlayerSelect()
	{

		if(playerAmount == 1)
		{
			playerOneButtonBG = JGColor.white;
			playerTwoButtonBG = JGColor.red;
			playerAmount = 2;
			
		}else if(playerAmount == 2)
		{
			playerOneButtonBG = JGColor.red;
			playerTwoButtonBG = JGColor.white;

			playerAmount = 1;

		}
	}

	// Start Level
	public void startStartGame()
	{
		objLevels.startLevel();
	}
	public void paintStartGame()
	{
		
	}
	public void doFrameStartGame()
	{
		if(getKey(KeyRight))
		{
			clearKey(KeyRight);
			objLevels.changeLevel(1);
		}
		if(getKey(KeyLeft))
		{
			clearKey(KeyLeft);
			objLevels.changeLevel(-1);
		}
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
