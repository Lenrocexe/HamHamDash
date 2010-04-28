package hamhamdash.states;

import hamhamdash.Game;
import hamhamdash.State;
import jgame.JGColor;
import jgame.JGObject;

/**
 *
 * @author Serhan Uygur
 */
public class StateRestart extends State
{
	private JGObject obj;
	int objX, objY;

	public StateRestart()
	{
		obj = new JGObject("hamreset", true, 0, 0, 0, "hhamha");
	}
	@Override
	public void start()
	{
		obj.startAnim();
		System.out.println("started.......");
	}

	@Override
	public void doFrame()
	{
		game.moveObjects();
		if(game.getKey(Game.KeyEnter))
		{
			game.clearKey(Game.KeyEnter);
			game.setCurrentState("InGame");
		}
	}

	@Override
	public void paintFrame()
	{
		objX = game.viewWidth() / 2;
		objY = game.viewHeight() / 2;

//		// Offset
		objY -= 20;

		game.setColor(JGColor.white);
		game.drawImage(0, 0, "restart_bg");
		game.drawString("Too bad you did not survive that. Have another go by pressing <ENTER>", objX, objY, 0);
		game.drawImage(objX - 45, objY + 5, game.getPlayer().getIdentifier() + "still");
		game.drawString("x" + game.getPlayer().getLifes(), objX, objY + 30, -1);
	}


}
