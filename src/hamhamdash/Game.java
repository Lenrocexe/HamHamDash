package hamhamdash;

import java.util.ArrayList;
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
	private Player player = null;
	private Levels objLevels;
	private String passString;
	private boolean debug = true;
	public int tileWidth = 40;
	public int tileHeight = 40;

//***************************************
// Start Game initialization
//***************************************
	private Game(JGPoint dimension)
	{
		initEngine(dimension.x, dimension.y);
	}

	/**
	 * Gets loaded on first execution of getGame() OR at the first call GameHolder.INSTANCE
	 * Next call will return the instance of the game
	 */
	private static class GameHolder
	{
		private static final Game INSTANCE = new Game(new JGPoint(800, 800));
	}

	/**
	 * Returns the instance of the game
	 * @return
	 */
	public static Game getGame()
	{
		return GameHolder.INSTANCE;
	}

	public void initCanvas()
	{
		setCanvasSettings(10, 10, tileWidth, tileHeight, JGColor.white, new JGColor(44, 44, 44), null);
	}

	public void initGame()
	{
		setFrameRate(45, 2);
		setVideoSyncedUpdate(true);
		defineMedia("datasheets/testdata.tbl");

		states.add("Title");
		states.add("PlayerSelect");
		states.add("StartGame");
		states.add("EnterPwd");
		states.add("InGame");

		// Create Levels object
		objLevels = new Levels();

		if (debug)
		{
			dbgShowBoundingBox(true);
			dbgShowGameState(true);
		}

		// Start with the title screen
		setGameState("Title");
	}

	public static void main(String[] args)
	{
		Game.getGame();
	}

//***************************************
// End Game initialization
//***************************************
//***************************************
// Start default loop
//***************************************
	@Override
	public void doFrame()
	{
		if (states.get(stateCounter).equals("InGame"))
		{
			moveObjects(null, 0);
		} else if (states.get(stateCounter).equals("EnterPwd"))
		{
			if (getKey(KeyEnter))
			{
				clearKey(KeyEnter);
				passString = "";
				for (String perPass : passPosList)
				{
					passString += perPass;
				}

				if (objLevels.checkPassword(passString))
				{
					passIsCorrect = true;
					stateCounter = nextState(stateCounter, states);
				} else
				{
					passIsCorrect = false;
					passAttempt++;
				}

			}
		} else
		{
			if (getKey(KeyEnter))
			{
				// next step is player selection
				clearKey(KeyEnter);
				stateCounter = nextState(stateCounter, states);
			} else if (getKey(KeyEsc))
			{
				clearKey(KeyEsc);
				stateCounter = prevState(stateCounter, states);
			}
		}


		// DBG MSG's
		if (debug)
		{
			dbgPrint("PlayerAmount = " + playerAmount);
			dbgPrint("LoadGame = " + loadGame);

			String pressedKey = getKeyDesc(getLastKey());
			dbgPrint(pressedKey + " was pressed");
			dbgPrint("Password = " + passString);

			dbgPrint(selectedPos + "");
			dbgPrint(goodNumbers[Integer.parseInt(passPosList[selectedPos])]);
			dbgPrint(passPosList[selectedPos] + "");
			dbgPrint(selectedNum + "");
		}
	}

	@Override
	public void paintFrame()
	{
		if (debug)
		{
			if (!(states.get(stateCounter).equals("InGame")))
			{
				drawImage(0, 0, "menu_bg");

				drawString("<ESC>     - Back", pfWidth() - 100, pfHeight() - 60, -1, true);
				drawString("<ENTER> - Next", pfWidth() - 100, pfHeight() - 40, -1, true);
				drawString("<ARROWS> - Navigation", pfWidth() - 100, pfHeight() - 20, -1, true);
			}
		}

	}

//***************************************
// End default loop
//***************************************
//***************************************
// Start Game State Title
//***************************************
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
//***************************************
// End Game State Title
//***************************************
//***************************************
// Start Game State Player Select
//***************************************
	// Define ps(Player Select) vars
	private int psButtonWidth;
	private JGPoint psPoint;
	private String playerOneButtonState;// = "rollover";
	private String playerTwoButtonState;// = "normal";

	public void startPlayerSelect()
	{
		psButtonWidth = 30;
		playerOneButtonState = "rollover";				// 'player one' is highlighted as default
		playerTwoButtonState = "normal";			// 'player tow' is not
		psPoint = new JGPoint(pfWidth() / 2, 60);
	}

	public void doFramePlayerSelect()
	{
		if (getKey(KeyLeft) || getKey(KeyUp))
		{
			clearKey(KeyLeft);
			clearKey(KeyUp);
			togglePlayerSelect();
		}
		if (getKey(KeyRight) || getKey(KeyDown))
		{
			clearKey(KeyRight);
			clearKey(KeyDown);
			togglePlayerSelect();
		}
	}

	public void paintFramePlayerSelect()
	{
		drawString("Select amount of Player", psPoint.x, psPoint.y, 0);
		drawImage(psPoint.x - (75/2), psPoint.y + 30, "player1_button_" + playerOneButtonState);
		drawImage(psPoint.x - (75/2), psPoint.y + 65, "player2_button_" + playerTwoButtonState);
	}

	public void togglePlayerSelect()
	{
		if (playerAmount == 1)
		{
			playerOneButtonState = "normal";
			playerTwoButtonState = "rollover";
			playerAmount = 2;
		} else if (playerAmount == 2)
		{
			playerOneButtonState = "rollover";
			playerTwoButtonState = "normal";
			playerAmount = 1;
		}
	}
