package geometry;

import biuoop.DrawSurface;
import blockdecorator.BlockDrawer;
import blockdecorator.BlockFillDrawer;
import collision.BlockRemover;
import collision.Collidable;
import collision.HitListener;
import game.BallRemover;
import game.Counter;
import game.GameLevel;
import sprite.HitNotifier;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the block class that has rectangle as member.
 */
public class Block implements Collidable, Sprite, HitNotifier, Cloneable {
    // members
    private Rectangle block;
    private Color color;
    private boolean isHitted = false;
    private int hitPoints;
    private List<HitListener> hitListener;
    private Map<Integer, List<BlockDrawer>> drawer = new HashMap<>();
    // Constractors.

    /**
     * constructors with rectangle.
     *
     * @param rec rectangle.
     */
    public Block(Rectangle rec) {
        this.block = rec;
        this.hitListener = new ArrayList<>();
    }

    /**
     * constructor that get point start left top, width, height and color.
     *
     * @param point  point start.
     * @param width  width rectangle.
     * @param height height of the rectangle.
     * @param color  color of the block.
     * @param b      the b
     */
    public Block(Point point, double width, double height, Color color, BlockRemover b) {
        this.block = new Rectangle(point, width, height);
        this.color = color;
        this.hitListener = new ArrayList<>();
        this.hitListener.add(b);
    }

