import java.util.TimerTask;

public class DrawThread extends TimerTask {
    public static DrawThread instance = new DrawThread();
    @Override
    public void run() {
        Main.currentScreen.repaint();
    }
}
