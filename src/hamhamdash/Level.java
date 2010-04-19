package hamhamdash;

import java.io.*;
import java.util.*;

/**
 *
 * @author Cornel Alders
 */
public class Level
{
	private Game game = Game.getGame();
	private TileMap objTileMap;
	private Properties settings = new Properties();
	private String[] tileMap;
	private String fileName;
	private ArrayList<int[]> arrEnemies = new ArrayList<int[]>();
	private ArrayList<Enemy> arrEnemyObj = new ArrayList<Enemy>();
	private ArrayList<int[]> arrDiamonds = new ArrayList<int[]>();
	private ArrayList<int[]> arrRocks = new ArrayList<int[]>();
	private int[] exitPos = new int[2];

	/**
	 * Level Constructor
	 * @param Game
	 * @param levelId
	 * @param fileName
	 */
	public Level(String fileName)
	{
		this.fileName = fileName;
		loadSettings();
		loadMapObjects();
	}

	/**
	 * Runs the current level
	 */
	public void runLevel()
	{
		// Get and Paint TileMap
		objTileMap = new TileMap();
		tileMap = objTileMap.getTiles(loadDataFile());
		game.setTiles(0, 0, tileMap);
		clearField();
	}

	/**
	 * Inserts game objects
	 */
	public void insertGObjects()
	{
		// Insert Diamonds
		for(int[] d : arrDiamonds)
		{
			int type = d[0];
			int x = d[1];
			int y = d[2];
			Diamond diamond = new Diamond("diamond", true, x*game.getTileSize(), y*game.getTileSize(), "diamond");
		}

		// Insert Rocks
		for(int[] r : arrRocks)
		{
			int type = r[0];
			int x = r[1];
			int y = r[2];
			Rock rock = new Rock("rock", true, x*game.getTileSize(), y*game.getTileSize(), "rock");
		}

		// Clear Enemies
		for(int[] e : arrEnemies)
		{
			int type = e[0];
			int x = e[1]; // Tile location multiplied by the tile size = tile location in pixels
			int y = e[2];
			String name = null;
			if(type==1)
			{
				name = "SpatA";
			}
			else if(type==2)
			{
				name = "SpatB";
			}

			arrEnemyObj.add(new Enemy(name, x*game.getTileSize(), y*game.getTileSize()));
		}
	}

	public void clearField()
	{
		// Insert Diamonds
		for(int[] d : arrDiamonds)
		{
			int x = d[1];
			int y = d[2];
			game.setTile(x,y,".");
		}

		// Insert Rocks
		for(int[] r : arrRocks)
		{
			int type = r[0];
			int x = r[1];
			int y = r[2];
			game.setTile(x,y,".");
		}

		// Clear Enemies
		for(int[] e : arrEnemies)
		{

			int x = e[1]; // Tile location multiplied by the tile size = tile location in pixels
			int y = e[2];
			game.setTile(x,y,".");
		}
	}

	/**
	 * Loads the level datafile
	 * @return
	 */
	private InputStream loadDataFile()
	{
		InputStream datafile = this.getClass().getResourceAsStream("levels/" + fileName);
		return datafile;
	}

	/**
	 * Loads the level settings
	 */
	private void loadSettings()
	{
		InputStream readSettings = loadDataFile();
		try
		{
			settings.load(readSettings);
		}
		catch(IOException ex){}
		finally
		{
			try
			{
				readSettings.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Loads the objects found on the map
	 */
	private void loadMapObjects()
	{
		// Read File
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(loadDataFile()));

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

					for(x = 0; x < line.split("").length; x++)
					{
						if(chars[x].matches("D"))
						{
							int[] pos = new int[3];
							pos[0] = 1;
							pos[1] = x - 1;
							pos[2] = y;
							arrDiamonds.add(pos);
						}
						else if(chars[x].matches("R"))
						{
							int[] pos = new int[3];
							pos[0] = 2;
							pos[1] = x - 1;
							pos[2] = y;
							arrRocks.add(pos);
						} 
						else if(chars[x].matches("1"))
						{
							int[] pos = new int[3];
							pos[0] = 1;
							pos[1] = x - 1;
							pos[2] = y;
							arrEnemies.add(pos);
						}
						else if(chars[x].matches("2"))
						{
							int[] pos = new int[3];
							pos[0] = 2;
							pos[1] = x - 1;
							pos[2] = y;
							arrEnemies.add(pos);
						}
						else if(chars[x].matches("E"))
						{
							exitPos[0] = x - 1;
							exitPos[1] = y;
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
		game.setTile(x, y, ".");
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

	public void openExit()
	{
		game.setTile(exitPos[0], exitPos[1], "E");
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
	 * Returns the tile type directly above the given position
	 * @param x
	 * @param y
	 * @return
	 */
	public String getTileAbove(int x, int y)
	{
		return new String(game.getTileStr(x, y - 1));
	}

	/**
	 * Returns the tile type directly right of the given position
	 * @param x
	 * @param y
	 * @return
	 */
	public String getTileRight(int x, int y)
	{
		return new String(game.getTileStr(x + 1, y));
	}

	/**
	 * Returns the tile type directly left of the given position
	 * @param x
	 * @param y
	 * @return
	 */
	public String getTileLeft(int x, int y)
	{
		return new String(game.getTileStr(x - 1, y));
	}

	/**
	 * Returns the tile type directly below the given position.
	 * @return
	 */
	public String getTileBelow(int x, int y)
	{
		return new String(game.getTileStr(x, y + 1));
	}

	/**
	 * Returns the tile type at the lower left corner of the given position.
	 * @return
	 */
	public String getTileBelowLeft(int x, int y)
	{
		return new String(game.getTileStr(x - 1, y + 1));
	}

	/**
	 * Returns the tile type at the lower right corner of the given position.
	 * @return
	 */
	public String getTileBelowRight(int x, int y)
	{
		return new String(game.getTileStr(x + 1, y + 1));
	}

	/**
	 * Returns an array of the surrounding tiles of the given tile.
	 * For each tile a String identifier, X and Y value are available.
	 * @param x
	 * @param y
	 * @return
	 */
	public String[][] getSurroundingTiles(int x, int y)
	{
		String[][] surroundingTiles = new String[8][3];
		int cntCoord = 0;
		int newY, newX;
		for(newY = y - 1; newY <= y + 1; newY++)
		{
			for(newX = x - 1; newX <= x + 1; newX++)
			{
				if(!(newX == x && newY == y))
				{
					surroundingTiles[cntCoord][0] = game.getTileStr(newX, newY);
					surroundingTiles[cntCoord][1] = newX + "";
					surroundingTiles[cntCoord][2] = newY + "";
					cntCoord++;
				}
			}
		}
		return surroundingTiles;
	}

	/**
	 * Returns an array with the X and the Y value of a tile calculated by pixel X and Y
	 * @param x
	 * @param y
	 * @return Array with Tile X [0] and Tile Y [1]
	 */
	public int[] getTileXYByPixel(int x, int y)
	{
		Double tileX = Math.floor((double) x / game.tileWidth());
		Double tileY = Math.floor((double) y / game.tileHeight());
		int[] tileXY = new int[2];
		tileXY[0] = tileX.intValue();
		tileXY[1] = tileY.intValue();
		return tileXY;
	}

	/**
	 * Returns an array with the tile count in the X and Y direction of the level
	 * @return
	 */
	public int[] getLevelSize()
	{
		int[] levelSize = new int[2];
		levelSize[0] = Integer.parseInt(settings.getProperty("xtiles"));
		levelSize[1] = Integer.parseInt(settings.getProperty("ytiles"));

		return levelSize;
	}
}
