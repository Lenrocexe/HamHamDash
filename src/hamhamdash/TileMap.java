package hamhamdash;

import java.io.*;
import java.util.*;

import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class TileMap
{
	private JGEngine game;
	public TileMap(JGEngine Game)
    {
        this.game = Game;
    }
    public void paintTiles()
	{
		// Read File
		int h=0;
		List<String> lineList = new ArrayList<String>();
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/level"+Game.currentLevelId+".hlf")));
			String line;
			while( ( line = br.readLine() ) != null )
			lineList.add( line );
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
		String[] lines = new String[ lineList.size() ];
		lineList.toArray( lines );
		// Paint the tiles on the Game panel
		game.setTiles(0,0,lines);
    }
    public void digTile()
	{	// Convert a ground tile to an empty tile
		game.setTile(1,1,".");
    }
	public void explodeTile()
	{	// Convert any tile to an empty tile
		game.setTile(1,2,".");
	}
}
