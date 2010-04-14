package hamhamdash;

import java.io.*;
import java.util.*;
import jgame.platform.*;
import java.net.URL;


/**
 *
 * @author Cornel Alders
 */
public class Level
{
    private JGEngine game;
	private TileMap objTileMap;
	private Properties settings;
	private String[] tileMap;
	private String fileName;
	public int levelId;

    public Level(JGEngine Game, int levelId, String fileName)
    {
		this.game = Game;
		this.levelId = levelId;
		this.fileName = fileName;
		//loadSettings();
    }
	
    public void runLevel(){
		// Get and Paint TileMap
		objTileMap = new TileMap(game);
		tileMap = objTileMap.getTiles(levelId);
		game.setTiles(0,0,tileMap);
    }
	private void loadSettings()
	{

        InputStream in = this.getClass().getResourceAsStream("levels/level1.hlf");
		try
		{
			System.out.println(in);
			in.read();
			BufferedReader test = new BufferedReader(new InputStreamReader(in));
			System.out.println(test.readLine());
			settings.load(test);
		}
		catch (IOException ex)
		{
			
		}
	
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
