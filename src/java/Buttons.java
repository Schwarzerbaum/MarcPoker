import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.ButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 * The type Buttons
 */
public class Buttons {
    /**
     * The Button
     */
    static JButton Button;
    /**
     * The Label.
     */
    JLabel label;


    /**
     * Instantiates a new Buttons.
     */
    public Buttons() {
        ActionListener checkListener = e -> {
            if (PokerTable.player.moneyPool != 0) {
                if (PokerTable.instance.cards.size() < 5) {
                    PokerTable.instance.cards.add(new PokerCard(PokerTable.instance.getRandomCard(), 0, 0, PokerTable.cardWidth, PokerTable.cardHeight, 0).turn());
                }
            }
        };

        JButton checkButton = new JButton ("Check");
        checkButton.setBounds(1305, 750, 150, 100);
        checkButton.addActionListener(checkListener);
        checkButton.setBackground(new Color(255, 0, 0));
        checkButton.setForeground(new Color(255,255,255));
        checkButton.setVisible(true);
        checkButton.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        checkButton.setBorder(BorderFactory.createEtchedBorder());
        checkButton.setFocusable(false);

        ActionListener betListener = e -> {
            System.out.println("yes");
            label.setText("<html>Your Money:</html>" + PokerTable.player.playerMoney + "<html><br/>Your Bet:</html>" + Arrays.toString(Chips.values()));
            if (PokerTable.instance.cards.size() < 5 && PokerTable.player.moneyPool != 0) {
                PokerTable.instance.cards.add(new PokerCard(PokerTable.instance.getRandomCard(), 0, 0, PokerTable.cardWidth, PokerTable.cardHeight, 0).turn());
            }
        };

        JButton betButton = new JButton("Bet");
        betButton.setBounds(1150, 750, 150, 100);
        betButton.addActionListener(betListener);
        betButton.setBackground(new Color(255, 0, 0));
        betButton.setForeground(new Color(255, 255, 255));
        betButton.setVisible(true);
        betButton.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        betButton.setBorder(BorderFactory.createEtchedBorder());
        betButton.setFocusable(false);

        label = new JLabel("<html>Your Money:<br/>Your Bet:</html>", SwingConstants.CENTER);
        label.setBounds(1350, 500, 250, 200);
        label.setBackground(new Color(255, 0, 0));
        label.setForeground(new Color(255, 255, 255));
        label.setVisible(true);
        label.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setFocusable(false);

        ActionListener foldListener = e -> {
            if (PokerTable.player.moneyPool != 0 && PokerTable.instance.cards.size() < 5) {
                System.out.println("yes");
                PokerTable.instance.resetPokerTable();
            }
        };

        JButton foldButton = new JButton("Fold");
        foldButton.setBounds(1460, 750, 150, 100);
        foldButton.addActionListener(foldListener);
        foldButton.setBackground(new Color(255, 0, 0));
        foldButton.setForeground(new Color(255,255,255));
        foldButton.setVisible(true);
        foldButton.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        foldButton.setBorder(BorderFactory.createEtchedBorder());
        foldButton.setFocusable(false);

        PokerTable.instance.add(checkButton);
        PokerTable.instance.add(label);
        PokerTable.instance.add(betButton);
        PokerTable.instance.add(foldButton);
    }
}
