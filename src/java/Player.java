import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Player.
 */
public class Player {
	/**
	 * The Cards.
	 */
	ArrayList<PokerCard> cards = new ArrayList<>();
	/**
	 * The Money pool.
	 */
	public int moneyPool;
	/**
	 * The Player money.
	 */
	int playerMoney = 500;


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
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).draw(g2d);
		}
	}

	/**
	 * Gets hand rank.
	 *
	 * @return the hand rank
	 */
	public Card.HandRank getHandRank() {
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < this.cards.size(); i++) {
			cards.add(this.cards.get(i).card);
		}
		for (int i = 0; i < PokerTable.instance.cards.size(); i++) {
			cards.add(PokerTable.instance.cards.get(i).card);
		}
		Card[] cardsArray = cards.toArray(new Card[0]);
		return Card.determineHandRank(cardsArray);
	}
}
