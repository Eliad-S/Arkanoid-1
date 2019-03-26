package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import blockdecorator.BlockCreator;
import blockdecorator.BlockDrawerCreator;
import blockdecorator.BlockHeightCreator;
import blockdecorator.BlockHitPointCreator;
import blockdecorator.BlockWidthCreator;
import blockdecorator.DefultBlock;
import collision.BlockRemover;
import collision.Collidable;
import constans.Constants;
import geometry.Ball;
import geometry.Block;
import geometry.Paddle;
import geometry.Point;
import geometry.Velocity;
import levels.LevelInformation;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;
import java.util.List;

/**
 * the game class that initilize and run the game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter numbOfLives;
    private GUI gui;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    // Constructor

    /**
     * constructor of game witout arguments.
     *
     * @param l the l
     */
    public GameLevel(LevelInformation l) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = new Counter(0);
        this.numbOfLives = new Counter(Constants.NUM_OF_LIFE);
        this.levelInformation = l;
        this.gui = new GUI(levelInformation.levelName(), Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Instantiates a new Game level.
     *
     * @param l           the l
     * @param ks          the ks
     * @param ar          the ar
     * @param score       the score
     * @param numbOfLives the numb of lives
     * @param currentGui  the current gui
     */
    public GameLevel(LevelInformation l, KeyboardSensor ks, AnimationRunner ar, Counter score, Counter numbOfLives,
                     GUI currentGui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.numbOfLives = numbOfLives;
        this.levelInformation = l;
        this.gui = currentGui;
        this.runner = new AnimationRunner(this.gui, 60);
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * add colldible to the collidble list.
     *
     * @param c collidble.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add ball.
     *
     * @param b the b
     */
    public void addBall(Ball b) {
        this.environment.addBall(b);
    }

    /**
     * get the list of collidable.
     *
     * @return list of collidable.
     */
    public List<Collidable> getCollidableList() {
        return this.environment.getCollidableList();
    }

    /**
     * get the list of sprites, the visual objects.
     *
     * @return list of sprites.
     */
    public SpriteCollection getSpriteList() {
        return this.sprites;
    }

    /**
     * add sprite to the list.
     *
     * @param s new sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * choose color by modulu, each modulo is a different color.
     *
     * @param num num of itteration.
     * @return color of itteration.
     */
    public Color chooseingColor(int num) {
        num = num % 6;
        switch (num) {
            case 0:
                return Color.cyan;
            case 1:
                return Color.red;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.gray;
            case 4:
                return Color.PINK;
            case 5:
                return Color.GREEN;
            default:
                return Color.ORANGE;
        }
    }

    /**
     * initilize balls by the amount inputed.
     *
     * @param ballsAmount numbers of balls.
     * @param paddle      the paddle
     */
    public void howManyBallsToAdd(int ballsAmount, Paddle paddle) {
        double ballXLocation = paddle.getMiddleX();
        double ballYLocation = paddle.getCollisionRectangle().getMiddleY() - 20;
        double dx = 0;
        for (int i = 0; i < ballsAmount; i++) {
            remainingBalls.increase(1);
            Ball ball1 = new Ball(ballXLocation + dx, ballYLocation,
                    Constants.BALL_RADIUS, Constants.BALL1_COLOR, this.gui);
            // the new ball velocity
            double newDX = levelInformation.initialBallVelocities().get(i).getDX();
            double newDY = levelInformation.initialBallVelocities().get(i).getDY();
            Velocity newVelocity = new Velocity(newDX, newDY);
            ball1.setVelocity(newVelocity);
//            Color color = chooseingColor(i);
            ball1.setColor(Color.WHITE);
            ball1.addToGame(this);
            ball1.setGameEnvi(this.environment);
        }
    }


    /**
     * Sets game blocks.
     *
     * @param blockRemover          the block remover
     * @param scoreTrackingListener the score tracking listener
     */
    public void setGameBlocks(BlockRemover blockRemover, ScoreTrackingListener scoreTrackingListener) {
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            remainingBlocks.increase(1);
        }
    }

    /**
     * Set death region block.
     *
     * @param b the ball Remover.
     */
    public void setDeathRegionBlock(BallRemover b) {
        int blockSize = Constants.DEATH_BLOCK_SIZE;
        double height = Constants.DEATH_BLOCK_Y_LOCATION;
        int width = Constants.GUI_WIDTH;
        Point bottomLeftPoint = new Point(0, height);
        Block deathRegionBlock = new Block(bottomLeftPoint, width, blockSize, Constants.FRAMES_COLOR, 0, b);
        this.addCollidable(deathRegionBlock);
    }

    /**
     * No blocks left boolean.
     *
     * @return the boolean
     */
    public Boolean noBlocksLeft() {
        if (this.remainingBlocks.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * No balls left boolean.
     *
     * @return the boolean
     */
    public Boolean noBallsLeft() {
        if (this.remainingBalls.getValue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * End game.
     */
    public void endGame() {
        this.gui.close();
    }

    /**
     * Add score indicator.
     */
    public void addScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Add live indicator.
     */
    public void addLiveIndicator() {
        LivesIndicator livesIndicator = new LivesIndicator(numbOfLives);
        livesIndicator.addToGame(this);
    }

    /**
     * Add level name indicator.
     */
    public void addLevelNameIndicator() {
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(levelInformation.levelName());
        levelNameIndicator.addToGame(this);
    }

    /**
     * Initialize a new game: create the Blocks and ball.
     * and add them to the game.
     */
    public void initialize() {
        Sprite background = levelInformation.getBackground();
        background.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        //Background color
        setGameFrameBlocks();
        addScoreIndicator();
        addLevelNameIndicator();
        addLiveIndicator();
        //set all the blocks in the level.
        setGameBlocks(blockRemover, scoreTrackingListener);
        setDeathRegionBlock(ballRemover);
    }

    /**
     * No more lives boolean.
     *
     * @return the boolean
     */
    public boolean noMoreLives() {
        if (numbOfLives.getValue() > 0) {
            return false;
        }
        return true;
    }

    /**
     * Run the game.
     */
    public void run() {
        while (!noMoreLives()) {
            playOneTurn();
        }
        endGame();
    }

    /**
     * Run the game start the animation loop.
     */
    public void playOneTurn() {
        Paddle paddle = new Paddle(Constants.PADDLE_Y_LOCATION,
                levelInformation.paddleWidth(), Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR,
                levelInformation.paddleSpeed(), this.keyboard);
        paddle.addToGame(this);
        howManyBallsToAdd(this.levelInformation.numberOfBalls(), paddle);
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        paddle.removeFromGame(this);
    }

    /**
     * Sets game frame blocks.
     */
    public void setGameFrameBlocks() {
        int blockSize = Constants.BLOCK_SIZE;
        int height = Constants.GUI_HEIGHT;
        int width = Constants.GUI_WIDTH;
        Point topLeftPoint = new Point(Constants.FRAME_BLOCK_X_LOCATION, Constants.FRAME_BLOCK_Y_LOCATION);
        Point topRightPoint = new Point(width - blockSize, Constants.FRAME_BLOCK_Y_LOCATION);
        Block top = createOneFrameBlock(topLeftPoint, width, blockSize, 0);
        Block left = createOneFrameBlock(topLeftPoint, blockSize, height, 0);
        Block right = createOneFrameBlock(topRightPoint, blockSize, height, 0);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
    }

    /**
     * Create one frame block block.
     *
     * @param start     the start
     * @param height    the height
     * @param width     the width
     * @param numOfHits the num of hits
     * @return the block
     */
    public Block createOneFrameBlock(Point start, int width, int height, int numOfHits) {
        BlockCreator blockCreator =
                new BlockDrawerCreator(new BlockDrawerCreator(
                        new BlockHeightCreator(
                                new BlockWidthCreator(
                                        new BlockHitPointCreator(new DefultBlock(), numOfHits),
                                        width),
                                height),
                        Color.LIGHT_GRAY,
                        true, -1), Color.BLACK, false, -1);
        Block block = blockCreator.create((int) start.getX(), (int) start.getY());
        return block;
    }

    /**
     * Remove collidable.
     *
     * @param c the c to revmove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove ball.
     *
     * @param b the b
     */
    public void removeBall(Ball b) {
        environment.removeBall(b);
    }

    /**
     * Remove sprite.
     *
     * @param s remove the sprite from the list.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Do one frame.
     *
     * @param d  the surface to print on.
     * @param dt time passed.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (noBlocksLeft()) {
            this.score.increase(100);
            this.running = false;
        }
        if (noBallsLeft()) {
            this.numbOfLives.decrease(1);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY,
                    new PauseScreen()
            ));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}