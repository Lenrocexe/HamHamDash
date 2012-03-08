package hamhamdash;

/**
 * A wrapper class that eases playing music
 * @author Cornel Alders
 */
public class Jukebox
{
    //Constructor should never be called.
    private Jukebox(){}

    /**
     * Play background music with loop.
     * Overrides whatever music is playing on channel one.
     * @param audio The audiofile to play
     */
    public static void playMusic(String audio)
    {
        try
        {
            Game.getGame().playAudio("1", audio, true);
        }
        catch(Exception e){System.out.println("could not play music: /n" + e.getMessage());}
    }

    /**
     * Play background music with or w/o loop.
     * Overrides whatever music is playing on channel one.
     * @param audio The audiofile to play
     * @param loop False for no loop
     */
    public static void playMusic(String audio, boolean loop)
    {
        try
        {
            Game.getGame().playAudio("1", audio, loop);
        }
        catch(Exception e){System.out.println("could not play music: /n" + e.getMessage());}
    }

    /**
     * Play a sound file.
     * @param audio The audiofile to play
     */
    public static void playSound(String audio)
    {
        try
        {
            Game.getGame().playAudio(audio);
        }
        catch(Exception e){System.out.println("could not play sound: /n" + e.getMessage());}
    }

    /**
     * Stop all audio from playing.
     */
    public static void stop()
    {
        Game.getGame().stopAudio();
    }

    /**
     * Stops a single channel from playing.
     * @param channel The channel to stop
     */
    public static void stopChannel(String channel)
    {
        Game.getGame().stopAudio(channel);
    }
}
