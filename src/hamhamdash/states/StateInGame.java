package hamhamdash.states;

import hamhamdash.*;

/**
 *
 * @author Cornel Alders
 */
public class StateInGame extends State
{
	private boolean init = false;

	public StateInGame()
	{
		game.setBackground(null);
	}

	@Override
	public void start()
	{
		game.setFieldSize(game.getObjLevels().getCurrentLevelSize());
		game.getObjLevels().startLevel();
		game.getObjLevels().getCurrentLevel().resetDiamonds();
		int startPosX = game.getObjLevels().getCurrentLevel().getStartPosition()[0] * game.tileWidth();
		int startPosY = game.getObjLevels().getCurrentLevel().getStartPosition()[1] * game.tileHeight();
		game.getPlayer().setPc(new PlayerCharacter("h", startPosX, startPosY));
		game.resetTimer();
		Jukebox.playMusic("levelbg");
	}

	@Override
	public void doFrame()
	{
		if(game.getKey(game.KeyEsc))
		{
			game.clearKey(game.KeyEsc);
			game.addState("Pause");
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
		if(!init)
		{
			game.getCurrentLevel().insertGObjects();
			init = true;
		}

		//Debug for level testing
		if(game.isDebug())
		{
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyRight))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyRight);
				game.getObjLevels().nextLevel();
			}
			if(game.getKey(Game.KeyCtrl) && game.getKey(Game.KeyShift) && game.getKey(Game.KeyLeft))
			{
				game.clearKey(Game.KeyCtrl);
				game.clearKey(Game.KeyShift);
				game.clearKey(Game.KeyLeft);
				game.getObjLevels().prevLevel();
			}
		}

		//Try to move the viewoffset based on the players coordinates
		try
		{
			game.setXoffset((int) game.getPlayer().getPc().x + game.viewWidth() / game.viewWidth());
			game.setYoffset((int) game.getPlayer().getPc().y + game.viewHeight() / game.viewHeight());
		}
		catch(java.lang.NullPointerException e){}

		game.setViewOffset(game.getXoffset(), game.getYoffset(), true);

		//Debug to get player positions
		if(game.isDebug() && game.getMouseButton(1))
		{
			System.out.println("X: " + game.getMouseX() + " Y: " + game.getMouseY());
			System.out.println(game.getPlayer().getPc().getBBox());
			System.out.println(game.getPlayer().getPc().getImageBBox());
			System.out.println(game.getPlayer().getPc().getTiles());
			System.out.println(game.getPlayer().getPc().getTopLeftTile());
		}

		if(game.getTimer() == 0)
		{
			game.getPlayer().getPc().remove();
			game.getPlayer().getPc().setWalking(false);
			game.getPlayer().getPc().setAlive(false);
			game.getPlayer().getPc().setGraphic(game.getPlayer().getPc().getName() + "howdie");
			game.stopTimer();
			game.addState("Death");
		}
	}

	@Override
	public void paintFrame()
	{
		game.drawImage(100, 0, "timebox", false);
		game.drawString("" + game.getTimer(), 126, 5, 0);
	}
}
