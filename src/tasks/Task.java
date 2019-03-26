package tasks;

/**
 * The interface Task.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {
    /**
     * Run the task.
     *
     * @return the task to run.
     */
    T run();
}