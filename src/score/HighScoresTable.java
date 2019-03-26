package score;

import constans.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScores;

    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<>();
    }

    /**
     * Highest score int.
     *
     * @return the int
     */
    public int highestScore() {
        if (highScores.isEmpty()) {
            return 0;
        }
        return highScores.get(0).getScore();
    }

    /**
     * Lowest score int.
     *
     * @return the int
     */
    public int lowestScore() {
        if (highScores.isEmpty()) {
            return 0;
        }
        return highScores.get(size - 1).getScore();
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score sorrted way.
    public void add(ScoreInfo score) {
        int rank = this.getRank(score.getScore());
        if (rank == -1) {
            return;
        }
        // in case one score need to be deleted because no space left.
        if (highScores.size() == size) {
            highScores.remove(size - 1);
        }
        //add the score and "push down" rest of the elements"
        highScores.add(rank - 1, score);
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Current size int.
     *
     * @return the int
     */
    public int currentSize() {
        return this.highScores.size();
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Gets rank.
     *
     * @param tempScore the temp score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int tempScore) {
        int i = 0;
        for (; i < highScores.size(); i++) {
            if (highScores.get(i).getScore() < tempScore) {
                //return the location with no zeros(middle of the table).
                return i + 1;
            }
        }
        if (i < size) {
            //return the location with no zeros.(end of the table)
            return i + 1;
        }
        // no space for this rank because it small.
        return -1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {

        // use conventional 'ser' file extension for java serialized objects
        this.clear();
        HighScoresTable hst = null;
        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));

            // unsafe down casting, we better be sure that the stream really contains a HighScoresTable!
            hst = (HighScoresTable) objectInputStream.readObject();
            this.highScores = hst.getHighScores();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is unknown to the JVM
            System.err.println("Unable to find class for object in file: " + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Gets name by index.
     *
     * @param i the
     * @return the name by index
     */
    public String getNameByIndex(int i) {
        return highScores.get(i).getName();
    }

    /**
     * Gets score by index.
     *
     * @param i the
     * @return the score by index
     */
    public int getScoreByIndex(int i) {
        return highScores.get(i).getScore();
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable highScoreTable = new HighScoresTable(Constants.HIGH_SCORE_TABLE_SIZE);
        try {
            highScoreTable.load(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return highScoreTable;
        }
        return highScoreTable;
    }

    @Override
    public String toString() {
        String print = "";
        for (int i = 0; i < highScores.size(); i++) {
            print = print + "Name:" + highScores.get(i).getName() + " Score:" + highScores.get(i).getScore() + "\n";
        }
        return print;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        HighScoresTable hst = new HighScoresTable(3);
        hst.add(new ScoreInfo("eyal", 10));
        hst.add(new ScoreInfo("ron", 20));
        hst.add(new ScoreInfo("david", 30));
        hst.add(new ScoreInfo("david", 33));
        System.out.println(hst);
    }
}
