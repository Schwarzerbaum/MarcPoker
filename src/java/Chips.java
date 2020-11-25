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
 * The enum Chips.
 */
public enum Chips {
    /**
     * One chips.
     */
    ONE(1),
    /**
     * Five chips.
     */
    FIVE(5),
    /**
     * Ten chips.
     */
    TEN(10),
    /**
     * Twenty chips.
     */
    TWENTY(20),
    /**
     * Fifty chips.
     */
    FIFTY(50),
    /**
     * Hundred chips.
     */
    HUNDRED(100),
    /**
     * Two hundred chips.
     */
    TWO_HUNDRED(200),
    /**
     * Five hundred chips.
     */
    FIVE_HUNDRED(500),
    /**
     * Thousand chips.
     */
    THOUSAND(1000);

    /**
     * The Value.
     */
    public int value;
    /**
     * The Image.
     */
    public BufferedImage image;
    /**
     * The Button.
     */
    public JButton button;



    Chips(int value) {
        this.value = value;
        try {
            image = ImageIO.read(Chips.class.getResource("/chips/chip" + this.value + ".png"));
            button = new JButton();

            ActionListener listener = e -> {
                System.out.println(value);
                /*Buttons.betButton.setText("Bet" + value);*/
                if (PokerTable.player.playerMoney > value) {
                    PokerTable.player.playerMoney -= value;
                    PokerTable.player.moneyPool += value;
                    System.out.println(PokerTable.player.moneyPool);
                    System.out.println(PokerTable.player.playerMoney);
                }
            };

            /*Object mX;
            Component e;
            double xOff = Math.abs(mX - e.getX());
            Object mY;
            double yOff = Math.abs(mY - e.getY());
            double dist = Math.sqrt(xOff * xOff + yOff * yOff);
            if (dist < GUI.bDeathStar.width / 2d) {
                GUI.bDeathStar.active = true;*/

            button.addActionListener(listener);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            //button.setVisible(false);

            PokerTable.instance.add(button);
        } catch (IOException ignored) {

        }
    }

    /**
     * Draw.
     *
     * @param g2d the g 2 d
     * @param x   the x
     * @param y   the y
     * @param w   the w
     * @param h   the h
     */
    public void draw(Graphics2D g2d, int x, int y, int w, int h) {
        g2d.drawImage(this.image, x, y, w, h, null);
        button.setBounds(x, y, w, h);
    }
}
