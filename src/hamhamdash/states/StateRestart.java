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
//		System.out.println("Restarting!");
//		System.out.println("I should move things....");
		game.moveObjects();
	}

	@Override
	public void paintFrame()
	{
		objX = (game.viewWidth() / 2);
		objY = game.viewHeight() / 2;

		// Offset
		objY -= 20;

		game.setColor(JGColor.white);
		game.drawImage(0, 0, "restart_bg");
		game.drawString("Too bad you did not survive that. Have another go by pressing <ENTER>", objX, objY, 0);
//		game.drawImage(objX - 40, objY + 5, "hhamha2");
		obj.setPos(150, 150);
		obj.paint();
		System.out.println(obj.getImageName());
//		obj.dbgPrint();
		game.drawString("x" + game.getPlayer().getLifes(), objX, objX, -1);
	}


}
