package hamhamdash;

/**
 * The dataclass of a player
 * @author Cornel Alders
 */
public class Player
{
	private PlayerCharacter pc = null;
	private int lifes = 0;
	private int totalscore = 0;
	private int levelscore = 0;
	private String playerName;
	private String identifier;

	public Player(String name)
	{
		setPlayerName(name);
		setIdentifier(String.valueOf(name.charAt(0)));
		resetLifes();
	}

	public int getLifes()
	{
		return lifes;
	}

	public int getScore()
	{
		return totalscore;
	}

	public int getLevelScore()
	{
		return levelscore;
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

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public void addScoreToTotal()
	{
		this.totalscore += levelscore;
	}

	public void resetScore()
	{
		totalscore = 0;
	}

	public void addToLevelScore(int i)
	{
		levelscore += i;
	}

	public void resetLevelScore()
	{
		levelscore = 0;
	}

	public void kill()
	{
		pc.kill();
	}

	public void setIdentifier(String s)
	{
		this.identifier = s;
	}

	public String getIdentifier()
	{
		return identifier;
	}
}
