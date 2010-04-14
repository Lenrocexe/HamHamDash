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
	private Level objLevel;
	public Level[] arrLevels;
	public int currentLevelId = 1;

	public Levels(JGEngine Game)
	{
		this.game = Game;
	}

	public void loadLevels()
	{
		// Get the available levels in an array
		String[] levels = getLevelDirList();

		arrLevels = new Level[levels.length];
		int i;
		for(i = 0; i < levels.length; i++)
		{
			Level tmpLevel = new Level(game, i, levels[i]);
			arrLevels[i] = tmpLevel;
		}
	}

	public void startLevel()
	{
		arrLevels[currentLevelId].runLevel();
	}

	public void startLevelPassword(String password)
	{
		int i;
		for(i = 0; i < arrLevels.length; i++)
		{
			if(arrLevels[i].getPassword() == password)
			{
				currentLevelId = i;
				startLevel();
				break;
			}
		}
	}

	public void nextLevel()
	{
		if(currentLevelId != arrLevels.length)
		{
			currentLevelId++;
			startLevel();
		}
	}

	public void prevLevel()
	{
		if(currentLevelId != arrLevels.length)
		{
			currentLevelId--;
			startLevel();
		}
	}

	public void gotoLevel(int levelId)
	{
		currentLevelId = levelId;
		startLevel();
	}

	// Get functions
	public int getLevelCount()
	{
		return arrLevels.length;
	}

	public int getCurrentLevel()
	{
		return currentLevelId;
	}

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
