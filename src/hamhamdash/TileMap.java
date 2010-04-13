package hamhamdash;

// General Java
import java.io.*;
import java.util.*;
// JGame
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
    public void paintTiles(){

	// Read File
	int h=0;
	List<String> lineList = new ArrayList<String>();
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("levels/level"+Game.currentLevelId+".hlf")));
	    String line;
	    while( ( line = br.readLine() ) != null )
	    lineList.add( line );
	} catch( IOException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		br.close();
	    } catch( IOException ex ) {
		ex.printStackTrace();
	    }
	}
	String[] lines = new String[ lineList.size() ];
	String[] mapRows = new String[ lineList.size() ];
	lineList.toArray( lines );
	game.setTiles(0,0,lines);

	digTile();
    }
    public void digTile(){
	//game.setTileCid(1,1,0);
	//game.setTile(1,1,"*");
	//System.out.println(game.getTileCid(1,1));
    }
}
