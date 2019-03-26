package sprite;

import game.GameLevel;
import biuoop.DrawSurface;

/**
 * sprite implenet, all the visual objects.
 */
public interface Sprite {
    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    void timePassed(double dt);

    /**
     * add the sprite to game.
     *
     * @param g the game to be added.
     */
    void addToGame(GameLevel g);
}
