package tests; /**
 * @author Eyal Cohen
 * check the object ball.
 */
import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Point;

/**
 * test the balls.
 */
public class BallsTest1 {
    /**
     * check the balls.
     * @param args nothing really.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Balls Test 1", 400, 400);
        DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(new Point(100, 100), 30, java.awt.Color.RED);
        Ball b2 = new Ball(new Point(100, 150), 10, java.awt.Color.BLUE);
        Ball b3 = new Ball(new Point(80, 249), 50, java.awt.Color.GREEN);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);

        gui.show(d);
    }
}
