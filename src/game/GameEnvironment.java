package game;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Ball;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * class that is the game enviroment, that has the collidable list.
 */
public class GameEnvironment {
    // Members
    private List<Collidable> collidableList;
    private List<Ball> ballList;

    /**
     * create a new game enviorment with no arguments.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
        this.ballList = new ArrayList<Ball>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c new collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Add ball.
     *
     * @param b the b
     */
    public void addBall(Ball b) {
        this.ballList.add(b);
    }

    /**
     * get the collidable list.
     *
     * @return collidable list.
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Gets ball list.
     *
     * @return the ball list
     */
    public List<Ball> getBallList() {
        return this.ballList;
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * bout the closest collision that is going to occur.
     *
     * @param trajectory line of the ball rooth.
     * @return null if there is no collision. collision info if there is.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.isEmpty()) {
            return null;
        }
        // Arguments that will save collide info.
        Point collidePoint;
        Collidable collideRec;
        List<CollisionInfo> intersectionInfo = new ArrayList<CollisionInfo>();
        // Save all the intersection points with the collidables.
        for (int i = 0; i < this.collidableList.size(); i++) {
            collidePoint = trajectory.closestIntersectionToStartOfLine(
                    this.collidableList.get(i).getCollisionRectangle());
            if (collidePoint != null) {
                // Add a new collisionInfo to the list, with the geometry.Rectangle and the point collide.
                intersectionInfo.add(new CollisionInfo(collidePoint, this.collidableList.get(i)));
            }
        }
        // if the are no intercetions return null.
        if (intersectionInfo.size() == 0) {
            return null;
        }
        //save the first distance between the start of the line to the first intersection.
        double minDistance = trajectory.start().distance(intersectionInfo.get(0).collisionPoint());
        // Will save the temp distance between the start line to the intercetion.
        double tempDistance;
        //will save the index of the first intercetion.
        int index = 0;
        for (int i = 0; i < intersectionInfo.size(); i++) {
            tempDistance = intersectionInfo.get(i).collisionPoint().distance(trajectory.start());
            if (tempDistance < minDistance) {
                minDistance = tempDistance;
                index = i;
            }
        }
        return intersectionInfo.get(index);
    }

    /**
     * check if the point is in any collidable.
     *
     * @param point center of the ball.
     * @return true if is, false otherwise.
     */
    public boolean pointIsInAnyCollidable(Point point) {
        for (int i = 0; i < collidableList.size(); i++) {
            if (collidableList.get(i).getCollisionRectangle().pointIsInRectangle(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if Point is in frame block.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean pointIsInFrameBlock(Point point) {
        for (int i = 0; i < 3; i++) {
            if (collidableList.get(i).getCollisionRectangle().pointIsInRectangle(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * In which block is the ball string.
     *
     * @param point the point
     * @return the string
     */
    public String inWhichBlockIsTheBall(Point point) {
        if (collidableList.get(0).getCollisionRectangle().pointIsInRectangle(point)) {
            return "top";
        }
        if (collidableList.get(1).getCollisionRectangle().pointIsInRectangle(point)) {
            return "left";
        }
        if (collidableList.get(2).getCollisionRectangle().pointIsInRectangle(point)) {
            return "right";
        }
        return null;
    }

    /**
     * Remove collidable.
     *
     * @param c the c is removed.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Remove ball.
     *
     * @param b the b
     */
    public void removeBall(Ball b) {
        this.ballList.remove(b);
    }
}
