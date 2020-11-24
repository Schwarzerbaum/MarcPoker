import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class CheckButton {
    JButton button;


    public CheckButton() {

        ActionListener listener = e -> {
            System.out.println("yes");
            if (PokerTable.instance.cards.size() < 5) {
                PokerTable.instance.cards.add(new PokerCard(PokerTable.instance.getRandomCard(), 0, 0, PokerTable.cardWidth, PokerTable.cardHeight, 0).turn());
            }
            if(PokerTable.instance.cards.size() == 5) {
                int playerHand = PokerTable.player.getHandRank().value;
                int botHand = PokerTable.bot.getHandRank().value;

                if(playerHand > botHand) {

                }
                else if(playerHand < botHand) {
                }
                else {
                }
            }
        };

        button = new JButton("Check");
        button.setBounds(1355, 750, 100, 60);
        button.addActionListener(listener);
        button.setBackground(new Color(255, 0, 0));
        button.setForeground(new Color(255,255,255));
        button.setVisible(true);
        button.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        button.setBorder(BorderFactory.createEtchedBorder());
        button.setFocusable(false);

        PokerTable.instance.add(button);
    }
}
