package animation;

import background.Background;
import biuoop.DrawSurface;
import score.HighScoresTable;

import java.awt.Color;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable highScoresTable;

    /**
     * Instantiates a new High scores animation.
     *
     * @param hst the HighScoresTable
     */
    public HighScoresAnimation(HighScoresTable hst) {
        this.highScoresTable = hst;
        this.stop = false;
    }

    /**
     * Gets table max size.
     *
     * @return the table max size.
     */
    public int getTableMaxSize() {
        return this.highScoresTable.size();
    }

    /**
     * Gets table size.
     *
     * @return the table size
     */
    public int getTableSize() {
        return this.highScoresTable.size();
    }

    /**
     * Do one frame.
     *
     * @param d  the surface to print on.
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Background background = setBackGround();
        background.drawOn(d);
        String highScoreTableString = "- High score table -";
        String name;
        int score;
        int tableSize = getTableSize();
        int fontSize = 20;
        int dy = (d.getHeight() - 100 - fontSize * tableSize) / (tableSize + 1);
        int y = dy + 100;
        d.setColor(Color.BLUE);
        d.drawText(d.getWidth() / 6, 60, highScoreTableString, 60);
        for (int i = 0; i < tableSize; i++) {
            // numeration of high score.
            d.drawText(50, y, Integer.toString(i + 1) + ".", fontSize);
            // print all the high scores.
            if (i < highScoresTable.currentSize()) {
                name = highScoresTable.getNameByIndex(i);
                score = highScoresTable.getScoreByIndex(i);
                d.drawText(100, y, name, fontSize);
                d.drawText(700, y, Integer.toString(score), fontSize);
            }
            y += dy + fontSize;
        }
    }

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Sets back ground.
     *
     * @return the back ground
     */
    public Background setBackGround() {
        Background background = new Background(Color.LIGHT_GRAY);
        return background;
    }
}
