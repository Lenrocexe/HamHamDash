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
		System.out.println("Starting Win!");
	}

	@Override
	public void doFrame()
	{
		System.out.println(game.player.getLifes());
	}

	@Override
	public void paintFrame()
	{
	}

}
