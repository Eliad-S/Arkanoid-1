package blockdecorator;

import geometry.Block;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Block drawer creator.
 */
public class BlockDrawerCreator extends BlockDecorator {
    private Integer indexOfHit;
    private BlockDrawer blockDrawer;

    /**
     * Instantiates a new Block drawer creator.
     *
     * @param decorated  the decorated
     * @param color      the color
     * @param isFill     the is fill
     * @param indexOfHit the index of hit
     */
    public BlockDrawerCreator(BlockCreator decorated, Color color, boolean isFill, Integer indexOfHit) {
        super(decorated);
        this.indexOfHit = indexOfHit;
        if (isFill) {
            this.blockDrawer = new BlockFillDrawer(color);
        } else {
            this.blockDrawer = new BlockFrameDrawer(color);
        }
    }

    /**
     * Instantiates a new Block drawer creator.
     *
     * @param decorated  the decorated
     * @param image      the image
     * @param indexOfHit the index of hit
     */
    public BlockDrawerCreator(BlockCreator decorated, Image image, Integer indexOfHit) {
        super(decorated);
        this.blockDrawer = new BlockImageDrawer(image);
        this.indexOfHit = indexOfHit;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Block block = super.create(xpos, ypos);
        block.addDrawer(blockDrawer, indexOfHit);
        return block;
    }
}
