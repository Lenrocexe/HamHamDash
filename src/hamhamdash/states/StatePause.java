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
		// Item 0, zonder submenu's
		{{"pause_res_game"}, {""} , {""}},
		// Item 1, met submenu's
		{{"pause_help"}, {"game_goal", "game_controls", "game_objects", "game_back"} , {"1", "2", "5", "0"}},
		// Item 2, zonder submenu's
		{{"pause_exit_title"}, {""} , {""}},
		// Item 3, zonder submenu's
		{{"pause_exit_windows"}, {""} , {""}}
	};

	// Counters for the screens
	private int currentMainScreen = 0;
	private int currentSubScreen = 0;
	private int currentPage = 1;

	// Checkers for sub or not
	private boolean inSub = false;
	private boolean arePages = false;
	
	public StatePause()
	{
	}

	@Override
	public void start()
	{
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
					currentSubScreen = 0;
				}
				else
				{
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
				
				if (toDrawImage.equals(pauseScreens[0][0][0]))
				{
					game.recoverState();
					game.removeGameState("Pause");
				}
				else if(toDrawImage.equals(pauseScreens[1][0][0]))
				{
					inSub = true;
					toDrawImage = pauseScreens[1][1][0] + 1; // select 1st sub page
				}
				else if(toDrawImage.equals(pauseScreens[2][0][0]))
				{
					game.setCurrentState("Title");
				}
				else if(toDrawImage.equals(pauseScreens[3][0][0]))
				{
					game.exitEngine("Thank you for playing!");
				}

			}
			else if(game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyRight);
//				prevScreen(pauseScreens);
				nextPage();
			}
		}

	}

	@Override
	public void paintFrame()
	{
		if(started)
		{
			game.drawImage(game.viewWidth() / 2 - (256/2), game.viewHeight() / 2 - (250/2), toDrawImage, false);
		}
	}


	public void nextScreen(String[][][] pauseScreens)
	{
		String toDrawImage = "";
		
		if(!inSub)
		{
			if(currentMainScreen < pauseScreens.length - 1)		// -1 because we start with 0
			{
				currentMainScreen++;
			}
			toDrawImage = pauseScreens[currentMainScreen][0][0];
		}
		else
		{
			currentPage = 1;
			if(currentSubScreen < pauseScreens[currentMainScreen].length)
			{
				currentSubScreen++;
			}
			toDrawImage = pauseScreens[currentMainScreen][1][currentSubScreen] + currentPage;
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
			toDrawImage = pauseScreens[currentMainScreen][0][0];
		}
		else
		{
			currentPage = 1;
			if(currentSubScreen > 0)
			{
				currentSubScreen--;
			}
			
			toDrawImage = pauseScreens[currentMainScreen][1][currentSubScreen] + currentPage;
		}


		this.toDrawImage = toDrawImage;
	}

	public void nextPage()
	{
		String toDrawImage = "";


//		if(currentMainScreen < pauseScreens.length - 1)		// -1 because we start with 0
		{
			currentPage++;
		}
		toDrawImage = pauseScreens[currentMainScreen][currentSubScreen][0] + currentPage;

System.out.println(toDrawImage);

		this.toDrawImage = toDrawImage;
	}

}
