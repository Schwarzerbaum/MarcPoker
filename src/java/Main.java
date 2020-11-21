import jdk.jfr.Percentage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    public static JFrame frame;
    public static Screen currentScreen;

    public static void main(String[] args) {
        Card[] bla = new Card[]{
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.TWO),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.TWO),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.FOUR),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.FOUR),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.FIVE),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.SIX),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.SIX),
                new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.SIX)
        };
        System.out.println(Card.isStraightFlush(bla));

        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(PokerTable.instance);
        frame.add(PauseMenu.instance);


        PokerTable.instance.setSize(frame.getWidth(), frame.getHeight());
        PauseMenu.instance.setSize(frame.getWidth(), frame.getHeight());

        currentScreen = PokerTable.instance;

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(DrawThread.instance, 0, 1000 / 60);
        timer.scheduleAtFixedRate(UpdateThread.instance, 0, 1000 / 60);

    }
}
