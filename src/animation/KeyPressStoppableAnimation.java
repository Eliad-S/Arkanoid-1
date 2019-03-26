package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboard;
    private boolean stop;
    private String stopKey;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param keyboard  the keyboard
     * @param stopKey   the stop key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String stopKey, Animation animation) {
        this.animation = animation;
        this.keyboard = keyboard;
        this.stopKey = stopKey;
        this.stop = false;
        this.isAlreadyPressed = true;
    }


    /**
     * Do one frame.
     *
     * @param d  the surface to print on.
     * @param dt the dt
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        animation.doOneFrame(d, dt);
        String exit = "Press " + this.stopKey + " to exit.";
        d.setColor(Color.BLACK);
        d.drawText(300, 590, exit, 20);
        if (!(this.keyboard.isPressed(this.stopKey))) {
            this.isAlreadyPressed = false;
        } else if (!isAlreadyPressed && this.keyboard.isPressed(stopKey)) {
            this.stop = true;
        }
    }

    /**
     * check if should stop.
     *
     * @return if should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
