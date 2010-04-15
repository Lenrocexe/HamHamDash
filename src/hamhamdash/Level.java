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

	public Level(JGEngine Game, int levelId, String fileName)
	{
		this.game = Game;
		this.fileName = fileName;
		loadSettings();
		loadEnemies();
		loadDiamondStone();
	}

	public void runLevel()
	{
		// Get and Paint TileMap
		objTileMap = new TileMap(game);
		tileMap = objTileMap.getTiles(fileName);
		game.setTiles(0,0,tileMap);
		insertEnemies();
		insertDiamondRock();
    }

	public void insertEnemies()
	{
		int i;
		for(i=0;i<arrEnemies.size();i++)
		{
			int x = arrEnemies.get(i)[1];
			int y = arrEnemies.get(i)[2];
			game.setTile(x,y,"."); // Clear background tiles
		}
	}

	public void insertDiamondRock()
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

	private void loadDiamondStone()
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

	public void digTile()
	{	// Convert a ground tile to an empty tile
		game.setTile(1, 1, ".");
	}

	public void explodeTile()
	{	// Convert any tile to an empty tile
		game.setTile(1, 2, ".");
	}

	// Get Functions
	public int getTotalDiamonds()
	{
		return 0;
	}

	public int getStartTimer()
	{
		return 0;
	}

	public int getAmoebetimer()
	{
		return 0;
	}

	public String getPassword()
	{
		return settings.getProperty("password");
	}

	public ArrayList<String> getEnemies()
	{
		ArrayList<String> temp = new ArrayList<String>();

		return temp;
	}

	public ArrayList<String> getGObjects()
	{
		ArrayList<String> temp = new ArrayList<String>();

		return temp;
	}

	/**
	 * Returns the tile type directly below the given position.
	 * @return
	 */
	public String getTileBelow()
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
