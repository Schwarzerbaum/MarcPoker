import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

/**
 * The type Poker table.
 */
public class PokerTable extends Screen {
    /**
     * The constant instance.
     */
    public static PokerTable instance = new PokerTable();
    /**
     * The constant image.
     */
    public static BufferedImage image;
    /**
     * The constant winImage.
     */
    public static BufferedImage winImage;
    /**
     * The constant loseImage.
     */
    public static BufferedImage loseImage;

    /**
     * The constant deckOfCards.
     */
    public static ArrayList<Card> deckOfCards = new ArrayList<>();
    /**
     * The constant player.
     */
    public static Player player = new Player();
    /**
     * The constant bot.
     */
    public static Bot bot = new Bot();

    /**
     * The constant cardWidth.
     */
    public static double cardWidth;
    /**
     * The constant cardHeight.
     */
    public static double cardHeight;

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
            URL winImageFile = PokerTable.class.getClassLoader().getResource("win.png");
            if (winImageFile != null)
                winImage = ImageIO.read(winImageFile);
            URL loseImageFile = PokerTable.class.getClassLoader().getResource("lose.png");
            if (loseImageFile != null)
                loseImage = ImageIO.read(loseImageFile);
        } catch (Exception ignored) {
        }
    }

    /**
     * The Cards.
     */
    ArrayList<PokerCard> cards = new ArrayList<>();


    @Override
    public void init() {
        super.init();
        /*System.out.println("lol");*/

        Buttons buttons = new Buttons();


        cardWidth = getWidth() / 15d;
        cardHeight = cardWidth * 1.5d;

        int i = 5;
        int j = 2;

        cards.clear();
        cards.add(new PokerCard(getRandomCard(), 0, 0, cardWidth, cardHeight, 0).turn());
        cards.add(new PokerCard(getRandomCard(), 0, 0, cardWidth, cardHeight, 0).turn());
        cards.add(new PokerCard(getRandomCard(), 0, 0, cardWidth, cardHeight, 0).turn());

        i = 10;

        player.cards.clear();
        player.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 3.5), cardHeight * (j + 1.4), cardWidth, cardHeight, 0).turn());
        player.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 2.5), cardHeight * (j + 1.4), cardWidth, cardHeight, 0).turn());

        bot.cards.clear();
        if (PokerTable.instance.cards.size() == 5) {
            bot.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 3.5), cardHeight * (j - 1.4), cardWidth, cardHeight, 0).turn());
            bot.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 2.5), cardHeight * (j - 1.4), cardWidth, cardHeight, 0).turn());
        }else {
            bot.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 3.5), cardHeight * (j - 1.4), cardWidth, cardHeight, 0));
            bot.cards.add(new PokerCard(getRandomCard(), cardWidth * (i - 2.5), cardHeight * (j - 1.4), cardWidth, cardHeight, 0));
        }
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


        int playerHand = PokerTable.player.getHandRank().value;
        int botHand = PokerTable.bot.getHandRank().value;

        /*if (PokerTable.instance.cards.size() == 5) {
            int i = 10;
            int j = 2;
            bot.cards.clear();
            bot.cards.add.turn());
            bot.cards.turn());
        }*/

        if (PokerTable.instance.cards.size() == 5) {
            int x = 200;
            int y = 280;
            int w = 400;
            int h = 400;
            if (playerHand > botHand) {
                g2d.drawImage(winImage, x, y, w, h, null);
                PokerTable.player.playerMoney += (PokerTable.player.moneyPool * 2);
            } else if (playerHand < botHand) {
                g2d.drawImage(loseImage, x, y, w, h, null);
            } else {
            }
        }


        int offX = 5;
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setX(cardWidth * offX++).setY(cardHeight * 2).draw(g2d);
        }

        player.draw(g2d);
        bot.draw(g2d);

        int w = 64 * 2;
        int h = 47 * 2;
        int xOff = 412;
        int yOff = getHeight() - h * 2;
        for (int i = 0; i < Chips.values().length; i++) {
            Chips.values()[i].draw(g2d, xOff + i * (w - 5), yOff, w, h);
        }


        g2d.setColor(Color.red);
        g2d.setFont(new Font("Comic Sans", Font.PLAIN, 25));
        g2d.drawString(player.getHandRank().toString(), 500, 700);
    }

    /**
     * Reset deck of cards.
     */
    public void resetDeckOfCards() {
        deckOfCards.clear();
        for (int i = 0; i < Card.CARD_COLORS.values().length; i++) {
            for (int j = 0; j < Card.CARD_NUMBERS.values().length; j++) {
                deckOfCards.add(new Card(Card.CARD_COLORS.values()[i], Card.CARD_NUMBERS.values()[j]));
            }
        }
    }

    /**
     * Reset poker table.
     */
    public void resetPokerTable() {
        resetDeckOfCards();
        init();
    }

    /**
     * Gets random card.
     *
     * @return the random card
     */
    public Card getRandomCard() {
        int index = (int) (Math.random() * deckOfCards.size());
        Card card = deckOfCards.get(index);
        deckOfCards.remove(index);
        return card;
    }
}
