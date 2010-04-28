package hamhamdash;

import jgame.*;

/**
 * The dataclass of a player
 * @author Cornel Alders
 */
public class Player
{
	public PlayerCharacter pc = null;
	private int lifes;
	private int score;
	private String playerName;

	public Player(String name)
	{
		setPlayerName(name);
		resetLifes();
	}

	public int getLifes()
	{
		return lifes;
	}

	public int getScore()
	{
		return score;
	}

	public String getPlayerName()
	{
		return playerName;
	}

	public void resetLifes()
	{
		setLifes(3);
	}

	/*public void gameOver()
	{
		if (lifes == 0)
		{
			setGameState("Game Over");
		}
		else if ( )
		{
			Game.setGameState("Win");
		}
	}*/

	public void setLifes(int lifes)
	{
		this.lifes = lifes;
	}

	public void addLife()
	{
		lifes++;
	}

	public void removeLife()
	{
		lifes--;
	}

	public PlayerCharacter getPc()
	{
		return pc;
	}

	public void setPc(PlayerCharacter pc)
	{
		this.pc = pc;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public void addScore(int score)
	{
		this.score += score;
	}

	public void resetScore()
	{
		setScore(0);
	}
	public void kill()
	{
		pc.kill();
	}
}
