
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyListener extends JPanel implements MouseListener{
	// this is where all the listening computation is done. It will send messages to all registered
	// abstract objects (which are added when they capture a ball) whenever it receives mouse events.
	// Remember that when you use this class, you have to use addObj in collisionReact, and removeObj
	// in whatever mouse event you want to "throw" the ball with (usually it's mouseReleased).
	static List <AbstractObject> objs;
	
	public MyListener(){
		objs = new Stack<AbstractObject>();
	}
	
	public static void addObj (AbstractObject obj){
		objs.add(obj);
	}
	
	public static void removeObj (AbstractObject obj){
		objs.remove(obj);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < objs.size(); i++){
			objs.get(i).mouseEntered(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < objs.size(); i++){
			objs.get(i).mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < objs.size(); i++){
			objs.get(i).mousePressed(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < objs.size(); i++){
			objs.get(i).mouseExited(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < objs.size(); i++){
			objs.get(i).mouseReleased(e);
		}
	}
	
}
