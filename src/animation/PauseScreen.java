package animation;

import background.Background;
import biuoop.DrawSurface;
import drawings.Airplane;
import drawings.Tower;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }
    /**
     * do one frame.
     *
     * @param d  the surface to print on.
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //creating new background;
        Background b1 = new Background(Color.blue);
        //add sprites to the list of sprites.
        Tower twin1 = new Tower(40, 350, 80);
        Tower twin2 = new Tower(130, 350, 80);
        b1.addSprite(twin1);
        b1.addSprite(twin2);
        Airplane airplane = new Airplane(400, 450);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 - 20, "pause screen", 32);
        b1.drawOn(d);
        airplane.drawOn(d);
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