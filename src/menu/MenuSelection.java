package menu;

/**
 * The type Menu selection.
 *
 * @param <T> the type parameter
 */
public class MenuSelection<T> {
    private String key;
    private String message;
    private T value;
    private Menu<T> subMenu;
    private boolean isSubMenu;

    /**
     * Instantiates a new Menu selection.
     *
     * @param key       the key
     * @param message   the message
     * @param value     the value
     * @param subMenu   the sub menus
     * @param isSubMenu the is sub menu
     */
    public MenuSelection(String key, String message, T value, Menu subMenu, boolean isSubMenu) {
        this.key = key;
        this.message = message;
        this.value = value;
        this.isSubMenu = isSubMenu;
        this.subMenu = subMenu;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Is sub menu boolean.
     *
     * @return the boolean
     */
    public boolean isSubMenu() {
        return isSubMenu;
    }

    /**
     * Gets sub menu.
     *
     * @return the sub menu
     */
    public Menu<T> getSubMenu() {
        return this.subMenu;
    }
}
