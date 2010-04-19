package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Serhan Uygur
 */
public class StatePause extends State implements Runnable
{
	private Thread pause;
	private boolean paused;
	private String toDrawImage;
	
	public StatePause()
	{
		paused = true;
		pause = new Thread(this);
		toDrawImage = "pause_res_game";

	}

	@Override
	public void start()
	{
		game.stop();
		pause.start();
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(Game.KeyEsc))
		{
			game.clearKey(Game.KeyEsc);
			paused = false;
			game.start();
			game.recoverState();
			game.removeGameState("Pause");
		}
		else if(game.getKey(Game.KeyDown))
		{
			game.clearKey(Game.KeyDown);
			toDrawImage = "pause_help";
		}
		else if(game.getKey(Game.KeyUp))
		{
			game.clearKey(Game.KeyDown);
			toDrawImage = "pause_res_game";
		}
	}

	@Override
	public void paintFrame()
	{
		if(paused)
		{
			game.drawImage(game.getViewportWidth() / 2 - (256/2), game.getViewportHeight() / 2 - (250/2), toDrawImage);
			System.out.println(toDrawImage);
		}
	}

	@Override
	public void run()
	{
		while(paused)
		{
			doFrame();
			paintFrame();

			try
			{
				Thread.sleep((int)(game.getFrameRate()) * 2);
			} catch (InterruptedException e)
			{
			}
			
		}
	}
}
