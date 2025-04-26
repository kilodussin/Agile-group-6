package Model;

import Model.Textbox.Textbox;
import Model.Trash.Trash;
import Model.Trashcan.Trashcan;
// import Model.Textbox.Textbox;

/**
 * Score class
 * <p>
 * This class is responsible for managing the player's score in the game.
 * It keeps track of the current score, updates it when trash is sorted,
 * and provides methods to reset or retrieve the score.
 * Points are added when trash is correctly sorted and subtracted when
 * incorrectly sorted, ensuring that the score does not go below zero.
 */

public class Score {

    /*--------------------Fields--------------------*/

    private int currentScore;

    /*--------------------Constructor--------------------*/

    /**
     * Constructs a new Score instance with a score of 0
     */
    public Score(double initialScore) {
        this.currentScore = (int) initialScore;
    }

   /**
     * Constructs a new Score instance with a default score of 0.
     */
    public Score() {
        this.currentScore = 0;
    }
    /*--------------------Getters--------------------*/

    /**
     * Getter for the current score
     *
     * @return the current score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /*--------------------Methods--------------------*/

    /**
     * Updates the score based on whether the trash was sorted correctly
     *
     * @param trash the trash being sorted
     * @param trashcan the trashcan where the trash was sorted
     */
    public void updateScoreTrash(Trash trash, Trashcan trashcan) {
        if (trashcan.isCorrectlySorted(trash)) {
            addPoints(trash.getPoints());
        } else {
            subtractPoints(trash.getPoints());
        }
    }

   /**
    * Updates the score based on whether the correct combination of trash and description was chosen.
    *
    * @param trash   the trash being sorted
    * @param textbox the textbox containing the description to check against
    */
   public void updateScoreDescription(Trash trash, Textbox textbox) {
       if (trash.getCorrectDescription().equals(textbox.getDescription())) {
           addPoints(trash.getPoints());
       } else {
           subtractPoints(trash.getPoints());
       }
   }

    /**
     * Adds points to the current score
     *
     * @param points the points to add
     * @throws IllegalArgumentException if points are negative
     */
    private void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points to add cannot be negative.");
        }
        this.currentScore += points;
    }

    /**
     * Subtracts points from the current score
     *
     * @param points the points to subtract
     * @throws IllegalArgumentException if points are negative
     */
    private void subtractPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points to subtract cannot be negative.");
        }
        this.currentScore -= points;
        if (this.currentScore < 0) {
            this.currentScore = 0; // Sets score to 0 if it goes negative
        }
    }

    /**
     * Resets the score to 0 (don't know if this is needed)
     */
    public void resetScore() {
        this.currentScore = 0;
    }
}