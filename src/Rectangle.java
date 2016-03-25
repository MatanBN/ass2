import biuoop.DrawSurface;
import java.awt.Color;
/**
 * Created by Matan on 23/03/2016.
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color c;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(int width, int height, Color c) {
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public Rectangle(int x, int y, int width, int height, Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public Rectangle(Point p, int width, int height, Color c) {
        this.x = (int) p.getX();
        this.y = (int) p.getY();
        this.width = width;
        this.height = height;
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxX() {
        return this.width + this.x;
    }

    public int getMaxY() {
        return this.height + this.y;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(this.c);
        d.drawRectangle(this.x, this.y, this.width, this.height);
    }
}
