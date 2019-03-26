package drawings;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Circle;
import geometry.Point;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Moon.
 */
public class Moon implements Sprite {
    private Point center;
    private int size;
    private Color backgroundColor;

    /**
     * Instantiates a new Moon.
     *
     * @param center the center
     * @param size   the size
     * @param color  the color
     */
    public Moon(Point center, int size, Color color) {
        this.center = center;
        this.size = size;
        this.backgroundColor = color;
    }

    /**
     * Instantiates a new Moon.
     *
     * @param x     the x
     * @param y     the y
     * @param size  the size
     * @param color the color
     */
    public Moon(int x, int y, int size, Color color) {
        this.center = new Point(x, y);
        this.size = size;
        this.backgroundColor = color;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Circle moon = new Circle(center, size, Color.WHITE);
        Point invsibleCirclePoint = new Point(center.getX() - 20, center.getY() - 5);
        Circle invisibleCircle = new Circle(invsibleCirclePoint, size - 5, backgroundColor);
        moon.fillCircle();
        invisibleCircle.fillCircle();
        moon.drawOn(d);
        invisibleCircle.drawOn(d);
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
