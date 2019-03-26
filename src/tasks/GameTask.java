package tasks;

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import levels.LevelInformation;
import score.HighScoresTable;

import java.util.List;

/**
 * The type Game task.
 */
public class GameTask implements Task<Void> {
    private AnimationRunner an;
    private List<LevelInformation> levelList;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    private GUI gui;
    private GameFlow gameFlow;

    /**
     * Instantiates a new Game task.
     *
     * @param an              the an
     * @param levelList       the level list
     * @param highScoresTable the high scores table
     */
    public GameTask(AnimationRunner an, List<LevelInformation> levelList, HighScoresTable highScoresTable) {
        this.an = an;
        this.levelList = levelList;
        this.keyboardSensor = an.getKeyboardSensor();
        this.highScoresTable = highScoresTable;
        this.gui = an.getGui();
        this.gameFlow = new GameFlow(an, highScoresTable);

    }


    /**
     * Gets high score table.
     *
     * @return the high score table
     */
    public HighScoresTable getHighScoreTable() {
        return this.gameFlow.getHighScoresTable();
    }

    /**
     * Run the task.
     *
     * @return the task to run.
     */
    @Override
    public Void run() {
        this.gameFlow = new GameFlow(this.an, this.highScoresTable);
        this.gameFlow.runLevels(levelList);
        return null;
    }
}
