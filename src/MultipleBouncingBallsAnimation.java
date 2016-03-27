import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * The MultipleBouncingBallsAnimation class animates a number of balls on a DrawSurface.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class MultipleBouncingBallsAnimation {
    /**
     * The main method.
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
        // Create the surface with the balls and animate them on the surface.
        MultipleBouncingBallsAnimation b = new MultipleBouncingBallsAnimation();
        Surface s = b.getBalls(args, new Rectangle(d.getWidth(), d.getHeight(), Color.white));
        b.animateBalls(s, gui);
    }

    /**
     * animateBalls animates the balls on the surface given to it.
     * @param s the surface that includes the borders and the balls to draw.
     * @param gui the gui to draw the surface on.
     */
    public void animateBalls(Surface s, GUI gui) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Move and draw the balls on the surface.
            s.moveBalls();
            s.drawBalls(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }

    /**
     * getSurface gets sizes for balls and a boundary for them and, returns a surface with balls and a frame for them.
     * @param args an array of strings which will include the sizes of the balls.
     * @param bound the frame of the surface.
     * @return the new surface to draw the balls on.
     * @throws ArithmeticException if a radius is 0.
     * @throws IndexOutOfBoundsException if no balls were entered.
     * @throws IllegalArgumentException if a radius of a ball is to big to fit in the frame.
     */
    public Surface getBalls(String[] args, Rectangle bound)
            throws ArithmeticException, IndexOutOfBoundsException, IllegalArgumentException {
        Ball[] balls = new Ball[args.length];
        int radiusLimit = 50;
        Random r = new Random();
        // The for loop creates balls in random coordinates according to the bound limits.
        for (int i = 0; i < balls.length; ++i) {
            int radius = Integer.parseInt(args[i]);
            try {
                // Create a random center point for the ball in the frame of the bounds.
                int centerX = r.nextInt(bound.getWidth() - 2 * radius) + radius + bound.getX();
                int centerY = r.nextInt(bound.getHeight() - 2 * radius) + radius + bound.getY();
                try {
                    balls[i] = new Ball(centerX, centerY, radius, Color.blue);
                    // If no balls were entered.
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No balls entered");
                }
                /*
                 * Set the velocity according to the radius limit (the speed starts with 50 and gets divided by the
                 * radius, so the bigger the radius slower the ball.
                 */
                try {
                    balls[i].setVelocity(radiusLimit / radius, radiusLimit / radius);
                    // If a radius that is equal to 0 was entered.
                } catch (ArithmeticException e) {
                    balls[i].setVelocity(0, 0);
                }
                // Check if the radius reached the limit.
                if (radius >= radiusLimit) {
                    // If so the velocity is one.
                    balls[i].setVelocity(1, 1);
                }
                // If a radius that was too big for the frame was entered.
            } catch (IllegalArgumentException e) {
                System.out.println("Ball #" + i + "radius is to big for the frame.");
            }

        }
        return new Surface(bound, balls);
    }

}
