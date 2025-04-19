package View;

import javax.swing.*;
import java.awt.*;
import Model.CountdownTimer;
import Model.HighscoreIO;
import Model.Hitbox;
import Model.Trashcan.PlasticTrashcan;
import Model.Trashcan.SpawnTrashcans;
import Model.Trashcan.Trashcan;


/**
 * View displayed during the actual gameplay
 * <p>
 * This class includes a header with an escape button and placeholders for a score system and a timer.
 * It also includes a center panel with a placeholder button for navigating to the GameOverView during the development process.
 * <p>
 * This view serves as the foundation for the actual game logic.
 * Current components serves as placeholders for now, and will be updated as the game develops.
 */
public class GameView extends BaseView{

    private JPanel headerPanel;
    protected JButton escapeButton;

    private JLabel scorePlaceholder;
    private CountdownTimer countdownTimer;

    private JPanel centerPanel;
    protected JButton gameOverViewPlaceholder;

    /**
     * Constructs the GameView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public GameView(){
        super("Game View");

        escapeButton = new JButton("ESCAPE");
        gameOverViewPlaceholder = new JButton("Game Over View (Placeholder for navigation)");

        createGameViewHeader();
        createGameViewCenterPanel();
    }

    /**
     * Creates a header panel containing an escape button, and placeholders for a score system and a timer.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The score placeholder is centered
     * - The timer placeholder is placed to the right
     * <p>
     * This panel is added to the north container of the frame.
     */

    public double getFinalScore() {
        double finalScore = Double.parseDouble(scorePlaceholder.getText());
        return finalScore;
    }
    private void createGameViewHeader(){
        HighscoreIO highscoreIO = new HighscoreIO();

        headerPanel = new JPanel(new BorderLayout());

        headerPanel.add(escapeButton, BorderLayout.WEST);

        scorePlaceholder = new JLabel("12345", SwingConstants.CENTER);
        scorePlaceholder.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(scorePlaceholder, BorderLayout.CENTER);

        countdownTimer = new CountdownTimer(this, highscoreIO);


        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,40));
        timerPanel.add(countdownTimer.getComponent(), BorderLayout.CENTER);

        headerPanel.add(timerPanel, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

    }

    /**
     * Creates a center panel - Currently works as a visual placeholder
     * <p>
     * This layout consists of a gray-colored panel that holds a placeholder button,
     * which should simulate navigation to the Game Over screen during the development progress.
     * This panel is added to the center panel of the frame.
     */
    private void createGameViewCenterPanel(){
        centerPanel = new JPanel(null);
        centerPanel.setBackground(Color.GRAY);

        centerPanel.add(gameOverViewPlaceholder);

        frame.add(centerPanel, BorderLayout.CENTER);

        /**
        Iterates through the cans and sends them through the renderer.
         */

        SpawnTrashcans spawnThem = new SpawnTrashcans();
        for (Trashcan thisCan : spawnThem.createTrashcans()) {
            renderTrashcans(thisCan);
        }


    }


    /**
    Renders the trashcan in view, it NEEDS int for the bounds.
    (That's why it's converted)
     */

    public void renderTrashcans(Trashcan trashcan) {
        ImageIcon imageIcon = new ImageIcon(trashcan.generateImagePath());
        JLabel jLabel = new JLabel(imageIcon);

        double y = trashcan.getY();
        double x = trashcan.getX();
        double width = trashcan.getWidth();
        double height = trashcan.getHeight();

        jLabel.setBounds((int) x, (int) y, (int) width, (int) height);
        centerPanel.add(jLabel);

    }
}

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the GameView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView gameView = new GameView();
            gameView.show();
        });

    } */

