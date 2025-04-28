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
    private JPanel topPanel;
    private JLabel iconLabel;
    private ImageIcon logo;

    private EmptyBorder buttonsBorder;

    private EmptyBorder logoBorder;
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
        super("Start Menu", "/Resources/Background1.png");

        startButton = new JButton("START");
        tutorialButton = new JButton("TUTORIALS");
        highscoreButton = new JButton("HIGHSCORE");
        settingsButton = new JButton("SETTINGS");

        iconLabel = new JLabel();

        createStartMenuCenterPanel();
        createStartMenuTopPanel();

    }

    /**
     * Creates a top panel containing the logo image for the game.
     * <p>
     * This panel uses a BorderLayout in combination with an EmptyBorder
     * to visually position the logo.
     * <p>
     * This panel is added to the north container of the frame.
     */

    private void createStartMenuTopPanel(){
        topPanel = new JPanel(new BorderLayout());
        logoBorder = new EmptyBorder(130, 355,20,0);

        logo = new ImageIcon(getClass().getResource("/Resources/Logo.png"));
        Image rawimage = logo.getImage();
        Image resizedLogo = rawimage.getScaledInstance(310,230, Image.SCALE_SMOOTH);
        logo = new ImageIcon(resizedLogo);
        iconLabel.setIcon(logo);
        topPanel.setBorder(logoBorder);

        topPanel.add(iconLabel, BorderLayout.CENTER);
        topPanel.setOpaque(false);

        frame.add(topPanel, BorderLayout.NORTH);

    }

    /**
     * Creates a center panel containing buttons for navigation to GameModeView, TutorialView, HighscoreBoardView and SettingView.
     * <p>
     * This panel uses Gridlayout to properly arrange the navigation buttons vertically:
     * - The start button is placed on top
     * - The tutorial button is placed second
     * - The highscore button is placed third
     * - The settings button is placed in the bottom
     * <p>
     * An EmptyBorder is applied to visually center the buttons vertically and horizontally within the panel.
     * <p>
     * This panel is added to the center container of the frame.
     */

    private void createStartMenuCenterPanel(){

        centerPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        buttonsBorder = new EmptyBorder(0, 300, 50, 300);


        /*Dimension buttonSize = new Dimension(200, 50);
        playAgainButton.setPreferredSize(buttonSize);
        mainMenuButton.setPreferredSize(buttonSize); */
        centerPanel.add(startButton);
        centerPanel.add(tutorialButton);
        centerPanel.add(highscoreButton);
        centerPanel.add(settingsButton);
        centerPanel.setBorder(buttonsBorder);
        centerPanel.setOpaque(false);

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
            MainMenuView startMenu = new MainMenuView();
            startMenu.show();
        });
    } */
}