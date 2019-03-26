package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private Point center; //the center of the ball
    private int radius; // the radius
    private Color color; // the color
    private boolean needToFill = false;

    /**
     * constructor.
     * has a default velocity that the user can change with "set velocity" method
     *
     * @param center point
     * @param r      radius
     * @param color  color
     */
    public Circle(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Instantiates a new Circle.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Circle(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    // accessors

    /**
     * return the x coordinate of the ball's circle point.
     *
     * @return x x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return the y coordinate of the ball's circle point.
     *
     * @return y y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the ball's radius.
     *
     * @return radius size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * return the ball's color.
     *
     * @return color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        // set the inside color of the ball
        surface.setColor(this.getColor());
        if (needToFill) {
            surface.fillCircle(this.getX(), this.getY(), this.getSize());
        } else {
            surface.drawCircle(this.getX(), this.getY(), this.getSize());
        }
    }

    /**
     * change to Fill circle method.
     */
    public void fillCircle() {
        this.needToFill = true;
    }

    /**
     * Draw circle.
     */
    public void drawCircle() {
        this.needToFill = false;
    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setColor(Color c) {
        this.color = c;
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
        g.addSprite(this);
    }

}
