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
			image = ImageIO.read(Chips.class.getResource("/chips/chip" + this.value + ".png"));
			button = new JButton(); /*{
				@Override
				public boolean contains(Point p) {
					return Math.sqrt(Math.pow(p.x - this.getX(), 2) + Math.pow(p.y - this.getY(), 2)) < this.getWidth() / 2d;
				}
			};*/
			ActionListener listener = e -> {

			};
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

	public void draw(Graphics2D g2d, int x, int y, int w, int h) {
		g2d.drawImage(this.image, x, y, w, h, null);
		button.setBounds(x, y, w, h);
	}
}
