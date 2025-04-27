package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import Model.*;
import Model.Collision.TrashSorter;
import Model.Trash.Trash;
import Model.Trash.TrashFactory;
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

    private final int ANIMATION_DELAY = 300;
    private Score score;
    private JPanel headerPanel;
    private ArrayList<Trashcan> trashcans;

    private EmptyBorder headerBorder;
    protected JButton escapeButton;

    private JLabel scorePlaceholder;
    private CountdownTimer countdownTimer;

    private JPanel centerPanel;
    protected JButton gameOverViewPlaceholder;

    private SpawnTrashDefault spawnTrashDefault;
    private Trash curSelectedTrash = null;
    private JLabel curTrashLabel = null;
    private Timer resetTrashAnimation;

    /**
     * Constructs the GameView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     * <p>
     * A different background is being used for the GameView:
     * - To separate the gameplay from the rest of the application
     * - To visually increase space for components top be placed
     */
    public GameView(){
        // super("Game View", "/Resources/Background1.png");
        super("Game View", "/Resources/Background2.png");

        escapeButton = new JButton("ESCAPE");
        gameOverViewPlaceholder = new JButton("Game Over View (Placeholder for navigation)");
        score = new Score();

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

    /**
     * Gets the final score, when the game is done (for highscores)
     * @return the final score when the time is out
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

        scorePlaceholder = new JLabel("0", SwingConstants.CENTER);
        scorePlaceholder.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(scorePlaceholder, BorderLayout.CENTER);

        countdownTimer = new CountdownTimer(this, highscoreIO);


        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,40));
        timerPanel.add(countdownTimer.getComponent(), BorderLayout.CENTER);
        timerPanel.setOpaque(false);

        headerPanel.add(timerPanel, BorderLayout.EAST);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);

        ArrayList<Trash> trashList = new ArrayList<>();
        TrashFactory trashFactory = new TrashFactory();
        spawnTrashDefault = new SpawnTrashDefault(countdownTimer, trashList, trashFactory);



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

    /**
     * Timer needs a reset before running again. The .stop() does this.
     * It is used for the animation delay, which determines how long it should render
     * before going back...
     */

    private void isTimerRunning() {
        if (resetTrashAnimation != null && resetTrashAnimation.isRunning()) {
            resetTrashAnimation.stop();
        }
    }

    private void createGameViewCenterPanel() {

        centerPanel = new JPanel(null);

        centerPanel.add(gameOverViewPlaceholder, BorderLayout.SOUTH);
        centerPanel.setOpaque(false);

        frame.add(centerPanel, BorderLayout.CENTER);

        /**
         Iterates through the cans and sends them through the renderer.
         */

        SpawnTrashcans spawnThem = new SpawnTrashcans();
        trashcans = spawnThem.createTrashcans();
        for (Trashcan thisCan : trashcans) {
            renderTrashcans(thisCan);
        }


        spawnAndRender();

        // Mouse listener to track WHERE the user clicks on the screen.
        // This is crucial for tracking where we should animate the trash the user clicked

        centerPanel.addMouseListener(new java.awt.event.MouseAdapter() {

            /**
             * This method tracks user mouse clicks.
             * It is necessary for rendering the selected trash in a different spot.
             * Takes the mouse coordinates and sets the currently selected trash to them
             */
            @Override
            public void mouseClicked(java.awt.event.MouseEvent z) {
                if (curSelectedTrash != null) {

                    int xMouseCoord = z.getX();
                    int yMouseCoord = z.getY();

                    curSelectedTrash.setX(xMouseCoord);
                    curSelectedTrash.setY(yMouseCoord);


                    // -50 on x and y vals to spawn in center of mouse

                    curTrashLabel.setBounds(xMouseCoord-50, yMouseCoord-50,
                            (int) curSelectedTrash.getWidth(),
                            (int) curSelectedTrash.getHeight());

                    // Trash picture to the front (index 0),
                    // IF not index 0, it will end up behind the trashcans & lost

                    centerPanel.setComponentZOrder(curTrashLabel, 0);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    if (TrashSorter.isCorrectlySorted(curSelectedTrash, trashcans)) {
                        isTimerRunning();
                        System.out.println("Correctly sorted");

                        score.addPoints(curSelectedTrash.getPoints());
                        scorePlaceholder.setText(String.valueOf(score.getCurrentScore()));

                        Trash trashToRemove = curSelectedTrash;
                        curSelectedTrash = null;

                        resetTrashAnimation = new javax.swing.Timer(ANIMATION_DELAY, e-> {

                            centerPanel.remove(curTrashLabel);
                            centerPanel.revalidate();
                            centerPanel.repaint();
                            spawnAndRender();
                        });
                        resetTrashAnimation.setRepeats(false);
                        resetTrashAnimation.start();

                        // NEW TRASH RENDER
                    } else {

                        isTimerRunning();


                        Trash trashToReset = curSelectedTrash;
                        curSelectedTrash = null;

                        // The trash animation delay can be tweaked (ANIMATION_DELAY)

                        System.out.println("Not correctly sorted!");

                        resetTrashAnimation = new javax.swing.Timer(ANIMATION_DELAY, e -> {

                            // Spawns the trash back to its original spawn location.

                            trashToReset.setX(spawnTrashDefault.X_VAL);
                            trashToReset.setY(spawnTrashDefault.Y_VAL);

                            curTrashLabel.setBounds(spawnTrashDefault.X_VAL, spawnTrashDefault.Y_VAL,
                                (int) trashToReset.getWidth(),
                                (int) trashToReset.getHeight());

                            centerPanel.revalidate();
                            centerPanel.repaint();
                        });
                        resetTrashAnimation.setRepeats(false);
                        resetTrashAnimation.start();
                    }

                    // Deselect the trash
                    curSelectedTrash = null;


                }
            }
        });
    }




    /**
     * The spawnAndRender randomizes a new trash and then sends it through renderTrash
     * to get a random trash spawned in the game.
     */

    public void spawnAndRender() {
        Trash newTrash = spawnTrashDefault.spawnRandomTrash();
        renderTrash(newTrash);
    }

    /**
     * the renderTrash renders trash in the game.
     * An action listener is placed on the trash, to detect if the player clicks it.
     *
     * @param trash
     */

    public void renderTrash(Trash trash) {
        ImageIcon imageIcon = new ImageIcon(trash.generateImagePath());
        curTrashLabel = new JLabel(imageIcon);

        double y = trash.getY();
        double x = trash.getX();
        double width = trash.getWidth();
        double height = trash.getHeight();

        curTrashLabel.setBounds((int) x, (int) y, (int) width, (int) height);
        centerPanel.add(curTrashLabel);
        curTrashLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.print("Trash is clicked!");
                curSelectedTrash = trash;
            }
        });
    }

    /**
     Renders the trashcan in view, it NEEDS int for the bounds.
     (That's why it's converted)
     */

    public void renderTrashcans(Trashcan trashcan) {
        ImageIcon imageIcon = new ImageIcon(trashcan.generateImagePath());
        JLabel jLabel = new JLabel(imageIcon) {

            // Needed to click through the trashcans
            // The action listener area is on the central panel (behind them)
            @Override
            public boolean contains(int x, int y) {
                return false;
            }
        };

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