//***************************************
// End Game State Player Select
//***************************************
//***************************************
// Start Game State Start Game
//***************************************
	// Define sg (Start Game) vars
	private int sgButtonWidth, sgButtonHeight;
	private JGPoint sgPoint;
	private int newGameState;
	private int loadGameState;
	private JGColor hamButtonLabelColor = new JGColor(180, 175, 150);

	public void startStartGame()
	{
		sgButtonWidth = 70;
		sgButtonHeight = psButtonWidth;
		sgPoint = psPoint;
		newGameState = 1;						// 'New Game' is highlighted as default
		loadGameState = 0;						// 'Load Game' is not
	}

	public void doFrameStartGame()
	{
		if (getKey(KeyLeft) || getKey(KeyUp))
		{
			clearKey(KeyLeft);
			clearKey(KeyUp);
			toggleLoadGame();
		}

		if (getKey(KeyRight) || getKey(KeyDown))
		{
			clearKey(KeyRight);
			clearKey(KeyDown);
			toggleLoadGame();
		}
	}

	public void paintFrameStartGame()
	{
		setBlendMode(1, 0);
		drawString("Select your action", sgPoint.x, sgPoint.y, 0);

		HamButton b;
		b = new HamButton(sgPoint.x, sgPoint.y, "New Game", hamButtonLabelColor, newGameState);
		b.paint();
		b = new HamButton(sgPoint.x, sgPoint.y + (sgButtonHeight * 2), "Load Game", hamButtonLabelColor, loadGameState);
		b.paint();
	}

	// Player Select Methods
	public void toggleLoadGame()
	{
		if (loadGame)
		{
			newGameState = 1;
			loadGameState = 0;
			loadGame = false;
		} else
		{
			newGameState = 0;
			loadGameState = 1;
			loadGame = true;
		}
	}
//***************************************
// End Game State Start Game
//***************************************
//***************************************
// Start Game State Enter Password
//***************************************
	// Define ep (Enter Pwd) vars
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
	private boolean passIsCorrect = false;
	private int passAttempt = 0;

	public void startEnterPwd()
	{
		epPoint = psPoint;
		passString = "";
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
		selectedNum = Integer.parseInt(passPosList[selectedPos]);
		if (getKey(KeyLeft))
		{
			clearKey(KeyLeft);
			if (selectedPos > 0)
			{
				selectedPos--;
			}
		}
		if (getKey(KeyUp))
		{
			clearKey(KeyUp);
			if (selectedNum < goodNumbers.length - 1)
			{
				selectedNum++;
			}
			passPosList[selectedPos] = goodNumbers[selectedNum];

		}
		if (getKey(KeyRight))
		{
			clearKey(KeyRight);
			if (selectedPos < passPosList.length - 1)
			{
				selectedPos++;
			}
		}
		if (getKey(KeyDown))
		{
			clearKey(KeyDown);
			if (selectedNum > 0)
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
		for (int i = 0; i < passPosList.length; i++)
		{
			if (selectedPos == i)
			{
				setColor(selectedPosColor);
			} else
			{
				setColor(JGColor.white);
			}
			drawString(passPosList[i], epPoint.x + (i * ppWidth) - (((passPosList.length - 1) * ppWidth) / 2), epPoint.y + 20, 1);
		}

		if (!passIsCorrect && passAttempt > 0)
		{
			drawString("Password was wrong, please try again!", epPoint.x, epPoint.y + 50, 0);
		}



	}

//***************************************
// End Game State Enter Password
//***************************************
//***************************************
// Start Game State InGame
//***************************************
	// InGame
	public void startInGame()
	{
		objLevels.startLevel();
//		setFieldSize(objLevels.getCurrentLevelSize());
	}

	public void doFrameInGame()
	{
//		if (getKey(KeyCtrl) && getKey(KeyShift) && getKey(KeyRight))
//		{
//			clearKey(KeyCtrl);
//			clearKey(KeyShift);
//			clearKey(KeyRight);
//			objLevels.nextLevel();
//		}
//		if (getKey(KeyCtrl) && getKey(KeyShift) && getKey(KeyLeft))
//		{
//			clearKey(KeyCtrl);
//			clearKey(KeyShift);
//			clearKey(KeyLeft);
//			objLevels.prevLevel();
//		}
		player = new Player();
		player.setPc(new PlayerCharacter(80, 160));
	}

	public void paintframeInGame()
	{
	}

	// Global Method(s)
	public int nextState(int counter, ArrayList<String> states)
	{
		if (counter < states.size() - 1)
		{
			counter++;
		}
		if (states.get(counter).equals("EnterPwd") && !loadGame)
		{
			counter++;
		}
		setGameState(states.get(counter));
		return counter;
	}

	public int prevState(int counter, ArrayList<String> states)
	{
		if (counter > 0)
		{
			counter--;
		}
		if (states.get(counter).equals("EnterPwd") && !loadGame)
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

	/**
	 * Sets the PlayField size based on the size of the current level
	 * @param t int[width, height] The size of the current level
	 */
	public void setFieldSize(int[] t)
	{
		setPFSize(t[0], t[1]);
	}
}
