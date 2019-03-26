package blockdecorator;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Image;

/**
 * The type Block image drawer.
 */
public class BlockImageDrawer implements BlockDrawer {
    private Image image;

    /**
     * Instantiates a new Block image drawer.
     *
     * @param image the image
     */
    public BlockImageDrawer(Image image) {
        this.image = image;
    }

    /**
     * Draw on.
     *
     * @param d         the d
     * @param rectangle the rectangle
     */
    @Override
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        rectangle.drawImage(d, image);
    }
}
