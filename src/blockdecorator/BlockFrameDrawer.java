package blockdecorator;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * The type Block frame drawer.
 */
public class BlockFrameDrawer implements BlockDrawer {
    private Color color;

    /**
     * Instantiates a new Block fill drawer.
     *
     * @param color the color
     */
    public BlockFrameDrawer(Color color) {
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
        rectangle.drawRectangle(d, this.color);
    }
}

