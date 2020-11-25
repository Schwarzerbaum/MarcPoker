import java.util.TimerTask;

/**
 * The type Draw thread.
 */
public class DrawThread extends TimerTask {
    /**
     * The constant instance.
     */
    public static DrawThread instance = new DrawThread();
    @Override
    public void run() {
        Main.currentScreen.repaint();
    }
}
