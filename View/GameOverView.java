import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View displayed when player loses the game.
 * <p>
 * This class includes a header with a title.
 * It also includes a main section (currently a visual placeholder),
 * that contains buttons to either play the game again or return to the main menu.
 */
public class GameOverView extends BaseView{
    private JPanel headerPanel;
    private JLabel titleLabel;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;

    private JPanel bottomPanel;

    protected JButton playAgainButton;
    protected JButton mainMenuButton;

    private EmptyBorder bottomButtonsBorder;

    /**
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public GameOverView(){
        super("Game Over");

        playAgainButton = new JButton("PLAY AGAIN");
        mainMenuButton = new JButton("MAIN MENU");

        createGameOverHeader();
        createGameOverCenterPanel();
        createGameOverBottomPanel();
    }

    /**
     * Creates a header panel containing the view title.
     * <p>
     * This header panel uses BorderLayout to provide better control over the layout and positioning of its components.
     * This panel is added to the north container of the frame.
     */
    private void createGameOverHeader(){

        headerPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel - Currently works as a visual placeholder
     * <p>
     * This layout currently uses two nested panels: A gray outer panel and a white inner panel,
     * to give a clearer visual idea of where future components can be placed.
     * This is purely for layout visualization and can easily be removed or replaced later.
     * This panel is added to the center panel of the frame.
     */
    private void createGameOverCenterPanel(){
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBackground(Color.GRAY);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        innerCenterPanel = new JPanel(new BorderLayout());
        innerCenterPanel.setBackground(Color.WHITE);

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);


        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a bottom panel that contains two buttons, to either play the game again or return to the main menu.
     * <p>
     * Buttons are spaced horizontally using an EmptyBorder for visual separation.
     * This panel is added to the south container of the frame.
     */
    private void createGameOverBottomPanel() {
        bottomPanel = new JPanel(new GridLayout(1, 2, 150, 0));
        bottomButtonsBorder = new EmptyBorder(0,50,0,50);

        bottomPanel.add(playAgainButton);
        bottomPanel.add(mainMenuButton);
        bottomPanel.setBorder(bottomButtonsBorder);

        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the GameOverView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameOverView gameOverView = new GameOverView();
            gameOverView.show();
        });

    } */
}
