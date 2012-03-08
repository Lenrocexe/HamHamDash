package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.Jukebox;
import hamhamdash.State;

/**
 *
 * @author Cornel Alders
 */
public class StateTitle extends State
{
	public StateTitle()
	{
		super("title");
		game.setViewOffset(0, 0, true);
		game.resetObjLevels();
		Jukebox.playMusic("titlebg1");
	}

	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		//Navigation
		if(game.getKey(Game.KeyEnter))
		{
			game.clearKey(Game.KeyEnter);
			game.setCurrentState("PlayerSelect");
		}

		//Juxebox
		if(game.getKey(Game.KeyCtrl) && game.getKey(game.getKeyCode("1")))
		{
			game.clearKey(game.getKeyCode("1"));
			Jukebox.playMusic("titlebg1");
		}
		else if(game.getKey(Game.KeyCtrl) && game.getKey(game.getKeyCode("2")))
		{
			game.clearKey(game.getKeyCode("2"));
			Jukebox.playMusic("titlebg2");
		}
		else if(game.getKey(Game.KeyCtrl) && game.getKey(game.getKeyCode("3")))
		{
			game.clearKey(game.getKeyCode("3"));
			Jukebox.playSound("select");
		}
		else if(game.getKey(Game.KeyCtrl) && game.getKey(game.getKeyCode("4")))
		{
			game.clearKey(game.getKeyCode("4"));
			Jukebox.stop();
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(0, 0, "title_bg");
		game.drawString("Press <ENTER> to continue", game.viewWidth() / 2, game.viewHeight() - 50, 0);
	}
}
