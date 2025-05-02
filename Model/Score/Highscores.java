package Model.Score;

public class Highscores {

    private String playerName;
    private int time;
    private double score;

    /**
     *
     * @param playerName -> Name of the player (currently NOT used, but could in the future should we allow player names).
     * @param time -> Is in game mode 1 the timer value at start (2 min for example).
     * @param score -> Is the final score the player gets after the game is over.

     Highscores object is written to a file to save the highscores in an organized manner

     */

    public Highscores(String playerName, int time, double score) {
        this.playerName = playerName;
        this.time = time;
        this.score = score;

    }

    public String getPlayerName() {
        return playerName;
    }
    public int getTime() {
        return time;
    }
    public double getScore() {
        return score;
    }

    /*
    No setters - data is read and written ONCE in highscores.txt file
     */
}
