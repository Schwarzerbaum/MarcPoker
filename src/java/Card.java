import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Card.
 */
public class Card implements Comparable<Card> {

    /**
     * The Color.
     */
    public CARD_COLORS color;
    /**
     * The Number.
     */
    public CARD_NUMBERS number;
    /**
     * The Image.
     */
    public BufferedImage image;

    /**
     * The constant cardImages.
     */
    public static Map<String, BufferedImage> cardImages = new HashMap<>();
    /**
     * The constant back.
     */
    public static String back = "BACK";

    /**
     * Load images.
     */
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

    /**
     * Instantiates a new Card.
     *
     * @param color  color of Cards(diamonds, clubs, hearts, spades)
     * @param number numbers of Cards(
     */
    public Card(CARD_COLORS color, CARD_NUMBERS number) {
        this.color = color;
        this.number = number;

        image = cardImages.get(getName());
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return number.filename + color.filename;
    }

    /**
     * The enum Hand rank.
     */
    public enum HandRank {

        /**
         * Royal flush hand rank.
         */
        ROYAL_FLUSH(10),
        /**
         * Straight flush hand rank.
         */
        STRAIGHT_FLUSH(9),
        /**
         * Four of a kind hand rank.
         */
        FOUR_OF_A_KIND(8),
        /**
         * Full house hand rank.
         */
        FULL_HOUSE(7),
        /**
         * Flush hand rank.
         */
        FLUSH(6),
        /**
         * Straight hand rank.
         */
        STRAIGHT(5),
        /**
         * Three of a kind hand rank.
         */
        THREE_OF_A_KIND(4),
        /**
         * Two pair hand rank.
         */
        TWO_PAIR(3),
        /**
         * Pair hand rank.
         */
        PAIR(2),
        /**
         * High card hand rank.
         */
        HIGH_CARD(1);

        /**
         * The Value.
         */
        public int value;

        HandRank(int value) {
            this.value = value;
        }
    }

    /**
     * Determine hand rank hand rank.
     *
     * @param cardsArg the cards arg
     * @return hand rank
     */
    public static HandRank determineHandRank(Card[] cardsArg) {
        if (royalFlush(cardsArg)) {
            return HandRank.ROYAL_FLUSH;
        } else if (straightFlush(cardsArg)) {
            return HandRank.STRAIGHT_FLUSH;
        } else if (fourOfAKind(cardsArg)) {
            return HandRank.FOUR_OF_A_KIND;
        } else if (fullHouse(cardsArg)) {
            return HandRank.FULL_HOUSE;
        } else if (flush(cardsArg)) {
            return HandRank.FLUSH;
        } else if (straight(cardsArg)) {
            return HandRank.STRAIGHT;
        } else if (threeOfAKind(cardsArg)) {
            return HandRank.THREE_OF_A_KIND;
        } else if (twoPair(cardsArg)) {
            return HandRank.TWO_PAIR;
        } else if (pair(cardsArg)) {
            return HandRank.PAIR;
        } else {
            return HandRank.HIGH_CARD;
        }
    }

