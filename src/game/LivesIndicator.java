package game;

import biuoop.DrawSurface;
import constans.Constants;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param c the c
     */
    public LivesIndicator(Counter c) {
        this.lives = c;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives.getValue();
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        String str = "LIVES: " + Integer.toString(getLives());
        int x = Constants.LIVES_BLOCK_X_LOCATION;
        int y = Constants.LIVES_BLOCK_Y_LOCATION;
        int height = Constants.LIVES_BLOCK_HEIGHT;
        d.setColor(Color.BLACK);
        d.setColor(Color.BLACK);
        d.drawText(x, y, str, Constants.LIVES_FONT_SIZE);
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
