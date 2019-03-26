package blockdecorator;

import geometry.Block;

/**
 * The type Defult block.
 */
public class DefultBlock implements BlockCreator {
    /**
     * Instantiates a new Defult block.
     */
    public DefultBlock() {
    }

    /**
     * Create block.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    @Override
    public Block create(int xpos, int ypos) {
        return new Block(xpos, ypos);
    }
}
