package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays the difficulty selection buttons for the game.
 * <p>
 * This class includes a header panel with a title and an escape button.
 * It also has a center panel that consists of buttons for selecting easy, normal or hard game modes.
 */

public class GameModeView extends BaseView{
    private JPanel headerPanel;
    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;
    protected JButton easyButton;
    protected JButton normalButton;
    protected JButton hardButton;
    private JPanel difficultyButtons;
    private EmptyBorder difficultyButtonsBorder;

    /**
     * Constructs the GameModeView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public GameModeView(){
        super("Game Mode", null);

        escapeButton = new JButton("ESCAPE");

        easyButton = new JButton("EASY");
        normalButton = new JButton("NORMAL");
        hardButton = new JButton("HARD");

        createGameModeHeader();
        createGameModeCenterPanel();

    }

    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left (WEST)
     * - The title label is centered (CENTER)
     * <p>
     * Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * The panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createGameModeHeader(){

        headerPanel = new JPanel(new BorderLayout(-90,0));
        headerBorder = new EmptyBorder(10,20,10,0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("GAME MODE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);

        headerPanel.setOpaque(false);


        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel containing all difficulty buttons (Easy, Normal, Hard).
     * <p>
     * This panel uses GridLayout to properly arrange the difficulty buttons vertically:
     * - The easy button is placed on top
     * - The normal button is placed in the middle
     * - The hard button is placed at the bottom
     * <p>
     * An EmptyBorder is applied to visually center the buttons vertically and horizontally within the panel.
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the center container of the frame.
     */
    private void createGameModeCenterPanel(){
        difficultyButtons = new JPanel(new GridLayout(3, 1, 0, 20));
        difficultyButtonsBorder = new EmptyBorder(250, 300, 120, 300);

        difficultyButtons.add(easyButton);
        difficultyButtons.add(normalButton);
        difficultyButtons.add(hardButton);
        difficultyButtons.setBorder(difficultyButtonsBorder);
        difficultyButtons.setOpaque(false);

        frame.add(difficultyButtons, BorderLayout.CENTER);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the GameModeView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameModeView gameModeView = new GameModeView();
            gameModeView.show();
        });

    } */

}

