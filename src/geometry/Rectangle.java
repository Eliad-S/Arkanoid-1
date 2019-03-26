package geometry;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * class of the geometry.Rectangle.
 */
public class Rectangle implements Sprite {
    // Members
    private Point recUpperLeftPoint;
    private double recWidth;
    private double recHeight;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft point
     * @param width     of the rectangle.
     * @param height    of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.recUpperLeftPoint = upperLeft;
        this.recWidth = width;
        this.recHeight = height;
    }

    /**
     * create a retangle with x and y.
     *
     * @param x      point value.
     * @param y      point value.
     * @param width  of the rectangle.
     * @param height of the height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.recUpperLeftPoint = new Point(x, y);
        this.recWidth = width;
        this.recHeight = height;
    }
    //Methods

    /**
     * return all the intersections point of a line with a rectangle.
     *
     * @param line line to intersect with.
     * @return list of intersection points.
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        //create the upper right point of the rectangle.
        Point upperRightPoint = new Point(getStartPointX() + this.recWidth, getStartPointY());
        Point lowerLeft = new Point(getStartPointX(), getStartPointY() + this.recHeight);
        Point lowerRight = new Point(getStartPointX() + this.recWidth, getStartPointY() + this.recHeight);
        Line top = new Line(this.recUpperLeftPoint, upperRightPoint);
        Line right = new Line(upperRightPoint, lowerRight);
        Line bottom = new Line(lowerLeft, lowerRight);
        Line left = new Line(this.recUpperLeftPoint, lowerLeft);
        // check if it hitted on any edge of the rectangle.
        if (line.isIntersecting(top)) {
            list.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(right)) {
            list.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(bottom)) {
            list.add(line.intersectionWith(bottom));
        }
        if (line.isIntersecting(left)) {
            list.add(line.intersectionWith(left));
        }
        return list;
    }

    /**
     * Sets rec height.
     *
     * @param rectangleHeight the rectangle height
     */
    public void setRecHeight(double rectangleHeight) {
        this.recHeight = rectangleHeight;
    }

    /**
     * Sets rec width.
     *
     * @param rectangleWidth the rectangle width
     */
    public void setRecWidth(double rectangleWidth) {
        this.recWidth = rectangleWidth;
    }

    /**
     * get the width of the rectangle.
     *
     * @return double width.
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.recWidth;
    }

    /**
     * get the height of the rectangle.
     *
     * @return double height of the rectangle.
     */
    public double getHeight() {
        return this.recHeight;
    }

    /**
     * get the uperleft point.
     *
     * @return point upperleft.
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.recUpperLeftPoint;
    }

    /**
     * get start point double x.
     *
     * @return double x.
     */
    public double getStartPointX() {
        return this.recUpperLeftPoint.getX();
    }

    /**
     * get Start point Y value.
     *
     * @return double y value.
     */
    public double getStartPointY() {
        return this.recUpperLeftPoint.getY();
    }

    /**
     * get end point X value.
     *
     * @return double X value.
     */
    public double getEndPointX() {
        return (this.getStartPointX() + this.recWidth);
    }

    /**
     * get End point Y value.
     *
     * @return double y value.
     */
    public double getEndPointY() {
        return (this.getStartPointY() + this.recHeight);
    }

    /**
     * set the start X of the rectangle.
     *
     * @param dx the delta x to add.
     */
    public void howManyToMoveX(double dx) {
        //set the strat point 10 inches left.
        Point tempPoint = this.recUpperLeftPoint;
        this.recUpperLeftPoint = new Point(tempPoint.getX() + dx, tempPoint.getY());
    }

    /**
     * set x.
     *
     * @param x the x
     */
    public void setX(double x) {
        //set the strat point 10 inches left.
        Point tempPoint = this.recUpperLeftPoint;
        this.recUpperLeftPoint = new Point(x, tempPoint.getY());
    }

    /**
     * return a string means witch edge the intersection heppend.
     *
     * @param intersection point of the intersection.
     * @return string edge.
     */
    public String intersectionEdge(Point intersection) {
        if (intersection.getY() == getStartPointY()) {
            return "top";
        }
        if (intersection.getY() == this.getEndPointY()) {
            return "bottom";
        }
        if (intersection.getX() == getStartPointX()) {
            return "left";
        }
        if (intersection.getX() == this.getEndPointX()) {
            return "right";
        } else {
            return "error";
        }
    }

    /**
     * Gets middle x.
     *
     * @return the middle x
     */
    public double getMiddleX() {
        return (this.getStartPointX() + this.getEndPointX()) / 2;
    }

    /**
     * Gets middle y.
     *
     * @return the middle y
     */
    public double getMiddleY() {
        return (this.getStartPointY() + this.getEndPointY()) / 2;
    }

    /**
     * check if the point got is int the rectangle.
     *
     * @param point to check it.
     * @return true if it is in the rectangle, else return false.
     */
    public boolean pointIsInRectangle(Point point) {
        double x = point.getX();
        double y = point.getY();
        if ((this.getStartPointX() <= x) && (x <= this.getEndPointX()) && (this.getStartPointY() <= y)
                && (y <= this.getEndPointY())) {
            return true;
        }
        return false;
    }

    /**
     * get the end point of the rectangle.
     *
     * @return point, right bottom end of the rectangle.
     */
    public Point getEndPoint() {
        return new Point(getEndPointX(), getEndPointY());
    }

    /**
     * print of the rectangle.
     *
     * @return string of the print rectangle.
     */
    public String toString() {
        String point = this.recUpperLeftPoint.toString();
        String width = String.valueOf(this.recWidth);
        String height = String.valueOf(this.recHeight);
        String print = point + "The width is: " + width + "\nThe height is: " + height + "\n";
        return print;
    }

    /**
     * check the rectangle.
     *
     * @param args nothing.
     */
    public static void main(String[] args) {

        Rectangle rec = new Rectangle(5, 5, 10, 20);
        System.out.println(rec);
    }

    /**
     * draw the sprit on.
     *
     * @param d the screen to add on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.fillRectangle((int) getStartPointX(), (int) getStartPointY(), (int) getWidth(), (int) getHeight());
    }

    /**
     * if you want to draw the rectangle and not fill.
     *
     * @param d     surface to draw on.
     * @param color choose color to draw
     */
    public void drawRectangle(DrawSurface d, Color color) {
        d.setColor(color);
        d.drawRectangle((int) getStartPointX(), (int) getStartPointY(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Fill rectangle.
     *
     * @param d     the d
     * @param color the color
     */
    public void fillRectangle(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle((int) getStartPointX(), (int) getStartPointY(), (int) getWidth(), (int) getHeight());
    }

    /**
     * Draw image.
     *
     * @param d     the d
     * @param image the image
     */
    public void drawImage(DrawSurface d, Image image) {
        d.drawImage((int) getStartPointX(), (int) getStartPointY(), image);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the value.
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
