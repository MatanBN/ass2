import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

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
		GUI gui = new GUI("Hanging Balls!", 700, 700);
		DrawSurface d = gui.getDrawSurface();
		Rectangle[] recs = new Rectangle[2];
		recs[0] = new Rectangle(50, 50, 450, 450, Color.gray);
		recs[1] = new Rectangle(450, 450, 150, 150, Color.yellow);
		gui.show(d);
		MultipleFramesBouncingBallsAnimation b = new MultipleFramesBouncingBallsAnimation();
		Surface[] s = b.mergeBalls(args, recs);
		// Draw and animate the balls.
		b.animateBalls(s, gui);
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
	public void animateBalls(Surface[] surfaces, GUI gui) {
		Sleeper sleeper = new Sleeper();
		while (true) {
			DrawSurface d = gui.getDrawSurface();
			// A for loop to draw on all of the surfaces needed.
			// A loop to draw all the balls needed.
			for (int i = 0; i < surfaces.length; ++i) {
				surfaces[i].drawFrame(d);
				surfaces[i].drawBalls(d);
				surfaces[i].moveBalls();
			}
			gui.show(d);
			sleeper.sleepFor(50); // wait for 50 milliseconds.
		}
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
	public Surface[] mergeBalls(String[] args, Rectangle[] boundaries) {
		MultipleBouncingBallsAnimation m = new MultipleBouncingBallsAnimation();
		int numberOfFrames = boundaries.length;
		int numberOfBalls = args.length / numberOfFrames;
		Surface[] s = new Surface[numberOfFrames];
		// Divide the balls in a 2-d array according to the number of frames.
		for (int i = 0; i < numberOfFrames; ++i) {
			String newArgs[] = new String[numberOfBalls];
			int takeBallsUntil = numberOfBalls * (i + 1);
			for (int j = i * numberOfBalls, count = 0; j < takeBallsUntil; ++j, ++count) {
				newArgs[count] = args[j];
			}
			s[i] = m.getBalls(newArgs, boundaries[i]);
		}
		return s;
	}
}
