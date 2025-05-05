package View.Game;

import Model.Score.HighscoreIO;
import Model.Score.Highscores;
import Model.Score.Score;
import View.ComponentsUtilities.BaseView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * View displayed when player loses the game.
 * <p>
 * This class includes a header with a title.
 * It also includes a main section that contains buttons to
 * either play the game again or return to the main menu.
 */
public class GameOverView2 extends BaseView {

    private Score score;
    private HighscoreIO highscoreIO;

    private Highscores newHighscore;
    private JPanel headerPanel;

    private EmptyBorder headerBorder;
    private JLabel titleLabel;

    private JPanel outerCenterPanel;

    private GridBagConstraints scoresLayout;
    private JPanel scoresPanel;
    private JLabel scoreLabel;
    private JLabel highscoreLabel;
    private JPanel innerCenterPanel;

    private JPanel bottomPanel;

    protected JButton playAgainButton;
    protected JButton mainMenuButton;

    private EmptyBorder bottomButtonsBorder;

    /**
     * Constructs the GameOverView2 and sets up all UI components.
     * <p>
     * This constructor initializes the buttons and calls methods to create the header, center, and bottom panels.
     * It also sets the background image and initializes the score and highscore management.
     * <p>
     * @param score the player's final score to be displayed
     */
    public GameOverView2(double score) {
        super("Game Over 2", "/Resources/Images/Backgrounds/Background1.png");

        playAgainButton = new JButton("PLAY AGAIN");
        mainMenuButton = new JButton("MAIN MENU");

        this.score = new Score(score);
        this.highscoreIO = new HighscoreIO();

        createGameOverHeader();
        createGameOverCenterPanel();
        createGameOverBottomPanel();
    }

    // Getters
    /**
     * Gets the "Play Again" button.
     * <p>
     * @return the JButton for the "Play Again" action
     */
    public JButton getPlayAgainButton() {
        return playAgainButton;
    }

    /**
     * Gets the "Main Menu" button.
     * <p>
     * @return the JButton for the "Main Menu" action
     */
    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    // Setters
    /**
     * Sets the "Play Again" button.
     * <p>
     * @param playAgainButton the JButton to set for the "Play Again" action
     */
    public void setPlayAgainButton(JButton playAgainButton) {
        this.playAgainButton = playAgainButton;
    }

    /**
     * Sets the "Main Menu" button.
     * <p>
     * @param mainMenuButton the JButton to set for the "Main Menu" action
     */
    public void setMainMenuButton(JButton mainMenuButton) {
        this.mainMenuButton = mainMenuButton;
    }

    /**
     * Creates a header panel containing the view title.
     * <p>
     * This header panel uses BorderLayout to provide better control over the layout and positioning of its components.
     * <p>
     * Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * The panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createGameOverHeader() {
        headerPanel = new JPanel(new BorderLayout());
        headerBorder = new EmptyBorder(50, 0, 0, 0);

        titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);
        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates and displays the center panel for the Game Over view, showing the player's score and the high score.
     * <p>
     * This panel consists of:
     * - An outer transparent panel that manages the layout and provides spacing.
     * - A white inner panel, which is reserved for future use or additional elements.
     * - A scores panel that displays both the current score and the high score:
     *   - If the current score is a new high score, it is displayed with special formatting.
     *   - The current score is retrieved from the `Score` class, while the high score is loaded from the `highscores2.txt` file.
     *   - If the current score exceeds the previous high score, it is automatically saved as the new high score.
     * <p>
     * The outer and scores panels are made transparent by setting their `opaque` property to `false`, allowing the
     * background image to remain visible through the panels.
     * <p>
     * This method adds the completed panel to the center container of the frame.
     * <p>
     * Note: The method also handles reading and writing to the `highscores2.txt` file, ensuring that the high score data is updated
     * accordingly based on the player's performance in the game.
     */
    private void createGameOverCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 50, 50));

        innerCenterPanel = new JPanel(new BorderLayout());
        innerCenterPanel.setBackground(Color.WHITE);

        scoresPanel = new JPanel(new GridBagLayout());
        scoresLayout = new GridBagConstraints();
        scoresLayout.insets = new Insets(5, 300, 5, 300);
        scoresLayout.anchor = GridBagConstraints.WEST;

        int currentScore = this.score.getCurrentScore();

        highscoreIO = new HighscoreIO();

        java.util.List<Highscores> highscores;
        try {
            highscores = highscoreIO.readFile("Resources/Textfiles/highscores2.txt");
        } catch (FileNotFoundException e) {
            highscores = new ArrayList<>();
        }

        boolean isNewHighscore = highscores.isEmpty() || currentScore > highscores.get(0).getScore();

        if (isNewHighscore) {
            newHighscore = new Highscores("Player", 0, currentScore);
            try {
                highscoreIO.sortAndWrite(newHighscore, "Resources/Textfiles/highscores2.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        scoreLabel = new JLabel(isNewHighscore ? "New Highscore: " + currentScore : "Score: " + currentScore, SwingConstants.LEFT);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setPreferredSize(new Dimension(200, 30));

        double highscore = highscores.isEmpty() ? currentScore : highscores.get(0).getScore();
        highscoreLabel = new JLabel("Highscore: " + highscore, SwingConstants.LEFT);
        highscoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highscoreLabel.setPreferredSize(new Dimension(200, 30));

        scoresLayout.gridx = 0;
        scoresLayout.gridy = 0;
        scoresPanel.add(scoreLabel, scoresLayout);

        scoresLayout.gridx = 1;
        scoresLayout.gridy = 0;
        scoresPanel.add(highscoreLabel, scoresLayout);

        outerCenterPanel.add(scoresPanel, BorderLayout.NORTH);
        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.setOpaque(false);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a bottom panel that contains two buttons, to either play the game again or return to the main menu.
     * <p>
     * Buttons are spaced horizontally using an EmptyBorder for visual separation.
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the south container of the frame.
     */
    private void createGameOverBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 2, 150, 0));
        bottomButtonsBorder = new EmptyBorder(0, 50, 50, 50);


        Dimension buttonSize = new Dimension(200, 50);
        playAgainButton.setPreferredSize(buttonSize);
        mainMenuButton.setPreferredSize(buttonSize);

        bottomPanel.add(playAgainButton);
        bottomPanel.add(mainMenuButton);
        bottomPanel.setBorder(bottomButtonsBorder);
        bottomPanel.setOpaque(false);

        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the GameOverView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            double score = 8888; // Example score
            GameOverView2 gameOverView2 = new GameOverView2(score);
            gameOverView2.show();
        });
    }*/
}
