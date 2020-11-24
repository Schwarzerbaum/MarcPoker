import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	ArrayList<PokerCard> cards = new ArrayList<>();

	public void update() {

	}

	public void draw(Graphics2D g2d) {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).draw(g2d);
		}
	}

	public Card.HandRank getHandRank() {
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < this.cards.size(); i++) {
			cards.add(this.cards.get(i).card);
		}
		for (int i = 0; i < PokerTable.instance.cards.size(); i++) {
			cards.add(PokerTable.instance.cards.get(i).card);
		}
		Card[] cardsArray = cards.toArray(new Card[0]);
		System.out.println("___");
		return Card.determineHandRank(cardsArray);
	}
}
