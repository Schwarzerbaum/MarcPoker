import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PokerTable extends Screen {
    public static PokerTable instance = new PokerTable();
    public static BufferedImage image;

    static {
        try {
            image = ImageIO.read(PokerTable.class.getClassLoader().getResource("table.jpg"));
        } catch (Exception ignored) {
        }
    }

    ArrayList<PokerCard> cards = new ArrayList<>();

    PokerTable() {
        cards.add(new PokerCard(new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.THREE), 100, 100, 75, 125, 0));
    }

    public void update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        instance.draw(g2d);
        //super.paintComponent(g);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.PINK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).draw(g2d);
        }
    }
}
