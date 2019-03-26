package blockdecorator;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block fill drawer.
 */
public class BlockFillDrawer implements BlockDrawer {
    private Color color;

    /**
     * Instantiates a new Block fill drawer.
     *
     * @param color the color
     */
    public BlockFillDrawer(Color color) {
        this.color = color;
    }

    /**
     * Draw on.
     *
     * @param d         the d
     * @param rectangle the rectangle
     */
    @Override
    public void drawOn(DrawSurface d, Rectangle rectangle) {
        rectangle.fillRectangle(d, this.color);
    }
}
