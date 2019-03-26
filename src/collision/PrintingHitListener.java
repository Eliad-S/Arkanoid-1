package collision;

import geometry.Ball;
import geometry.Block;
/**
 * The type Printing hit listener.
 */
public class PrintingHitListener implements HitListener {
    /**
     * print how many times hitted.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A geometry.Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}