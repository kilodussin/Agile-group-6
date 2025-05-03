package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import View.GameView;
import Model.HighscoreIO;

/**
 CountdownTimer is responsible for the in-game timer.
 * We adjust the time with the final int timer
 * timeLabel is the label for displaying the time in view
 */


public class CountdownTimer {

    private JLabel timeLabel;
    private Timer mainTimer;
    private GameView gameView;

    private HighscoreIO highscoreIO;


    /** Timer could be edited dynamically, if we choose to allow it...
     */
    private final int timerInSeconds = 120;
    private int timeLeft;

    /**
     Constructs a CountdownTimer, takes gameview and highscores parameters.
     uses buildTimerVisuals() to build the visuals
     starts the actual timer with startTimer();
     */

    public CountdownTimer(GameView gameView, HighscoreIO highscoreIO) {
        this.highscoreIO = highscoreIO;
        this.gameView = gameView;
        timeLeft = timerInSeconds;
        buildTimerVisuals();
        startTimer();
    }

    /**
     getComponent is used to get the value (text for time left) of the timeLabel properly
     */

    public JComponent getComponent() {
        return timeLabel;
    }

    /**
     * Returns the starting time in seconds (the timer value on game start).
     * @return start time for timer
     */

    public int returnGameTime() {
        return timerInSeconds;
    }

    /**
     * Returns time left on the timer at the exact moment of calling this function.
     * @return time left at the exact moment of calling
     */

    public int getTimeLeft() {
        return timeLeft;
    }

    /** Necessary code for basic timer UI (Java Swing)
     * Should be moved to view for perfect MVC (can be done later)
     */
    private void buildTimerVisuals() {

        timeLabel = new JLabel(timerInSeconds + " s left!");
        timeLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        timeLabel.setOpaque(false);
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
                timeLabel.setText(timeLeft + " s left!");

            } else {

                mainTimer.stop();
                timeLabel.setText("Time is up! ");

                // Functionality for grabbing final score when game is over
                // Send the new Highscores object through the sortAndWrite to sort entries and write it to file
                // Could be broken out into a standalone function

                double score = gameView.getFinalScore();
                int time = returnGameTime();
                String player = "player 1";

                Highscores newEntry = new Highscores(player, time, score);
                try {
                    highscoreIO.sortAndWrite(newEntry, "Resources/highscores.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        });

        mainTimer.start();
    }
}