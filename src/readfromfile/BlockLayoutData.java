package readfromfile;

/**
 * The type Rectangle data.
 */
public class BlockLayoutData {
    private double rowHeight;
    private double blocksStartX;
    private double blocksStartY;

    /**
     * Instantiates a new Rectangle data.
     *
     * @param rowHeight    the row height
     * @param blocksStartX the blocks start x
     * @param blocksStartY the blocks start y
     */
    public BlockLayoutData(double rowHeight, double blocksStartX, double blocksStartY) {
        this.rowHeight = rowHeight;
        this.blocksStartX = blocksStartX;
        this.blocksStartY = blocksStartY;
    }

    /**
     * Gets blocks start y.
     *
     * @return the blocks start y
     */
    public double getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * Sets blocks start y.
     *
     * @param blockY the block y
     */
    public void setBlocksStartY(double blockY) {
        this.blocksStartY = blockY;
    }

    /**
     * Gets blocks start x.
     *
     * @return the blocks start x
     */
    public double getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * Sets blocks start x.
     *
     * @param blockX the block x
     */
    public void setBlocksStartX(double blockX) {
        this.blocksStartX = blockX;
    }

    /**
     * Gets row height.
     *
     * @return the row height
     */
    public double getRowHeight() {
        return rowHeight;
    }

    /**
     * Sets row height.
     *
     * @param height the height
     */
    public void setRowHeight(double height) {
        this.rowHeight = height;
    }
}
