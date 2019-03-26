package game;

import biuoop.DrawSurface;
import constans.Constants;
import sprite.Sprite;

/**
 * The type Level name.
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param levelName the level name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(
                Constants.LVL_NAME_X_LOCATION,
                Constants.LVL_NAME_Y_LOCATION,
                this.levelName,
                Constants.LVL_NAME_FONT_SIZE);
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
