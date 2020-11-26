import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;


/**
 *
 * Version 1.5
 * @Author Marc
 * The type Main.
 */
public class Main {
    /**
     *
     * Version 1.5
     * @Author Marc
     * The constant frame.
     */
    public static JFrame frame;
    /**
     *
     * Version 1.5
     * @Author Marc
     * The constant currentScreen.
     */
    public static Screen currentScreen;

    /**
     *
     * Version 1.5
     * @Author Marc
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Card.loadImages();
        createJFrame();

        PokerTable.instance.init();
        PauseMenu.instance.init();

        currentScreen = PokerTable.instance;

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(DrawThread.instance, 0, 1000 / 60);
        timer.scheduleAtFixedRate(UpdateThread.instance, 0, 1000 / 60);
    }


    /**
     *
     * Version 1.5
     * @Author Marc
     * Create j frame.
     */
    public static void createJFrame() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(PokerTable.instance);
        frame.add(PauseMenu.instance);

        PokerTable.instance.addMouseListener(new PokerTableMouseHandler());

        Music.music("resources/music.wav");
    }
}
