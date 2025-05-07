package View.Game;

import View.ComponentsUtilities.BaseView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays the difficulty selection buttons for the game.
 * <p>
 * This class includes a header panel with a title and an escape button.
 * It also has a center panel that consists of buttons for selecting easy, normal or hard game modes.
 */

public class GameModeView extends BaseView {
    private JPanel headerPanel;
    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;
    protected JButton gamemode1Button;
    protected JButton gamemode2Button;
    private JPanel gamemodeButtons;
    private EmptyBorder gamemodeButtonsBorder;

    /**
     * Constructs the GameModeView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public GameModeView(){
        super("Game Mode", "/Resources/Images/Backgrounds/Background1.png");

        escapeButton = new JButton("ESCAPE");

        gamemode1Button = new JButton("GAMEMODE 1");
        gamemode2Button = new JButton("GAMEMODE 2");

        createGameModeHeader();
        createGameModeCenterPanel();

    }

    // Getters

    /**
     * Returns the escape button.
     * <p>
     * @return the escape button
     */
    public JButton getEscapeButton() {
        return escapeButton;
    }

    /**
     * Returns the first game mode button.
     * <p>
     * @return the first game mode button
     */
    public JButton getGamemode1Button() {
        return gamemode1Button;
    }

    /**
     * Returns the second game mode button.
     * <p>
     * @return the second game mode button
     */
    public JButton getGamemode2Button() {
        return gamemode2Button;
    }

    // Setters

    /**
     * Sets the escape button.
     * <p>
     * @param escapeButton the escape button to set
     */
    public void setEscapeButton(JButton escapeButton) {
        this.escapeButton = escapeButton;
    }

    /**
     * Sets the first game mode button.
     * <p>
     * @param gamemode1Button the first game mode button to set
     */
    public void setGamemode1Button(JButton gamemode1Button) {
        this.gamemode1Button = gamemode1Button;
    }

    /**
     * Sets the second game mode button.
     * <p>
     * @param gamemode2Button the second game mode button to set
     */
    public void setGamemode2Button(JButton gamemode2Button) {
        this.gamemode2Button = gamemode2Button;
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
        headerBorder = new EmptyBorder(20,20,10,0);

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
        gamemodeButtons = new JPanel(new GridLayout(3, 1, 0, 20));
        gamemodeButtonsBorder = new EmptyBorder(250, 300, 120, 300);

        gamemodeButtons.add(gamemode1Button);
        gamemodeButtons.add(gamemode2Button);
        gamemodeButtons.setBorder(gamemodeButtonsBorder);
        gamemodeButtons.setOpaque(false);

        frame.add(gamemodeButtons, BorderLayout.CENTER);
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

