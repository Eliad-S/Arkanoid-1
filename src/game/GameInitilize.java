package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import constans.Constants;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import readfromfile.LevelSpecificationReader;
import readfromfile.Utils;
import score.HighScoresTable;
import tasks.GameTask;
import tasks.QuitTask;
import tasks.ShowHiScoresTask;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type Game initilize.
 */
public class GameInitilize {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    private GUI gui;
    private String path;
    private int numOflifes;
    private Utils util = new Utils();

    /**
     * Instantiates a new Game initilize.
     *
     * @param path the path
     */
    public GameInitilize(String path) {
        setHighScoresTable();
        this.gui = new GUI("Arkanoid", Constants.GUI_WIDTH, Constants.GUI_HEIGHT);
        this.animationRunner = new AnimationRunner(this.gui, 60);
        this.keyboardSensor = gui.getKeyboardSensor();
        this.numOflifes = 1;
        this.path = path;

    }

    /**
     * The Set menu.
     *
     * @return the menu
     */
    public Menu<Task<Void>> setMenu() {
        Menu<Task<Void>> menu = new MenuAnimation<>(this.keyboardSensor, this.animationRunner);
        Animation highScoresAnimation = new HighScoresAnimation(highScoresTable);
        menu.addSubMenu("c", "choose levels", initializeSubMenu());
        menu.addSelection("h", "High scores", new ShowHiScoresTask(animationRunner, highScoresAnimation));
        menu.addSelection("q", "Quit", new QuitTask());

        return menu;
    }

    /**
     * Initialize sub menu menu.
     *
     * @return the menu
     */
    public Menu<Task<Void>> initializeSubMenu() {
        Menu<Task<Void>> subMenu = new MenuAnimation<>(this.keyboardSensor, this.animationRunner);
        //the reader of the file
        InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(ireader);
        //split the file to lines.
        List<String> divideFileToLines = util.divideIntoLines(reader);

        if (divideFileToLines.size() % 2 != 0) {
            throw new RuntimeException("odd number of lines in main file");
        }

        // split the odd lines to key an name value, and the second line to a path.
        for (int i = 0; i < divideFileToLines.size(); i += 2) {
            //divide the line by ":"
            String[] parts = divideFileToLines.get(i).split(":");
            if (parts.length != 2) {
                throw new RuntimeException("cant split the file levellists");
            }
            String key = parts[0];
            String name = parts[1];
            String gameTaskpath = divideFileToLines.get(i + 1);
            GameTask gameTask = setGameTaskByPath(gameTaskpath);
            subMenu.addSelection(key, name, gameTask);
        }
        return subMenu;
    }

    /**
     * Sets game task by path.
     *
     * @param gameTaskpath the game taskpath
     * @return the game task by path
     */
    public GameTask setGameTaskByPath(String gameTaskpath) {
        InputStream ireader = ClassLoader.getSystemClassLoader().getResourceAsStream(gameTaskpath);
        InputStreamReader reader = new InputStreamReader(ireader);
        // create the level from a reader
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        List<LevelInformation> levels = levelSpecificationReader.fromReader(reader);
        //initilize game task specific by list added by reader.
        GameTask gameTask = new GameTask(animationRunner, levels, highScoresTable);
        return gameTask;
    }

    /**
     * Run game.
     */
    public void runGame() {
        Menu<Task<Void>> menu = setMenu();
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * Sets high scores table.
     *
     */
    public void setHighScoresTable() {
        // cant use loadFromFile if not declerd.
        this.highScoresTable = null;
        File file = new File(Constants.FILE_PATH);
        // If the file already exists, load it.
        if (file.exists() && !file.isDirectory()) {
            this.highScoresTable = highScoresTable.loadFromFile(file);
            return;
        } else {
            try {
                this.highScoresTable = new HighScoresTable(10);
                this.highScoresTable.save(file);
            } catch (IOException e) {
                System.out.println("A failure occurred while saving");
            }
        }
    }
}
