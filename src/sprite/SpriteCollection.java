package sprite;

import biuoop.DrawSurface;
import constans.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * class of list of sprites.
 */
public class SpriteCollection {
    // Members
    private List<Sprite> spriteList;

    /**
     * get the sprite List.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * add a sprite to the list.
     *
     * @param s sprite to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        double dt = (double) 1 / Constants.FRAMES_PER_SECOND;
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed(dt);
        }
    }

    /**
     * Remove sprite.
     *
     * @param s remove it.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d draw the sprites list.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}

