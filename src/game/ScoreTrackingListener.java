package game;

import collision.HitListener;
import geometry.Ball;
import geometry.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Instantiates a new Score tracking listener.
     */
    public ScoreTrackingListener() {
        this.currentScore = new Counter(0);
    }

    /**
     * plus points.
     *
     * @param beingHit the being hit.
     * @param hitter   the hitter.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 0) {
            //incase a regular hit.
            currentScore.increase(5);
        } else {
            // destroy the block.
            currentScore.increase(10);
            //kill the hit listener.
            beingHit.removeHitListener(this);
        }
    }
}