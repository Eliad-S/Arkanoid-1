package blockdecorator;

import geometry.Block;

/**
 * The type Block hit point creator.
 */
public class BlockHitPointCreator extends BlockDecorator {
    private Integer numOfHits;

    /**
     * Instantiates a new Block decorator.
     *
     * @param decorated the decorated
     * @param numOfHits the num of hits
     */
    public BlockHitPointCreator(BlockCreator decorated, Integer numOfHits) {
        super(decorated);
        this.numOfHits = numOfHits;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = super.create(xpos, ypos);
        block.setHitPoints(numOfHits);
        return block;
    }
}
