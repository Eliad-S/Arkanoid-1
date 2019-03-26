package collision;

import geometry.Point;

/**
 * get the collision Info, point and collidable.
 */
public class CollisionInfo {

    // Members:
    private Point collisionP;
    private Collidable collidableObj;

    // Constructor:

    /**
     * constructor wi collidable and point.
     *
     * @param pointCollision point.
     * @param collidable     collision.
     */
    public CollisionInfo(Point pointCollision, Collidable collidable) {
        this.collisionP = pointCollision;
        this.collidableObj = collidable;
    }

    /**
     * reture the collidable.
     *
     * @return the collidable
     */
    public Collidable getCollidableObj() {
        return this.collidableObj;
    }

    /**
     * return the point collision.
     *
     * @return the point collision.
     */
    public Point getCollisionP() {
        return this.collisionP;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the point hitted.
     */
    public Point collisionPoint() {
        return this.collisionP;
    }


    /**
     * the collidable object involved in the collision.
     *
     * @return the collidable.
     */
    public Collidable collisionObject() {
        return this.collidableObj;
    }
}
