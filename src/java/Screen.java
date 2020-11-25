import javax.swing.*;
import java.awt.*;

/**
 * The type Screen.
 */
public abstract class Screen extends JPanel {
	/**
	 * Draw.
	 *
	 * @param g2d the g 2 d
	 */
	public abstract void draw(Graphics2D g2d);

	/**
	 * Update.
	 */
	public abstract void update();

	/**
	 * Init.
	 */
	public void init() {
		setSize(Main.frame.getWidth(), Main.frame.getHeight());
	}
}
