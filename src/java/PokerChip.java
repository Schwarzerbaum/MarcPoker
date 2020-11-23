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

public class PokerChip implements ActionListener {
    public static PokerTable instance = new PokerTable();

    public static ImageIcon image1;
    public static ImageIcon image2;
    public static JButton button;
    public static JLabel label;

    public PokerChip() {

        ImageIcon chip1 = new ImageIcon(getClass().getResource("resources//chips//chip1.png"));
        ImageIcon chip5 = new ImageIcon(getClass().getResource("resources//chips//chip5.png"));
        ImageIcon chip10 = new ImageIcon(getClass().getResource("resources//chips//chip10.png"));
        ImageIcon chip20 = new ImageIcon(getClass().getResource("resources//chips//chip20.png"));
        ImageIcon chip50 = new ImageIcon(getClass().getResource("resources//chips//chip50.png"));
        ImageIcon chip100 = new ImageIcon(getClass().getResource("resources//chips//chip100.png"));
        ImageIcon chip200 = new ImageIcon(getClass().getResource("resources//chips//chip200.png"));
        ImageIcon chip500 = new ImageIcon(getClass().getResource("resources//chips//chip500.png"));
        ImageIcon chip1000 = new ImageIcon(getClass().getResource("resources//chips//chip1000.png"));

        button = new JButton(chip1);
        button.setBounds(150, 50, 100,60);
        button.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            System.out.println("IT FUCKING WORKS YESSSSS!!!!!");
        }

    }


    public void update(){

    }
    public void draw(Graphics2D g2d) {
        g2d.drawImage(chip1, 0, 0, 0, 0, null);

    }

}
