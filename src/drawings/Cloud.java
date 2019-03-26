package drawings;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Circle;
import geometry.Point;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Cloud.
 */
public class Cloud implements Sprite {
    private Point center;

    /**
     * Instantiates a new Cloud.
     *
     * @param center the center
     */
    public Cloud(Point center) {
        this.center = center;
    }

    /**
     * Instantiates a new Cloud.
     *
     * @param x the x
     * @param y the y
     */
    public Cloud(double x, double y) {
        this.center = new Point(x, y);
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color color = Color.LIGHT_GRAY;
        double x = center.getX();
        double y = center.getY();
        int r = 25;
        Rain rain = new Rain((int) x - r - 5, (int) x + r + 15, (int) y + r, Color.GRAY);
        Circle middle = new Circle(x, y, r, color);
        Circle right = new Circle(x + r + 5, y + 6, r - 2, color.GRAY.brighter());
        Circle left = new Circle(x - r - 5, y + 10, r - 3, color.LIGHT_GRAY);
        Circle downLeft = new Circle(x - r, y + r, r - 6, color);
        Circle downRight = new Circle(x + 12, y + r, r, color.GRAY.brighter());
        rain.drawOn(d);
        middle.fillCircle();
        right.fillCircle();
        left.fillCircle();
        downLeft.fillCircle();
        downRight.fillCircle();
        left.drawOn(d);
        middle.drawOn(d);
        right.drawOn(d);
        downLeft.drawOn(d);
        downRight.drawOn(d);
    }

    /**
     * notify the sprite that time has passed.
     *  @param dt the value.
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
