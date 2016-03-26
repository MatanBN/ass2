import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The Rectangle class has two coordinates, a width, a height and a color.
 * The Rectangle has methods to calculate its area and to draw the rectangle.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class Rectangle {
    private int x; // The x coordinate of the rectangle.
    private int y; // The y coordinate of the rectangle.
    private int width; // The width of the rectangle.
    private int height; // The height of the rectangle
    private Color color; // The color of the rectangle

    /**
     * Rectangle is the constructor and creates the rectangle with x and y equal zero and initialize the width and
     * height according to the user parameters.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     */
    public Rectangle(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

    /**
     * Rectangle is the constructor and creates the rectangle with x and y equal zero and initialize the width and
     * height according to the user parameters.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(int width, int height, Color c) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * Rectangle is the constructor and creates the rectangle according to the user parameters.
     * @param x the x coordinate to start the rectangle from.
     * @param y the y coordinate to start the rectnagle from.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(int x, int y, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * Rectangle is the constructor and creates the rectangle according to the user parameters.
     * @param p the x,y coordinates to start the rectangle from.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(Point p, int width, int height, Color c) {
        this.x = (int) p.getX();
        this.y = (int) p.getY();
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * getX method returns the x coordinate that the rectangle starts from.
     * @return the x coordinate that the rectangle start from.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY method returns the y coordinate that the rectangle starts from.
     * @return the y coordinate that the rectangle start from.
     */
    public int getY() {
        return y;
    }

    /**
     * getWidth method returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight method returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * getMaxX method returns the maximum x coordinate that the rectangle reaches.
     * @return the maximum x coordinate that the rectangle reaches.
     */
    public int getMaxX() {
        return this.width + this.x;
    }

    /**
     * getMaxY method returns the maximum y coordinate that the rectangle reaches.
     * @return the maximum y coordinate that the rectangle reaches.
     */
    public int getMaxY() {
        return this.height + this.y;
    }

    /**
     * getArea method returns the area of the rectangle.
     * @return the area of the rectangle.
     */
    public int getArea() {
        return this.height * this.width;
    }

    /**
     * setColor method sets the color of the rectangle.
     * @param c the desired color for the rectangle.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * drawOn method draws the rectangle.
     * @param d the DrawSurface to draw the rectangle on.
     * @throws NullPointerException if the color hasn't been initialized.
     */
    public void drawOn(DrawSurface d) throws NullPointerException {
        try {
            d.setColor(this.color);
            d.drawRectangle(this.x, this.y, this.width, this.height);
        } catch (NullPointerException e) {
            System.out.println("You must set a color for the rectangle in order to draw it.");
        }
    }
}
