import java.awt.*;
import java.util.ArrayList;

public class Bot {
    ArrayList<PokerCard> cards = new ArrayList<>();

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).draw(g2d);
        }
    }
}
