import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class PokerTable extends Screen {
	public static PokerTable instance = new PokerTable();
	public static BufferedImage image;

	public static ArrayList<Card> deckOfCards = new ArrayList<>();

	static {
		for (int i = 0; i < Card.CARD_COLORS.values().length; i++) {
			for (int j = 0; j < Card.CARD_NUMBERS.values().length; j++) {
				deckOfCards.add(new Card(Card.CARD_COLORS.values()[i], Card.CARD_NUMBERS.values()[j]));
			}
		}
	}

	static {
		try {
			URL imageFile = PokerTable.class.getClassLoader().getResource("table.png");
			if (imageFile != null)
				image = ImageIO.read(imageFile);
		} catch (Exception ignored) {
		}
	}

	ArrayList<PokerCard> cards = new ArrayList<>();

	@Override
	public void init() {
		super.init();

		double cardWidth = getWidth() / 15d;
		double cardHeight = cardWidth * 1.5d;

		int i = 5;
		int j = 2;

		cards.add(new PokerCard(getRandomCard(), cardWidth * i++, cardHeight * j, cardWidth, cardHeight, 0));
		cards.add(new PokerCard(getRandomCard(), cardWidth * i++, cardHeight * j, cardWidth, cardHeight, 0));
		cards.add(new PokerCard(getRandomCard(), cardWidth * i++, cardHeight * j, cardWidth, cardHeight, 0));
		cards.add(new PokerCard(getRandomCard(), cardWidth * i++, cardHeight * j, cardWidth, cardHeight, 0));
		cards.add(new PokerCard(getRandomCard(), cardWidth * i++, cardHeight * j, cardWidth, cardHeight, 0));

		cards.add(new PokerCard(getRandomCard(), cardWidth * (i-3.5) , cardHeight * (j+1.5), cardWidth, cardHeight, 0));
		cards.add(new PokerCard(getRandomCard(), cardWidth * (i-2.5), cardHeight * (j+1.5), cardWidth, cardHeight, 0));
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

	public void resetDeckOfCards() {
		deckOfCards.clear();
		for (int i = 0; i < Card.CARD_COLORS.values().length; i++) {
			for (int j = 0; j < Card.CARD_NUMBERS.values().length; j++) {
				deckOfCards.add(new Card(Card.CARD_COLORS.values()[i], Card.CARD_NUMBERS.values()[j]));
			}
		}
	}

	public Card getRandomCard() {
		int index = (int) (Math.random() * deckOfCards.size());
		Card card = deckOfCards.get(index);
		deckOfCards.remove(index);
		return card;
	}
}
