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

		game.getPlayer().addScoreToTotal();
		game.getPlayer().resetLevelScore();

		boolean nextLevel = game.getObjLevels().nextLevel();
		if(!nextLevel)
		{
			//Do win logic here.
			System.out.println("over!");
		}
		else
		{
			game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
			game.getObjLevels().startLevel();
			int startPosX = game.getObjLevels().getCurrentLevel().getStartPosition()[0] * game.getTileSize();
			int startPosY = game.getObjLevels().getCurrentLevel().getStartPosition()[1] * game.getTileSize();
			game.getPlayer().getPc().setPos(startPosX, startPosY);
			game.setCurrentState("InGame");
		}
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyEnter) || game.getKey(Game.KeyEsc))
		{
			game.clearKey(game.getLastKey());
			game.setCurrentState("Title");
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(game.viewHeight()/2-100, game.viewHeight()/2-100, "congrats");
	}
}
