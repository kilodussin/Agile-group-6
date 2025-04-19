package Model;

public class Highscores {

    private String playerName;
    private int time;
    private double score;

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
