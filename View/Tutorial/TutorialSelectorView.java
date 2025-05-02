package View.Tutorial;

import View.ComponentsUtilities.BaseView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays tutorial selection options.
 */
public class TutorialSelectorView extends BaseView {

    private JPanel headerPanel;
    private EmptyBorder headerPadding;

    private JLabel titleLabel;
    protected JButton escapeButton;
    protected JButton tutorial1Button;
    protected JButton tutorial2Button;
    private JPanel centerPanel;

    /**
     * Constructs the TutorialSelector and sets up all UI components.
     */
    public TutorialSelectorView() {
        super("Tutorial Selection", "/Resources/Images/Backgrounds/Background1.png");

        escapeButton = new JButton("ESCAPE");
        tutorial1Button = new JButton("GAME MODE 1");
        tutorial2Button = new JButton("GAME MODE 2");

        createTutorialSelectorPanel();
        createTutorialSelectorHeaderPanel();
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
     * Returns the tutorial1 button.
     * <p>
     * @return the tutorial1 button
     */
    public JButton getTutorial1Button() {
        return tutorial1Button;
    }

    /**
     * Returns the tutorial2 button.
     * <p>
     * @return the tutorial2 button
     */
    public JButton getTutorial2Button() {
        return tutorial2Button;
    }

    // Setters

    /**
     * Sets the escape button.
     * <p>
     * @param escapeButton the escape button
     */
    public void setEscapeButton(JButton escapeButton) {
        this.escapeButton = escapeButton;
    }

    /**
     * Sets the tutorial1 button.
     * <p>
     * @param tutorial1Button the tutorial1 button
     */
    public void setTutorial1Button(JButton tutorial1Button) {
        this.tutorial1Button = tutorial1Button;
    }

    /**
     * Sets the tutorial2 button.
     * <p>
     * @param tutorial2Button the tutorial2 button
     */
    public void setTutorial2Button(JButton tutorial2Button) {
        this.tutorial2Button = tutorial2Button;
    }

    /**
     * Creates the header panel containing the escape button and title.
     */
    private void createTutorialSelectorHeaderPanel() {
        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerPadding = new EmptyBorder(20, 20, 10, 0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("Select a Tutorial", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        headerPanel.setBorder(headerPadding);
        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates the center panel containing buttons for tutorial selection.
     */
    private void createTutorialSelectorPanel() {
        centerPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        EmptyBorder buttonsBorder = new EmptyBorder(100, 300, 100, 300);

        centerPanel.add(tutorial1Button);
        centerPanel.add(tutorial2Button);
        centerPanel.setBorder(buttonsBorder);
        centerPanel.setOpaque(false);

        frame.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the TutorialSelectorView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialSelectorView tutorialSelectorView = new TutorialSelectorView();
            tutorialSelectorView.show();
        });
    } */
}