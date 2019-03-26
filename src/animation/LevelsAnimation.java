package animation;

import biuoop.DrawSurface;

/**
 * The type Levels animation.
 */
public class LevelsAnimation implements Animation {
    /**
     * Do one frame.
     *
     * @param d  the surface to print on.
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

    }

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
