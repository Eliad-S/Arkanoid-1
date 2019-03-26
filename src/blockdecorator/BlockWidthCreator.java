package blockdecorator;

import geometry.Block;

/**
 * The type Block width creator.
 */
public class BlockWidthCreator extends BlockDecorator {
    private double width;

    /**
     * Instantiates a new Block creator.
     *
     * @param decorated the decorated
     * @param width     the width
     */
    public BlockWidthCreator(BlockCreator decorated, int width) {
        super(decorated);
        this.width = width;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = super.create(xpos, ypos);
        block.setwidth(width);
        return block;
    }
}
