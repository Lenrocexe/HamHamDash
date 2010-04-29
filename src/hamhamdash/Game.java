package hamhamdash;

import java.util.ArrayList;
import jgame.*;
import jgame.platform.*;

/**
 * This class is the startup class and contains all game items
 * @author Cornel Alders
 */
public class Game extends JGEngine
{
	// DEBUG VAR!
	private boolean debug = false;

	// Game constants
	private Player player = null; //The player that is currently playing
	private ArrayList<Player> playerList = new ArrayList<Player>(); //The list that tracks all players
	private Levels objLevels; //Stores an instance of the Levels factory
	private int tileSize = 40; //Default tile size
	private int xofs, yofs = 0; //The scrollpane offset
	private State currentState = null;	//The state that should be running
	private State previousState = null; //the state that was previously running
										//Mainly used to save the InGame state during the Pause
	private int timer = 0; //This is the amount of time left to finish a level
	private int timercounter = 0; //If zero, timer does not count down

//***************************************
// Start Game initialization
//***************************************
	private Game(JGPoint dimension)
	{
		initEngine(dimension.x, dimension.y);
		//setGameSpeed(0.8);
	}

	/**
	 * Gets loaded on first execution of getGame() OR at the first call GameHolder.INSTANCE
	 * Next call will return the instance of the game
	 */
	private static class GameHolder
	{
		private static final Game INSTANCE = new Game(new JGPoint(400, 400));
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
		setCanvasSettings(10, 10, getTileSize(), getTileSize(), JGColor.white, new JGColor(44, 44, 44), null);
	}

	public void initGame()
	{
		setFrameRate(45, 2);
		setVideoSyncedUpdate(true);
		defineMedia("datasheets/spritetable.tbl");

		objLevels = new Levels();

		if(isDebug())
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
		// DBG MSG's
		if(isDebug())
		{
			dbgPrint(getKeyDesc(getLastKey()) + " was pressed");
			dbgPrint(inGameState("EnterPwd") + "");
		}
	}

	@Override
	public void paintFrame()
	{
		if (!(inGameState("InGame")) && !(inGameState("Restart")))
		{
			drawImage(0, 0, "menu_bg");
			int x = viewWidth() - 30;
			drawString("<ESC>    -            Back", x, viewHeight() - 60, 1, true);
			drawString("<ENTER>    -             Next", x, viewHeight() - 40, 1, true);
			drawString("<ARROWS>    -   Navigation", x, viewHeight() - 20, 1, true);
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
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameTitle()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameTitle()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Title
//***************************************
//***************************************
// Start Game State Player Select
//***************************************
	public void startPlayerSelect()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFramePlayerSelect()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFramePlayerSelect()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Player Select
//***************************************
//***************************************
// Start Game State Start Game
//***************************************
	public void startStartGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameStartGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameStartGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Start Game
//***************************************
//***************************************
// Start Game State Enter Password
//***************************************
	public void startEnterPwd()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameEnterPwd()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameEnterPwd()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Enter Password
//***************************************
//***************************************
// Start Game State InGame
//***************************************
	public void startInGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameInGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameInGame()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State InGame
//***************************************
//***************************************
// Start Game State Death
//***************************************
	public void startDeath()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameDeath()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameDeath()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Death
//***************************************
//***************************************
// Start Game State Restart
//***************************************
	public void startRestart()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameRestart()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameRestart()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Restart
//***************************************
//***************************************
// Start Game State Pause
//***************************************
	public void startPause()
	{
		if (inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFramePause()
	{
		if (inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFramePause()
	{
		if (inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Pause
//***************************************
//***************************************
// Start Game State Win
//***************************************
	public void startWin()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameWin()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameWin()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State Win
//***************************************
//***************************************
// Start Game State GameOver
//***************************************
	public void startGameOver()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().start();
		}
	}

	public void doFrameGameOver()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().doFrame();
		}
	}

	public void paintFrameGameOver()
	{
		if (!inGameState("Pause"))
		{
			getCurrentState().paintFrame();
		}
	}
//***************************************
// End Game State GameOver
//***************************************

	public int getTileSize()
	{
		return tileSize;
	}

	public int getViewportWidth()
	{
		return 200;
	}

	public int getViewportHeight()
	{
		return 200;
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

	public void resetObjLevels()
	{
		removeObjects(null, 0);
		objLevels = new Levels();
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
	 * Add a Game State to the current Game State. Mostly used for the Pause state.
	 * Will create a new Instance of the given state class and stores ref in currentState.
	 * @param state The state name
	 */
	public void addState(String state)
	{
		saveState();
		this.currentState = getStateClass(state);
		addGameState(state);
	}

	/**
	 * Save the current state and all it contains before switching to a new state.
	 * Use it when you want to pause the game or something.
	 */
	public void saveState()
	{
		this.previousState = currentState;
	}

	/**
	 * Recover the previously saved state to run it again.
	 */
	public void recoverState()
	{
		currentState = previousState;
	}

	/**
	 * Dynamically searches for the given state and returns a new instance of it.
	 *
	 * @param state
	 * @return
	 */
	public State getStateClass(String state)
	{
		State c = null;
		try
		{
			c = (State) Class.forName("hamhamdash.states.State" + state).newInstance();
			return c;
		}
		catch(ClassNotFoundException cnfe){System.out.println("Class not found!");}
		catch(InstantiationException ie){System.out.println("Instantiation Error!");}
		catch(IllegalAccessException iae){System.out.println("Illegal Access!");}
		return null;
	}

	/**
	 * Resets the viewpoint offset to zero
	 */
	public void resetViewport()
	{
		setViewOffset(0, 0, true);
		setPFSize(10, 10);
	}

	/**
	 * Starts the level timer.
	 * This should only be called in the InGame state.
	 */
	public void startTimer()
	{
		timercounter = 1;
		timer -= timercounter;
	}

	/**
	 * Stop the timer. Use it in states like Pause.
	 */
	public void stopTimer()
	{
		timercounter = 0;
	}

	/**
	 * Resets the level timer.
	 * Should be called when a life has been lost and when changing levels.
	 */
	public void resetTimer()
	{
		timer = getObjLevels().getCurrentLevel().getLevelTimer();
	}

	/**
	 * Returns the current value of the level timer.
	 * @return amount of time left
	 */
	public int getTimer()
	{
		return timer;
	}

	public boolean allowStateExecute()
	{
		return !(inGameState("Death") || inGameState("Pause"));
	}

	public Player getPlayer()
	{
		return player;
	}

	public void addPlayer(Player p)
	{
		playerList.add(p);
	}

	public void setActivePlayer(int i)
	{
		player = playerList.get(i);
	}

	public void switchPlayers()
	{
		if(player.getPlayerName().equals("hamtaro"))
		{
			player = playerList.get(1);
		}
		else
		{
			player = playerList.get(0);
		}
	}

	public int countPlayers()
	{
		return playerList.size();
	}

	public void clearPlayerList()
	{
		playerList.clear();
	}

	public ArrayList<Player> getPlayerList()
	{
		return playerList;
	}
}
