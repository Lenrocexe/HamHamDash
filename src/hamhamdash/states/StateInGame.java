package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Cornel Alders
 */
public class StateInGame extends State
{
	public StateInGame()
	{
	}

	@Override
	public void start()
	{
		game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
		game.getObjLevels().startLevel();
		game.player.setPc(new PlayerCharacter("h", 80, 160));
		game.enemy = new Enemy("SpatA", 120, 160);
	}

	@Override
	public void doFrame()
	{
		game.setXoffset((int) game.player.getPc().x + game.pfWidth() /game.viewWidth());
		game.setYoffset((int) game.player.getPc().y + game.pfHeight() / game.viewHeight());

		game.setViewOffset(game.getXoffset(), game.getYoffset(), true);
	}

	@Override
	public void paintFrame()
	{
	}
}
