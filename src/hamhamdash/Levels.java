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
		for(i=0; i < levels.length; i++)
		{
			arrLevels[i] = new Level(game,i,levels[i]);
			/*int search = Arrays.binarySearch(levels, "level"+findLevel+".hlf");
			if(search >= 0)
			{
				System.out.println("newlevelid: "+newLevelId);
				//arrLevels[newLevelId] = new Level(game, i, "level"+findLevel+".hlf");
				Level tmpObjLevel = new Level(game,1,"level"+findLevel+".hlf");
				//arrLevels.add(tmpObjLevel);
				newLevelId++;
			}
			findLevel++;*/
		}

	}
	public void startLevel()
	{
		//arrLevels[currentLevelId].runLevel();
		//System.out.println(arrLevels[currentLevelId-1]);
	}
    public void changeLevel(int direction)
	{
		/*
		// Get the available levels in an array
		String[] levels = getAvailableLevels();

		// Search the array for the next level (if there is one)
		boolean found = false;
		int findLevel = Game.currentLevelId + direction;
		int i;
		for(i=0; i < levels.length; i++)
		{
			int search = Arrays.binarySearch(levels, "level"+findLevel+".hlf");
			if(search >= 0)
			{
				found = true;
				break;
			}
			else {
				if(direction < 0)
				{
					findLevel--;
				} else
				{
					findLevel++;
				}
			}
		}

		if(found == true)
		{
			gotoLevel(findLevel);
		} */

		// Search the array for the next level (if there is one)
		
    }
	public void gotoLevel(int levelId)
	{
		//Game.currentLevelId = levelId;
		//game.dbgPrint("You are currently running level "+ levelId);
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
