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
	public boolean loadGame = false; // by default 'New Game' is selected
	private int stateCounter = 0;
	private ArrayList<String> states = new ArrayList<String>();
	public Player player = new Player();
	public Enemy enemy = null;
	private Levels objLevels;
	public String passString;
	private boolean debug = true;
	public int tileWidth = 40;
	public int tileHeight = 40;
	private int xofs, yofs = 0;
	private State currentState = null;
	private State previousState = null;

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

		objLevels = new Levels();
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
		// Start the game in title screen
		setCurrentState("Title");
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
		if(inGameState("InGame"))
		{
			moveObjects(null, 0);
			//Object collision
			//Enemy -> Hamtaro
			checkCollision(2, 1);
			//Diamond -> Hamtaro
			checkCollision(3, 1);
			//Rock -> Hamtaro
			checkCollision(4, 1);
			//Enemy collision
			checkCollision(2, 2);
			checkCollision(3, 2);
			checkCollision(4, 2);
			//Tile collision
			//Hamtaro
			checkBGCollision(1, 1);
			checkBGCollision(2, 1);
			checkBGCollision(3, 1);
			checkBGCollision(4, 1);
			//Enemy
			checkBGCollision(1, 2);
			checkBGCollision(2, 2);
			checkBGCollision(3, 2);
			checkBGCollision(4, 2);

			if(getKey(KeyEsc))
			{
				clearKey(KeyEsc);
				addState("Pause");
			}
		}
		else if(inGameState("EnterPwd"))
		{
			if(getKey(KeyEnter))
			{
				clearKey(KeyEnter);
				passString = "";
				for(String perPass : passPosList)
				{
					passString += perPass;
				}
				if(getObjLevels().checkPassword(passString))
				{
					passIsCorrect = true;
					stateCounter = nextState(stateCounter, states);
				}
				else
				{
					passIsCorrect = false;
					passAttempt++;
				}
			}
			else if(getKey(KeyEsc))
			{
				clearKey(KeyEsc);
				stateCounter = prevState(stateCounter, states);
			}
		}
		else
		{
			if(getKey(KeyEnter))
			{
				// next step is player selection
				clearKey(KeyEnter);
				stateCounter = nextState(stateCounter, states);
			}
			else if(getKey(KeyEsc))
			{
				clearKey(KeyEsc);
				stateCounter = prevState(stateCounter, states);
			}
		}

		// DBG MSG's
		if(debug)
		{
			dbgPrint("LoadGame = " + loadGame);
			dbgPrint(getKeyDesc(getLastKey()) + " was pressed");
			dbgPrint(inGameState("EnterPwd") + "");
		}
	}

	@Override
	public void paintFrame()
	{
		if(debug)
		{
			if(!(inGameState("InGame")))
			{
				drawImage(0, 0, "menu_bg");
				drawString("<ESC> - Back", pfWidth() - 91, pfHeight() - 60, -1, true);
				drawString("<ENTER> - Next", pfWidth() - 103, pfHeight() - 40, -1, true);
				drawString("<ARROWS> - Navigation", pfWidth() - 115, pfHeight() - 20, -1, true);
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
		getCurrentState().start();
	}

	public void doFrameTitle()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameTitle()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Title
//***************************************
//***************************************
// Start Game State Player Select
//***************************************
	public void startPlayerSelect()
	{
		getCurrentState().start();
	}

	public void doFramePlayerSelect()
	{
		getCurrentState().doFrame();
	}

	public void paintFramePlayerSelect()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Player Select
//***************************************
//***************************************
// Start Game State Start Game
//***************************************
	public void startStartGame()
	{
		getCurrentState().start();
	}

	public void doFrameStartGame()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameStartGame()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Start Game
//***************************************
//***************************************
// Start Game State Enter Password
//***************************************
	// Array with correct password chars
	public String[] goodNumbers =
	{
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
	};
	// 1 var for each password position, these vars will combine to be the passString
	public String[] passPosList;
	public int selectedPos, selectedNum;
	public JGColor selectedPosColor = JGColor.red;
	public boolean passIsCorrect = false;
	public int passAttempt = 0;

	public void startEnterPwd()
	{
		getCurrentState().start();
	}

	public void doFrameEnterPwd()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameEnterPwd()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Enter Password
//***************************************
//***************************************
// Start Game State InGame
//***************************************
	public void startInGame()
	{
		getCurrentState().start();
	}

	public void doFrameInGame()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameInGame()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State InGame
//***************************************
//***************************************
// Start Game State Pause
//***************************************
	public void startPause()
	{
		getCurrentState().start();
	}

	public void doFramePause()
	{
		getCurrentState().doFrame();
	}

	public void paintFramePause()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Pause
//***************************************
//***************************************
// Start Game State Win
//***************************************
	public void startWin()
	{
		getCurrentState().start();
	}

	public void doFrameWin()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameWin()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State Win
//***************************************
//***************************************
// Start Game State GameOver
//***************************************
	public void startGameOver()
	{
		getCurrentState().start();
	}

	public void doFrameGameOver()
	{
		getCurrentState().doFrame();
	}

	public void paintFrameGameOver()
	{
		getCurrentState().paintFrame();
	}
//***************************************
// End Game State GameOver
//***************************************

	// Global Method(s)
	public int nextState(int counter, ArrayList<String> states)
	{
		if(counter < states.size() - 1)
		{
			counter++;
		}
		if(inGameState("StartGame") && !loadGame)
		{
			counter++;
		}
		setCurrentState(states.get(counter));
		return counter;
	}

	public int prevState(int counter, ArrayList<String> states)
	{
		if(counter > 0)
		{
			counter--;
		}
		if(inGameState("StartGame") && !loadGame)
		{
			counter--;
		}
		setCurrentState(states.get(counter));
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

	public Level getCurrentLevel()
	{
		return getObjLevels().getCurrentLevel();
	}

	/**
	 * @return the objLevels
	 */
	public Levels getObjLevels()
	{
		return objLevels;
	}

	public int getXoffset()
	{
		return xofs;
	}

	public int getYoffset()
	{
		return yofs;
	}

	public void setXoffset(int i)
	{
		this.xofs = i;
	}

	public void setYoffset(int i)
	{
		this.yofs = i;
	}

	public boolean isDebug()
	{
		return debug;
	}

	public State getCurrentState()
	{
		return currentState;
	}

	/**
	 * Set the currente Game State ex: "InGame" or "Title"
	 * Will create a new Instance of the given state class and stores ref in currentState.
	 * @param state The state name
	 */
	public void setCurrentState(String state)
	{
		this.currentState = getStateClass(state);
		setGameState(state);
	}

	/**
	 * Add a Game State ex: "InGame" or "Title", to the current Game State
	 * Will create a new Instance of the given state class and stores ref in currentState.
	 * @param state The state name
	 */
	public void addState(String state)
	{
		saveState();
		this.currentState = getStateClass(state);
		addGameState(state);
	}

	public void saveState()
	{
		this.previousState = currentState;
	}

	public void recoverState()
	{
		currentState = previousState;
	}

	public State getStateClass(String state)
	{
		State c = null;
		try
		{
			//State c = (State) Class.forName("hamhamdash.states.State" + state).newInstance();
			c = (State) Class.forName("hamhamdash.states.State" + state).newInstance();
			return c;
		}
		catch(ClassNotFoundException cnfe){System.out.println("Class not found!");}
		catch(InstantiationException ie){System.out.println("Instantiation Error!");}
		catch(IllegalAccessException iae){System.out.println("Illegal Access!");}
		return null;
	}
}
