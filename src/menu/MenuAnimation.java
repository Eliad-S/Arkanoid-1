package menu;

import animation.AnimationRunner;
import background.Background;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<MenuSelection> menuSelectionList;
    private KeyboardSensor keyboard;
    private T status;
    private boolean isAlreadyPressed;
    private AnimationRunner animationRunner;

    /**
     * Instantiates a new Menu animation.
     *
     * @param keyboard        the keyboard
     * @param animationRunner the animation runner
     */
    public MenuAnimation(KeyboardSensor keyboard, AnimationRunner animationRunner) {
        this.menuSelectionList = new ArrayList<>();
        this.keyboard = keyboard;
        this.status = null;
        this.isAlreadyPressed = true;
        this.animationRunner = animationRunner;
    }

    /**
     * Add selection.
     *
     * @param key     the key
     * @param message the message
     * @param val     the val
     */
    public void addSelection(String key, String message, T val) {
        this.menuSelectionList.add(new MenuSelection(key, message, val, null, false));
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.menuSelectionList.add(new MenuSelection(key, message, null, subMenu, true));
    }

    /**
     * Do one frame.
     *
     * @param d  the surface to print on.
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Background background = new Background(Color.RED.darker());
        background.drawOn(d);
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 4 + 1, d.getHeight() / 5, "Menu", 60);
        int dy = 0;
        for (MenuSelection<T> s : this.menuSelectionList) {
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 4 + 1, d.getHeight() / 3 + dy,
                    "(" + s.getKey() + ") " + s.getMessage(), 50);
            d.drawText(d.getWidth() / 4 - 1, d.getHeight() / 3 + dy,
                    "(" + s.getKey() + ") " + s.getMessage(), 50);
            d.drawText(d.getWidth() / 4, d.getHeight() / 3 + 1 + dy,
                    "(" + s.getKey() + ") " + s.getMessage(), 50);
            d.drawText(d.getWidth() / 4, d.getHeight() / 3 - 1 + dy,
                    "(" + s.getKey() + ") " + s.getMessage(), 50);
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 4, d.getHeight() / 3 + dy,
                    "(" + s.getKey() + ") " + s.getMessage(), 50);
            dy += 100;
        }
        for (MenuSelection<T> selections : this.menuSelectionList) {
            if (this.keyboard.isPressed(selections.getKey())) {
                if (selections.isSubMenu()) {
                    this.animationRunner.run(selections.getSubMenu());
                    this.status = selections.getSubMenu().getStatus();
                    selections.getSubMenu().doOneFrame(d, dt);
                    selections.getSubMenu().resetMenu();
                    break;
                } else {
                    this.status = selections.getValue();
                }

            }
        }
    }

    /**
     * this method resets this menu.
     */
    public void resetMenu() {
        this.status = null;
    }

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public T getStatus() {
        T temp = this.status;
        this.status = null;
        return temp;
    }
}
