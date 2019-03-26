package game;

import biuoop.DrawSurface;
import constans.Constants;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score.getValue();
    }

    /**
     * draw the sprit on.
     *
     * @param surface the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        String str = "Score: " + Integer.toString(getScore());
        int x = Constants.SCORE_BLOCK_X_LOCATION;
        int y = Constants.SCORE_BLOCK_Y_LOCATION;
        int width = Constants.SCORE_BLOCK_WIDTH;
        int height = Constants.SCORE_BLOCK_HEIGHT;
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawText(Constants.SCORE_FONT_X, Constants.SCORE_FONT_Y, str, Constants.SCORE_FONT_SIZE);
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
