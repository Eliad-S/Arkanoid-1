package background;

import biuoop.DrawSurface;
import constans.Constants;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Background.
 */
public class Background implements Sprite {
    private List<Sprite> spriteList;
    private Color color;
    private Image image;
    private String path;

    /**
     * Instantiates a new Background.
     *
     * @param color the color
     */
    public Background(Color color) {
        this.spriteList = new ArrayList<>();
        this.color = color;
        this.image = null;
        this.path = null;
    }

    /**
     * Instantiates a new Background.
     *
     * @param image the image
     */
    public Background(Image image) {
        this.spriteList = new ArrayList<>();
        this.image = image;
        this.color = null;
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (image == null) {
            d.setColor(color);
            d.fillRectangle(0, 0, Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
            for (Sprite sprite : spriteList) {
                sprite.drawOn(d);
            }
        } else {
// Draw the image on a DrawSurface
            d.drawImage(0, 0, image);
        }
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Instantiates a new Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the speed.
     */
    @Override
    public void timePassed(double dt) {
        for (Sprite sprite : spriteList) {
            sprite.timePassed(dt);
        }
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