    /**
     * constructor that get point start left top, width, height and color, and number of hits the block can get.
     *
     * @param point   point start.
     * @param width   width rectangle.
     * @param height  height of the rectangle.
     * @param color   color of the block.
     * @param numHits num of hits the block can be hitted.
     */
    public Block(Point point, double width, double height, Color color, int numHits) {
        this.block = new Rectangle(point, width, height);
        this.color = color;
        this.hitPoints = numHits;
        this.hitListener = new ArrayList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param point   the point
     * @param width   the width
     * @param height  the height
     * @param color   the color
     * @param numHits the num hits
     * @param b       the b
     */
    public Block(Point point, double width, double height, Color color, int numHits, BallRemover b) {
        this.block = new Rectangle(point, width, height);
        this.color = color;
        this.hitPoints = numHits;
        this.hitListener = new ArrayList<>();
        this.hitListener.add(b);
    }

    /**
     * constructor that get point start left top, width, height and color.
     *
     * @param x      point start x.
     * @param y      point start y.
     * @param width  width rectangle.
     * @param height height of the rectangle.
     * @param color  color of the block.
     * @param b      the b
     */
    public Block(double x, double y, double width, double height, Color color, BlockRemover b) {
        this.block = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hitListener = new ArrayList<>();
        this.hitListener.add(b);
    }

    /**
     * Instantiates a new Block.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.block = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hitListener = new ArrayList<>();
    }

    /**
     * Instantiates a new Block.
     *
     * @param x         the x
     * @param y         the y
     * @param width     the width
     * @param height    the height
     * @param color     the color
     * @param numOfHits the num of hits
     */
    public Block(double x, double y, double width, double height, Color color, int numOfHits) {
        this.block = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.hitListener = new ArrayList<>();
        hitPoints = numOfHits;
        List<BlockDrawer> drawerList = new ArrayList<>();
        drawerList.add(new BlockFillDrawer(color));
        this.drawer.put(-1, drawerList);
    }

    /**
     * Instantiates a new Block.
     *
     * @param x the x
     * @param y the y
     */
    public Block(double x, double y) {
        this.block = new Rectangle(new Point(x, y), 0, 0);
        this.hitListener = new ArrayList<>();
    }

    /**
     * set the number of hits the block can be hitted.
     *
     * @param num num of hits.
     */
    public void setHitPoints(int num) {
        this.hitPoints = num;
    }

    /**
     * Sets block remover.
     *
     * @param gameLevel the gameLevel
     */
    public void setBlockRemover(GameLevel gameLevel) {
        Counter counter = new Counter(this.hitPoints);
        BlockRemover b = new BlockRemover(gameLevel, counter);
        this.hitListener.add(b);
    }

    /**
     * draw the block on the game, and it's hits.
     *
     * @param surface the screen to draw on.
     */
    public void drawOn(DrawSurface surface) {

        List<BlockDrawer> blockDrawerList;
        if (drawer.containsKey(hitPoints) && hitPoints >= 0) {
            blockDrawerList = drawer.get(hitPoints);
        } else {
            blockDrawerList = drawer.get(-1);
        }
        if (blockDrawerList != null) {
            for (BlockDrawer b : blockDrawerList) {
                b.drawOn(surface, getCollisionRectangle());
            }
        }
    }

    /**
     * Add drawer.
     *
     * @param bd    the bd
     * @param index the index
     */
    public void addDrawer(BlockDrawer bd, Integer index) {
        if (drawer.containsKey(index)) {
            this.drawer.get(index).add(bd);
        } else {
            List<BlockDrawer> blockDrawerList = new ArrayList<>();
            blockDrawerList.add(bd);
            this.drawer.put(index, blockDrawerList);
        }
    }

    /**
     * get the start x value of the block.
     *
     * @return double x value.
     */
    public double getStartX() {
        return this.getCollisionRectangle().getStartPointX();
    }

    /**
     * get the end x value of the block.
     *
     * @return double x value.
     */
    public double getEndX() {
        return this.getCollisionRectangle().getEndPointX();
    }

    /**
     * get the start Y value of the block.
     *
     * @return double y value.
     */
    public double getStartY() {
        return this.getCollisionRectangle().getStartPointY();
    }

    /**
     * get the end Y value of the block.
     *
     * @return double end value.
     */
    public double getEndY() {
        return this.getCollisionRectangle().getEndPointY();
    }

    /**
     * set the block color.
     *
     * @param newColor new color of the block.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Darker the block.
     */
    public void darker() {
        this.color = this.color.darker();
    }

    /**
     * Setwidth.
     *
     * @param width the width
     */
    public void setwidth(double width) {
        this.getCollisionRectangle().setRecWidth(width);
    }

    /**
     * Set height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.getCollisionRectangle().setRecHeight(height);
    }

    /**
     * do nothing right now.
     *
     * @param dt the value.
     */
    public void timePassed(double dt) {
        return;
    }

    /**
     * get the rectangle member.
     *
     * @return rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return (this.block);
    }

    /**
     * add the block to the game by visual and bulbul.
     *
     * @param g the game played.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Instantiates a new Remove from gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * get the block color.
     *
     * @return color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * decrease the number of hits by one.
     */
    public void decreaseInCount() {
        if (hitPoints <= 0) {
            return;
        }
        hitPoints--;
    }

    /**
     * change the member hitted to be true.
     */
    public void hitTheBlock() {
        if (!isHitted) {
            isHitted = true;
        }
    }

    /**
     * Return a new geometry.Velocity after collison, by checking where the were the collision on the block.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point on the block.
     * @param currentVelocity the curren geometry.Velocity of an object.
     * @return the new velocity after collision based on where the collison heppened on the block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            this.notifyHit(hitter);
            return currentVelocity;
        }
        double velocityDX = currentVelocity.getDX();
        double velocityDY = currentVelocity.getDY();
        // Collided on the left or right side of the block.
        // Collided on the top or bottom side of the block.
        if (collisionPoint.getY() == this.block.getStartPointY()
                || collisionPoint.getY() == this.block.getEndPointY()) {
            if (collisionPoint.getX() <= this.block.getEndPointX()
                    && collisionPoint.getX() >= this.block.getStartPointX()) {
                velocityDY = -1 * velocityDY;
            }
        } else if (collisionPoint.getX() == this.block.getStartPointX()
                || collisionPoint.getX() == this.block.getEndPointX()) {
            // If the X and the Y hitted the geometry.Rectangle
            if (collisionPoint.getY() <= this.block.getEndPointY()
                    && collisionPoint.getY() >= this.block.getStartPointY()) {
                velocityDX = -1 * velocityDX;
            }
        }
        decreaseInCount();
        hitTheBlock();
        this.notifyHit(hitter);
        return new Velocity(velocityDX, velocityDY);
    }

    /**
     * notify hitted.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListener);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListener.add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListener.remove(hl);
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * clone of blocks.
     *
     * @return clon block.
     */
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
