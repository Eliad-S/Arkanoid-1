package readfromfile;

import background.Background;
import geometry.Block;
import geometry.Velocity;
import levels.DefaultLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information factory.
 */
public class LevelInformationFactory {
    private int numOfBalls;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Background background;
    private List<Block> blocks;
    private BlockLayoutData recData;
    private int numberOfBlocksToRemove;
    private String blockPathString;
    private Utils utils = new Utils();

    /**
     * Instantiates a new Level information factory.
     *
     * @param levelDataMap the level data map
     */
    public LevelInformationFactory(LevelDataMap levelDataMap) {
        setBallVelocities(levelDataMap);
        this.paddleSpeed = utils.converToInt(levelDataMap.getValue("paddle_speed"));
        this.paddleWidth = utils.converToInt(levelDataMap.getValue("paddle_width"));
        this.levelName = levelDataMap.getValue("level_name");
        this.numberOfBlocksToRemove = utils.converToInt(levelDataMap.getValue("num_blocks"));
        double rowHeight = utils.converToInt(levelDataMap.getValue("row_height"));
        double blocksStartX = utils.convertToDouble(levelDataMap.getValue("blocks_start_x"));
        double blocksStartY = utils.convertToDouble(levelDataMap.getValue("blocks_start_y"));
        recData = new BlockLayoutData(rowHeight, blocksStartX, blocksStartY);
        this.numOfBalls = this.ballVelocities.size();
        setBackground(levelDataMap.getValue("background"));
        this.blockPathString = levelDataMap.getValue("block_definitions");
    }

    /**
     * Sets background.
     *
     * @param stringBackGround the string back ground
     */
    public void setBackground(String stringBackGround) {
        if (stringBackGround.startsWith("image(")) {
            this.background = new Background(utils.setImage(stringBackGround));
        } else if (stringBackGround.startsWith("color(")) {
            Color color = utils.setColor(stringBackGround);
            Background newBackground = new Background(color);
            this.background = newBackground;
        }
    }

    /**
     * Sets ball velocities.
     *
     * @param levelDataMap the level data map
     */
    public void setBallVelocities(LevelDataMap levelDataMap) {
        String stringVelocities = levelDataMap.getValue("ball_velocities");
        List<String> separatedVelocity = separateVelocityBySpaces(stringVelocities);
        this.ballVelocities = separateVelocityByCommas(separatedVelocity);
    }

    /**
     * Separate velocity by commas list.
     *
     * @param velocitys the velocitys
     * @return the list
     */
    public List<Velocity> separateVelocityByCommas(List<String> velocitys) {
        List<Velocity> velocities = new ArrayList<>();
        for (String twinVelocity : velocitys) {
            if (twinVelocity.contains(",")) {
                //separte each string in the list to two numbers.
                String[] parts = twinVelocity.split(",");
                try {
                    velocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(parts[0]),
                            Double.parseDouble(parts[1])));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid numbers");
                }
            } else {
                throw new IllegalArgumentException("String " + twinVelocity + " does not contain :");
            }
        }
        return velocities;
    }

    /**
     * Separate velocity by spaces list.
     *
     * @param velocities the velocities
     * @return the list
     */
    public List<String> separateVelocityBySpaces(String velocities) {
        List<String> separatedVelocity = new ArrayList<>();
        if (velocities.contains(" ")) {
            String[] parts = velocities.split(" ");
            for (int i = 0; i < parts.length; i++) {
                separatedVelocity.add(parts[i]);
            }
        } else {
            separatedVelocity.add(velocities);
        }
        return separatedVelocity;
    }

    /**
     * Gets block path string.
     *
     * @return the block path string
     */
    public String getBlockPathString() {
        return blockPathString;
    }

    /**
     * Game blocks list.
     *
     * @param blockLines   the block lines
     * @param blockFactory the block factory
     * @return the list
     */
    public List<Block> setGameBlocks(List<String> blockLines, BlocksFromSymbolsFactory blockFactory) {
        // create all the blocks from data got.
        CreateBlockLayout blockLayout = new CreateBlockLayout(recData, blockLines, blockFactory);
        return blockLayout.getGameBlocks();
    }

    /**
     * Set this level information default level.
     *
     * @param d         the d
     * @param blockList the block list
     * @return the default level
     */
    public DefaultLevel setThisLevelInformation(DefaultLevel d, List<Block> blockList) {
        d.setNumOfBalls(numOfBalls);
        d.setBallVelocities(ballVelocities);
        d.setPaddleSpeed(paddleSpeed);
        d.setPaddleWidth(paddleWidth);
        d.setLevelName(levelName);
        d.setBackground(background);
        d.setBlocks(blockList);
        d.setNumberOfBlocksToRemove(numberOfBlocksToRemove);
        return d;
    }
}
