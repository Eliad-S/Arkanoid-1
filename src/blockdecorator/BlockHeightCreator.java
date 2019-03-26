package blockdecorator;

import geometry.Block;

/**
 * The type Block height creator.
 */
public class BlockHeightCreator extends BlockDecorator {
    private int height;

    /**
     * Instantiates a new Block decorator.
     *
     * @param decorated the decorated
     * @param height    the height
     */
    public BlockHeightCreator(BlockCreator decorated, int height) {
        super(decorated);
        this.height = height;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = super.create(xpos, ypos);
        block.setHeight(height);
        return block;
    }
}
