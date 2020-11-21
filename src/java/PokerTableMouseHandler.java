import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PokerTableMouseHandler implements MouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int r = (int) (Math.random() * PokerTable.instance.cards.size());
		PokerTable.instance.cards.get(r).turn();
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
