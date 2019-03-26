package animation;

import background.Background;
import biuoop.DrawSurface;
import constans.Constants;
import drawings.Cloud;
import drawings.SadSmiley;
import drawings.Tower;

import java.awt.Color;

/**
 * The type Game over screen.
 */
public class GameOverScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Instantiates a new Pause screen.
     *
     * @param score the score
     */
    public GameOverScreen(int score) {
        this.stop = false;
        this.score = score;
    }

    /**
     * Sets back ground.
     *
     * @return the back ground
     */
    public Background setBackGround() {
        Background background = new Background(Color.GREEN.darker());
        background.addSprite(new Tower(100, 400, 100));
        background.addSprite(new Tower(600, 400, 100));
        background.addSprite(new SadSmiley(Constants.GUI_WIDTH / 2, 150, 80));
        background.addSprite(new Cloud(250, 300));
        background.addSprite(new Cloud(700, 150));
        return background;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt the dt value.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Background background = setBackGround();
        background.drawOn(d);
        String gameOver = "- GAME OVER -";
        String yourScore = "Your score is: " + Integer.toString(score);
        String exit = "Press SPACE to exit.";
        d.setColor(Color.YELLOW);
        d.drawText(d.getWidth() / 4 - 20, d.getHeight() / 2 - 20, gameOver, 60);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2 + 60, yourScore, 60);
        d.drawText(d.getWidth() / 4 - 50, d.getHeight() / 2 + 150, exit, 60);
//        if (  {
//            this.stop = true;
//        }
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