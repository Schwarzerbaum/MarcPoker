import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class FoldButton {
    JButton button;
    public FoldButton() {

        ActionListener listener = e -> {
            System.out.println("yes");
            PokerTable.instance.resetPokerTable();
        };

        button = new JButton("Fold");
        button.setBounds(1460, 750, 100, 60);
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
