package drawings;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Airplane.
 */
public class Airplane implements Sprite {
    private Point startPoint;

    /**
     * Instantiates a new Airplane.
     *
     * @param startPoint the start point
     */
    public Airplane(Point startPoint) {
        this.startPoint = startPoint;
    }

    /**
     * Instantiates a new Airplane.
     *
     * @param x the x
     * @param y the y
     */
    public Airplane(double x, double y) {
        this.startPoint = new Point(x, y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return startPoint.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return startPoint.getY();
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle airplane = new Rectangle(getX(), getY(), 200, 50);
        Rectangle tail = new Rectangle(getX() + 200 - 40, getY() - 50, 40, 50);
        Rectangle wing = new Rectangle(getX() + 100, getY() + 25, 20, 70);
        d.setColor(Color.gray);
        airplane.drawOn(d);
        tail.drawOn(d);
        airplane.drawRectangle(d, Color.BLACK);
        tail.drawRectangle(d, Color.BLACK);
        d.setColor(Color.gray);
        wing.drawOn(d);
        wing.drawRectangle(d, Color.BLACK);
    }

    /**
     * Move airplane.
     */
    public void moveAirplane() {
        double newX = getX() - 5;
        double newY = getY();
        if (newX <= -200) {
            this.startPoint = new Point(850, newY);
            return;
        }
        this.startPoint = new Point(newX, newY);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the value.
     */
    @Override
    public void timePassed(double dt) {
        moveAirplane();
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
