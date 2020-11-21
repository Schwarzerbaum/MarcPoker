import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    public abstract void draw(Graphics2D g2d);
    public abstract void update();

}
