package View.Gamemode2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Model.CountdownTimer2;
import Model.HighscoreIO;
import Model.Hitbox;
import Model.Trashcan.PlasticTrashcan;
import Model.Trashcan.SpawnTrashcans;
import Model.Trashcan.Trashcan;
import View.*;


/**
 * View displayed during the actual gameplay
 * <p>
 * This class includes a header with an escape button and placeholders for a score system and a timer.
 * It also includes a center panel with a placeholder button for navigating to the GameOverView during the development process.
 * <p>
 * This view serves as the foundation for the actual game logic.
 * Current components serves as placeholders for now, and will be updated as the game develops.
 */
public class GameView2 extends BaseView2{

    private JPanel headerPanel;

    private EmptyBorder headerBorder;
    protected JButton escapeButton;

    private JLabel scorePlaceholder;
    private CountdownTimer2 countdownTimer;

    private JPanel centerPanel;
    protected JButton gameOverViewPlaceholder;

    /**
     * Constructs the GameView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     * <p>
     * A different background is being used for the GameView:
     * - To separate the gameplay from the rest of the application
     * - To visually increase space for components top be placed
     */
    public GameView2(){
        // super("Game View", "/Resources/Background1.png");
        super("Game View", "/Resources/Background2.png");

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
     *  Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
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
        headerBorder = new EmptyBorder(20,20,0,20);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        scorePlaceholder = new JLabel("12345", SwingConstants.CENTER);
        scorePlaceholder.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(scorePlaceholder, BorderLayout.CENTER);

        countdownTimer = new CountdownTimer2(this, highscoreIO);


        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,40));
        timerPanel.add(countdownTimer.getComponent(), BorderLayout.CENTER);
        timerPanel.setOpaque(false);

        headerPanel.add(timerPanel, BorderLayout.EAST);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);


    }

    /**
     * Creates a center panel
     * <p>
     * This layout consists of a panel that is made transparent by setting opaque to false,
     * allowing the background image to show through.
     * <p>
     * This panel currently holds a placeholder button,
     * which should simulate navigation to the Game Over screen during the development progress.
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the center panel of the frame.
     */

    private void createGameViewCenterPanel(){

        centerPanel = new JPanel(null);

        centerPanel.add(gameOverViewPlaceholder, BorderLayout.SOUTH);
        centerPanel.setOpaque(false);

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

