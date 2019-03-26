package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *  @param d  the surface to print on.
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    boolean shouldStop();
}
