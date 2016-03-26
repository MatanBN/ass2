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
     * The main method creates a 2 surfaces, each with a few balls and calls the function to animate them.
     * @param args input from command line which will be the size of the balls.
     */
    public static void main(String[] args) {
        // Create a window with the title "Hanging Balls!"
        // which is 700 pixels wide and 700 pixels high.
        GUI gui = new GUI("Hanging Balls!", 700, 700);
        DrawSurface d = gui.getDrawSurface();
        // Create the frames for the balls.
        Rectangle[] recs = new Rectangle[2];
        recs[0] = new Rectangle(50, 50, 450, 450, Color.gray);
        recs[1] = new Rectangle(450, 450, 150, 150, Color.yellow);
        MultipleFramesBouncingBallsAnimation b = new MultipleFramesBouncingBallsAnimation();
        // Divide the balls to each surface.
        Surface[] s = b.divideToSurfaces(args, recs);
        // Draw and animate the balls.
        b.animateBalls(s, gui);
    }

    /**
     * animateBalls animates the balls on the surface given to it.
     * @param surfaces the surfaces with the balls and frame to animate.
     * @param gui the gui to draw the surface on.
     */
    public void animateBalls(Surface[] surfaces, GUI gui) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // A for loop to draw on all of the surfaces needed.
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
     * divideToSurfaces divides in an even division balls according to the number of
     * surfaces needed (for example if we had 12 balls and 3 frames, each frame
     * would get 4 balls).
     * @param args an array of strings which will include the sizes of the balls.
     * @param boundaries an array of rectangles which include every frame to draw the ball on.
     * @return a surfaces array which will include all of the surfaces to draw.
     */
    public Surface[] divideToSurfaces(String[] args, Rectangle[] boundaries) {
        MultipleBouncingBallsAnimation m = new MultipleBouncingBallsAnimation();
        int numberOfFrames = boundaries.length; // The number of frames needed.
        int numberOfBalls = args.length / numberOfFrames; // The number of balls per each frame.
        Surface[] s = new Surface[numberOfFrames];
        // Divide the balls in a each surface according to the number of frames.
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
