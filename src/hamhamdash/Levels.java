package hamhamdash;

import java.util.*;

/**
 * This class maintains all interaction with levels
 * @author Cornel Alders
 */
public class Levels
{
	private Game game = Game.getGame();
	private ArrayList<Level> arrLevels = new ArrayList<Level>();
	private int currentLevelId = 0;

	/**
	 * Levels constructor
	 * @param Game
	 */
	public Levels()
	{
		loadLevels();
	}

	/**
	 * Loads the available levels in memory
	 */
	public void loadLevels()
	{
		// Get the available levels in an array
		String[] levels = getLevelDirList();

		for(String l : levels)
		{
			arrLevels.add(new Level(l));
		}
	}

	/**
	 * Starts current level
	 */
	public void startLevel()
	{
		arrLevels.get(currentLevelId).runLevel();
		arrLevels.get(currentLevelId).getTileXYByPixel(92, 10);
	}

	/**
	 * Checks if the password belongs to a level
	 * If it does, set the current level and returns true
	 * If it doesn't returns false
	 * @param password
	 * @return
	 */
	public boolean checkPassword(String password)
	{
		for(Level level : arrLevels)
		{
			if(level.getPassword().equals(password))
			{
				currentLevelId = arrLevels.indexOf(level);
				return true;
			}
		}
		return false;
	}

	/**
	 * Starts the next available level
	 */
	public void nextLevel()
	{
		if(currentLevelId != arrLevels.size() - 1)
		{
			currentLevelId++;
			startLevel();
		}
	}

	/**
	 * Starts the previous level
	 */
	public void prevLevel()
	{
		if(currentLevelId != 0)
		{
			currentLevelId--;
			startLevel();
		}
	}

	/**
	 * Starts a specific level
	 * @param levelId
	 */
	public void gotoLevel(int levelId)
	{
		currentLevelId = levelId;
		startLevel();
	}

	// Get functions
	/**
	 * Returns total amount of levels
	 * @return
	 */
	public int getLevelCount()
	{
		return arrLevels.size();
	}

	/**
	 * Returns the current level
	 * @return
	 */
	public int getCurrentLevelId()
	{
		return currentLevelId;
	}

	/**
	 * Get the level object of the current level
	 * @return
	 */
	public Level getCurrentLevel()
	{
		return arrLevels.get(currentLevelId);
	}

	public int[] getCurrentLevelSize()
	{
		return getCurrentLevel().getLevelSize();
	}

	/**
	 * Get the level object of the given level
	 * @param levelId
	 * @return
	 */
	public Level getLevel(int levelId)
	{
		return arrLevels.get(levelId);
	}

	/**
	 * Returns a list of available level files
	 * @return
	 */
	private String[] getLevelDirList()
	{
		String[] levels = new String[1];
		levels[0] = "level1.hlf";
		return levels;
	}
}
