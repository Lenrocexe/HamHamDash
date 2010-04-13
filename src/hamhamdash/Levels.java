package hamhamdash;

import jgame.platform.*;
import java.io.*;
import java.util.Arrays;
/**
 *
 * @author Cornel Alders
 */
public class Levels
{
    private Level objLevel;
    private JGEngine game;

    public Levels(JGEngine Game)
    {
        this.game = Game;
    }
    public void startLevel()
	{
		objLevel = new Level(game);
		objLevel.createLevel();
    }
    public void nextLevel()
	{
		String[] levels = getAvailableLevels();

		boolean found = false;
		int findLevel = Game.currentLevelId + 1;
		int i;
		for(i=0; i < levels.length; i++)
		{
			int search = Arrays.binarySearch(levels, "level"+findLevel+".hlf");
			if(search > 0)
			{
				found = true;
				break;
			}
			else {
				findLevel++;
			}
		}

		if(found == true)
		{
			Game.currentLevelId = findLevel;
			startLevel();
		} 
		else
		{
			System.out.println("Next level not found");
		}

    }
    public String[] getAvailableLevels()
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
