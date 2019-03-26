package collision;

import game.GameLevel;
import geometry.Ball;
import geometry.Block;

/**
 * The type Block hit darker.
 */
public class BlockHitDarker implements HitListener {
    private GameLevel gameLevel;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel the gameLevel
     */
    public BlockHitDarker(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     *
     * @param beingHit the being hit.
     * @param hitter   the hitter.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.darker();
    }
}