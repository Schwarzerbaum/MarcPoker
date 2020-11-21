import java.awt.*;
import java.sql.Struct;

public class PokerCard {
    private double x, y, width, height;
    private double angle;

    public Card card;

    public PokerCard(Card card, double x, double y, double width, double height, double angle) {
        this.card = card;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
    }

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);

        g2d.setColor(Color.WHITE);
        g2d.drawString(card.color.name(), (int) x, (int) y);
        g2d.drawString(card.number.name(), (int) x, (int) y + 10);

    }
}
