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
    public String[] getTiles(int levelId)
	{
		// Read File
		int h=0;
		List<String> lineList = new ArrayList<String>();
		BufferedReader br = null;
		try
		{	// Put lines in ArrayList
			br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/level"+levelId+".hlf")));
			String line;
			boolean blockStarted = false;
			while( ( line = br.readLine() ) != null ){
				if(blockStarted)
				{
					lineList.add( line );
				}
				if(line.contains("[MAP]"))
				{
					blockStarted = true;
				}
				else if(line.contains("[/MAP]"))
				{
					blockStarted = false;
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
		String[] lines = new String[ lineList.size() ];
		lineList.toArray( lines );
		// Return TileMap
		return lines;
		
    }
}
