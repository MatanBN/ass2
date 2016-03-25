import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * The MultipleBouncingBallsAnimation class animates a number of balls on a
 * DrawSurface.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class MultipleFramesBouncingBallsAnimation {
	/**
	 * The main method creates 2 guis and creates 2-d array of balls which will
	 * include the first haft of the balls to the first gui and the second haft
	 * to the second gui.
	 *
	 * @param args
	 *            input from command line which will be the size of the balls.
	 */
	public static void main(String[] args) {
		// Create a window with the title "Hanging Balls!"
		// which is 1000 pixels wide and 1000 pixels high.
		GUI gui = new GUI("Hanging Balls!", 1000, 1000);
		DrawSurface d = gui.getDrawSurface();
		Rectangle[] recs = new Rectangle[2];
		recs[0] = new Rectangle(50, 50, 450, 450, Color.gray);
		recs[1] = new Rectangle(450, 450, 150, 150, Color.yellow);
		recs[0].drawOn(d);
		recs[1].drawOn(d);
		gui.show(d);
		if (args.length == 0) {
			return;
		}
		Ball[][] balls = mergeBalls(args, recs);
		// Draw and animate the balls.
		BouncingBallAnimation.animateBalls(balls, gui);
	}

	/**
	 * mergeBalls divides in an even division balls according to the number of
	 * frames needed (for example if we had 12 balls and 3 frames, each frame
	 * would get 4 balls).
	 *
	 * @param args
	 *            an array of strings which will include the sizes of the balls.
	 * @param boundaries
	 *            an array of guis which include every frame to draw the ball
	 *            on.
	 * @return a 2-d array of balls.
	 */
	public static Ball[][] mergeBalls(String[] args, Rectangle[] boundaries) {
		int numberOfFrames = boundaries.length;
		int numberOfBalls = args.length / boundaries.length;
		Ball[][] balls = new Ball[numberOfFrames][numberOfBalls];
		// Divide the balls in a 2-d array according to the number of frames.
		for (int i = 0; i < numberOfFrames; ++i) {
			balls[i] = MultipleBouncingBallsAnimation.getBalls(args, i * args.length / boundaries.length,
					(i + 1) * args.length / boundaries.length, boundaries[i]);
		}
		return balls;
	}
}
