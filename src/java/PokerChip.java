import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PokerChip {
    public static PokerTable instance = new PokerTable();

    public static ImageIcon image1;
    public static ImageIcon image2;
    public static JButton button;
    public static JLabel label;

    public PokerChip() {


    }


    public void update() {

    }

    public void draw(Graphics2D g2d) {
        Chips.ONE.draw(g2d, 100, 100, 50, 50);
    }

    public enum Chips {
        ONE(1),
        FIVE(5),
        TEN(10),
        TWENTY(20),
        FIFTY(50),
        HUNDRED(100),
        TWO_HUNDRED(200),
        FIVE_HUNDRED(500),
        THOUSAND(1000);

        public int value;
        public BufferedImage image;
        public JButton button;

        Chips(int value) {
            this.value = value;
            try {
                image = ImageIO.read(PokerChip.class.getResource("/chips/chip" + this.value + ".png"));
                button = new JButton(new ImageIcon(image));
                ActionListener listener = e -> System.out.println("IT FUCKING WORKS YESSSSS!!!!!");
                button.addActionListener(listener);
            } catch (IOException ignored) {

            }
        }

        public void draw(Graphics2D g2d, int x, int y, int w, int h) {
            g2d.drawImage(this.image, x, y, w, h, null);
            button.setBounds(x, y, w, h);
        }
    }
}
