package Model.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import Model.Score.HighscoreIO;
import Model.Score.Highscores;

import View.Game.GameView2;
import View.ViewManager;

/**
 CountdownTimer is responsible for the in-game timer.
 * We adjust the time with the final int timer
 * timeLabel is the label for displaying the time in view
 */


public class CountdownTimer2 {

    private JLabel timeLabel;
    private Timer mainTimer;
    private GameView2 gameView;

    private HighscoreIO highscoreIO;


    /**
     * Timer could be edited dynamically, if we choose to allow it...
     */
    private final int timerInSeconds = 60;
    private int timeLeft;

    /**
     * Constructs a CountdownTimer, takes gameview and highscores parameters.
     * uses buildTimerVisuals() to build the visuals
     * starts the actual timer with startTimer();
     */

    public CountdownTimer2(GameView2 gameView, HighscoreIO highscoreIO) {
        this.highscoreIO = highscoreIO;
        this.gameView = gameView;
        timeLeft = timerInSeconds;
        buildTimerVisuals();
        startTimer();
    }

    /**
     * getComponent is used to get the value (text for time left) of the timeLabel properly
     */

    public JComponent getComponent() {
        return timeLabel;
    }

    /**
     * Returns the starting time in seconds (the timer value on game start).
     *
     * @return start time for timer
     */

    public int returnGameTime() {
        return timerInSeconds;
    }

    /**
     * Returns time left on the timer at the exact moment of calling this function.
     *
     * @return time left at the exact moment of calling
     */

    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Necessary code for basic timer UI (Java Swing)
     * Should be moved to view for perfect MVC (can be done later)
     */
    private void buildTimerVisuals() {

        timeLabel = new JLabel(timerInSeconds + " s left!");
        timeLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        timeLabel.setOpaque(false);

        Dimension fixedSize = new Dimension(150, timeLabel.getPreferredSize().height);
        timeLabel.setPreferredSize(fixedSize);
        timeLabel.setMinimumSize(fixedSize);
        timeLabel.setMaximumSize(fixedSize);
    }

    /**
     * Creates Timer object with 1000 ms delay ("refresh rate")
     * timeLeft counts down by -=1 (1000 ms), which is then set on the timeLabel
     * in the else (time is up) {
     * start a function displaying the game over screen, collected trash whatnot...
     * }
     * <p>
     * When time is up, the timer stops, the final score is processed, and the second game over view
     * is displayed using the ViewManager instance.
     */
    private void startTimer() {

        mainTimer = new Timer(1000, (ActionEvent x) -> {
            timeLeft -= 1;

            if (timeLeft >= 0) {
                timeLabel.setText(timeLeft + " s left!");

            } else {

                mainTimer.stop();
                gameView.stopBackgroundMusic();

                // Functionality for grabbing final score when game is over
                // Send the new Highscores object through the sortAndWrite to sort entries and write it to file
                // Could be broken out into a standalone function

                double score = gameView.getFinalScore();
                int time = returnGameTime();
                String player = "player 1";

                Highscores newEntry = new Highscores(player, time, score);
                try {
                    highscoreIO.sortAndWrite(newEntry, "Resources/Textfiles/highscores2.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                SwingUtilities.invokeLater(() -> {
                    ViewManager.getInstance().showGameOverView2(score);
                });
            }
        });
        mainTimer.start();
    }
}