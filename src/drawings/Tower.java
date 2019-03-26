package drawings;

import biuoop.DrawSurface;
import constans.Constants;
import game.GameLevel;
import geometry.Circle;
import geometry.Point;
import geometry.Rectangle;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Tower.
 */
public class Tower implements Sprite {
    private Point startPoint;
    private double width;

    /**
     * Instantiates a new Tower.
     *
     * @param p     the p
     * @param width the width
     */
    public Tower(Point p, double width) {
        this.startPoint = p;
        this.width = width;
    }

    /**
     * Instantiates a new Tower.
     *
     * @param x     the x
     * @param y     the y
     * @param width the width
     */
    public Tower(double x, double y, double width) {
        this.startPoint = new Point(x, y);
        this.width = width;
    }

    /**
     * Gets start x.
     *
     * @return the start x
     */
    public double getStartX() {
        return this.startPoint.getX();
    }

    /**
     * Gets start y.
     *
     * @return the start y
     */
    public double getStartY() {
        return this.startPoint.getY();
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Rectangle rectangle = new Rectangle(getStartX(), getStartY(), width,
                Constants.GUI_HEIGHT);
        d.setColor(Color.BLACK);
        rectangle.drawOn(d);
        d.setColor(Color.WHITE);
        double dy = (Constants.GUI_HEIGHT - getStartY() - Constants.LVL_4_WINDOWS_HEIGHT
                * Constants.LVL_4_NUM_WINDOWS_ROWS) / (Constants.LVL_4_NUM_WINDOWS_ROWS + 1);
        double dx = (width - Constants.LVL_4_NUM_WINDOWS_COLUMNS * Constants.LVL_4_WINDOWS_WIDTH)
                / (Constants.LVL_4_NUM_WINDOWS_COLUMNS + 1);
        //windows
        for (double i = dy + getStartY(); i < Constants.GUI_HEIGHT; i = i + dy + Constants.LVL_4_WINDOWS_HEIGHT) {
            for (double j = dx + getStartX(); j < getStartX() + width; j = j + dx + Constants.LVL_4_WINDOWS_WIDTH) {
                d.fillRectangle((int) j, (int) i, Constants.LVL_4_WINDOWS_WIDTH, Constants.LVL_4_WINDOWS_HEIGHT);
            }
        }
        //antenna
        int baseWidth = Constants.LVL_4_BASE_WIDTH;
        int baseHeight = Constants.LVL_4_BASE_HEIGHT;
        int baseX = (int) rectangle.getMiddleX() - baseWidth / 2;
        int baseY = (int) getStartY();
        int antennaWidth = Constants.LVL_4_ANTENNA_WIDTH;
        int antennaHeight = Constants.LVL_4_ANTENNA_HEIGHT;
        int antennaX = (baseX + baseWidth / 2) - antennaWidth / 2;
        int antennaY = baseY - baseHeight;
        d.setColor(Color.darkGray);
        d.fillRectangle(baseX, baseY - baseHeight, baseWidth, baseHeight);
        d.setColor(Color.GRAY);
        d.fillRectangle(antennaX, antennaY - antennaHeight, antennaWidth, antennaHeight);
        int r1 = 15;
        int x = antennaX + antennaWidth / 2;
        int y = antennaY - antennaHeight - r1 / 2;
        Color color = Color.ORANGE;
        for (int i = 0; i < 3; i++, r1 = r1 - 3 - i, color = color.darker()) {
            if (i == 0) {
                color = Color.ORANGE;
            }
            if (i == 1) {
                color = Color.RED.darker();
            }
            if (i == 2) {
                color = Color.WHITE;
            }
            Circle c1 = new Circle(x, y, r1, color);
            c1.fillCircle();
            c1.drawOn(d);
        }
    }

    /**
     * notify the sprite that time has passed.
     *  @param dt the value.
     */
    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * add the sprite to game.
     *
     * @param g the game to be added.
     */
    @Override
    public void addToGame(GameLevel g) {
        return;
    }
}
