package hamhamdash;

/**
 *
 * @author Cornel Alders
 */
import jgame.*;

public class Player
{
	private PlayerCharacter pc;
	private int lifes;
	private int score;


	public Player()
	{
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

	public void resetLifes()
	{
		setLifes(3);
	}

	public void setLifes(int lifes)
	{
		this.lifes = lifes;
	}

	public void addLife()
	{
		lifes ++;
	}

	public void removeLife()
	{
		lifes --;
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
}
