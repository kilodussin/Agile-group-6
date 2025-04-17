package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays a highscore board
 * <p>
 * This class includes a header consisting of a title.
 * It also has a main section meant to hold a highscore board later in the development process.
 * The main section currently serves as a visual placeholder.
 */
public class HighscoreBoardView extends BaseView{

    private JPanel headerPanel;

    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;

    /**
     * Constructs the HighscoreBoardView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public HighscoreBoardView(){
        super("Highscore Board", null);

        escapeButton = new JButton("ESCAPE");

        createHighscoreHeader();
        createHighscoreCenterPanel();
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
     * This panel is added to the center container of the frame.
     */
    private void createHighscoreHeader(){

        headerPanel = new JPanel(new BorderLayout(-100, 0));
        headerBorder = new EmptyBorder(10,20,10,10);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("HIGHSCORE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);


        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel – currently used as a visual placeholder.
     * <p>
     * This layout uses two nested panels: An outer panel and a white inner panel,
     * to give a clearer idea of where future components can be placed.
     * It's purely for layout visualization and can be easily removed or replaced later.
     * <p>
     * The outerCenterPanel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the center of the frame.
     */
    private void createHighscoreCenterPanel(){
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        innerCenterPanel = new JPanel(new BorderLayout());
        innerCenterPanel.setBackground(Color.WHITE);

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.setOpaque(false);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
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
