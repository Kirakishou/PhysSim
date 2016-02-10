import java.awt.event.MouseEvent;

public class SampleObject extends AbstractCircle{
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	// NOTE: This class, and most other classes, will only have to interact with PlayerObject class,
	// as well as MyListener class. Although you won't be using all of the mouse functions, you
	// should at least write down their methods, as you will see below in the code.
	
	final static int LAUNCH_SPEED = 15;
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	
	
	
	

	private PlayerObject player;
	
	public SampleObject(String name, int x, int y, int radius){
		super(name, x, y, radius);
	}
	
	//"freezes" the player ball
	@Override
	public String collisionReact(PlayerObject player){
		
		// will only react when it isn't currently holding a ball
		if (this.player != null) return "occupied";
		this.player = player;
		
		// changes ball coordinates to center of object
		player.setCenterX(this.getCenterX());
		player.setCenterY(this.getCenterY());
		
		// stops player from moving
		player.setPaused(true);
		
		// allows MyListener to transmit mouse actions to this object (note: must cast as AbstractObject)
		MyListener.addObj((AbstractObject)this);
		
		// random
		return "wait";
	}
	
	public void mouseReleased(MouseEvent e) {
		
		double dx = Math.abs(e.getX()-this.getCenterX());
		double dy = Math.abs(this.getCenterY()-e.getY());
		double angle = 0;
		double relx = this.getCenterX();
		double rely = this.getCenterY();
		double deltax=1;
		double deltay=1;
		
		angle = Math.atan(dy/dx);
		
		deltax = (this.getRadius()*Math.cos(angle));
		deltay = (this.getRadius()*Math.sin(angle));
		if (e.getY()<this.getCenterY()) deltay *= -1;
		if (e.getX()<this.getCenterX()) deltax *= -1;
			
		relx = this.getCenterX() + deltax;
		rely = this.getCenterY() + deltay;
		
		player.setVectorX((int)Math.round((double)deltax * LAUNCH_SPEED / this.getRadius()));
		player.setVectorY((int)Math.round((double)deltay * LAUNCH_SPEED / this.getRadius()));
		
		player.setCenterX((int)relx);
		player.setCenterY((int)rely);
		
		
		// tells the ball to start moving again
		player.setPaused(false);
		
		// frees player variable so that it can capture other balls again
		player = null;
		
		MyListener.removeObj(this);
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}