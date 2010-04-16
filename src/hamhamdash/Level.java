package hamhamdash;

import java.io.*;
import java.util.*;
import jgame.platform.*;
import jgame.JGRectangle.*;

/**
 *
 * @author Cornel Alders
 */
public class Level
{
	private JGEngine game;
	private TileMap objTileMap;
	private Properties settings = new Properties();
	private String[] tileMap;
	private String fileName;
	private ArrayList<int[]> arrEnemies = new ArrayList<int[]>();
	private ArrayList<int[]> arrDiamondRock = new ArrayList<int[]>();

	/**
	 * Level Constructor
	 * @param Game
	 * @param levelId
	 * @param fileName
	 */
	public Level(JGEngine Game, int levelId, String fileName)
	{
		this.game = Game;
		this.fileName = fileName;
		loadSettings();
		loadEnemies();
		loadDiamondRock();
	}

	/**
	 * Runs the current level
	 */
	public void runLevel()
	{
		// Get and Paint TileMap
		objTileMap = new TileMap(game);
		tileMap = objTileMap.getTiles(fileName);
		game.setTiles(0,0,tileMap);
		insertEnemies();
		insertDiamondRock();
    }

	/**
	 * Inserts enemies in the current level
	 */
	private void insertEnemies()
	{
		int i;
		for(i=0;i<arrEnemies.size();i++)
		{
			int x = arrEnemies.get(i)[1];
			int y = arrEnemies.get(i)[2];
			game.setTile(x,y,"."); // Clear background tiles
		}
	}

	/**
	 * Inserts Diamonds and Rocks in the current level
	 */
	private void insertDiamondRock()
	{
		int i;
		for(i=0;i<arrDiamondRock.size();i++)
		{
			int type = arrDiamondRock.get(i)[0];
			int x = arrDiamondRock.get(i)[1];
			int y = arrDiamondRock.get(i)[2];
			game.setTile(x,y,"."); // Clear background tiles
		}
	}

	/**
	 * Loads the level settings
	 */
	private void loadSettings()
	{
		InputStream readSettings = this.getClass().getResourceAsStream("levels/"+fileName);
		try
		{
			settings.load(readSettings);
		}

		catch (IOException ex)
		{
			
		}
		finally
		{
			try
			{
				readSettings.close();
			}
			catch( IOException ex )
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Loads the enemies
	 */
	private void loadEnemies()
	{
		// Read File
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/"+fileName)));

			String line;
			int y = 0;
			boolean blockStarted = false;
			while((line = br.readLine()) != null)
			{
				if(line.contains("[/MAP]"))
				{
					blockStarted = false;
				}
				if(blockStarted)
				{
					int x;
					String[] chars = new String[line.split("").length];
					chars = line.split("");
					
					for(x=0;x<line.split("").length;x++)
					{
						if(chars[x].matches("1"))
						{
							int[] pos = new int[3];
							pos[0] = 1;
							pos[1] = x-1;
							pos[2] = y;
							arrEnemies.add(pos);
						}
						else if(chars[x].matches("2"))
						{
							int[] pos = new int[3];
							pos[0] = 2;
							pos[1] = x-1;
							pos[2] = y;
							arrEnemies.add(pos);
						}
					}
				}
				y++;
				if(line.contains("[MAP]"))
				{
					blockStarted = true;
					y = 0;
				}
			}
		}
		catch(IOException e)
		{	// Fileread error
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Loads the diamonds and rocks
	 */
	private void loadDiamondRock()
	{
		// Read File
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/"+fileName)));

			String line;
			int y = 0;
			boolean blockStarted = false;
			while((line = br.readLine()) != null)
			{
				if(line.contains("[/MAP]"))
				{
					blockStarted = false;
				}
				if(blockStarted)
				{
					int x;
					String[] chars = new String[line.split("").length];
					chars = line.split("");

					for(x=0;x<line.split("").length;x++)
					{
						if(chars[x].matches("D"))
						{
							int[] pos = new int[3];
							pos[0] = 1;
							pos[1] = x-1;
							pos[2] = y;
							arrDiamondRock.add(pos);
						}
						else if(chars[x].matches("R"))
						{
							int[] pos = new int[3];
							pos[0] = 2;
							pos[1] = x-1;
							pos[2] = y;
							arrDiamondRock.add(pos);
						}
					}
				}
				y++;
				if(line.contains("[MAP]"))
				{
					blockStarted = true;
					y = 0;
				}
			}
		}
		catch(IOException e)
		{	// Fileread error
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Changes a given tile to an empty tile
	 * @param x
	 * @param y
	 */
	public void digTile(int x, int y)
	{
		game.setTile(1, 1, ".");
	}

	/**
	 * Explodes a given tile and changes it to an empty tile
	 * @param x
	 * @param y
	 */
	public void explodeTile(int x, int y)
	{
		game.setTile(1, 2, ".");
	}

	// Get Functions
	/**
	 * Returns total diamond count for this level
	 * @return
	 */
	public int getTotalDiamonds()
	{
		return 0;
	}

	/**
	 * Returns the timercount for this level
	 * @return
	 */
	public int getStartTimer()
	{
		return 0;
	}

	/**
	 * Returns the amoebe count for this level
	 * @return
	 */
	public int getAmoebetimer()
	{
		return 0;
	}

	/**
	 * Returns the password for this level
	 * @return
	 */
	public String getPassword()
	{
		return settings.getProperty("password");
	}

	/**
	 * Returns an ArrayList with Enemies in this level
	 * @return
	 */
	public ArrayList<String> getEnemies()
	{
		ArrayList<String> temp = new ArrayList<String>();

		return temp;
	}

	/**
	 * Returns an ArrayList with GObjects in this level
	 * @return
	 */
	public ArrayList<String> getGObjects()
	{
		ArrayList<String> temp = new ArrayList<String>();

		return temp;
	}

	/**
	 * Returns the tile type directly below the given position.
	 * @return
	 */
	public String getTileBelow(int x, int y)
	{
		return new String("X");
	}

	/**
	 * Returns the tile type at the lower left corner of the given position.
	 * @return
	 */
	public String getTileBelowLeft()
	{
		return new String("X");
	}

	/**
	 * Returns the tile type at the lower right corner of the given position.
	 * @return
	 */
	public String getTileBelowRight()
	{
		return new String("X");
	}
}
