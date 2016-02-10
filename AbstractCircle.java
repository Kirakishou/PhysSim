import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class AbstractCircle extends AbstractObject{
	private String name;
	private int centerX;
	private int centerY;
	private int spriteX;
	private int spriteY;
	private int radius;
	
	protected AbstractCircle(String name, int centerX, int centerY, int radius){
		this.name = name;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public boolean isInRegion(Rectangle r){
		return centerX > r.x - radius && centerY > r.y - radius 
				&& centerX < r.x + r.width + radius && centerY < r.y + r.height + radius;
	}
	
	protected boolean detectCollision(PlayerObject player){
		if (Math.sqrt(Math.pow(centerX - player.getCenterX(), 2) + Math.pow(centerY - player.getCenterY(), 2))
				< radius + player.getRadius() &&
				Math.sqrt(Math.pow(centerX - player.getCenterX() - player.getVectorX(), 2) + 
						Math.pow(centerY - player.getCenterY() - player.getVectorY() - PlayerObject.GRAVITY_CONSTANT, 2))
				< radius + player.getRadius()){
			return true;
		}
		return false;
	}
	
	//default reaction is to bounce the object using vector formulas
	protected String collisionReact(PlayerObject player){
		int nx = this.centerX - player.getCenterX();
		int ny = this.centerY - player.getCenterY();
		float projFactor = (float) (player.getVectorX() * nx + player.getVectorY() * ny) / (nx*nx + ny*ny);
		player.setVectorX((int)Math.round(player.getVectorX() - (2*projFactor*nx)));
		player.setVectorY((int)Math.round(player.getVectorY() - (2*projFactor*ny)));
		//return null;
		return name;
	}
	
	//default action is to fill a circle
	public void drawObject(){
	}
	
	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getRadius() {
		return radius;
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
