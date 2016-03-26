import biuoop.DrawSurface;

/**
 * The Surface class has a frame and an array of balls.
 * The Surface has methods to draw the balls, and move all the balls.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class Surface {
    private Rectangle frame; // The frame of the surface.
    private Ball balls[]; // The balls of the surface.

    /**
     * Surface is the constructor and creates the surface according to the user's parameters.
     * @param frame the desired frame for the surface.
     * @param balls an array of balls for the surface.
     */
    public Surface(Rectangle frame, Ball[] balls) {
        this.frame = frame;
        this.balls = balls;
    }

    /**
     * Surface is the constructor and creates the surface with only one ball.
     * @param frame the desired frame for the surface.
     * @param ball a ball for the surface.
     */
    public Surface(Rectangle frame, Ball ball) {
        this.frame = frame;
        this.balls = new Ball[1];
        this.balls[0] = ball;
    }

    /**
     * drawBalls method draws all the balls on the surface.
     * @param d the DrawSurface to draw the balls on.
     * @throws NullPointerException if one of the ball hasn't been initialized.
     */
    public void drawBalls(DrawSurface d) throws NullPointerException {
        for (int i = 0; i < balls.length; ++i) {
            try {
                balls[i].drawOn(d);
            } catch (NullPointerException e) {
                System.out.println("Ball #" + i + " Not found");
            }
        }
    }

    /**
     * drawFrame method draws the frame of the surface.
     * @param d the DrawSurface to draw the frame on.
     */
    public void drawFrame(DrawSurface d) {
        frame.drawOn(d);
    }

    /**
     * moveBalls method moves all the balls inside the surface.
     * @throws NullPointerException if the velocity hasn't been initialized.
     */
    public void moveBalls() throws NullPointerException {
        for (int i = 0; i < balls.length; ++i) {
            try {
                Velocity v = balls[i].getVelocity();
                // Check if the ball is in inside the x bounds.
                if (!checkX(balls[i])) {
                    v.setDx(-v.getDx());
                }
                // Check if the ball is in inside the y bounds.
                if (!checkY(balls[i])) {
                    v.setDy(-v.getDy());
                }
                Velocity savedV = new Velocity(v.getDx(), v.getDy());
                setBrakeSpeed(balls[i]);
                balls[i].moveOneStep();
                balls[i].setVelocity(savedV);
            } catch (NullPointerException e) {
                System.out.println("Ball #" + i + " Velcotiy isn't initialized");
            }
        }
    }

    /**
     * getBrakeVelocity checks if the velocity will move to ball to a point out of the frame, and if so modify the
     * velocity so the ball will stay in the frame.
     * @param b the ball to modify the speed.
     */
    public void setBrakeSpeed(Ball b) {
        double cX = b.getX();
        double cY = b.getY();
        Velocity v = b.getVelocity();
        int r = b.getSize();
        // Check if the x or y velocity will get the ball out of bounds and if so modify the speed.
        if (v.getDx() + cX - r < frame.getX() && v.getDx() < 0) {
            v.setDx(frame.getX() - cX + r);
        } else if (v.getDx() + cX + r > frame.getMaxX() && v.getDx() > 0) {
            v.setDx(frame.getMaxX() - r - cX);
        }
        if (v.getDy() + cY - r < frame.getY() && v.getDy() < 0) {
            v.setDy(frame.getY() + r - cY);
        } else if (v.getDy() + cY + r > frame.getMaxY() && v.getDy() > 0) {
            v.setDy(frame.getMaxY() - r - cY);
        }
    }

    /**
     * checkX checks if the x coordinate of a ball is inside the frame.
     * @param b the ball to check.
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkX(Ball b) {
        double x = b.getX();
        if (x - b.getSize() <= frame.getX() || x + b.getSize() >= frame.getMaxX()) {
            return false;
        }
        return true;
    }

    /**
     * checkY checks if the y coordinate of a ball is inside the frame.
     * @param b the ball to check.
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkY(Ball b) {
        double y = b.getY();
        if (y - b.getSize() <= frame.getY() || y + b.getSize() >= frame.getMaxY()) {
            return false;
        }
        return true;
    }
}
