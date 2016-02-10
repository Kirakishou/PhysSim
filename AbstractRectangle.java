import java.awt.Rectangle;
import java.awt.event.MouseEvent;
public class AbstractRectangle extends AbstractObject{
	private String name;
	private Rectangle border;
	private Rectangle orig;
	private int spriteX;
	private int spriteY;
	
	public AbstractRectangle(Rectangle objectRect, int ballRadius, String name){
		border = new Rectangle(
				objectRect.x - ballRadius,
				objectRect.y - ballRadius,
				objectRect.width + 2*(ballRadius),
				objectRect.height + 2*(ballRadius));
		orig = objectRect;
		this.name = name;
	}
	
	public AbstractRectangle(String name, int minX, int minY, int width, int height, int ballRadius){
		border = new Rectangle(
				minX - ballRadius,
				minY - ballRadius,
				width + 2*(ballRadius),
				height + 2*(ballRadius));
		orig = new Rectangle (minX, minY, width, height);
		this.name = name;
	}
	
	public boolean isInRegion(Rectangle r){
		return border.intersects(r);
	}
	
	public boolean detectCollision(PlayerObject player){
		if (border.contains(player.getCenterX(), player.getCenterY())){
			return true;
		}
		return false;
	}
	
	//default reaction is to bounce the object by reversing a vector
	public String collisionReact(PlayerObject player){
		if (!(border.contains(player.getCenterX() - player.getVectorX(), player.getCenterY()))){
			player.setVectorX(-1 * (player.getVectorX()));
		}
		else if (!(border.contains(player.getCenterX(), player.getCenterY() - player.getVectorY() - PlayerObject.GRAVITY_CONSTANT))){
			player.setVectorY(-1 * (player.getVectorY()));
		}
		//return null;
		return name;
	}
	
	//default action is to fill a rectangle based on border
	public void drawObject(){
	}
	
	public Rectangle getBorder() {
		return border;
	}

	public Rectangle getOrig() {
		return orig;
	}

	public String getName() {
		return name;
	}
	
	void mouseEntered(MouseEvent e) {}
	void mouseClicked(MouseEvent e) {}
	void mousePressed(MouseEvent e) {}
	void mouseExited(MouseEvent e) {}
	void mouseReleased(MouseEvent e) {}
}
