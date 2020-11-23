import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.net.URL;

public class Main {
	public static JFrame frame;
	public static Screen currentScreen;

	public static ImageIcon image1;
	public static ImageIcon image2;
	public static JButton button;
	public static JLabel label;
	public static ImageIcon image = new ImageIcon("resources//chips//chip1.png");
	/*URL imageFile = PokerTable.class.getClassLoader().getResource("table.png");*/

	public static void main(String[] args) {
		Card.loadImages();

		createJFrame();

		Card[] bla = new Card[]{
				new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.ACE),
				new Card(Card.CARD_COLORS.CLUBS, Card.CARD_NUMBERS.ACE),
				new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.QUEEN),
				new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.TWO),
				new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.NINE),
				new Card(Card.CARD_COLORS.DIAMONDS, Card.CARD_NUMBERS.JACK),
				new Card(Card.CARD_COLORS.SPADES, Card.CARD_NUMBERS.JACK)
		};


		System.out.println(Card.determineHandRank(bla));

		/*PokerChip test = new PokerChip();*/

		/*System.out.println(Card.royalFlush(bla));
		System.out.println(Card.straightFlush(bla));
		System.out.println(Card.fourOfAKind(bla));
		System.out.println(Card.fullHouse(bla));
		System.out.println(Card.flush(bla));
		System.out.println(Card.threeOfAKind(bla));
		*//*System.out.println(Card.twoPair(bla));*//*
		System.out.println(Card.pair(bla));*/

		PokerTable.instance.init();
		PauseMenu.instance.init();

		currentScreen = PokerTable.instance;

		java.util.Timer timer = new java.util.Timer();
		timer.scheduleAtFixedRate(DrawThread.instance, 0, 1000 / 60);
		timer.scheduleAtFixedRate(UpdateThread.instance, 0, 1000 / 60);

	}




	public static void createJFrame() {

		button = new JButton(image);
		button.setBounds(150, 50, 100,60);
		/*button.addActionListener(this);*/



		frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(button);
		frame.add(PokerChip.instance);
		frame.add(PokerTable.instance);
		frame.add(PauseMenu.instance);

		PokerTable.instance.addMouseListener(new PokerTableMouseHandler());

		/*music.music("resources/audio/poker");*/

	}
}
