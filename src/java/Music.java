import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The type Music.
 */
public class Music {
    /**
     * Music.
     *
     * @param track the track
     */
    public static synchronized void music(String track) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {

                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Music.class.getResource("music.wav"));
                        clip.open(inputStream);
                        clip.loop(clip.LOOP_CONTINUOUSLY);

                        Thread.sleep(clip.getMicrosecondLength() / 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
