public class Card {
    public CARD_COLORS color;
    public CARD_NUMBERS number;

    public Card(CARD_COLORS color, CARD_NUMBERS number) {
        this.color = color;
        this.number = number;
    }

    /*public boolean isRoyalFlush(Card[] cards) {
        if ()
    }*/

    public static boolean isStraightFlush(Card[] cards) {
        int[] values = new int[cards.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = CARD_NUMBERS.getIndex(cards[i].number);
        }

        //values -> [2,4,5,6,7,8,10]

        outer:
        for (int i = 0; i < cards.length - 4; i++) {
            int current = values[i];
            CARD_COLORS color = cards[i].color;
            System.out.println(i);

            for (int j = i + 1; j < 5; j++) {
                System.out.println(j);
                if (values[j] == current + 1 && cards[j].color == color) {
                    current++;
                } else
                    continue outer;
            }
            return true;
        }
        return false;
    }

    public enum CARD_COLORS {
        CLUBS,
        HEARTS,
        SPADES,
        DIAMONDS;
    }

    public enum CARD_NUMBERS {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE;

        public static int getIndex(CARD_NUMBERS numbers) {
            for (int i = 0; i < values().length; i++) {
                if (values()[i] == numbers) return i;
            }
            return -1;
        }
    }
}



