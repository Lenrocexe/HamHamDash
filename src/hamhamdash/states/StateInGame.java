package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Cornel Alders
 */
public class StateInGame extends State
{
	private boolean init = false;
	public StateInGame()
	{
	}

	@Override
	public void start()
	{
		game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
		game.getObjLevels().startLevel();
		game.player.setPc(new PlayerCharacter("h", 80, 160));
	}

	@Override
	public void doFrame()
	{
		if(!init)
		{
			game.enemy = new Enemy("SpatA", 120, 160);
			init = true;
		}
		if(game.isDebug())
		{
			if(game.getKey(game.KeyCtrl) && game.getKey(game.KeyShift) && game.getKey(game.KeyRight))
			{
				game.clearKey(game.KeyCtrl);
				game.clearKey(game.KeyShift);
				game.clearKey(game.KeyRight);
				game.getObjLevels().nextLevel();
			}
			if(game.getKey(game.KeyCtrl) && game.getKey(game.KeyShift) && game.getKey(game.KeyLeft))
			{
				game.clearKey(game.KeyCtrl);
				game.clearKey(game.KeyShift);
				game.clearKey(game.KeyLeft);
				game.getObjLevels().prevLevel();
			}
		}
		game.setXoffset((int) game.player.getPc().x + game.pfWidth() /game.viewWidth());
		game.setYoffset((int) game.player.getPc().y + game.pfHeight() / game.viewHeight());

		game.setViewOffset(game.getXoffset(), game.getYoffset(), true);
	}

	@Override
	public void paintFrame()
	{
	}
}
