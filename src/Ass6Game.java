import game.GameInitilize;

/**
 * class that run the game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameInitilize game;
        if (args.length > 0) {
            game = new GameInitilize(args[0]);
        } else {
            game = new GameInitilize("level_sets.txt");
        }
        game.runGame();
    }
}
