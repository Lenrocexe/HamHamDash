package hamhamdash.states;

import hamhamdash.*;
import java.awt.Color;

/**
 *
 * @author Cornel Alders
 */
public class StateInGame extends State
{
	private boolean init = false;
	public StateInGame()
	{
		game.setBackground(null);
	}

	@Override
	public void start()
	{
		game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
		game.getObjLevels().startLevel();
		game.getPlayer().setPc(new PlayerCharacter("h", 80, 160));
		game.stateCounter = 0;
	}

	@Override
	public void doFrame()
	{
		game.moveObjects();
//					System.out.println("Player: " + player.getPc().colid);
//					System.out.println("Enemy: " + enemy.colid);
		//Object collision
		//Enemy -> Hamtaro
		game.checkCollision(2, 1);
		//Diamond -> Hamtaro
		game.checkCollision(3, 1);
		//Rock -> Hamtaro
		game.checkCollision(4, 1);
		//Enemy collision
		game.checkCollision(2, 2);
		game.checkCollision(3, 2);
		game.checkCollision(4, 2);
		//Tile collision
		//Hamtaro
		game.checkBGCollision(1, 1);
		game.checkBGCollision(game.player.getPc().getBBox());
		game.checkBGCollision(game.player.getPc().getBBox());
		game.checkBGCollision(game.player.getPc().getBBox());
		//Enemy
		game.checkBGCollision(1, 2);
		game.checkBGCollision(2, 2);
		game.checkBGCollision(3, 2);
		game.checkBGCollision(4, 2);

		if(!init)
		{
			game.getCurrentLevel().insertGObjects();
			init = true;
		}
		if(game.isDebug())
		{
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyRight);
				game.getObjLevels().nextLevel();
			}
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyLeft))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyLeft);
				game.getObjLevels().prevLevel();
			}
		}
		game.setXoffset((int) game.player.getPc().x + game.viewWidth() /game.viewWidth());
		game.setYoffset((int) game.player.getPc().y + game.viewHeight() / game.viewHeight());

		game.setViewOffset(game.getXoffset(), game.getYoffset(), true);
		if(game.getMouseButton(1))
		{
			System.out.println("X: " + game.getMouseX() + " Y: " + game.getMouseY());
			System.out.println(game.getPlayer().getPc().getBBox());
			System.out.println(game.getPlayer().getPc().getImageBBox());
			System.out.println(game.getPlayer().getPc().getTiles());
			System.out.println(game.getPlayer().getPc().getTopLeftTile());
		}
//		game.getPlayer().getPc().setTileBBox(game.getPlayer().getPc().getTopLeftTile().x, game.getPlayer().getPc().getTopLeftTile().y, 1, 1);
	}

	@Override
	public void paintFrame()
	{
	}
}
