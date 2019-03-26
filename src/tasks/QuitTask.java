package tasks;

/**
 * The type Quit task.
 */
public class QuitTask implements Task<Void> {
    /**
     * Run the task.
     *
     * @return the task to run.
     */
    @Override
    public Void run() {
        System.exit(0);
        return null;
    }
}
