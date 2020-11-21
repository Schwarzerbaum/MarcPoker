import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Card implements Comparable<Card> {
	public CARD_COLORS color;
	public CARD_NUMBERS number;
	public BufferedImage image;

	public static Map<String, BufferedImage> cardImages = new HashMap<>();
	public static String back = "BACK";

	public static void loadImages() {
		try {
			for (int i = 0; i < CARD_COLORS.values().length; i++) {
				for (int j = 0; j < CARD_NUMBERS.values().length; j++) {
					String name = CARD_NUMBERS.values()[j].filename + CARD_COLORS.values()[i].filename;
					cardImages.put(name, ImageIO.read(Card.class.getResource("cards/" + name + ".png")));
				}
			}
			cardImages.put(back, ImageIO.read(Card.class.getResource("cards/" + back + ".png")));
		} catch (IOException ignored) {
		}
	}

	public Card(CARD_COLORS color, CARD_NUMBERS number) {
		this.color = color;
		this.number = number;

		image = cardImages.get(getName());
	}

	public String getName() {
		return number.filename + color.filename;
	}

	public static boolean isRoyalFlush(Card[] cardsArg) {
		Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

		Arrays.sort(cards);

		for (CARD_COLORS color : CARD_COLORS.values()) {
			Card[] coloredCards = Arrays.stream(cards.clone())
					.filter(card -> card.color == color)
					.filter(card -> Arrays.asList(CARD_NUMBERS.TEN, CARD_NUMBERS.JACK, CARD_NUMBERS.QUEEN, CARD_NUMBERS.KING, CARD_NUMBERS.ACE).contains(card.number))
					.toArray(Card[]::new);
			if (coloredCards.length == 5) return true;
		}
		return false;
	}

	public static boolean fourOfAKind(Card[] cardsArg) {
		Card[] cards = cardsArg.clone();

		Arrays.sort(cards);

		for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
			Card[] coloredCards = Arrays.stream(cards.clone()).filter(card -> card.number == number).toArray(Card[]::new);
			if (coloredCards.length == 4) return true;
		}
		return false;
	}

	public static boolean fullHouse(Card[] cardsArg) {
		Card[] cards = cardsArg.clone();

		Arrays.sort(cards);

		boolean three = false;
		boolean two = false;

		for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
			Card[] coloredCards = Arrays.stream(cards.clone()).filter(card -> card.number == number).toArray(Card[]::new);
			if (coloredCards.length == 3) three = true;
			else if (coloredCards.length == 2) two = true;
		}
		return three && two;
	}

	public static boolean isStraightFlush(Card[] cardsArg) {
		Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

		Arrays.sort(cards);

		for (CARD_COLORS color : CARD_COLORS.values()) {
			Card[] coloredCards = Arrays.stream(cards.clone()).filter(card -> card.color == color).toArray(Card[]::new);
			if (coloredCards.length < 5) continue;

			subarray:
			for (int i = 0; i < (coloredCards.length - 4); i++) {
				int current = coloredCards[i].number.index;
				for (int j = i + 1; j <= 5; j++) {
					Card c = coloredCards[j];

					if (c.number.index != current + 1) {
						continue subarray;
					}
					current++;
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Card o) {
		return Integer.compare(this.number.index, o.number.index);
	}

	@Override
	public String toString() {
		return color.toString() + "_" + number.toString();
	}

	public enum CARD_COLORS {
		CLUBS("C"),
		HEARTS("H"),
		SPADES("S"),
		DIAMONDS("D");

		public String filename;

		CARD_COLORS(String filename) {
			this.filename = filename;
		}
	}

	public enum CARD_NUMBERS {
		TWO("2", 0),
		THREE("3", 1),
		FOUR("4", 2),
		FIVE("5", 3),
		SIX("6", 4),
		SEVEN("7", 5),
		EIGHT("8", 6),
		NINE("9", 7),
		TEN("10", 8),
		JACK("J", 9),
		QUEEN("Q", 10),
		KING("K", 11),
		ACE("A", 12);

		public String filename;
		public int index;

		CARD_NUMBERS(String filename, int index) {
			this.filename = filename;
			this.index = index;
		}

		public static int getIndex(CARD_NUMBERS numbers) {
			for (int i = 0; i < values().length; i++) {
				if (values()[i] == numbers) return i;
			}
			return -1;
		}
	}
}



