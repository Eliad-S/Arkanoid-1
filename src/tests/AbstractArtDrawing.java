package tests;

import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

import java.util.Random;
import java.awt.Color;

/**
 * @author Eyal Cohen screen that shown lines intersection Mission 2.
 */
public class AbstractArtDrawing {
    /**
     * draw the lines randomly.
     */
    public void drawRandomLines() {
        Point middle, intersect;
        int r = 3;
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random lines intersection", 400, 300);
        Line[] lines = new Line[10];
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            d.setColor(Color.BLACK);
            lines[i] = new Line(x1, y1, x2, y2);
            d.drawLine(x1, y1, x2, y2);
            d.setColor(Color.BLUE);
            middle = lines[i].middle();
            d.fillCircle((int) middle.getX(), (int) middle.getY(), r);
        }
        d.setColor(Color.RED);
        for (int i = 0; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    intersect = lines[i].intersectionWith(lines[j]);
                    d.fillCircle((int) intersect.getX(), (int) intersect.getY(), r);
                }
            }
        }
        gui.show(d);
    }
    /**
     * create the screen and show the intercections.
     * @param args .
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}
