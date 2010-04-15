package hamhamdash;

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
	private Levels objLevels;
	static int currentLevelId = 1;
	private String passString;
	private boolean debug = true;

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



		if(debug)
		{
			dbgShowBoundingBox(true);
			dbgShowGameState(true);
		}

		objLevels = new Levels(this);

		// Start with the title screen
		setGameState("Title");
	}

	@Override
	public void doFrame()
	{
//		moveObjects(null, 0);
		if(!(states.get(stateCounter).equals("InGame")))
		{
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
		}

		// DBG MSG's
		if(debug)
		{
			dbgPrint("PlayerAmount = " + playerAmount);
			dbgPrint("LoadGame = " + loadGame);
			String pressedKey = getKeyDesc(getLastKey());
			dbgPrint(pressedKey + " was pressed");
			dbgPrint("Password = " + passString);
			dbgPrint(selectedPos + "" );
			dbgPrint(goodNumbers[Integer.parseInt(passPosList[selectedPos])]);
			dbgPrint(passPosList[selectedPos] + "");
			dbgPrint(selectedNum + "");
		}
	}

	@Override
	public void paintFrame()
	{
		if(debug)
		{
			if(!(states.get(stateCounter).equals("InGame")))
			{
				drawImage(0, 0, "menu_bg");
				drawString("<ESC>     - Back", pfWidth() - 100, pfHeight() - 40, -1, true);
				drawString("<ENTER> - Next", pfWidth() - 100, pfHeight() - 20, -1, true);
			}
		}
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
		new HamButton(this, "1P", psPoint.x, psPoint.y + psButtonWidth, psButtonWidth, psButtonWidth, JGColor.black);
		setColor(playerTwoButtonBG);
		new HamButton(this, "2P", psPoint.x, psPoint.y + (psButtonWidth * 2), psButtonWidth, psButtonWidth, JGColor.black);

	}

	// Player Select Methods
	public void togglePlayerSelect()
	{

		if(playerAmount == 1)
		{
			playerOneButtonBG = JGColor.white;
			playerTwoButtonBG = JGColor.red;
			playerAmount = 2;
		}
		else if(playerAmount == 2)
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
			toggleLoadGame();
		}

		if(getKey(KeyRight) || getKey(KeyDown))
		{
			clearKey(KeyRight);
			clearKey(KeyDown);
			toggleLoadGame();
		}
	}

	public void paintFrameStartGame()
	{
		drawString(playerAmount + " Player game selected", sgPoint.x, sgPoint.y, 0);
		setColor(newGameButtonBG);
		new HamButton(this, "New Game", sgPoint.x, sgPoint.y + sgButtonHeight, sgButtonWidth, sgButtonHeight, JGColor.black);
		setColor(loadGameButtonBG);
		new HamButton(this, "Load Game", sgPoint.x, sgPoint.y + (sgButtonHeight * 2), sgButtonWidth, sgButtonHeight, JGColor.black);
	}

	// Player Select Methods
	public void toggleLoadGame()
	{
		if(loadGame)
		{
			newGameButtonBG = JGColor.red;
			loadGameButtonBG = JGColor.white;
			loadGame = false;
		}
		else
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
	// Array with correct password chars
	private String[] goodNumbers =
	{
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
	};
	// 1 var for each password position, these vars will combine to be the passString
	private String[] passPosList;
	// pp (passPos) draw attributes
	private int ppWidth = 10;
	private int ppHeight = 10;
	private int selectedPos, selectedNum;
	private JGColor selectedPosColor = JGColor.red;

	public void startEnterPwd()
	{
		epButtonWidth = 70;
		epButtonHeight = psButtonWidth;
		epPoint = psPoint;
		newGameButtonBG = JGColor.red;			// 'New Game' is highlighted as default
		loadGameButtonBG = JGColor.white;		// 'Load Game' is not
		selectedPos = 0;
		selectedNum = 0;
		passPosList = new String[6];
		passPosList[0] = goodNumbers[0];
		passPosList[1] = goodNumbers[0];
		passPosList[2] = goodNumbers[0];
		passPosList[3] = goodNumbers[0];
		passPosList[4] = goodNumbers[0];
		passPosList[5] = goodNumbers[0];
	}

	public void doFrameEnterPwd()
	{
//		voegen in de array!!!!
		selectedNum = Integer.parseInt(passPosList[selectedPos]);
		if(getKey(KeyLeft))
		{
			clearKey(KeyLeft);
			if(selectedPos > 0)
			{
				selectedPos--;
			}
		}
		if(getKey(KeyUp))
		{
			clearKey(KeyUp);
			if(selectedNum < goodNumbers.length - 1)
			{
				selectedNum++;
			}
			passPosList[selectedPos] = goodNumbers[selectedNum];
			
		}
		if(getKey(KeyRight))
		{
			clearKey(KeyRight);
			if(selectedPos < passPosList.length - 1)
			{
				selectedPos++;
			}
		}
		if(getKey(KeyDown))
		{
			clearKey(KeyDown);
			if(selectedNum > 0)
			{
				selectedNum--;
			}
			passPosList[selectedPos] = goodNumbers[selectedNum];
		}
	}

	public void paintFrameEnterPwd()
	{
		drawString("Enter Password", epPoint.x, epPoint.y, 0);

		// Draw the individual passPos vars
		for(int i = 0; i < passPosList.length; i++)
		{
			if(selectedPos == i)
			{
				setColor(selectedPosColor);
			}
			else
			{
				setColor(JGColor.white);
			}
			drawString(passPosList[i], epPoint.x + (i * ppWidth) - (((passPosList.length - 1) * ppWidth) / 2), epPoint.y + 20, 1);
		}
	}

	// InGame
	public void startInGame()
	{
		objLevels.startLevel();
	}

	public void doFrameInGame()
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

	public void paintframeInGame()
	{
	}

	// EnterPwd Methods
	public boolean checkPasswordString(String passString)
	{
		String lvlPass = "98567";
		if(lvlPass.equals(passString))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Global Method(s)
	public int nextState(int counter, ArrayList<String> states)
	{
		if(counter < states.size() - 1)
		{
			counter++;
		}
		if(states.get(counter).equals("EnterPwd") && !loadGame)
		{
			counter++;
		}
		setGameState(states.get(counter));
		return counter;
	}

	public int prevState(int counter, ArrayList<String> states)
	{
		if(counter > 0)
		{
			counter--;
		}
		if(states.get(counter).equals("EnterPwd") && !loadGame)
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
