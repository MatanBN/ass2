/**
 * The Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor to create the ball's velocity.
     * @param dx the speed in the x plane.
     * @param dy the speed in the y plane.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed calculates the dx and dy parameters by angle and speed
     * that are given.
     * @param angle the angle of the ball's movement.
     * @param speed the speed of the ball.
     * @return the Velocity created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p The current coordinates.
     * @return the updated coordinates.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * setDx sets the ball's dx attribute.
     * @param newDx the new x speed.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * setDy sets the ball's dy attribute.
     * @param newDy is the y speed.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * getDx returns the ball's dx attribute.
     * @return the x speed.
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy returns the ball's dy attribute.
     * @return the y speed.
     */
    public double getDy() {
        return dy;
    }
}
