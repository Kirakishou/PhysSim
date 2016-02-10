import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;


//test
@SuppressWarnings("serial")
class Test extends JPanel {
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	
	// Display settings
	final static int DEFAULT_SCREEN_WIDTH = 600;
	final static int DEFAULT_SCREEN_HEIGHT = 800;
	
	// Configurations for the ball states, multiple balls will have slightly altered angles
	final static int DEFAULT_BALL_RADIUS = 5;
	final static int TEST_POS_X = 78;
	final static int TEST_POS_Y = 416;
	final static int TEST_VEL_X = -2;
	final static int TEST_VEL_Y = 11;
	final static int TEST_BALL_NUM = 3;
	
//---------------------------------------------------------------------------------------
//***************************************************************************************
//---------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	static List <PlayerObject> balls;
	static List <AbstractObject> objs;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.drawOval(20, 470, 60, 60);
		g2d.drawRect(100, 100, 300, 100);
		g2d.drawRect(300, 400, 50, 200);
		g2d.drawRect(450, 450, 50, 50);
		g2d.drawRect(500, 100, 50, 100);
		
		g2d.setColor(Color.BLUE);
		g2d.drawOval(260, 260, 80, 80);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, DEFAULT_SCREEN_WIDTH, 20);
		g2d.fillRect(0, 0, 20, DEFAULT_SCREEN_HEIGHT);
		g2d.fillRect(0, DEFAULT_SCREEN_HEIGHT - 40, DEFAULT_SCREEN_WIDTH, 20);
		g2d.fillRect(DEFAULT_SCREEN_WIDTH - 20, 0, 20, DEFAULT_SCREEN_HEIGHT);
		
		g2d.setColor(Color.GREEN);
		for (int i = 0; i < balls.size(); i++){
			g2d.fillOval(balls.get(i).getCenterX() - balls.get(i).getRadius(), 
				balls.get(i).getCenterY() - balls.get(i).getRadius(),
				balls.get(i).getRadius() * 2, balls.get(i).getRadius() * 2);
		}
	}
	
	public static void main (String[]args) throws IOException, InterruptedException{
		balls = new Stack<PlayerObject>();
		
		// create balls
		for (int i = 0; i < TEST_BALL_NUM; i++){
			balls.add(new PlayerObject(DEFAULT_BALL_RADIUS, TEST_VEL_X - (i % 7), TEST_VEL_Y - (i % 11), TEST_POS_X, TEST_POS_Y));
		}
		
		objs = new Stack<AbstractObject>();
		objs.add(new AbstractRectangle("Rect1", 100, 100, 300, 100, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("Rect2", 300, 400, 50, 200, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("Rect3", 450, 450, 50, 50, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("Rect4", 500, 100, 50, 100, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("Ceiling", 0, 0, DEFAULT_SCREEN_WIDTH, 20, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("Floor", 0, DEFAULT_SCREEN_HEIGHT - 40, DEFAULT_SCREEN_WIDTH, 20, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("LeftWall", 0, 0, 20, DEFAULT_SCREEN_HEIGHT, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractRectangle("RightWall", DEFAULT_SCREEN_WIDTH - 20, 0, 20, DEFAULT_SCREEN_HEIGHT, DEFAULT_BALL_RADIUS));
		objs.add(new AbstractCircle("Circ1", 50, 500, 30));
		objs.add(new SampleObject("Teleporter1", 300, 300, 40));
		QuadTreeImpl q = new QuadTreeImpl(new Rectangle (0, 0, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT), objs);

		JFrame frame = new JFrame("Test");
		JPanel game = new Test();
		game.addMouseListener(new MyListener());
		frame.add(game);
		frame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		frame.setBackground(Color.BLACK);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true){
			Thread.sleep(32);
			for (int i = 0; i < balls.size(); i++){
				balls.get(i).update(q);
			}
			frame.getComponent(0).repaint();
		}
	}
}