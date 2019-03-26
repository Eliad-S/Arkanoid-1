package drawings;


import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Block;
import geometry.Circle;
import geometry.Line;
import sprite.Sprite;

import java.awt.Color;

/**
 * The type Target.
 */
public class Target implements Sprite {
    private Block block;

    /**
     * Instantiates a new Target.
     *
     * @param b the block
     */
    public Target(Block b) {
        this.block = b;

    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int middleX = (int) block.getCollisionRectangle().getMiddleX();
        int middleY = (int) block.getCollisionRectangle().getMiddleY();
        Line right = new Line(middleX + 20, middleY, middleX + 220, middleY);
        Line left = new Line(middleX - 20, middleY, middleX - 220, middleY);
        Line up = new Line(middleX, middleY - 20, middleX, middleY - 220);
        Line down = new Line(middleX, middleY + 20, middleX, middleY + 220);
        int r = 15;
        for (int i = 0; i < 15; i++) {
            Circle circle1 = new Circle(middleX, middleY, r, Color.RED);
            circle1.drawOn(d);
            r += 10;
        }
        right.drawOn(d);
        left.drawOn(d);
        up.drawOn(d);
        down.drawOn(d);
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
        g.addSprite(this);
    }
}
