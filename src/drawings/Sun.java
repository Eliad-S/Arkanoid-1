package drawings;

import biuoop.DrawSurface;
import constans.Constants;
import game.GameLevel;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Sun.
 */
public class Sun implements Sprite {
    private Point center;
    private double yLimit;

    /**
     * Instantiates a new Sun.
     *
     * @param p      the center.
     * @param yLimit the x limit
     */
    public Sun(Point p, double yLimit) {
        this.center = p;
        this.yLimit = yLimit;
    }

    /**
     * Instantiates a new Sun.
     *
     * @param x      the x
     * @param y      the y
     * @param yLimit the y limit
     */
    public Sun(double x, double y, double yLimit) {
        this.center = new Point(x, y);
        this.yLimit = yLimit;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int r = 50;
        Circle outerCircle = new Circle(center, r, Color.orange);
        Circle innerCircle = new Circle(center, r - 10, Color.YELLOW.brighter());
        outerCircle.fillCircle();
        innerCircle.fillCircle();
        d.setColor(Color.YELLOW);
        for (int i = 0; i < Constants.GUI_WIDTH; i += 10) {
            Point tempPoint = new Point(i, yLimit);
            Line line = new Line(center, tempPoint);
            line.drawOn(d);
        }
        outerCircle.drawOn(d);
        innerCircle.drawOn(d);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the value.
     */
    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * add the sprite to game.
     *
     * @param g the game to be added.
     */
    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
