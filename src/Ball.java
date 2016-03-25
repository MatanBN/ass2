import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Ball class has a center Point, a radius and a color, the class has method to draw the ball.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class Ball {
    private Point center; //The center point of the ball.
    private int radius; // The radius of the ball.
    private Color color; // The color of the blue.
    private Velocity v; // The velocity of the ball.
    private Rectangle boundary; // The boundary of the ball.

    /**
     * Constructor to create the ball.
     *
     * @param p     a Point which will be the center of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point p, int r, java.awt.Color color) {
        this.center = p;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor to create the ball.
     *
     * @param x     the x center of the ball.
     * @param y     the y center of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor to create the ball.
     *
     * @param x     a Point which will be the center of the ball.
     * @param y     a Point which will be the center of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     * @param bound The blocking rectangle of the circle.
     */
    public Ball(int x, int y, int r, java.awt.Color color, Rectangle bound) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.boundary = bound;
    }

    /**
     * getX method returns the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * getY method returns the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * getSize method returns the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor method returns the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn method draws the ball on a given surface.
     *
     * @param surface the surface to draw the balls on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.drawCircle(getX(), getY(), radius);
        surface.fillCircle(getX(), getY(), radius);
    }

    /**
     * setVelocity sets the balls velocity.
     *
     * @param newV the velocity to set.
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }

    /**
     * setVelocity sets the balls velocity.
     *
     * @param dx the velocity of the x plane.
     * @param dy the velocity of the y plane.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getVelocity gets the balls velocity.
     *
     * @return the balls velocity.
     */
    public Velocity getVelocity() {
        return v;
    }

    /**
     * moveOneStep Changes the center of the ball according to the balls velocity.
     */
    public void moveOneStep() {
        if (v != null) {
        	double cX = this.center.getX();
        	double cY = this.center.getY();
            if (!checkX()) {
            	v.setDx(-v.getDx());
            	if (!checkY()) {
            		v.setDy(-v.getDy());
            	}
            	this.center = v.applyToPoint(this.center);
            }
            else if (!checkY()) {
                v.setDy(-v.getDy());
                this.center = v.applyToPoint(this.center);
            }
            else if (this.v.getDx() + cX - radius < boundary.getX() && this.v.getDx() < 0) {
            	Velocity brakeVelocity = new Velocity(boundary.getX() - cX + radius, v.getDy());
            	if (v.getDy() + cY - radius < boundary.getY()) {
            		brakeVelocity.setDy(boundary.getY() + radius - cY);
            	}
            	else if (v.getDy() + cY + radius > boundary.getMaxY()) {
            		brakeVelocity.setDy(boundary.getMaxY() - radius - cY);
            	}
            	
            	this.center = brakeVelocity.applyToPoint(this.center);
            }
            else if (v.getDx() + cX + radius > boundary.getMaxX() && this.v.getDx() > 0) {
            	Velocity brakeVelocity = new Velocity(boundary.getMaxX() - radius - cX, v.getDy());
            	if (v.getDy() + cY - radius < boundary.getY()) {
            		brakeVelocity.setDy(boundary.getY() + radius - cY);
            	}
            	else if (v.getDy() + cY + radius > boundary.getMaxY()) {
            		brakeVelocity.setDy(boundary.getMaxY() - radius - cY);
            	}
            	this.center = brakeVelocity.applyToPoint(this.center);
            }
            else if (v.getDy() + cY - radius < boundary.getY() && v.getDy() < 0) {
            	Velocity brakeVelocity = new Velocity(v.getDx(), boundary.getY() + radius - cY);
            	this.center = brakeVelocity.applyToPoint(this.center);
            }
            else if (v.getDy() + cY + radius > boundary.getMaxY() && v.getDy() > 0) {
            	Velocity brakeVelocity = new Velocity(v.getDx(), boundary.getMaxY() - radius - cY);
            	this.center = brakeVelocity.applyToPoint(this.center);
            }
            else {
            	this.center = v.applyToPoint(this.center);
            }
        }
    }

    /**
     * checkX checks if the x coordinate is in the bounds of the DrawSurface.
     *
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkX() {
        double x = center.getX();
        if (x <= boundary.getX() || x - radius <= boundary.getX() || x >= boundary.getMaxX() ||
                x + radius >= boundary.getMaxX()) {
            return false;
        }
        return true;
    }

    /**
     * checkY checks if the y coordinate is in the bounds of the DrawSurface.
     *
     * @return true if it is in the bounds, otherwise false..
     */
    public boolean checkY() {
        double y = center.getY();
        if (y <= boundary.getY() || y - radius <= boundary.getY() || y >= boundary.getMaxY() ||
                y + radius >= boundary.getMaxY()) {
            return false;
        }
        return true;
    }

	public Rectangle getBoundary() {
		return boundary;
	}
    
    
}
