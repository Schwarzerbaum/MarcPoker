import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Struct;

public class PokerCard {
	private double x, y, width, height;
	private double angle;

	public boolean visible = false;

	public Card card;

	public PokerCard(Card card, double x, double y, double width, double height, double angle) {
		this.card = card;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	public void turn() {
		visible = !visible;
	}

	public void update() {

	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		BufferedImage image;
		if (visible)
			image = card.image;
		else image = Card.cardImages.get(Card.back);
		g2d.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
	}
}
