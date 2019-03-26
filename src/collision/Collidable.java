package collision;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * interface of collidable objects like block and paddle.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point on the rectanlge.
     * @param currentVelocity the current velocity.
     * @return the velocity after hitted.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
