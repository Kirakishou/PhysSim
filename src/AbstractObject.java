import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public abstract class AbstractObject {
	abstract boolean detectCollision(PlayerObject player);
	abstract String collisionReact(PlayerObject player);
	abstract void drawObject();
	abstract boolean isInRegion(Rectangle r);
	abstract String getName();
	abstract void mouseEntered(MouseEvent e);
	abstract void mouseClicked(MouseEvent e);
	abstract void mousePressed(MouseEvent e);
	abstract void mouseExited(MouseEvent e);
	abstract void mouseReleased(MouseEvent e);
}
