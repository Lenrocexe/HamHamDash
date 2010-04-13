package hamhamdash;

import jgame.platform.*;

/**
 *
 * @author Cornel Alders
 */
public class Level
{
    private TileMap objTileMap;
    private JGEngine game;

    public Level(JGEngine Game)
    {
	this.game = Game;
    }
    public void createLevel(){
	objTileMap = new TileMap(game);
	objTileMap.paintTiles();
    }
}
