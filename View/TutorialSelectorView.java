package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays tutorial selection options.
 */
public class TutorialSelectorView extends BaseView {

    protected JButton tutorial1Button;
    protected JButton tutorial2Button;
    protected JButton escapeButton;
    private JPanel centerPanel;

    /**
     * Constructs the TutorialSelector and sets up all UI components.
     */
    public TutorialSelectorView() {
        super("Tutorial Selection", "/Resources/Background1.png");

        tutorial1Button = new JButton("GAME MODE 1");
        tutorial2Button = new JButton("GAME MODE 2");
        escapeButton = new JButton("ESCAPE");

        createTutorialSelectorPanel();
    }

    /**
     * Creates the center panel containing buttons for tutorial selection.
     */
    private void createTutorialSelectorPanel() {
        // Create header panel with escape button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 0));
        headerPanel.add(escapeButton, BorderLayout.WEST);

        // Create title label
        JLabel titleLabel = new JLabel("Select a Tutorial", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Add header panel to the frame
        frame.add(headerPanel, BorderLayout.NORTH);

        // Center panel with tutorial buttons
        centerPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        EmptyBorder buttonsBorder = new EmptyBorder(100, 300, 100, 300);

        centerPanel.add(tutorial1Button);
        centerPanel.add(tutorial2Button);
        centerPanel.setBorder(buttonsBorder);
        centerPanel.setOpaque(false);

        frame.add(centerPanel, BorderLayout.CENTER);
    }
}