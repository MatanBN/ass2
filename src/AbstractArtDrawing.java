import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The AbstractArtDrawing class draws 10 lines with blue circles in their middle and red circles on each intersection.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
public class AbstractArtDrawing {
    /**
     * drawPainting method draws 10 random lines with blue circles in their middle and red circles on each intersection.
     */
    public void drawPainting() {
        // Create a window with the title "Pollock Painting"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Pollock Painting", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        // Create an array for the 10 lines.
        Line[] lines = new Line[10];
        // The loop creates 10 random lines and draws them.
        for (int i = 0; i < 10; ++i) {
            lines[i] = generateRandomLine(d);
            drawLine(lines[i], d);
        }
        drawIntercectionPoints(lines, d);
        gui.show(d);
    }

    /**
     * drawIntercectionPoints method draws all of the intersection points between the lines given to it.
     * @param lines the array of lines that can intersect.
     * @param d the DrawSurface the draw the intersection points on.
     */
    public void drawIntercectionPoints(Line[] lines, DrawSurface d) {
        // The double for loop draws a red circle around each intersection.
        for (int i = 0; i < 10; ++i) {
            for (int j = i + 1; j < 10; j++) {
                // Get the intersection point.
                Point intersectPoint = lines[i].intersectionWith(lines[j]);
                // Check if there was an intersection point around the segment.
                if (intersectPoint != null) {
                    drawCircle(intersectPoint, Color.red, 3, d);
                }
            }
        }
    }

    /**
     * generateRandomLine method creates a random line.
     * @param d the DrawSurface to create the line on.
     * @return The random line.
     */
    public Line generateRandomLine(DrawSurface d) {
        Random rand = new Random();
        return new Line(rand.nextInt(d.getWidth()) + 1, rand.nextInt(d.getHeight()) + 1, rand.nextInt(d.getWidth()) + 1,
                rand.nextInt(d.getHeight()) + 1);
    }

    /**
     * drawLine method draws a line and a blue circle in the line middle point.
     * @param l the Line to draw.
     * @param d the DrawSurface to draw on.
     */
    void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.black);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
        drawCircle(l.middle(), Color.blue, 3, d);
    }

    /**
     * drawCircle method draws a circle.
     * @param p the coordinates to draw the circle.
     * @param c the color to draw the circle.
     * @param radius the radius of the circle to draw.
     * @param d the DrawSurface to draw the circle on.
     */
    void drawCircle(Point p, Color c, int radius, DrawSurface d) {
        d.setColor(c);
        d.drawCircle((int) p.getX(), (int) p.getY(), radius);
        d.fillCircle((int) p.getX(), (int) p.getY(), radius);
    }

    /**
     * The main method.
     * @param args the input from command line.
     */
    public static void main(String[] args) {
        AbstractArtDrawing painting = new AbstractArtDrawing();
        painting.drawPainting();
    }

}
