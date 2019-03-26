package levels;

import geometry.Block;
import geometry.Velocity;
import sprite.Sprite;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Default level.
 */
public class DefaultLevel implements LevelInformation, Cloneable {
    private int numOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private double paddleXLocation;

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Get paddle x location double.
     *
     * @return the double
     */
    @Override
    public double getPaddleXLocation() {
        return this.paddleXLocation;
    }

    /**
     * Sets background.
     *
     * @param backgroundTemp the background temp
     */
    public void setBackground(Sprite backgroundTemp) {
        this.background = backgroundTemp;
    }

    /**
     * Sets ball velocities.
     *
     * @param ballVelocitiesTemp the ball velocities temp
     */
    public void setBallVelocities(List<Velocity> ballVelocitiesTemp) {
        this.ballVelocities = ballVelocitiesTemp;
    }

    /**
     * Sets num of balls.
     *
     * @param numOfBallsTemp the num of balls temp
     */
    public void setNumOfBalls(int numOfBallsTemp) {
        this.numOfBalls = numOfBallsTemp;
    }

    /**
     * Sets paddle speed.
     *
     * @param paddleSpeedTemp the paddle speed temp
     */
    public void setPaddleSpeed(int paddleSpeedTemp) {
        this.paddleSpeed = paddleSpeedTemp;
    }

    /**
     * Sets paddle width.
     *
     * @param paddleWidthTemp the paddle width temp
     */
    public void setPaddleWidth(int paddleWidthTemp) {
        this.paddleWidth = paddleWidthTemp;
    }

    /**
     * Sets level name.
     *
     * @param levelNameTemp the level name temp
     */
    public void setLevelName(String levelNameTemp) {
        this.levelName = levelNameTemp;
    }

    /**
     * Sets blocks.
     *
     * @param blocksTemp the blocks temp
     */
    public void setBlocks(List<Block> blocksTemp) {
        this.blocks = blocksTemp;
    }

    /**
     * Sets number of blocks to remove.
     *
     * @param numberOfBlocksToRemoveTemp the number of blocks to remove temp
     */
    public void setNumberOfBlocksToRemove(int numberOfBlocksToRemoveTemp) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemoveTemp;
    }

    /**
     * Sets paddle x location.
     *
     * @param paddleXLocationTemp the paddle x location temp
     */
    public void setPaddleXLocation(double paddleXLocationTemp) {
        this.paddleXLocation = paddleXLocationTemp;
    }

    @Override
    public LevelInformation clone() {
        try {
            DefaultLevel cloned = (DefaultLevel) super.clone();
            List<Block> newList = new LinkedList<>();
            for (Block b : cloned.blocks) {
                newList.add(b.clone());
            }
            cloned.setBlocks(newList);
            return cloned;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}