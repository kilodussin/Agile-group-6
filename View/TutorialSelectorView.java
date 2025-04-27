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
    private JPanel centerPanel;

    /**
     * Constructs the TutorialSelector and sets up all UI components.
     */
    public TutorialSelectorView() {
        super("Tutorial Selection", "/Resources/Background1.png");

        tutorial1Button = new JButton("GAME MODE 1");
        tutorial2Button = new JButton("GAME MODE 2");

        createTutorialSelectorPanel();
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

        JLabel titleLabel = new JLabel("Select a Tutorial", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setOpaque(false);
        titlePanel.setBorder(new EmptyBorder(50, 0, 0, 0));

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
    }
}