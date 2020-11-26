import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Struct;

/**
 * The type Poker card.
 */
public class PokerCard {
	private double x, y, width, height;
	private double angle;

	/**
	 * The Visible
	 * is not visible
	 */
	public boolean visible = false;

	/**
	 * The Card.
	 */
	public Card card;

	/**
	 * Instantiates a new Poker card.
	 *
	 * @param card   the card
	 * @param x      the x
	 * @param y      the y
	 * @param width  the width
	 * @param height the height
	 * @param angle  the angle
	 */
	public PokerCard(Card card, double x, double y, double width, double height, double angle) {
		this.card = card;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}


	/**
	 * Turn poker card.
	 *
	 * @return the poker card
	 */
	public PokerCard turn() {
		visible = !visible;
		return this;
	}

	/**
	 * Update.
	 */
	public void update() {

	}

	/**
	 * Draw.
	 *
	 * @param g2d the g 2 d
	 */
	public void draw(Graphics2D g2d) {

		BufferedImage image;
		if (visible)
			image = card.image;
		else image = Card.cardImages.get(Card.back);
		g2d.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
	}

	/**
	 * Sets x.
	 *
	 * @param x the x
	 * @return the x
	 */
	public PokerCard setX(double x) {
		this.x = x;
		return this;
	}

	/**
	 * Sets y.
	 *
	 * @param y the y
	 * @return the y
	 */
	public PokerCard setY(double y) {
		this.y = y;
		return this;
	}
}
