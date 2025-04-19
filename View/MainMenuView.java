package View;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays the main menu of the game.
 * <p>
 * This class includes buttons for navigation to GameModeView, TutorialView, HighscoreBoardView and SettingView.
 */
public class MainMenuView extends BaseView{

    private JPanel centerPanel;

    private EmptyBorder buttonsBorder;

    protected JButton startButton;
    protected JButton tutorialButton;
    protected JButton highscoreButton;
    protected JButton settingsButton;

    /**
     * Constructs the MainMenu and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public MainMenuView() {
        super("Start Menu");

        startButton = new JButton("START");
        tutorialButton = new JButton("TUTORIAL");
        highscoreButton = new JButton("HIGHSCORE");
        settingsButton = new JButton("SETTINGS");

        createStartMenuCenterPanel();

    }

    /**
     * Creates a center panel containing buttons for navigation to GameModeView, TutorialView, HighscoreBoardView and SettingView.
     * <p>
     * This panel uses Gridlayout to properly arrange the navigation buttons vertically:
     * - The start button is placed on top
     * - The tutorial button is placed second
     * - The highscore button is placed third
     * - The settings button is placed in the bottom
     * An EmptyBorder is applied to visually center the buttons vertically and horizontally within the panel.
     * This panel is added to the center container of the frame.
     */
     private void createStartMenuCenterPanel(){

        centerPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        buttonsBorder = new EmptyBorder(250, 300, 120, 300);

        centerPanel.add(startButton);
        centerPanel.add(tutorialButton);
        centerPanel.add(highscoreButton);
        centerPanel.add(settingsButton);
        centerPanel.setBorder(buttonsBorder);

        frame.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the MainMenuView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenuView startMenu = new StartMenuView();
            startMenu.show();
        });
    } */
}
