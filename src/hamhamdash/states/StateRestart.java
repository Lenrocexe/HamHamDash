package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.State;
import jgame.JGColor;

/**
 *
 * @author Serhan Uygur
 */
public class StateRestart extends State
{
	private boolean started = false; //lowsy boolean for start() method


	public StateRestart()
	{
		
	}
@Override
	public void start()
	{
	
		game.setBGColor(JGColor.red);
		System.out.println("Restarting!");

		started = true;
	}

	@Override
	public void doFrame()
	{
//		System.out.println("Restarting!");
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(0, 0, "restart_bg");
		game.drawString("Je ben DOOD, Jongè!", game.viewWidth() / 2, game.viewHeight() / 2, 0);
		game.drawString("Life x" + game.getPlayer().getLifes(), game.viewWidth() / 2, (game.viewHeight() / 2)  + 30, 0);
	}


}
