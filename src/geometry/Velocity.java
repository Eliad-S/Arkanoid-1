package geometry;

/**
 * geometry.Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Eyal Cohen
 */
public class Velocity {
    // members
    private double dx;
    private double dy;

    // constructor

    /**
     * create geometry.Velocity from 2 doubles.
     *
     * @param dx .
     * @param dy .
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * create geometry.Velocity from angle and speed.
     *
     * @param angle .
     * @param speed .
     * @return new geometry.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx = Math.round((Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed));
        double newDy = Math.round((Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed));
        return new Velocity(newDx, newDy);
    }

    /**
     * get the speed of the velocity.
     *
     * @return double speed.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx,y+dy).
     *
     * @param p .
     * @return the new geometry.Point of the ball
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Return the dx of the geometry.Velocity.
     *
     * @return this.dx dx
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Return the dy of the geometry.Velocity.
     *
     * @return this.dy dy
     */
    public double getDY() {
        return this.dy;
    }
}