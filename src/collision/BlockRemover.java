package collision;

import game.Counter;
import game.GameLevel;
import geometry.Ball;
import geometry.Block;

/**
 * The type Block remover.
 */
// a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
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
        if (beingHit.getHitPoints() == 0) {
            gameLevel.removeCollidable(beingHit);
            gameLevel.removeSprite(beingHit);
            //decrease the count.
            this.remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
        }
    }
}