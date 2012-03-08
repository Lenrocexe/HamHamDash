package hamhamdash;

import java.io.*;
import java.util.*;

/**
 *
 * @author Cornel Alders
 */
public class TileMap
{
    /**
     * TileMap constructor
     * @param Game
     */
    public TileMap()
    {
    }

    /**
     * Returns an array of the tiles found in the levelfile
     * @param fileName
     * @return
     */
    public String[] getTiles(InputStream is)
    {
        // Read File
        int h = 0;
        List<String> lineList = new ArrayList<String>();
        BufferedReader br = null;
        try
        {	// Put lines in ArrayList
            br = new BufferedReader(new InputStreamReader(is));

            String line;
            boolean blockStarted = false;
            while((line = br.readLine()) != null)
            {
                if(blockStarted)
                {
                    lineList.add(line);
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
        String[] lines = new String[lineList.size()];
        lineList.toArray(lines);
        // Return TileMap
        return lines;
    }
}
