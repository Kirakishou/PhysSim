
public class PlayerObject {
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	// use this class in your abstractobjects when you want to change or get its state.
	// Current fields are:
	// 	1. radius
	//	2. center position
	// 	3. vector components
	//	4. the object it is in contact with (this probably won't be useful yet, we may 
	//		implement something like explosive/destructive/piercing balls in the future)
	//	5. its pause state (when paused, it won't change its coordinates or do collisions)
	
	private int radius;
	private int centerX;
	private int centerY;
	private int vectorX;
	private int vectorY;
	private AbstractObject contactObject;
	private boolean isPaused;

	final static int GRAVITY_CONSTANT = 0;
	final static int TERMINAL_VECTOR_Y = 1000;
	// final static int SPEED_RESISTANCE = 7; // air resistance if air resistance function unblocked
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	
	
	
	
	public PlayerObject(int radius){
		this.radius = radius;
		vectorX = 0;
		vectorY = 0;
		centerX = 0;
		centerY = 0;
		isPaused = false;
	}
	
	public PlayerObject (int radius, int vX, int vY, int cX, int cY){
		this.radius = radius;
		vectorX = vX;
		vectorY = vY;
		centerX = cX;
		centerY = cY;
		isPaused = false;
	}
	
	public void update(QuadTreeImpl objTree){
		
		// only updates when it isn't paused
		if (isPaused) return;
		
		vectorY += GRAVITY_CONSTANT;
		if (vectorY > TERMINAL_VECTOR_Y) vectorY = TERMINAL_VECTOR_Y;
		centerX += vectorX;
		
		
		/* terminal velocity , little sketchy for small velocities because x,y vector increments are in integers
		 * 
		if (vectorX * vectorX + vectorY * vectorY > SPEED_RESISTANCE * SPEED_RESISTANCE){
			double factor = Math.sqrt((float) (vectorX * vectorX + vectorY * vectorY) / (SPEED_RESISTANCE * SPEED_RESISTANCE));
			vectorX = (int) (vectorX / factor);
			vectorY = (int) (vectorY / factor);
		}
		*/
		
		centerY += vectorY;
		objTree.detectCollision(this);
	}

	public int getVectorX() {
		return vectorX;
	}

	public void setVectorX(int vectorX) {
		this.vectorX = vectorX;
	}

	public int getVectorY() {
		return vectorY;
	}

	public void setVectorY(int vectorY) {
		this.vectorY = vectorY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getRadius() {
		return radius;
	}
	
	public AbstractObject getContactObject() {
		return contactObject;
	}

	public void setContactObject(AbstractObject contactObject) {
		this.contactObject = contactObject;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
}
