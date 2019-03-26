package readfromfile;

import blockdecorator.BlockCreator;
import blockdecorator.BlockDrawerCreator;
import blockdecorator.BlockHeightCreator;
import blockdecorator.BlockHitPointCreator;
import blockdecorator.BlockWidthCreator;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Block creator factory.
 */
public class BlockCreatorFactory {
    /**
     * Decorate block block creator.
     *
     * @param block the block
     * @param key   the key
     * @param value the value
     * @return the block creator
     */
    public BlockCreator decorateBlock(BlockCreator block, String key, String value) {
        Utils utils = new Utils();
        // Decorate by width.
        if (key.equals("width")) {
            return new BlockWidthCreator(block, Integer.parseInt(value));
        } else if (key.equals("height")) {
            // Decorate by height.
            return new BlockHeightCreator(block, Integer.parseInt(value));
        } else if (key.equals("hit_points")) {
            // Decorate by hit_points.
            return new BlockHitPointCreator(block, Integer.parseInt(value));
        } else if (key.startsWith("fill") || key.startsWith("stroke")) {
            Integer hitPointsValueToAdd = -1;
            Boolean isFill = key.startsWith("fill");
            int dividerIndex = key.indexOf("-");
            // Get - separator index
            if (dividerIndex != -1) {
                hitPointsValueToAdd = Integer.parseInt(key.substring(dividerIndex + 1));
            }
            // Handle fill or stroke properties
            if (value.startsWith("image(")) {
                // Handle images
                Image image = utils.setImage(value);
                return new BlockDrawerCreator(block, image, hitPointsValueToAdd);
            } else if (value.startsWith("color(")) {
                // Handle colors
                Color color = utils.setColor(value);
                return new BlockDrawerCreator(block, color, isFill, hitPointsValueToAdd);
            }
        } else if (key.equals("") || key.equals(" ")) {
            return block;
        }

        // Unable to parse value
        throw new RuntimeException(String.format("Unable to parse file at property: %s", key));
    }
}
