package View;

import Model.HighscoreIO;
import Model.Highscores;
import Model.Score;

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
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public GameOverView2() {
        super("Game Over 2", "/Resources/Background1.png");

        playAgainButton = new JButton("PLAY AGAIN");
        mainMenuButton = new JButton("MAIN MENU");

        createGameOverHeader();
        createGameOverCenterPanel();
        createGameOverBottomPanel();
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
     * Creates a center panel that displays the player's score and highscore.
     * <p>
     * The panel consists of:
     * - An outer transparent panel (for layout spacing)
     * - A white inner panel (currently unused but reserved for future elements)
     * - A scores panel showing both current and highscore
     *  <p>
     * The scores panel displays the current score and highscore:
     * - If the current score is a new highscore it's displayed with special formatting
     * - The score is retrieved from the Score class, and the highscore is loaded from the highscores.txt file.
     * - If the current score beats the highscore, it is automatically saved as the new highscore.
     * <p>
     * The outerCenterPanel and scoresPanel are made transparent by setting opaque to false, allowing the
     * background image to show through.
     * <p>
     * This panel is added to the center container of the frame.
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

        score = new Score();
        int currentScore = score.getCurrentScore();

        highscoreIO = new HighscoreIO();
        java.util.List<Highscores> highscores;
        try {
            highscores = highscoreIO.readFile("Resources/highscores.txt");
        } catch (FileNotFoundException e) {
            highscores = new ArrayList<>();
        }

        boolean isNewHighscore = highscores.isEmpty() || currentScore > highscores.get(0).getScore();

        if (isNewHighscore) {
            newHighscore = new Highscores("Player", 0, currentScore); // Replace "Player" with actual player name if available
            try {
                highscoreIO.sortAndWrite(newHighscore);
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameOverView2 gameOverView2 = new GameOverView2();
            gameOverView2.show();
        });
    }
}