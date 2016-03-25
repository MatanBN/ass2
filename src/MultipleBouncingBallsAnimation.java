import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * The MultipleBouncingBallsAnimation class animates a number of balls on a DrawSurface.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The main method.
     *
     * @param args input from command line which will be the size of the balls.
     */
    public static void main(String[] args) {
        // Create a window with the title "Fun With Balls!!!!"
        // which is 500 pixels wide and 500 pixels high.
        GUI gui = new GUI("Fun With Balls!", 500, 500);
        DrawSurface d = gui.getDrawSurface();
        if (args.length == 0) {
        	return;
        }
        // Create the balls and draw them on the surface.
        Ball[][] balls = new Ball[1][args.length];
        balls[0] = getBalls(args, 0, args.length, new Rectangle(d.getWidth(), d.getHeight(),Color.white));
        BouncingBallAnimation.animateBalls(balls, gui);
    }

    /**
     * getBalls gets a number of string which include ball sizes and returns an array of balls in those sizes.
     *
     * @param args  an array of strings which will include the sizes of the balls.
     * @param start The start index of args to take the balls.
     * @param end   The end index of args to take the balls.
     * @param bound the blocking rectangle of the circle.
     * @return an array of balls.
     */
    public static Ball[] getBalls(String[] args, int start, int end, Rectangle bound) {
        Ball[] balls = new Ball[end - start];
        int radiusLimit = 50;
        Random r = new Random();
        // The for loop creates balls in random coordinates in the DrawSurface width and height.
         for (int i = 0; i < balls.length; ++i) {
             int radius = Integer.parseInt(args[start + i]);
             balls[i] = new Ball((r.nextInt(bound.getWidth())) - radius + bound.getX(),
                    r.nextInt(bound.getHeight()) - radius + bound.getY(), radius, Color.blue, bound);
            // Check if the random point is in the right bounds.
            while (!balls[i].checkX() || !balls[i].checkY()) {
                // Create another ball if not.
                balls[i] = new Ball((r.nextInt(bound.getWidth())) - radius + bound.getX(),
                        r.nextInt(bound.getHeight()) - radius + bound.getY(), radius, Color.blue, bound);
            }
            // Set the velocity of the ball according to the radius.
            balls[i].setVelocity(radiusLimit / radius, radiusLimit / radius);
            // Check if the radius reached the limit.
            if (radius >= radiusLimit) {
                // If so the velocity is one.
                balls[i].setVelocity(1, 1);
            }
        }
        // Return the array of balls.
        return balls;
    }
}
