package hamhamdash.states;

import hamhamdash.*;
import jgame.JGTimer;

/**
 *
 * @author Serhan Uygur
 */
public class StateWin extends State
{
	boolean nextLevel = true;
	public StateWin()
	{
		
	}

	@Override
	public void start()
	{
		game.removeObjects(null, 2); // Clear all objects from the field
		game.removeObjects(null, 1);
		game.removeObjects(null, 4);
		game.resetViewport();

		game.getPlayer().addScoreToTotal();
		game.getPlayer().resetLevelScore();

		new JGTimer(70, true)
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				nextLevel = game.getObjLevels().nextLevel();
				if(nextLevel)
				{
					game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
					game.getObjLevels().startLevel();
					int startPosX = game.getObjLevels().getCurrentLevel().getStartPosition()[0] * game.getTileSize();
					int startPosY = game.getObjLevels().getCurrentLevel().getStartPosition()[1] * game.getTileSize();
					game.getPlayer().getPc().setPos(startPosX, startPosY);
					game.setCurrentState("InGame");
				}
			}
		};
	}

	@Override
	public void doFrame()
	{
		if(!nextLevel)
		{
			if(game.getKey(Game.KeyEnter) || game.getKey(Game.KeyEsc))
			{
				game.clearKey(game.getLastKey());
				game.setCurrentState("Title");
			}
		}
	}

	@Override
	public void paintFrame()
	{
		if(!nextLevel)
		{
			game.drawImage(game.viewHeight()/2-100, game.viewHeight()/2-100, "congrats");
		}
		else
		{
			game.drawImage(game.viewHeight()/2-150, game.viewHeight()/2-100, "victory");
		}
	}
}
