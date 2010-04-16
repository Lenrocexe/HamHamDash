package hamhamdash;

import jgame.platform.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Cornel Alders
 */
public class Levels
{
	private JGEngine game;
	private Level[] arrLevels;
	private int currentLevelId = 0;

	/**
	 * Levels constructor
	 * @param Game
	 */
	public Levels(JGEngine Game)
	{
		this.game = Game;
		loadLevels();
	}

	/**
	 * Loads the available levels in memory
	 */
	public void loadLevels()
	{
		// Get the available levels in an array
		String[] levels = getLevelDirList();

		arrLevels = new Level[levels.length];
		int i;
		for (i = 0; i < levels.length; i++)
		{
			arrLevels[i] = new Level(game, i, levels[i]);
		}
	}

	/**
	 * Starts current level
	 */
	public void startLevel()
	{
		arrLevels[currentLevelId].runLevel();
		arrLevels[currentLevelId].getTileXYByPixel(92, 10);
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

		for (int i = 0; i < arrLevels.length; i++)
		{
			if (arrLevels[i].getPassword().equals(password))
			{
				currentLevelId = i;
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
		if (currentLevelId != arrLevels.length)
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
		if (currentLevelId != 1)
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
		return arrLevels.length;
	}

	/**
	 * Returns the current level
	 * @return
	 */
	public int getCurrentLevel()
	{
		return currentLevelId;
	}

	/**
	 * Returns a list of available level files
	 * @return
	 */
	private String[] getLevelDirList()
	{
		File dir = new File("./src/hamhamdash/levels");
		String[] children = dir.list();

		FilenameFilter filter = new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.endsWith(".hlf");
			}
		};

		children = dir.list(filter);
		Arrays.sort(children);
		return children;
	}
}
