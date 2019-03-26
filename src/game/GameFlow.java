package game;

import animation.AnimationRunner;
import animation.GameOverScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.WinningScreen;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import constans.Constants;
import levels.DefaultLevel;
import levels.LevelInformation;
import score.HighScoresTable;
import score.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numbOfLives;
    private HighScoresTable highScoresTable;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar              the ar
     * @param highScoresTable the high scores table
     */
    public GameFlow(AnimationRunner ar, HighScoresTable highScoresTable) {
        this.keyboardSensor = ar.getKeyboardSensor();
        this.animationRunner = ar;
        this.numbOfLives = new Counter(Constants.NUM_OF_LIFE);
        this.score = new Counter(0);
        this.gui = ar.getGui();
        this.highScoresTable = highScoresTable;
    }

    /**
     * Gets high scores table.
     *
     * @return the high scores table
     */
    public HighScoresTable getHighScoresTable() {
        return highScoresTable;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        DefaultLevel currentLevel;
        for (LevelInformation levelInfo : levels) {
            currentLevel = (DefaultLevel) levelInfo.clone();
            GameLevel level = new GameLevel(
                    currentLevel,
                    this.keyboardSensor,
                    this.animationRunner,
                    this.score,
                    this.numbOfLives,
                    this.gui);

            level.initialize();

            while (!(level.noBlocksLeft()) && !(level.noMoreLives())) {
                level.playOneTurn();
            }
            if (level.noMoreLives()) {
                this.animationRunner.run(
                        new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                                new GameOverScreen(score.getValue())
                        ));
                addHighscore();
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(highScoresTable)
                ));
                return;
            }
        }
        this.animationRunner.run(
                new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new WinningScreen(score.getValue())
                ));
        addHighscore();
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY,
                new HighScoresAnimation(highScoresTable)
        ));
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets numb of lives.
     *
     * @return the numb of lives
     */
    public Counter getNumbOfLives() {
        return numbOfLives;
    }

    /**
     * Gets keyboard sensor.
     *
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return keyboardSensor;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return score;
    }

    /**
     * Gets score value.
     *
     * @return the score
     */
    public int getScoreValue() {
        return score.getValue();
    }

    /**
     * Gets animation runner.
     *
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return animationRunner;
    }

    /**
     * Add result.
     */
    public void addHighscore() {
        int currentRank = highScoresTable.getRank(getScoreValue());
        if (currentRank != -1) {
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            highScoresTable.add(new ScoreInfo(name, getScoreValue()));
            try {
                highScoresTable.save(new File(Constants.FILE_PATH));
            } catch (IOException e) {
                System.out.println("Failed to save");
            }
        }
    }
}