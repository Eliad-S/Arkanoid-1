package animation;

import background.Background;
import biuoop.DrawSurface;
import constans.Constants;
import drawings.Airplane;
import drawings.Cloud;
import drawings.HappySmiley;
import drawings.Moon;
import drawings.Sun;
import drawings.Tower;

import java.awt.Color;

/**
 * The type Winning screen.
 */
public class WinningScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param score the score
     */
    public WinningScreen(int score) {
        this.stop = false;
        this.score = score;
    }

    /**
     * Sets back ground.
     *
     * @return the back ground
     */
    public Background setBackGround() {
        Background background = new Background(Color.BLUE);
        background.addSprite(new Tower(100, 400, 100));
        background.addSprite(new Tower(600, 400, 100));
        background.addSprite(new Sun(50, 50, 200));
        background.addSprite(new Moon(650, 100, 60, Color.blue));
        background.addSprite(new Airplane(250, 100));
        background.addSprite(new Cloud(250, 300));
        background.addSprite(new Cloud(700, 150));
        background.addSprite(new HappySmiley(Constants.GUI_WIDTH / 2 + 10, 500, 80));
        return background;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Background background = setBackGround();
        background.drawOn(d);
        String winner = "- You Win! -";
        String yourScore = "Your score is: " + Integer.toString(score);
        d.setColor(Color.ORANGE);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2 - 50, winner, 60);
        d.drawText(d.getWidth() / 4 - 20, d.getHeight() / 2 + 40, yourScore, 60);
    }

    /**
     * Returns whether the game should be stopped.
     *
     * @return whether the game should be stopped.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
