import javax.swing.*;
import java.awt.*;

/**
 * View that displays the games tutorial
 * <p>
 * This class includes a header with a title.
 * It also includes a main section meant to be used to show the tutorial to the user,
 * later in the development process.
 * The main section currently serves as a visual placeholder.
 */
public class TutorialView extends BaseView{
    private JPanel headerPanel;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;

    /**
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public TutorialView(){
        super("Tutorial");

        escapeButton = new JButton("ESCAPE");

        createTutorialHeader();
        createTutorialCenterPanel();
    }


    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The title label is centered
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createTutorialHeader(){

        headerPanel = new JPanel(new BorderLayout(-90, 0));

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);


        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel – currently used as a visual placeholder.
     * <p>
     * This layout uses two nested panels: a gray outer panel and a white inner panel,
     * to give a clearer idea of where future components can be placed.
     * It's purely for layout visualization and can be easily removed or replaced later.
     * The panel is added to the center of the frame.
     */
    private void createTutorialCenterPanel(){
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBackground(Color.GRAY);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        innerCenterPanel = new JPanel(new BorderLayout());
        innerCenterPanel.setBackground(Color.WHITE);

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the TutorialView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialView tutorialView = new TutorialView();
            tutorialView.show();
        });

    } */
}
