package hamhamdash.states;

import hamhamdash.*;


/**
 *
 * @author Cornel Alders
 */
public class StateInGame extends State
{
	private boolean init = false;
	private int waitTillDeath = 0; //Used for selfKill
	
	public StateInGame()
	{
		game.setBackground(null);
		game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
		game.getObjLevels().startLevel();
		int startPosX = game.getObjLevels().getCurrentLevel().getStartPosition()[0] * game.tileWidth();
		int startPosY = game.getObjLevels().getCurrentLevel().getStartPosition()[1] * game.tileHeight();
		game.getPlayer().setPc(new PlayerCharacter(game.getPlayer().getIdentifier(), startPosX, startPosY, game.getPlayer()));
		game.resetTimer();
		Jukebox.playMusic("levelbg");
	}

	@Override
	public void start()
	{
	}

	@Override
	public void doFrame()
	{
		if(game.isDebug())
		{
			//Debug to get player positions
			if(game.getMouseButton(1))
			{
				game.clearMouseButton(1);
				System.out.println("Mouse X: " + game.getMouseX() + " Y: " + game.getMouseY());
				System.out.println("Player BBox: " + game.getPlayer().getPc().getBBox());
				System.out.println("Player ImageBBox: " + game.getPlayer().getPc().getImageBBox());
				System.out.println("Player Tiles: " + game.getPlayer().getPc().getTiles());
				System.out.println("Player TopleftTile: " + game.getPlayer().getPc().getTopLeftTile());
			}
			//Debug for level testing
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyRight);
				game.getObjLevels().nextLevel();
				started = false;
			}
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyLeft))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyLeft);
				game.getObjLevels().prevLevel();
				started = false;
			}
		}

		if(game.getKey(Game.KeyEsc))
		{
			game.clearKey(Game.KeyEsc);
			game.addState("Pause");
		}

		if(game.getKey(game.KeyCtrl) && game.getKey(game.getKeyCodeStatic("1")))
		{
			waitTillDeath++;
		}
		else
		{
			waitTillDeath = 0;
		}

		game.startTimer();
		game.moveObjects();
		//Object collision
//		game.checkCollision(1, 4); // Hamtaro , Rock
//		game.checkCollision(2, 4); // Enemy, Rock
		//Enemy -> Hamtaro
		game.checkCollision(2, 1);
		//Diamond -> Hamtaro
		game.checkCollision(3, 1);
		//Rock -> Hamtaro
		game.checkCollision(4, 1);
		game.checkCollision(1, 4);
		//Rock, Diamond <-> Rock, Diamond
		game.checkCollision(4, 4);
		game.checkCollision(3, 4);
		game.checkCollision(4, 3);
		game.checkCollision(3, 3);
		//Enemy collision
		game.checkCollision(2, 2);
		game.checkCollision(3, 2);
		game.checkCollision(4, 2);
		game.checkCollision(2, 3);
		game.checkCollision(2, 4);
		//Tile collision
//		//Hamtaro
//		game.checkBGCollision(69, 1);
		game.checkBGCollision(2, 1);
//		game.checkBGCollision(3, 1);
//		game.checkBGCollision(4, 1);
//		//Enemy
//		game.checkBGCollision(1, 2);
//		game.checkBGCollision(2, 2);
//		game.checkBGCollision(3, 2);
//		game.checkBGCollision(4, 2);
		//Daimond
		game.checkBGCollision(69, 3);
//		game.checkBGCollision(7, 3);
//		game.checkBGCollision(6, 3);
//		game.checkBGCollision(3, 3);
//		game.checkBGCollision(2, 3);
		//Rock
		game.checkBGCollision(69, 4);
		//Objects should only be loaded once
		if(!started)
		{
			game.getCurrentLevel().insertGObjects();
			started = true;
		}

		//Try to move the viewoffset based on the players coordinates
//		try
//		{
			game.setXoffset((int) game.getPlayer().getPc().x + game.viewWidth() / game.viewWidth());
			game.setYoffset((int) game.getPlayer().getPc().y + game.viewHeight() / game.viewHeight());
//		}
//		catch(java.lang.NullPointerException e){}

		game.setViewOffset(game.getXoffset(), game.getYoffset(), true);

		if(game.getTimer() == 0 || waitTillDeath >= 100)
		{
			//Need a graphic that tells times up.
			game.getPlayer().getPc().remove();
			game.getPlayer().getPc().setWalking(false);
			game.getPlayer().getPc().setAlive(false);
			game.getPlayer().getPc().setGraphic(game.getPlayer().getIdentifier() + "howdie");
			game.getPlayer().resetLevelScore();
			game.stopTimer();
			game.addState("Death");
		}
	}

	@Override
	public void paintFrame()
	{
		int seconden = (int)Math.round((game.getTimer()/game.getFrameRate()) + 0.0);
		int x = (game.getViewportWidth() / 2) - 66;
		int y = 0;

		// ONDER ELKAAR
//		game.drawImage(x + 100, y + 5, "timebox", false);
//		game.drawString("" + seconden, x + 140, y + 10, 1);
//		game.drawImage(x + 100, y + 0, "scorebox", false);
//		game.drawString("" + (game.getPlayer().getScore() + game.getPlayer().getLevelScore()), x + 140, y + 25, 1);
//		game.drawImage(x + 100, y + 35, "daimondbox", false);
//		game.drawString("" + (game.getObjLevels().getCurrentLevelRemainingDiamonds()), x + 140, y + 40, 1);


		// NAAST ELKAAR
		game.drawImage(x + 100, y + 5, "timebox", false);
		game.drawString("" + seconden, x + 138, y + 10, 1);
		game.drawImage(x + 144, y + 5, "scorebox", false);
		game.drawString("" + (game.getPlayer().getScore() + game.getPlayer().getLevelScore()), x + 182, y + 10, 1);
		game.drawImage(x + 188, y + 5, "daimondbox", false);
		game.drawString("" + (game.getObjLevels().getCurrentLevelRemainingDiamonds()), x + 227, y + 10, 1);
	}
}
