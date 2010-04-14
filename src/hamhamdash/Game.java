package hamhamdash;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import jgame.*;
import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class Game extends JGEngine
{
	// Define "GLOBAL" Vars
	private int playerAmount = 1;								// by default '1P' is selected
	private boolean loadGame = false;							// by default 'New Game' is selected
	private int stateCounter = 0;
	private ArrayList<String> states = new ArrayList<String>();
	private JTextField passTfield;
	
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

		states.add("Title");
		states.add("PlayerSelect");
		states.add("StartGame");
		states.add("EnterPwd");
		states.add("InGame");
		

		dbgShowBoundingBox(true);
		dbgShowGameState(true);


		// Start with the title screen
		setGameState("Title");

	}

	@Override
	public void doFrame()
	{
//		moveObjects(null, 0);
		
		if(getKey(KeyEnter))
		{
			// next step is player selection
			clearKey(KeyEnter);
			stateCounter = nextState(stateCounter, states);
		}

		if(getKey(KeyEsc))
		{
			clearKey(KeyEsc);
			stateCounter = prevState(stateCounter, states);
		}


		// DBG MSG's
		dbgPrint("" + playerAmount);
		dbgPrint("" + loadGame);

	}

	@Override
	public void paintFrame()
	{
//		drawString("TOP LEFT", 0, 8, -1, true);
//		drawString("BOTTOM LEFT", 0, pfHeight() - 20, -1, true);
//		drawString("TOP RIGHT", pfWidth(), 8, 1, true);
//		drawString("BOTTOM RIGHT", pfWidth(), pfHeight() - 20, 1, true);
		drawString("<ESC>     - Back", pfWidth() - 100, pfHeight() - 40, -1, true);
		drawString("<ENTER> - Next", pfWidth() - 100, pfHeight() - 20, -1, true);

	}



	/* Title Screen */
	public void startTitle()
	{

	}

	public void doFrameTitle()
	{
		
	}

	public void paintFrameTitle()
	{
		drawImage(0, 0, "title_bg");
		drawString("Press <ENTER> to continue", pfWidth() / 2, pfHeight() - 50, 0);
	}

	/* Player Select */
	// Define ps(Player Select) vars
	private int psButtonWidth;
	private JGPoint psPoint;
	private JGColor playerOneButtonBG;
	private JGColor playerTwoButtonBG;

	// Player Select Main Methods
	public void startPlayerSelect()
	{
		psButtonWidth = 30;
		psPoint = new JGPoint(pfWidth() / 2, 100);
		playerOneButtonBG = JGColor.red;		// '1P' is highlighted as default
		playerTwoButtonBG = JGColor.white;		// '2P' is not
	}

	public void doFramePlayerSelect()
	{

		if(getKey(KeyLeft) || getKey(KeyUp))
		{
			clearKey(KeyLeft);
			clearKey(KeyUp);
			togglePlayerSelect();
		}

		if(getKey(KeyRight) || getKey(KeyDown))
		{
			clearKey(KeyRight);
			clearKey(KeyDown);
			togglePlayerSelect();
		}



	}

	public void paintFramePlayerSelect()
	{		
		drawString("Select amount of Player", psPoint.x, psPoint.y, 0);

		setColor(playerOneButtonBG);
		new HamButton(this,"1P",psPoint.x, psPoint.y + psButtonWidth, psButtonWidth, psButtonWidth, JGColor.black);
		setColor(playerTwoButtonBG);
		new HamButton(this,"2P",psPoint.x, psPoint.y + (psButtonWidth * 2), psButtonWidth, psButtonWidth, JGColor.black);

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


	/* Start Game */
	// Define sg (Start Game) vars
	private int sgButtonWidth, sgButtonHeight;
	private JGPoint sgPoint;
	private JGColor newGameButtonBG;
	private JGColor loadGameButtonBG;

	public void startStartGame()
	{
		sgButtonWidth = 70;
		sgButtonHeight = psButtonWidth;
		sgPoint = psPoint;
		newGameButtonBG = JGColor.red;			// 'New Game' is highlighted as default
		loadGameButtonBG = JGColor.white;		// 'Load Game' is not
	}

	public void doFrameStartGame()
	{
		if(getKey(KeyLeft) || getKey(KeyUp))
		{
			clearKey(KeyLeft);
			clearKey(KeyUp);
			toggleStartGame();
		}

		if(getKey(KeyRight) || getKey(KeyDown))
		{
			clearKey(KeyRight);
			clearKey(KeyDown);
			toggleStartGame();
		}


	}

	public void paintFrameStartGame()
	{
		drawString(playerAmount + " Player game selected", sgPoint.x, sgPoint.y, 0);

		setColor(newGameButtonBG);
		new HamButton(this,"New Game",sgPoint.x, sgPoint.y + sgButtonHeight, sgButtonWidth, sgButtonHeight, JGColor.black);
		setColor(loadGameButtonBG);
		new HamButton(this,"Load Game",sgPoint.x, sgPoint.y + (sgButtonHeight * 2), sgButtonWidth, sgButtonHeight, JGColor.black);
	}


	// Player Select Methods
	public void toggleStartGame()
	{
		if(loadGame)
		{
			newGameButtonBG = JGColor.red;
			loadGameButtonBG = JGColor.white;
			loadGame = false;
		}else
		{
			newGameButtonBG = JGColor.white;
			loadGameButtonBG = JGColor.red;
			loadGame = true;
		}

	}





	/* Enter Pwd*/
	// Define ep (Enter Pwd) vars
	private int epButtonWidth, epButtonHeight;
	private JGPoint epPoint;


	public void startEnterPwd()
	{
	
		if(loadGame)
		{
			epButtonWidth = 70;
			epButtonHeight = psButtonWidth;
			epPoint = psPoint;
			newGameButtonBG = JGColor.red;			// 'New Game' is highlighted as default
			loadGameButtonBG = JGColor.white;		// 'Load Game' is not
		}

	}

	public void doFrameEnterPwd()
	{
		if(loadGame)
		{
			if(getKey(KeyLeft) || getKey(KeyUp))
			{
				clearKey(KeyLeft);
				clearKey(KeyUp);
				toggleStartGame();
			}


			if(getKey(KeyRight) || getKey(KeyDown))
			{
				clearKey(KeyRight);
				clearKey(KeyDown);
				toggleStartGame();
			}
		}

	}

	public void paintFrameEnterPwd()
	{
		if(loadGame)
		{
			drawString("Enter Password", epPoint.x, epPoint.y, 0);
			drawString("_ _ _", epPoint.x, epPoint.y + 20, 0);
//			setColor(newGameButtonBG);
//			new HamButton(this,"New Game",epPoint.x, epPoint.y + epButtonHeight, epButtonWidth, epButtonHeight, JGColor.black);
//			setColor(loadGameButtonBG);
//			new HamButton(this,"Load Game",epPoint.x, epPoint.y + (epButtonHeight * 2), epButtonWidth, epButtonHeight, JGColor.black);
		}
	}


	// Player Select Methods





	// Global Method(s)
	public int nextState(int counter, ArrayList<String> states)
	{

//
//		if(loadGame)
//		{
//
//			counter +=2;
//
//			setGameState(states.get(counter));
//
//			return counter;
//		}


		if(counter < states.size()-1 )
		{
			counter++;
		}


		
		System.out.println(states.get(counter));
		setGameState(states.get(counter));


		return counter;
	}
	
	public int prevState(int counter, ArrayList<String> states)
	{

//
//			if(loadGame)
//			{
//
//				counter -=2;
//
//				setGameState(states.get(counter));
//
//				return counter;
//			}


			if(counter > 0 )
			{
				counter--;
			}

			setGameState(states.get(counter));

			return counter;

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
