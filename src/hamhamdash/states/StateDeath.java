package hamhamdash.states;

import hamhamdash.State;
import jgame.JGTimer;

/**
 *
 * @author Serhan Uygur
 */
public class StateDeath extends State
{
	private boolean started = false; //lowsy boolean for start() method

	public StateDeath()
	{
	}

	@Override
	public void start()
	{
		System.out.println("I am Dead!!!!");
//		new JGTimer(70, true)
//		{
//			// the alarm method is called when the timer ticks to zero
//			public void alarm()
//			{
//				game.getPlayer().getPc().remove();
//
//				System.out.println("Removed Player");
//
//				if (game.getPlayer().getLifes() > 0)
//				{
//					game.getPlayer().removeLife();
//					game.setCurrentState("InGame");
//					System.out.println("Continue!!!");
//				}
//				else
//				{
//					game.resetViewport();
//					game.setCurrentState("GameOver");
//				}
//			}
//		};





		








		started = true;
	}

	@Override
	public void doFrame()
	{
		new JGTimer(50, true, "Death")
		{
			// the alarm method is called when the timer ticks to zero
			public void alarm()
			{
				System.out.println("Making Timer!");

//				game.getPlayer().getPc().remove();
//				System.out.println("Removed Player");

				if (game.getPlayer().getLifes() > 0)
				{
					game.getPlayer().removeLife();
					game.resetViewport();
					game.setCurrentState("Restart");
					System.out.println("Continue with restart of game!!!");
				}
				else
				{
					game.resetViewport();
					game.setCurrentState("GameOver");
				}
			}
		};
//		System.out.println("Made Timer!");
	


//		if (game.getPlayer().getLifes() > 0)
//		{
//			game.getPlayer().removeLife();
//			game.setCurrentState("Restart");
//			System.out.println("Continue with restart of game!!!");
//		}
//		else
//		{
//			game.resetViewport();
//			game.setCurrentState("GameOver");
//		}
	}

	@Override
	public void paintFrame()
	{
//		game.drawImage(0, 0, "title_bg");
//		game.drawString("Press <ENTER> to continue", game.viewWidth() / 2, game.viewHeight() - 50, 0);
	}
}
