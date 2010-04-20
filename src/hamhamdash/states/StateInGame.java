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
	}

	@Override
	public void paintFrame()
	{
	}
}
