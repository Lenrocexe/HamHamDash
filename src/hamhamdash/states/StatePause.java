package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Serhan Uygur
 */
public class StatePause extends State
{
	private String toDrawImage;
	private boolean started = false; //lowsy boolean for start() method

	// Pause Screens
	//	[ Main Screen Name ][ Sub Screens ][ Pages ]
	private String[][][] pauseScreens =
	{
		{{"pause_res_game"}, {""} , {""}},														// Item 0, zonder submenu's
		{{"pause_help"}, {"game_goal", "game_controls", "game_objects"} , {"1", "2", "5"}},		// Item 1, met submenu's
		{{"pause_exit_title"}, {""} , {""}},													// Item 2, zonder submenu's
		{{"pause_exit_windows"}, {""} , {""}}													// Item 3, zonder submenu's
	};

	// Counters for the screens
	private int currentMainScreen = 0;
	private int currentSubScreen = 0;
	private int currentPage = 0;

	// Checkers for sub or not
	private boolean inSub = false;
	private boolean arePages = false;
	
	public StatePause()
	{
	}

	@Override
	public void start()
	{
		game.paused = true;
		toDrawImage = pauseScreens[0][0][0];
		started = true;
	}

	@Override
	public void doFrame()
	{
		if(started)
		{
			if(game.getKey(Game.KeyEsc))
			{
				game.clearKey(Game.KeyEsc);
				if(inSub)
				{
					inSub = false;
					toDrawImage = pauseScreens[1][0][0];
				}
				else
				{
					game.paused = false;
					game.recoverState();
					game.removeGameState("Pause");
				}
			}
			else if(game.getKey(Game.KeyDown))
			{
				game.clearKey(Game.KeyDown);
				nextScreen(pauseScreens);
			}
			else if(game.getKey(Game.KeyUp))
			{
				game.clearKey(Game.KeyUp);
				prevScreen(pauseScreens);
			}
			else if(game.getKey(Game.KeyEnter))
			{
				game.clearKey(Game.KeyEnter);
				
				if (toDrawImage.endsWith(pauseScreens[0][0][0]))
				{

				}
				else if(toDrawImage.equals(pauseScreens[1][0][0]))
				{
					inSub = true;
					toDrawImage = pauseScreens[1][1][0];
				}
				else if(toDrawImage.endsWith(pauseScreens[2][0][0]))
				{
					game.setCurrentState("Title");
					game.paused = false;
				}
				else if(toDrawImage.endsWith(pauseScreens[3][0][0]))
				{
					game.exitEngine("Thank you for playing!");
				}

			}
		}

	}

	@Override
	public void paintFrame()
	{
		if(started)
		{
			game.drawImage(game.getViewportWidth() / 2 - (256/2), game.getViewportHeight() / 2 - (250/2), toDrawImage);
		}
	}


	public void nextScreen(String[][][] pauseScreens)
	{
		String toDrawImage = "";
		
		if(!inSub)
		{
			if(currentMainScreen < pauseScreens.length - 1)
			{
				currentMainScreen++;
			}
			toDrawImage = pauseScreens[currentMainScreen][0][currentSubScreen];
		}
		else
		{
			
			if(currentSubScreen < pauseScreens[currentMainScreen].length-1)
			{
				currentSubScreen++;
			}
			toDrawImage = pauseScreens[currentMainScreen][1][currentSubScreen];
		}
		

		this.toDrawImage = toDrawImage;
	}

	public void prevScreen(String[][][] pauseScreens)
	{
		String toDrawImage = "";



		if(!inSub)
		{
			if(currentMainScreen > 0)
			{
				currentMainScreen--;
			}
			toDrawImage = pauseScreens[currentMainScreen][0][currentSubScreen];
		}
		else
		{

			if(currentSubScreen > 0)
			{
				currentSubScreen--;
			}
			
			toDrawImage = pauseScreens[currentMainScreen][1][currentSubScreen];
		}


		this.toDrawImage = toDrawImage;
	}
}
