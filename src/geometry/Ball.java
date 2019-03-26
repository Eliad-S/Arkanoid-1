package geometry;

import biuoop.DrawSurface;
import biuoop.GUI;
import collision.Collidable;
import collision.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.awt.Frame;
import java.util.List;

/**
 * The type geometry.Ball.
 *
 * @author Eyal Cohen. creating the object ball.
 */
public class Ball implements Sprite {
    // members.
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvi;
    private GUI gui;
    private Boolean isDtChanged = false;

    // constructor.

    /**
     * create ball with point,radius and color.
     *
     * @param center .
     * @param r      .
     * @param color  .
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constrctor.
     *
     * @param x     x value.
     * @param y     y value.
     * @param r     radius.
     * @param color color of the ball.
     * @param gui   gui of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GUI gui) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gui = gui;
    }

    /**
     * create ball with int x and y for point center, radius and color.
     *
     * @param x     .
     * @param y     .
     * @param r     .
     * @param color .
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * create ball with inputed int x,y, color and frame.
     *
     * @param x     .
     * @param y     .
     * @param r     .
     * @param color .
     * @param frame .
     */
    public Ball(int x, int y, int r, java.awt.Color color, Frame frame) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * create ball with inputed point center    , color and frame.
     *
     * @param center .
     * @param r      .
     * @param color  .
     * @param frame  .
     */
    public Ball(Point center, int r, java.awt.Color color, Frame frame) {
        this.center = center;
        this.color = color;
    }

    // accessors.

    /**
     * get the x value of the point.
     *
     * @return int x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get the y value of the point.
     *
     * @return int y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return the radius.
     *
     * @return int r
     */
    public int getSize() {
        return this.r;
    }

    /**
     * return the color of the ball.
     *
     * @return color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get the color of the ball.
     *
     * @param newColor .
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * add the ball to the visual game.
     *
     * @param g game enviorment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addBall(this);
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface get surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * when the game loop over, move the ball.
     *
     * @param dt the value.
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * set the ball velocity to a given velocity.
     *
     * @param v .
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the ball velocity to a given dx and dy.
     *
     * @param dx .
     * @param dy .
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * get the ball velocity.
     *
     * @return this.velocity velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set the game enviorment.
     *
     * @param game the game played.
     */
    public void setGameEnvi(GameEnvironment game) {
        this.gameEnvi = game;
    }

    /**
     * get the game enviorment.
     *
     * @return gameenviorment member.
     */
    public GameEnvironment getGameEnvi() {
        return this.gameEnvi;
    }

    /**
     * get the collidablelist size of the game.
     *
     * @return collidablelist size of the game.
     */
    public int getGameEnvironmentCollidableNum() {
        return this.gameEnvi.getCollidableList().size();
    }

    /**
     * get the collidablelist of the game.
     *
     * @return collidablelist of the game.
     */
    public List<Collidable> getCollidableList() {
        return this.gameEnvi.getCollidableList();
    }

    /**
     * return new point center base on the edge that the ball hitted on the collidable.
     *
     * @param collisionInfo1 point and collidable.
     * @return intersection new center base on the intersection
     */
    public Point newApllyToPoint(CollisionInfo collisionInfo1) {
        Point intersection = collisionInfo1.getCollisionP();
        double intersectionX = collisionInfo1.getCollisionP().getX();
        double intersectionY = collisionInfo1.getCollisionP().getY();
        String edge = collisionInfo1.getCollidableObj().getCollisionRectangle().intersectionEdge(intersection);
        if (edge.equals("left")) {
            return new Point(intersectionX - this.getSize(), intersectionY);
        } else if (edge.equals("top")) {
            return new Point(intersectionX, intersectionY - this.getSize());
        } else if (edge.equals("right")) {
            return new Point(intersectionX + this.getSize(), intersectionY);
        } else if (edge.equals("bottom")) {
            return new Point(intersectionX, intersectionY + this.getSize());
        }
        return intersection;
    }

    /**
     * Is ball in frame blocks boolean.
     *
     * @return the boolean
     */
    public boolean ballInFrameBlocks() {
        return gameEnvi.pointIsInFrameBlock(this.center);
    }

    /**
     * Move ball out of frame block.
     */
    public void moveBallOutOfFrameBlock() {
        String blockName = gameEnvi.inWhichBlockIsTheBall(this.center);
        double newX = this.getX();
        double newY = this.getY();
        if (blockName == null) {
            return;
        }
        if (blockName.equals("top")) {
            this.center = new Point(newX, newY + 10);
        }
        if (blockName.equals("left")) {
            this.center = new Point(newX + 10, newY);
        }
        if (blockName.equals("right")) {
            this.center = new Point(newX - 10, newY);
        }
    }

    /**
     * move ty ball by its velocity.
     *
     * @param dt the dt
     */
    public void moveOneStep(double dt) {
        if (!isDtChanged) {
            Velocity newVelocity = new Velocity(getVelocity().getDX() * dt, getVelocity().getDY() * dt);
            this.setVelocity(newVelocity);
            isDtChanged = true;
        }
        Line line = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo collisionInfo1 = this.gameEnvi.getClosestCollision(line);
        Point tempCenter;
        if (collisionInfo1 != null) {
            // using the hit method to get the new velocity base on the collision where the ball hitted the collidable.
            tempCenter = newApllyToPoint(collisionInfo1);
            this.setVelocity(collisionInfo1.getCollidableObj().hit(this,
                    collisionInfo1.getCollisionP(), this.getVelocity()));
            if (ballInFrameBlocks()) {
                moveBallOutOfFrameBlock();
                return;
            }
            if (gameEnvi.pointIsInAnyCollidable(tempCenter)) {
                this.setVelocity(this.getVelocity().getDX() * -1, this.getVelocity().getDY() * -1);
            }
            this.center = this.getVelocity().applyToPoint(this.center);
            //find the edge that the point is intersect with.
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
}
