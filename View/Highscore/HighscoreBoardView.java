package View.Highscore;

import Model.Score.HighscoreIO;
import Model.Score.Highscores;
import View.ComponentsUtilities.BaseView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * View that displays a highscore board
 * <p>
 * This class includes a header consisting of a title.
 * It also has a main section meant to hold a highscore board later in the development process.
 * The main section currently serves as a visual placeholder.
 */
public class HighscoreBoardView extends BaseView {

    private JPanel headerPanel;

    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;
    private JLabel gameMode1Label;
    private JPanel highscoreGameMode1;
    private JTextArea highscoreTextArea;
    private JButton nextButton;
    private JPanel highscoreGameMode2;
    private JTextArea highscoreTextArea2;
    private JLabel gameMode2Label;
    private JButton backButton;
    private CardLayout cardLayout;

    /**
     * Constructs the HighscoreBoardView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public HighscoreBoardView() {
        super("Highscore Board", "/Resources/Images/Backgrounds/Background1.png");

        escapeButton = new JButton("ESCAPE");

        createHighscoreHeader();
        createHighscoreCenterPanel();
    }

    // Getters
    /**
     * Gets the "Escape" button.
     * <p>
     * @return the JButton for the "Escape" action
     */
    public JButton getEscapeButton() {
        return escapeButton;
    }

    // Setters
    /**
     * Sets the "Escape" button.
     * <p>
     * @param escapeButton the JButton to set for the "Escape" action
     */
    public void setEscapeButton(JButton escapeButton) {
        this.escapeButton = escapeButton;
    }

    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The title label is centered
     * <p>
     * Padding is applied using EmptyBorder to create space around the header content.
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the center container of the frame.
     */
    private void createHighscoreHeader() {

        headerPanel = new JPanel(new BorderLayout(-100, 0));
        headerBorder = new EmptyBorder(20, 20, 10, 10);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("HIGHSCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates and displays the center panel for the Highscore view with a CardLayout to switch between two game modes.
     * <p>
     * This method utilizes two nested panels:
     * - The outer panel provides layout spacing and is used to hold the inner panel with the CardLayout.
     * - The inner panel, which employs a `CardLayout`, contains two cards that represent two different game modes.
     *   - The first card (Game Mode 1) contains a label and a button to navigate to the second card (Game Mode 2)
     *     and a `JTextArea` displaying the high scores loaded from the `highscores1.txt` file.
     *   - The second card (Game Mode 2) contains a label, a back button to return to the first card,
     *     and a `JTextArea` displaying the high scores loaded from the `highscores2.txt` file.
     * <p>
     * The outer panel is made transparent by setting its `opaque` property to `false`, which allows the background image to show through.
     * <p>
     * This panel is added to the center container of the frame.
     */
    private void createHighscoreCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBackground(Color.WHITE);

        highscoreGameMode1 = new JPanel(new BorderLayout());
        highscoreGameMode1.setBackground(Color.WHITE);
        gameMode1Label = new JLabel("Highscores Game Mode 1", SwingConstants.CENTER);
        gameMode1Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gameMode1Label.setFont(new Font("Arial", Font.BOLD, 20));
        highscoreGameMode1.add(gameMode1Label, BorderLayout.NORTH);
        nextButton = new JButton("→");
        nextButton.addActionListener((ActionEvent e) -> cardLayout.show(innerCenterPanel, "Highscores Game Mode 2"));
        highscoreGameMode1.add(nextButton, BorderLayout.EAST);

        highscoreTextArea = new JTextArea(10, 30);
        highscoreTextArea.setEditable(false);
        highscoreTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        highscoreTextArea.setText(getHighscoresFromFile("Resources/Textfiles/highscores.txt"));
        highscoreTextArea.setBorder(BorderFactory.createEmptyBorder(10, 315, 10, 10));

        highscoreGameMode1.add(highscoreTextArea, BorderLayout.CENTER);

        highscoreGameMode2 = new JPanel(new BorderLayout());
        highscoreGameMode2.setBackground(Color.WHITE);
        gameMode2Label = new JLabel("Highscores Game Mode 2", SwingConstants.CENTER);
        gameMode2Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        gameMode2Label.setFont(new Font("Arial", Font.BOLD, 20));
        highscoreGameMode2.add(gameMode2Label, BorderLayout.NORTH);

        backButton = new JButton("←");
        backButton.addActionListener((ActionEvent e) -> cardLayout.show(innerCenterPanel, "Highscores Game Mode 1"));
        highscoreGameMode2.add(backButton, BorderLayout.WEST);

        highscoreTextArea2 = new JTextArea(10, 30);
        highscoreTextArea2.setEditable(false);
        highscoreTextArea2.setFont(new Font("Arial", Font.PLAIN, 20));
        highscoreTextArea2.setText(getHighscoresFromFile("Resources/Textfiles/highscores2.txt"));
        highscoreTextArea2.setBorder(BorderFactory.createEmptyBorder(10, 240, 10, 10));

        highscoreGameMode2.add(highscoreTextArea2, BorderLayout.CENTER);

        innerCenterPanel.add(highscoreGameMode1, "Highscores Game Mode 1");
        innerCenterPanel.add(highscoreGameMode2, "Highscores Game Mode 2");

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.setOpaque(false);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Gets the high scores from the given file path and formats them into a string.
     *
     * @param filePath the path to the high score file
     * @return a string representing the high scores
     */
  private String getHighscoresFromFile(String filePath) {
      StringBuilder highscoreString = new StringBuilder();

      try {
          HighscoreIO highscoreIO = new HighscoreIO();
          ArrayList<Highscores> highscores = highscoreIO.readFile(filePath);

          for (Highscores hs : highscores) {
              highscoreString.append(hs.getPlayerName())
                      .append(" - Time: ").append(hs.getTime())
                      .append(", Score: ").append(hs.getScore())
                      .append("\n");
          }
      } catch (FileNotFoundException e) {
          highscoreString.append("Error loading high scores from file.");
      }
      return highscoreString.toString();
  }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the HighscoreBoardView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HighscoreBoardView highscoreBoard = new HighscoreBoardView();
            highscoreBoard.show();
        });
    } */
}