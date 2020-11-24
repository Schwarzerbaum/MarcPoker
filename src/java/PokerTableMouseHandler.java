import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PokerTableMouseHandler implements MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && PokerTable.instance.cards.size() < 5)
			PokerTable.instance.cards.add(new PokerCard(PokerTable.instance.getRandomCard(), 0, 0, PokerTable.cardWidth, PokerTable.cardHeight, 0));
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
