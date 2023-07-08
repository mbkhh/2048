package Game;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * AudioPlayer
 */
public class AudioPlayer {

    public static AudioPlayer audioPlayer;
    Double SFXVolume,musicVolume;
    MediaPlayer background;
    MediaPlayer click;
    MediaPlayer error;
    MediaPlayer hit;
    public AudioPlayer()
    {
        SFXVolume = .5;
        musicVolume = .5;


        Media thick = new Media(getClass().getResource("../resources/sounds/tick.mp3").toExternalForm());
        click = new MediaPlayer(thick);
        Media er = new Media(getClass().getResource("../resources/sounds/error.mp3").toExternalForm());
        error = new MediaPlayer(er);
        Media h = new Media(getClass().getResource("../resources/sounds/toggle.mp3").toExternalForm());
        hit = new MediaPlayer(h);

        Media back = new Media(getClass().getResource("../resources/sounds/back.mp3").toExternalForm());
        background = new MediaPlayer(back);
        background.setOnEndOfMedia(new Runnable() {
            public void run() {
              background.seek(Duration.ZERO);
            }
        });
    }
    public void playBack()
    {
        background.setCycleCount(0);
        background.setVolume(musicVolume);
        background.seek(Duration.ZERO);
        background.play();
    }
    public void playTick()
    {
        click.setVolume(SFXVolume);
        click.seek(Duration.ZERO);
        click.play();
    }
    public void playError()
    {
        error.setVolume(SFXVolume);
        error.seek(Duration.ZERO);
        error.play();
    }
    public void playHit()
    {
        hit.setVolume(SFXVolume);
        hit.seek(Duration.ZERO);
        hit.play();
    }
    public void setSFXVolume(int t)
    {
        SFXVolume = ((double)t/100);
    }
    public void setMusicVolume(int t)
    {
        musicVolume = ((double)t/100);
        background.setVolume(musicVolume);
    }
}