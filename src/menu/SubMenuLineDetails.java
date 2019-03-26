package menu;

import tasks.Task;

import java.util.List;

/**
 * The type Sub menu line details.
 *
 * @param <T> the type parameter
 */
public class SubMenuLineDetails<T> {
    private String key;
    private String levelName;
    private Task<T> chooseLevelPackage;
    private List<Boolean> isSubMenuOption;

    /**
     * Instantiates a new Sub menu line details.
     *
     * @param key                the key
     * @param levelName          the level name
     * @param chooseLevelPackage the choose level package
     */
    public SubMenuLineDetails(String key, String levelName, Task<T> chooseLevelPackage) {
        this.key = key;
        this.levelName = levelName;
        this.chooseLevelPackage = chooseLevelPackage;

    }
}