    /**
     * Royal flush boolean.
     *
     * @param cardsArg the cards arg
     * @return boolean
     */
    public static boolean royalFlush(Card[] cardsArg) {
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

    /**
     * Straight flush boolean.
     *
     * @param cardsArg the cards arg
     * @return boolean
     */
    public static boolean straightFlush(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        for (CARD_COLORS color : CARD_COLORS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.color == color).toArray(Card[]::new);
            if (coloredCards.length < 5) continue;

            subarray:
            for (int i = 0; i < (coloredCards.length - 4); i++) {
                int current = coloredCards[i].number.index;
                for (int j = i + 1; j <= i + 5; j++) {
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

    /**
     * Four of a kind boolean.
     *
     * @param cardsArg the cards arg
     * @return boolean
     */
    public static boolean fourOfAKind(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);


        Arrays.sort(cards);

        for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.number == number).toArray(Card[]::new);
            if (coloredCards.length == 4) return true;
        }
        return false;
    }

    /**
     * Full house boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean fullHouse(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        boolean three = false;
        boolean two = false;

        for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.number == number).toArray(Card[]::new);
            if (coloredCards.length == 3) three = true;
            else if (coloredCards.length == 2) two = true;
        }
        return three && two;
    }

    /**
     * Flush boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean flush(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        for (CARD_COLORS color : CARD_COLORS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.color == color).toArray(Card[]::new);
            if (coloredCards.length >= 5) return true;
        }
        return false;
    }

    /**
     * Straight boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean straight(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        subarray:
        for (int i = 0; i < (cards.length - 4); i++) {
            int current = cards[i].number.index;
            for (int j = i + 1; j <= i + 4; j++) {
                Card c = cards[j];

                if (c.number.index != current + 1) {
                    continue subarray;
                }
                current++;
            }
            return true;
        }
        return false;
    }

    /**
     * Three of a kind boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean threeOfAKind(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        boolean three = false;

        for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.number == number).toArray(Card[]::new);
            if (coloredCards.length == 3) three = true;
        }
        return three;
    }

    /**
     * Two pair boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean twoPair(Card[] cardsArg) {

        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        int pairs = 0;

        for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
            Card[] numberedCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.number == number).toArray(Card[]::new);
            if (numberedCards.length == 2) pairs++;
        }
        return pairs == 2;
    }

    /**
     * Pair boolean.
     *
     * @param cardsArg the cards arg
     * @return the boolean
     */
    public static boolean pair(Card[] cardsArg) {
        Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);

        Arrays.sort(cards);

        boolean two = false;

        for (CARD_NUMBERS number : CARD_NUMBERS.values()) {
            Card[] coloredCards = Arrays.stream(Arrays.copyOf(cards, cards.length)).filter(card -> card.number == number).toArray(Card[]::new);
            if (coloredCards.length == 2) two = true;
        }
        return two;
    }

	/*public static Card highCard (Card[] cardsArg) {
		Card[] cards = Arrays.copyOf(cardsArg, cardsArg.length);
		Arrays.sort(cards);
		System.out.println();
	}*/

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.number.index, o.number.index);
    }

    @Override
    public String toString() {
        return color.toString() + "_" + number.toString();
    }

    /**
     * The enum Card colors.
     */
    public enum CARD_COLORS {
        /**
         * Clubs card colors.
         */
        CLUBS("C"),
        /**
         * Hearts card colors.
         */
        HEARTS("H"),
        /**
         * Spades card colors.
         */
        SPADES("S"),
        /**
         * Diamonds card colors.
         */
        DIAMONDS("D");

        /**
         * The Filename.
         */
        public String filename;

        CARD_COLORS(String filename) {
            this.filename = filename;
        }
    }

    /**
     * The enum Card numbers.
     */
    public enum CARD_NUMBERS {
        /**
         * Two card numbers.
         */
        TWO("2", 0),
        /**
         * Three card numbers.
         */
        THREE("3", 1),
        /**
         * Four card numbers.
         */
        FOUR("4", 2),
        /**
         * Five card numbers.
         */
        FIVE("5", 3),
        /**
         * Six card numbers.
         */
        SIX("6", 4),
        /**
         * Seven card numbers.
         */
        SEVEN("7", 5),
        /**
         * Eight card numbers.
         */
        EIGHT("8", 6),
        /**
         * Nine card numbers.
         */
        NINE("9", 7),
        /**
         * Ten card numbers.
         */
        TEN("10", 8),
        /**
         * Jack card numbers.
         */
        JACK("J", 9),
        /**
         * Queen card numbers.
         */
        QUEEN("Q", 10),
        /**
         * King card numbers.
         */
        KING("K", 11),
        /**
         * Ace card numbers.
         */
        ACE("A", 12);

        /**
         * The Filename.
         */
        public String filename;
        /**
         * The Index.
         */
        public int index;

        CARD_NUMBERS(String filename, int index) {
            this.filename = filename;
            this.index = index;
        }

        /**
         * Gets index.
         *
         * @param numbers the numbers
         * @return index
         */
        public static int getIndex(CARD_NUMBERS numbers) {
            for (int i = 0; i < values().length; i++) {
                if (values()[i] == numbers) return i;
            }
            return -1;
        }
    }
}



