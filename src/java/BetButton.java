import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;


public class BetButton {

    JButton button;

    public BetButton() {

        ActionListener listener = e -> {
            System.out.println("yes");
        };

        button = new JButton("Bet");
        button.setBounds(1250, 750, 100, 60);
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





