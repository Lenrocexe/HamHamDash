package hamhamdash;

import java.io.*;
import java.util.*;
import jgame.platform.*;

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
	private ArrayList arrEnemies = new ArrayList();

    public Level(JGEngine Game, int levelId, String fileName)
    {
		this.game = Game;
		this.fileName = fileName;
		loadSettings();
    }
	
    public void runLevel(){
		// Get and Paint TileMap
		objTileMap = new TileMap(game);
		tileMap = objTileMap.getTiles(fileName);
		game.setTiles(0,0,tileMap);
		loadEnemies();
    }

	private void loadSettings()
	{
        InputStream in = this.getClass().getResourceAsStream("levels/"+fileName);
		BufferedReader readSettings = new BufferedReader(new InputStreamReader(in));
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
		int h=0;
		List<String> lineList = new ArrayList<String>();
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/"+fileName)));
			String line;
			int currentEnemy = 0;
			Properties tempProperties = new Properties();
			boolean blockStarted = false;
			while( ( line = br.readLine() ) != null ){
				if(line.contains("[/ENEMY]") && blockStarted == true)
				{
					blockStarted = false;
					arrEnemies.add(tempProperties);
					tempProperties.clear();
					currentEnemy++;
				}

				if(blockStarted)
				{
					String key = line.split("=")[0];
					String value = line.split("=")[1];
					tempProperties.setProperty(key, value);
				}

				if(line.contains("[ENEMY]"))
				{
					blockStarted = true;
				}
			}
		}
		catch( IOException e )
		{	// Fileread error
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch( IOException ex )
			{
				ex.printStackTrace();
			}
		}

		/*int i;
		for(i=0;i<arrEnemies.size();i++)
		{
			System.out.println(arrEnemies.get(i));
		}*/
	}
	
	public void digTile()
	{	// Convert a ground tile to an empty tile
		game.setTile(1,1,".");
    }
	
	public void explodeTile()
	{	// Convert any tile to an empty tile
		game.setTile(1,2,".");
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
}
