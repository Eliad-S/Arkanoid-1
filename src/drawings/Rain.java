package drawings;

import biuoop.DrawSurface;
import constans.Constants;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Rain.
 */
public class Rain implements Sprite {
    private int startX;
    private int endX;
    private int startY;
    private Color color;

    /**
     * Instantiates a new Rain.
     *
     * @param start  the start
     * @param end    the end
     * @param startY the start y
     * @param color  the color
     */
    public Rain(int start, int end, int startY, Color color) {
        this.startX = start;
        this.endX = end;
        this.startY = startY;
        this.color = color;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int randomize = 0;
        for (int i = startX; i < endX; i += 15) {
            for (int j = startY; j < Constants.GUI_HEIGHT; j += 13) {
                d.setColor(this.color);
                if (randomize % 3 == 0) {
                    d.fillOval(i, j, 4, 25);
                }
                randomize += 1;
            }
        }
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
