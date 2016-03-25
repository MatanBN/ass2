import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The BouncingBallAnimation class animates a ball on a DrawSurface.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class BouncingBallAnimation {
	/**
	 * The main method animates a ball on a surface..
	 *
	 * @param args
	 *            input from command line.
	 */
	public static void main(String[] args) {
		// Create a window with the title "Balls Balls Balls!!!"
		// which is 200 pixels wide and 200 pixels high.
		GUI gui = new GUI("Balls Balls Balls!!!", 200, 200);
		// Gets the DrawSurface to draw on.
		DrawSurface d = gui.getDrawSurface();
		// Create the ball to draw.
		Ball ball = new Ball(100, 150, 30, Color.BLACK, new Rectangle(d.getWidth(), d.getHeight(), Color.white));
		// Sets the velocity of the ball.
		ball.setVelocity(2, 2);
		Ball[][] balls = new Ball[1][1];
		balls[0][0] = ball;
		animateBalls(balls, gui);
	}

	/**
	 * animateBalls animates the balls on the DrawSurfaces given to it.
	 *
	 * @param balls
	 *            a 2-d array of balls which will include the balls each row
	 *            will include the array of balls to draw on a specific
	 *            DrawSurface.
	 * @param gui
	 *            an array of guis which will include the guis to draw the balls
	 *            on.
	 */
	public static void animateBalls(Ball[][] balls, GUI gui) {
		Sleeper sleeper = new Sleeper();
		while (true) {
			DrawSurface d = gui.getDrawSurface();
			// A for loop to draw on all of the surfaces needed.
			for (int j = 0; j < balls.length; j++) {
				
				Rectangle rec = balls[j][0].getBoundary();
				// A loop to draw all the balls needed.
				for (int i = 0; i < balls[j].length; ++i) {
					drawBall(balls[j][i], d);
				}
				rec.drawOn(d);
				
			}
			gui.show(d);
			sleeper.sleepFor(50); // wait for 50 milliseconds.
		}
	}

	/**
	 * drawBall draw a ball on the DrawSurface given to it.
	 *
	 * @param ball
	 *            is the ball to draw
	 * @param d
	 *            is the DrawSurface to draw the ball on.
	 */
	public static void drawBall(Ball ball, DrawSurface d) {
		ball.moveOneStep();
		ball.drawOn(d);
	}
}
