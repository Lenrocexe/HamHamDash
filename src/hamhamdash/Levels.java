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
    public void changeLevel(int direction)
	{
		// Get the available levels in an array
		String[] levels = getAvailableLevels();

		// Search the array for the next level (if there is one)
		boolean found = false;
		System.out.println(Game.currentLevelId);
		int findLevel = Game.currentLevelId + direction;
		System.out.println(findLevel);
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
					System.out.println("-");
					findLevel--;
				} else
				{
					System.out.println("+");
					findLevel++;
				}
			}
		}

		if(found == true)
		{
			System.out.println("Level "+findLevel+" found");
			gotoLevel(findLevel);
		} 
		else
		{
			System.out.println("Next level not found");
		}

    }
	public void gotoLevel(int levelId)
	{
		Game.currentLevelId = levelId;
		startLevel();
		game.dbgPrint("You are currently running level "+ levelId);
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
