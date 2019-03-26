package blockdecorator;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * The interface Block drawer.
 */
public interface BlockDrawer {
    /**
     * Draw on.
     *
     * @param d         the d
     * @param rectangle the rectangle
     */
    void drawOn(DrawSurface d, Rectangle rectangle);
}
