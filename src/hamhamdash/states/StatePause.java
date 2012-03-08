package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Serhan Uygur
 */
public class StatePause extends State
{
	private String toDrawImage = "";
	// Pause Screens
	//	[ Main Screen Name ][ Sub Screens ][ Pages ]
	private String[][][] pauseScreens =
	{
		// Item 0, zonder submenu's
		{
			{"pause_res_game"},
			{""},
			{""}
		},
		// Item 1, met submenu's
		{
			{"pause_help"},
			{"game_goal", "game_controls", "game_objects", "game_back"},
			{"2", "2", "8", "0"}
		},
		// Item 2, zonder submenu's
		{
			{"pause_exit_title"},
			{""},
			{""}
		},
		// Item 3, zonder submenu's
		{
			{"pause_exit_windows"},
			{""},
			{""}
		}
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
		super("pauze");
		toDrawImage = pauseScreens[0][0][0];
		game.stopTimer();
		started = true;
	}

	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		if(started)
		{
			if(game.getKey(Game.KeyEsc))
			{
				game.clearKey(Game.KeyEsc);
				if(inSub) //Go back to previous menu
				{
					inSub = false;
					toDrawImage = pauseScreens[1][0][0];
					currentSubScreen = 0;
				}
				else //Exit pause state
				{
					game.recoverState();
					game.startTimer();
					game.repaint();
//					game.removeGameState("Pause");
				}
			}
			//navigate between options
			else if(game.getKey(Game.KeyUp)) // Moves selection up
			{
				game.clearKey(Game.KeyUp);
				currentPage = 1;
				prevScreen(pauseScreens);
			}
			else if(game.getKey(Game.KeyDown)) // Moves selection down
			{
				game.clearKey(Game.KeyDown);
				currentPage = 1;
				nextScreen(pauseScreens);
			}
			else if(game.getKey(Game.KeyEnter)) // Confirm selection
			{
				game.clearKey(Game.KeyEnter);

				if(toDrawImage.equals(pauseScreens[0][0][0]))
				{
					game.recoverState();
//					game.removeGameState("Pause");
				}
				else if(toDrawImage.equals(pauseScreens[1][0][0])) // Help item has subs
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

				if(toDrawImage.equals(pauseScreens[1][1][3] + 1))
				{
					inSub = false;
					toDrawImage = pauseScreens[1][0][0];
					currentSubScreen = 0;
				}
			}
			//Cycle through pages
			else if(game.getKey(Game.KeyRight) && inSub) //Go page forward
			{
				game.clearKey(Game.KeyRight);
				nextPage(pauseScreens);
			}
			else if(game.getKey(Game.KeyLeft) && inSub) //Go back a page
			{
				game.clearKey(Game.KeyLeft);
				prevPage(pauseScreens);
			}
		}
	}

	@Override
	public void paintFrame()
	{
		if(started)
		{
			game.drawImage(game.viewWidth() / 2 - (256 / 2), game.viewHeight() / 2 - (250 / 2), toDrawImage, false);
		}
	}

	public void nextScreen(String[][][] screens)
	{
		String toDrawImage = "";

		if(!inSub)
		{
			if(currentMainScreen < screens.length - 1)		// -1 because we start with 0
			{
				currentMainScreen++;
			}
			toDrawImage = screens[currentMainScreen][0][0];
		}
		else
		{
			if(currentSubScreen < screens[currentMainScreen].length)
			{
				currentSubScreen++;
			}
			toDrawImage = screens[currentMainScreen][1][currentSubScreen] + 1;
		}

		this.toDrawImage = toDrawImage;
	}

	public void prevScreen(String[][][] screens)
	{
		String toDrawImage = "";

		if(!inSub)
		{
			if(currentMainScreen > 0)
			{
				currentMainScreen--;
			}
			toDrawImage = screens[currentMainScreen][0][0];
		}
		else
		{
			if(currentSubScreen > 0)
			{
				currentSubScreen--;
			}

			toDrawImage = screens[currentMainScreen][1][currentSubScreen] + 1;
		}
		this.toDrawImage = toDrawImage;
	}

	public void nextPage(String[][][] screens)
	{
		String toDrawImage = "";

		if(currentPage < Integer.parseInt(screens[currentMainScreen][2][currentSubScreen]))
		{
			currentPage++;
		}
		toDrawImage = screens[currentMainScreen][1][currentSubScreen] + currentPage;

		this.toDrawImage = toDrawImage;
	}

	public void prevPage(String[][][] screens)
	{
		String toDrawImage = "";

		if(currentPage > 1)
		{
			currentPage--;
		}
		toDrawImage = screens[currentMainScreen][1][currentSubScreen] + currentPage;

		this.toDrawImage = toDrawImage;
	}
}
