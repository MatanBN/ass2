import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The BouncingBallAnimation class animates a ball on a DrawSurface.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class BouncingBallAnimation {
    /**
     * The main creates a surface with one ball and calls the right methods to animate the ball on that surface.
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        // Create a window with the title "Balls Balls Balls!!!"
        // which is 200 pixels wide and 200 pixels high.
        GUI gui = new GUI("Balls Balls Balls!!!", 200, 200);
        // Gets the DrawSurface to draw on.
        DrawSurface d = gui.getDrawSurface();
        // Create the ball to draw.
        Ball ball = new Ball(100, 150, 30, Color.BLACK);
        // Sets the velocity of the ball.
        ball.setVelocity(2, 2);
        // Create the surface to draw the ball on.
        Surface s = new Surface(new Rectangle(d.getWidth(), d.getHeight()), ball);
        BouncingBallAnimation b = new BouncingBallAnimation();
        // Animate the ball on that surface.
        b.animateBall(s, gui);
    }

    /**
     * animateBalls animates the balls on the surface given to it.
     * @param s the surface with the ball to draw.
     * @param gui the gui to draw the surface with the ball on.
     */
    public void animateBall(Surface s, GUI gui) {
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Move and draw the ball;
            s.drawBalls(d);
            s.moveBalls();
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}
