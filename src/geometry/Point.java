package geometry;

/**
 * @author Eyal Cohen. Object of geometry.Point Build from double x an double y value.
 */
public class Point {
    // Members
    private double x;
    private double y;

    // constructor

    /**
     * create point with double x and y values.
     *
     * @param x .
     * @param y .
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other .
     * @return distance between the two points.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other .
     * @return true or false.
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * return the x value of the point.
     *
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * return the y value of the point.
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Printing geometry.Point definition.
     *
     * @return string print of the point.
     */
    public String toString() {
        String stringX = String.valueOf(this.getX());
        String stringY = String.valueOf(this.getY());
        String pointName = "The geometry.Point is: (" + stringX + "," + stringY + ")\n";
        return pointName;
    }

    /**
     * test the object geometry.Point.
     *
     * @param args .
     */
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(2, 1);
        Point p3 = new Point(1, 2);
        System.out.println(p1);
        if (p1.equals(p3)) {
            System.out.println(p2);
        } else {
            System.out.println("dam");
        }
    }
}