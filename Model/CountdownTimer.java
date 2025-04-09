package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CountdownTimer {

    private JLabel timeLabel;
    private Timer mainTimer;

    /** Timer could be edited dynamically, if we choose to allow it...
     */
    private final int timerInSeconds = 10;
    private int timeLeft;

    /** _____________________________________________________________
     */

    public CountdownTimer() {
        timeLeft = timerInSeconds;
        buildTimerVisuals();
        startTimer();
    }

    /** Necessary code for basic timer UI (Java Swing)
     * Should be moved to view for perfect MVC (can be done later)
     */
    private void buildTimerVisuals() {

        timeLabel = new JLabel(timerInSeconds + " secs left!");
        timeLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
    }

    /**
     * Creates Timer object with 1000 ms delay ("refresh rate")
     * timeLeft counts down by -=1 (1000 ms), which is then set on the timeLabel
     * in the else (time is up) {
     *     start a function displaying the game over screen, collected trash whatnot...
     * }
     */
    private void startTimer() {


        mainTimer = new Timer(1000, (ActionEvent x) -> {
            timeLeft-=1;

            if (timeLeft >= 0) {
                timeLabel.setText(timeLeft + " secs left!");

            } else {

                mainTimer.stop();
                timeLabel.setText("Time is up! ");

                // Fire up the game over screen...
            }

        });

        mainTimer.start();
    }
}