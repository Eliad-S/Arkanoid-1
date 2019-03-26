package readfromfile;


import geometry.Block;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Create block layout.
 */
public class CreateBlockLayout {
    private List<String> rows;
    private double xPos;
    private double yPos;
    private double rowHeight;
    private BlocksFromSymbolsFactory symbolsFactory;

    /**
     * Instantiates a new Create block layout.
     *
     * @param blockLayoutData the block layout data
     * @param rows            the rows
     * @param symbolsFactory  the symbols factory
     */
    public CreateBlockLayout(
            BlockLayoutData blockLayoutData, List<String> rows, BlocksFromSymbolsFactory symbolsFactory) {
        this.xPos = blockLayoutData.getBlocksStartX();
        this.yPos = blockLayoutData.getBlocksStartY();
        this.rowHeight = blockLayoutData.getRowHeight();
        this.rows = rows;
        this.symbolsFactory = symbolsFactory;
    }

    /**
     * inilize game blocks and get them.
     *
     * @return the list
     */
    public List<Block> getGameBlocks() {
        List<Block> gameBlocks = new ArrayList<>();
        int blocksY = (int) this.yPos;
        // inilize each row.
        for (String currentRow : rows) {
            int blocksX = (int) this.xPos;
            //inilize each block.
            for (int i = 0; i < currentRow.length(); i++) {
                String symbol = String.valueOf(currentRow.charAt(i));

                // inilize block and promote x Block value
                if (symbolsFactory.isBlockSymbol(symbol)) {
                    Block block = symbolsFactory.getBlock(symbol, blocksX, blocksY);
                    gameBlocks.add(block);
                    blocksX += (int) block.getCollisionRectangle().getWidth();
                } else if (symbolsFactory.isSpaceSymbol(symbol)) {
                    // promote x blocks by spaces.
                    blocksX += symbolsFactory.getSpaceWidth(symbol);
                } else {
                    throw new RuntimeException("cannot rear from symbol" + symbol + " from symbole factory");
                }
            }
            blocksY += this.rowHeight;
        }
        return gameBlocks;
    }
}
