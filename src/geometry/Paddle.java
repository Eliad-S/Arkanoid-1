package geometry;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import constans.Constants;
import game.GameLevel;
import sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * paddle class object.
 */
public class Paddle implements Sprite, Collidable {
    //members
    private Rectangle rectangle;
    private Color color;
    private KeyboardSensor keyboardSensor;
    private double speed;

    //constructors.

    /**
     * constructor that get rectangle and create a new paddle.
     *
     * @param rec rectangle.
     */
    public Paddle(Rectangle rec) {
        this.rectangle = rec;
        this.color = Color.YELLOW;
    }

    /**
     * create new paddle with given paddle.
     *
     * @param newPaddle new paddle.
     */
    public Paddle(Paddle newPaddle) {
        Rectangle rec = newPaddle.getCollisionRectangle();
        this.rectangle = new Rectangle(rec.getUpperLeft(), rec.getWidth(), rec.getHeight());
        this.color = Color.YELLOW;
    }

    /**
     * create new paddle.
     *
     * @param point    point start.
     * @param width    width.
     * @param height   height.
     * @param color    color of the paddle.
     * @param newSpeed new speed of the paddle.
     * @param keyboard keyboard of the paddle.
     */
    public Paddle(Point point, double width, double height, Color color, double newSpeed, KeyboardSensor keyboard) {
        this.rectangle = new Rectangle(point, width, height);
        this.color = color;
        this.keyboardSensor = keyboard;
        this.speed = newSpeed * 60;
    }

    /**
     * create paddle.
     *
     * @param y        y value.
     * @param width    width.
     * @param height   heidht.
     * @param color    color of the paddle.
     * @param newSpeed new speed.
     * @param keyboard keyboard of the game.
     */
    public Paddle(double y, double width, double height, Color color, double newSpeed,
                  KeyboardSensor keyboard) {
        double x = (Constants.GUI_WIDTH - width) / 2;
        this.rectangle = new Rectangle(new Point(x, y), width, height);
        this.color = color;
        this.keyboardSensor = keyboard;
        this.speed = newSpeed;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * if the customer press left, move the paddle left.
     *
     * @param dt the dt
     */
    public void moveLeft(double dt) {
        if (this.rectangle.getStartPointX() > Constants.BLOCK_SIZE) {
            this.rectangle.howManyToMoveX(-speed * dt);
        }
    }

    /**
     * if the customer press right, move the paddle right.
     *
     * @param dt the dt
     */
    public void moveRight(double dt) {
        if (this.rectangle.getEndPointX() < Constants.GUI_WIDTH - Constants.BLOCK_SIZE) {
            this.rectangle.howManyToMoveX(speed * dt);
        }
    }

    /**
     * if the time passed, check if right or left is pressed and change the location by it.
     *
     * @param dt the dt.
     */
    public void timePassed(double dt) {
        if (keyboardSensor.isPressed(keyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        } else if (keyboardSensor.isPressed(keyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        //return the paddle to limits.
        if (this.rectangle.getStartPointX() <= Constants.BLOCK_SIZE) {
            this.rectangle.setX(Constants.BLOCK_SIZE);
        } else if (this.rectangle.getEndPointX() >= Constants.GUI_WIDTH - Constants.BLOCK_SIZE) {
            this.rectangle.setX(Constants.GUI_WIDTH - Constants.BLOCK_SIZE - this.getWidth());
        }
    }

    /**
     * draw tha paddle on the screen.
     *
     * @param d the screen
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int x = (int) rectangle.getStartPointX();
        int y = (int) rectangle.getStartPointY();
        int width = (int) rectangle.getWidth();
        int height = Constants.PADDLE_HEIGHT;
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * return the geometry.Rectangle of the paddle.
     *
     * @return geometry.Rectangle rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * divide the line in to segment.
     *
     * @return list of segments.
     */
    public List<Line> divisionPaddleInToSegments() {
        double startingXLocation = this.rectangle.getStartPointX();
        double segmentsWidth = getWidth() / Constants.PADDLE_NUM_OF_SEGMENETS;
        List<Line> segmentsList = new ArrayList<>();

        for (int i = 0; i < Constants.PADDLE_NUM_OF_SEGMENETS; i++) {
            Point startPoint = new Point(startingXLocation, Constants.PADDLE_Y_LOCATION);
            Point endPoint = new Point(startingXLocation + segmentsWidth, Constants.PADDLE_Y_LOCATION);
            segmentsList.add(new Line(startPoint, endPoint));
            startingXLocation += segmentsWidth;
        }
        return segmentsList;
    }

    /**
     * return new velocity by the segments.
     *
     * @param segmentNum number of segments.
     * @param velocity   new velocity be segments.
     * @return new velocity.
     */
    public Velocity newVelocity(int segmentNum, Velocity velocity) {
        double angle = 0;
        switch (segmentNum) {
            case 0:
                angle = 300;
                break;
            case 1:
                angle = 330;
                break;
            case 2:
                return new Velocity(velocity.getDX(), -1 * velocity.getDY());
            case 3:
                angle = 30;
                break;
            case 4:
                angle = 60;
                break;
            default:
                angle = 0;
                break;
        }
        double newSpeed = velocity.getSpeed();
        return velocity.fromAngleAndSpeed(angle, newSpeed);
    }

    /**
     * change the velocity after hitting a block.
     *
     * @param collisionPoint  the collision point on the rectanlge.
     * @param currentVelocity the current velocity.
     * @param hitter          the hitter ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null) {
            return currentVelocity;
        }
        List<Line> segmentsList = divisionPaddleInToSegments();
        for (int i = 0; i < segmentsList.size(); i++) {
            double startXlocation = segmentsList.get(i).getStartX();
            double endXlocation = segmentsList.get(i).getEndX();
            double yLocation = this.getCollisionRectangle().getStartPointY();
            if (startXlocation <= collisionPoint.getX() && collisionPoint.getX() <= endXlocation
                    && collisionPoint.getY() == yLocation) {
                return newVelocity(i, currentVelocity);
            }
        }
        return new Velocity(-1 * currentVelocity.getDX(), currentVelocity.getDY());
    }

    /**
     * Get start x double.
     *
     * @return the double
     */
    public double getStartX() {
        return this.getCollisionRectangle().getStartPointX();
    }

    /**
     * Get start y double.
     *
     * @return the double
     */
    public double getStartY() {
        return this.getCollisionRectangle().getStartPointY();
    }

    /**
     * Get end x double.
     *
     * @return the double
     */
    public double getEndX() {
        return this.getCollisionRectangle().getEndPointX();
    }

    /**
     * Get end y double.
     *
     * @return the double
     */
    public double getEndY() {
        return this.getCollisionRectangle().getEndPointY();
    }

    /**
     * Get paddle middle x double.
     *
     * @return the double
     */
    public double getMiddleX() {
        return (getEndX() + getStartX()) / 2;
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
     * Restart paddle location.
     */
    public void restartPaddleLocation() {
        this.rectangle = new Rectangle(Constants.PADDLE_X_LOCATION, Constants.PADDLE_Y_LOCATION, Constants.PADDLE_WIDTH,
                Constants.PADDLE_HEIGHT);
    }

    /**
     * Add this paddle to the game.
     *
     * @param g adding the ball to game g.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}


