package blockdecorator;

import geometry.Block;

/**
 * The type Block decorator.
 */
public abstract class BlockDecorator implements BlockCreator {
    private BlockCreator decorated;

    /**
     * Instantiates a new Block decorator.
     *
     * @param decorated the decorated
     */
    protected BlockDecorator(BlockCreator decorated) {
        this.decorated = decorated;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return this.decorated.create(xpos, ypos);
    }
}
