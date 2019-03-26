package animation;

import biuoop.DrawSurface;
import constans.Constants;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private int countFrom;
    private double numOfSeconds;
    private SpriteCollection gameScreen;
    private double originalCountFrom;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.originalCountFrom = countFrom;
        this.stop = false;
    }

    /**
     * do one itteration.
     *  @param d  the surface to print on.
     * @param dt the surface to print on.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        double watingTime = numOfSeconds * 1000 / (originalCountFrom);
        int width = Constants.GUI_WIDTH / 2;
        String print = Integer.toString(countFrom);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW);
        sleeper.sleepFor((long) watingTime);
        if (this.countFrom == -1) {
            this.stop = true;
        }
        if (this.countFrom == 0) {
            print = "GO!";
            width -= 60;
        }
        d.drawText(width, Constants.GUI_HEIGHT / 2, print, 100);
        countFrom--;
    }

    /**
     * return wether to stop.
     *
     * @return boolean if to stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}