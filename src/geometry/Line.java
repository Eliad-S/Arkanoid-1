package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.util.List;

/**
 * @author Eyal Cohen creating the object line.
 */

public class Line implements Sprite {
    // Members
    private Point start;
    private Point end;

    // constructors

    /**
     * create the line givent 2 points.
     *
     * @param start .
     * @param end   .
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * create the line given 4 doubles.
     *
     * @param x1 .
     * @param y1 .
     * @param x2 .
     * @param y2 .
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     *
     * @return length of the segment.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return point middle of the line
     */

    public Point middle() {
        double middleX = (this.end.getX() + this.start.getX()) / 2;
        double middleY = (this.end.getY() + this.start.getY()) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return start of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return point of the end of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other .
     * @return true or false.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * get start value x.
     *
     * @return value x.
     */
    public double getStartX() {
        return this.start.getX();
    }

    /**
     * value of end x.
     *
     * @return double value of end x.
     */
    public double getEndX() {
        return this.end.getX();
    }

    /**
     * get start value y.
     *
     * @return double value y.
     */
    public double getStartY() {
        return this.start.getY();
    }

    /**
     * get end point y value.
     *
     * @return y value point.
     */
    public double getEndY() {
        return this.end.getY();
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other .
     * @return point of intersection or null.
     */
    public Point intersectionWith(Line other) {
        // Mathematical calculation of straight equations
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double delta = a1 * b2 - a2 * b1;
        // The lines are parallel.
        if (delta == 0) {
            return null;
        }
        double x = (b2 * c1 - b1 * c2) / delta;
        double y = (a1 * c2 - a2 * c1) / delta;
        // Gets the start and the end of each line
        double minXLine1 = Math.min(this.start.getX(), this.end.getX());
        double maxXLine1 = Math.max(this.start.getX(), this.end.getX());
        double minXLine2 = Math.min(other.start.getX(), other.end.getX());
        double maxXLine2 = Math.max(other.start.getX(), other.end.getX());
        double minYLine1 = Math.min(this.start.getY(), this.end.getY());
        double maxYLine1 = Math.max(this.start.getY(), this.end.getY());
        double minYLine2 = Math.min(other.start.getY(), other.end.getY());
        double maxYLine2 = Math.max(other.start.getY(), other.end.getY());
        /**
         * check if the intersection of 2 lines are in the 2 segments. if it does,
         * return the point, else return null.
         */
        if (((minXLine1 <= x) && (x <= maxXLine1)) && ((minXLine2 <= x) && (x <= maxXLine2))) {
            if (((minYLine1 <= y) && (y <= maxYLine1)) && ((minYLine2 <= y) && (y <= maxYLine2))) {
                return new Point(x, y);
            }
        }
        return null;
    }


    /**
     * return true is the lines are equal, false otherwise.
     *
     * @param other .
     * @return true or false.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start())));
    }

    /**
     * get the first intercetion with the triangle.
     *
     * @param rect the rectangle that we check with the intercetiom.
     * @return the first point intercection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // List of intercetions with the triangle.
        List<Point> list = rect.intersectionPoints(this);
        // First assaing.
        // In case empty return null.
        if (list.isEmpty()) {
            return null;
        }
        Point firstPointIntercect = list.get(0);
        double distance = this.start.distance(list.get(0));
        double tempDistance;
        // Find the first intercetion.
        for (int i = 1; i < list.size(); i++) {
            tempDistance = this.start.distance(list.get(i));
            if (tempDistance < distance) {
                distance = tempDistance;
                firstPointIntercect = list.get(i);
            }
        }
        return firstPointIntercect;
    }

    /**
     * test the object line.
     *
     * @param args .
     */
    public static void main(String[] args) {
        Line l1 = new Line(0, 0, 3, 3);
        Line l2 = new Line(0, 0, 20, 0);
        if (l1.isIntersecting(l2)) {
            System.out.println("OK");
        } else {
            System.out.println("dam");
        }
       /* if(l1.isInSegment(new geometry.Point(1,1))){
            System.out.println("point in segment");
        }*/
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int startX = (int) getStartX();
        int startY = (int) getStartY();
        int endX = (int) getEndX();
        int endY = (int) getEndY();
        d.drawLine(startX, startY, endX, endY);
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
