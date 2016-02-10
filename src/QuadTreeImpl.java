import java.awt.Rectangle;
import java.util.ListIterator;
import java.util.Stack;
import java.util.List;

public class QuadTreeImpl {
	
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	// note the amount of objects in this test is minimal; the choice of quadtree
	// implementation was purely for practice
	
	// sets maximum number of items to be detected per area
	final static int MAX_ELEM = 5;
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	
	
	
	
	private boolean isLeaf;
	private Rectangle bounds;
	private List<AbstractObject> objs;
	private QuadTreeImpl topLeft;
	private QuadTreeImpl topRight;
	private QuadTreeImpl botLeft;
	private QuadTreeImpl botRight;

	// Checks to see if a list of objects are in a rectangular region
	public List<AbstractObject> objsInRegion (Rectangle subBounds){
		List<AbstractObject> toDump = new Stack<AbstractObject>();
		ListIterator<AbstractObject> litr = objs.listIterator();
		while (litr.hasNext()){
			AbstractObject temp = litr.next();
			if (temp.isInRegion(subBounds)) toDump.add(temp);
		}
		return toDump;
	}
	
	// Constructs the quadtree data structure to store abstract objects
	public QuadTreeImpl (Rectangle bounds, List<AbstractObject> objs){
		this.bounds = bounds;
		this.objs = objs;
		if (objs.size() > MAX_ELEM){
			isLeaf = false;
			Rectangle topLeftRect = new Rectangle(bounds.x, bounds.y, bounds.width / 2, bounds.height / 2);
			Rectangle topRightRect = new Rectangle(bounds.x + bounds.width / 2, bounds.y, bounds.width / 2, bounds.height / 2);
			Rectangle botLeftRect = new Rectangle(bounds.x, bounds.y + bounds.height / 2, bounds.width / 2, bounds.height / 2);
			Rectangle botRightRect = new Rectangle(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2, bounds.width / 2, bounds.height / 2);
			
			topLeft = new QuadTreeImpl (topLeftRect, objsInRegion(topLeftRect));
			topRight = new QuadTreeImpl (topRightRect, objsInRegion (topRightRect));
			botLeft = new QuadTreeImpl (botLeftRect, objsInRegion (botLeftRect));
			botRight = new QuadTreeImpl (botRightRect, objsInRegion (botRightRect));

			objs = null;
		}
		else {
			isLeaf = true;
		}
	}
	
	// 1. Uses the quadtree data structure to locate up to 5 nearby objects 
	// 2. Checks for collision between nearby objects and player ball
	// 3. If there are any collisions, then collision reactions will be computed (eg bounce, tp)
	public void detectCollision (PlayerObject player){
		if (isLeaf){
			ListIterator<AbstractObject> litr = objs.listIterator();
			while (litr.hasNext()){
				AbstractObject temp = litr.next();
				if (temp.detectCollision(player)){
					temp.collisionReact(player);
					player.setContactObject(temp);
				}
			}
		}
		else {
			Rectangle detector = new Rectangle (
					player.getCenterX() - player.getRadius(),
					player.getCenterY() - player.getRadius(),
					player.getRadius() * 2,
					player.getRadius() * 2);
			if (topLeft.bounds.intersects(detector)) topLeft.detectCollision(player);
			else if (topRight.bounds.intersects(detector)) topRight.detectCollision(player);
			else if (botLeft.bounds.intersects(detector)) botLeft.detectCollision(player);
			else botRight.detectCollision(player);
		}
	}
}





