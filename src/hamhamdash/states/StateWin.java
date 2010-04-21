package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Serhan Uygur
 */
public class StateWin extends State
{

	public StateWin()
	{
		
	}

	@Override
	public void start()
	{
		game.removeObjects(null, 2); // Clear all objects from the field
		//game.removeObjects(null, 3);
		game.removeObjects(null, 4);
		game.resetViewport();

		boolean nextLevel = game.getObjLevels().nextLevel();
		if(!nextLevel)
		{
		}
		else
		{
			game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
			game.getObjLevels().startLevel();
			int startPosX = game.getObjLevels().getCurrentLevel().getStartPosition()[0] * game.getTileSize();
			int startPosY = game.getObjLevels().getCurrentLevel().getStartPosition()[1] * game.getTileSize();
			game.player.getPc().setPos(startPosX, startPosY);
			game.stateCounter = 0;
			game.setCurrentState("InGame");
		}
	}

	@Override
	public void doFrame()
	{

	}

	@Override
	public void paintFrame()
	{
		game.drawImage(game.viewHeight()/2-100, game.viewHeight()/2-100, "congrats");
	}

}
