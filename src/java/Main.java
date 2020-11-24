import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static JFrame frame;
    public static Screen currentScreen;

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


    public static void createJFrame() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(PokerTable.instance);
        frame.add(PauseMenu.instance);

        PokerTable.instance.addMouseListener(new PokerTableMouseHandler());

        /*music.music("resources/audio/poker");*/

    }
}
