package constans;

import java.awt.Color;

/**
 * constans of the project.
 */
public class Constants {
    // Gui
    public static final int GUI_WIDTH = 800;
    public static final int GUI_HEIGHT = 600;
    public static final Color FRAMES_COLOR = Color.GRAY;
    // Ball
    public static final int BALL_RADIUS = 5;
    public static final Color BALL1_COLOR = Color.white;
    // Score
    public static final int SCORE_BLOCK_X_LOCATION = 0;
    public static final int SCORE_BLOCK_Y_LOCATION = 0;
    public static final int SCORE_BLOCK_WIDTH = GUI_WIDTH;
    public static final int SCORE_BLOCK_HEIGHT = 30;
    public static final int SCORE_FONT_SIZE = 15;
    public static final int SCORE_FONT_X = GUI_WIDTH / 2 - 30;
    public static final int SCORE_FONT_Y = 20;
    // lives
    public static final int NUM_OF_LIFE = 1;
    public static final int LIVES_BLOCK_X_LOCATION = 80;
    public static final int LIVES_BLOCK_Y_LOCATION = SCORE_FONT_Y;
    public static final int LIVES_BLOCK_HEIGHT = 20;
    public static final int LIVES_FONT_SIZE = 15;
    // level name
    public static final int LVL_NAME_X_LOCATION = 600;
    public static final int LVL_NAME_Y_LOCATION = SCORE_FONT_Y;
    public static final int LVL_NAME_FONT_SIZE = 15;
    //Frame Blocks
    public static final int BLOCK_SIZE = 20;
    public static final int FRAME_BLOCK_X_LOCATION = 0;
    public static final int FRAME_BLOCK_Y_LOCATION = SCORE_BLOCK_HEIGHT;
    // Blocks
    public static final double BLOCK_WIDTH = 70;
    public static final double BLOCK_HEIGHT = 25;
    public static final int BLOCK_X_LOCATION = GUI_WIDTH - BLOCK_SIZE - (int) BLOCK_WIDTH;
    public static final int BLOCK_Y_LOCATION = 120;
    public static final int NUM_OF_BLOCKS_IN_FIRST_ROW = 8;
    public static final int NUM_OF_BLOCKSROWS = 7;
    // Paddle
    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = 5;
    // Center of the screen
    public static final double PADDLE_X_LOCATION = ((GUI_WIDTH / 2) - (PADDLE_WIDTH / 2));
    // locate the paddle above the block frame.
    public static final double PADDLE_Y_LOCATION = GUI_HEIGHT - PADDLE_HEIGHT - 2;
    public static final int PADDLE_NUM_OF_SEGMENETS = 5;
    public static final Color PADDLE_COLOR = Color.ORANGE;
    // Death Region Block
    public static final double DEATH_BLOCK_Y_LOCATION = GUI_HEIGHT;
    public static final int DEATH_BLOCK_SIZE = BLOCK_SIZE;
    // Time
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECONDS = 1000;
    public static final int MILLISECONDS_PER_FRAME = MILLISECONDS / FRAMES_PER_SECOND;
    // level 1
    public static final int LVL_1_NUM_OF_BALLS = 1;
    public static final int LVL_1_PADDLE_SPEED = 10;
    public static final int LVL_1_PADDLE_WIDTH = 150;
    public static final double LVL_1_PADDLE_X_LOCATION = ((GUI_WIDTH / 2) - (LVL_1_PADDLE_WIDTH / 2)) + 5;
    // level 2
    public static final int LVL_2_NUM_OF_BALLS = 9;
    public static final int LVL_2_PADDLE_SPEED = 5;
    public static final int LVL_2_PADDLE_WIDTH = 500;
    public static final int LVL_2_BLOCK_Y_LOCATION = 220;
    public static final double LVL_2_PADDLE_X_LOCATION = ((GUI_WIDTH / 2) - (LVL_2_PADDLE_WIDTH / 2)) + 5;
    // level 3
    public static final int LVL_3_NUM_OF_BALLS = 5;
    public static final int LVL_3_PADDLE_SPEED = 15;
    public static final int LVL_3_PADDLE_WIDTH = 280;
    public static final int LVL_3_BLOCK_Y_LOCATION = 100;
    public static final double LVL_3_PADDLE_X_LOCATION = ((GUI_WIDTH / 2) - (LVL_3_PADDLE_WIDTH / 2));
    public static final double LVL_3_NUM_ROWS = 7;
    // level 4
    public static final int LVL_4_NUM_OF_BALLS = 3;
    public static final int LVL_4_PADDLE_SPEED = 10;
    public static final int LVL_4_PADDLE_WIDTH = 200;
    public static final double LVL_4_PADDLE_X_LOCATION = ((GUI_WIDTH / 2) - (LVL_4_PADDLE_WIDTH / 2));
    public static final double LVL_4_NUM_WINDOWS_ROWS = 7;
    public static final int LVL_4_WINDOWS_WIDTH = 7;
    public static final int LVL_4_WINDOWS_HEIGHT = 12;
    public static final double LVL_4_NUM_WINDOWS_COLUMNS = 5;
    public static final int LVL_4_BASE_WIDTH = 20;
    public static final int LVL_4_BASE_HEIGHT = 40;
    public static final int LVL_4_ANTENNA_WIDTH = 10;
    public static final int LVL_4_ANTENNA_HEIGHT = 100;
    // score
    public static final int HIGH_SCORE_TABLE_SIZE = 10;
    public static final String FILE_PATH = "highscores.ser";
}
