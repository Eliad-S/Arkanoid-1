package drawings;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Sad smiley.
 */
public class SadSmiley implements Sprite {

    private Point center;
    private int size;

    /**
     * Instantiates a new Sad smiley.
     *
     * @param center the center
     * @param size   the size
     */
    public SadSmiley(Point center, int size) {
        this.center = center;
        this.size = size;
    }

    /**
     * Instantiates a new Sad smiley.
     *
     * @param x    the x
     * @param y    the y
     * @param size the size
     */
    public SadSmiley(int x, int y, int size) {
        this.size = size;
        this.center = new Point(x, y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillCircle(getX(), getY(), getSize());
        d.setColor(Color.WHITE);
        d.fillOval(getX() + 20, getY() - 40, 30, 40);
        d.fillOval(getX() - 45, getY() - 40, 30, 40);
        d.setColor(Color.BLACK);
        d.fillOval(getX() + 27, getY() - 30, 15, 20);
        d.fillOval(getX() - 37, getY() - 30, 15, 20);
        d.drawOval(getX() - 45, getY() + 25, 100, 30);
        d.setColor(Color.YELLOW);
        d.fillOval(getX() - 45, getY() + 26, 100, 30);
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
