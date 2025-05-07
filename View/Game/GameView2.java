package View.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import Model.Timer.CountdownTimer2;
import Model.Score.HighscoreIO;
import Model.Score.Score;
import Model.SpawnTrashDefault2;
import Model.Collision.TrashSorter2;
import Model.Hitbox;
import Model.Textbox.SpawnTextboxes;
import Model.Textbox.Textbox;
import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import View.ComponentsUtilities.*;
import View.ComponentsUtilities.Sound.SoundManager;

/**
 * View displayed during the actual gameplay
 * <p>
 * This class includes a header with an escape button and placeholders for a score system and a timer.
 * It also includes a center panel with a placeholder button for navigating to the GameOverView during the development process.
 * <p>
 * This view serves as the foundation for the actual game logic.
 * Current components serves as placeholders for now, and will be updated as the game develops.
 */
public class GameView2 extends BaseView {

    private JPanel headerPanel;
    private EmptyBorder headerBorder;
    protected JButton escapeButton;

    private Score score;
    private JLabel scoreLabel;
    private CountdownTimer2 countdownTimer;

    private JPanel centerPanel;
    private SpawnTrashDefault2 spawnTrashDefault;
    private Trash curSelectedTrash = null;
    private JLabel curTrashLabel = null;
    private Timer resetTrashAnimation;
    private final int ANIMATION_DELAY = 300;
    private int correctlySortedTrashCount = 0;

    private Clip backgroundMusicClip;
    private SoundManager soundManager = new SoundManager();
    private String currentMusicTrack;

    /**
     * Constructs the GameView and sets up all UI components.
     * <p>
     * This includes initializing buttons, score and calling methods to create each panel.
     * <p>
     * A different background is being used for the GameView:
     * - To separate the gameplay from the rest of the application
     * - To visually increase space for components top be placed
     */
  
    public GameView2() {
        super("Game View 2", "/Resources/Images/Backgrounds/Background2.png");

        score = new Score();
        escapeButton = new JButton("ESCAPE");
        escapeButton.addActionListener(e -> {stopBackgroundMusic();});

        String[] musicTracks = {
                "Resources/Sounds/513427__mrthenoronha__cartoon-game-theme-loop-3.wav",
                "Resources/Sounds/513667__mrthenoronha__cartoon-game-theme-loop-4.wav",
                "Resources/Sounds/513869__mrthenoronha__cartoon-game-theme-loop-5.wav"
        };
        int randomIndex = new Random().nextInt(musicTracks.length);
        currentMusicTrack = musicTracks[randomIndex];

        soundManager.playSound(currentMusicTrack);
        soundManager.setVolume(currentMusicTrack, 20);

        createGameViewHeader();
        createGameViewCenterPanel();
    }

    // Getters
    /**
     * Gets the "Escape" button.
     * <p>
     * @return the JButton for the "Escape" action
     */
    public JButton getEscapeButton() {
        return escapeButton;
    }

    // Setters
    /**
     * Sets the "Escape" button.
     * <p>
     * @param escapeButton the JButton to set for the "Escape" action
     */

    /**
     * Retrieves the final score from the score label.
     * <p>
     * This method gets the score value from the scoreLabel text,
     * removes "Score: ", trims any whitespace, and converts
     * the resulting string into a double.
     * <p>
     * @return the final score as a double
     */
    public double getFinalScore() {
        String scoreText = scoreLabel.getText().replace("Score: ", "").trim();
        return Double.parseDouble(scoreText);
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
    private void createGameViewHeader(){
        HighscoreIO highscoreIO = new HighscoreIO();

        headerPanel = new JPanel(new BorderLayout());

        headerBorder = new EmptyBorder(0, 20, 0, 0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        scoreLabel = new JLabel("Score: " + score.getCurrentScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        //headerPanel.add(scoreLabel, BorderLayout.CENTER);
        scoreLabel.setPreferredSize(new Dimension(150,40));

        JPanel newScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        newScorePanel.setBorder(new EmptyBorder(5,120,0,0));
        newScorePanel.setOpaque(false);

        newScorePanel.add(scoreLabel);
        headerPanel.add(newScorePanel, BorderLayout.CENTER);

        countdownTimer = new CountdownTimer2(this, highscoreIO);

        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(5,0,0,30));
        timerPanel.add(countdownTimer.getComponent(), BorderLayout.EAST);
        timerPanel.setPreferredSize(new Dimension(250,40));
        timerPanel.setOpaque(false);

        headerPanel.add(timerPanel, BorderLayout.EAST);
        headerPanel.setOpaque(false);
        headerPanel.setBorder(headerBorder);

        frame.add(headerPanel, BorderLayout.NORTH);

        ArrayList<Trash> trashList = new ArrayList<>();
        TrashFactory trashFactory = new TrashFactory();
        spawnTrashDefault = new SpawnTrashDefault2(countdownTimer, trashList, trashFactory);

    }

    /**
     * Updates the UI to display the current score based on interactions between trash and descriptions.
     * <p>
     * This method updates the score logic and refreshes the score label to reflect
     * the latest score in the game.
     * <p>
     * @param trash the trash item being sorted
     * @param textbox the textbox containing the description to check against
     */
    public void updateScoreDescription(Trash trash, Textbox textbox) {
        score.updateScoreDescription(trash, textbox);
        scoreLabel.setText("Score: " + score.getCurrentScore());
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

    private void createGameViewCenterPanel(){

        centerPanel = new JPanel(null);

        centerPanel.setOpaque(false);

        frame.add(centerPanel, BorderLayout.CENTER);

        /**
        Iterates through the boxes and sends them through the renderer.
         */


        SpawnTextboxes spawnThem = new SpawnTextboxes();
        ArrayList<String> descriptions = new ArrayList<>(Arrays.asList( "Example Description 1", 
        "Example Description 2", 
        "Example Description 3", 
        "Example Description 4"));
        for (Textbox thisBox : spawnThem.createTextboxes(descriptions)) {
            renderTextboxes(thisBox);
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

                    Point currentXY = curTrashLabel.getLocation();
                    Point targetXY = new Point(xMouseCoord - 50, yMouseCoord - 50);

                    animateTrash(currentXY, targetXY, centerPanel, curTrashLabel, 500);

                    curSelectedTrash.setX(targetXY.x);
                    curSelectedTrash.setY(targetXY.y);

                    // -50 on x and y vals to spawn in center of mouse

                    // Trash picture to the front (index 0),
                    // IF not index 0, it will end up behind the trashcans & lost

                    centerPanel.setComponentZOrder(curTrashLabel, 0);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    // This is to avoid running the animation too early
                    // The delay should be the same delay as the animation time in ms
                    // It allows for the previous animation to finish


                    Timer avoidDupeAnimation = new Timer(500, l -> {

                    if (TrashSorter2.isCorrectlySorted(curSelectedTrash, textboxes)) {
                        isTimerRunning();
                        System.out.println("Correctly sorted");

                        showFeedbackIcon("Resources/Sounds/checkmark-64.gif");

                        score.addPoints(curSelectedTrash.getPoints());
                        scoreLabel.setText("Score: " + String.valueOf(score.getCurrentScore()));

                        Trash trashToRemove = curSelectedTrash;
                        curSelectedTrash = null;

                        resetTrashAnimation = new javax.swing.Timer(ANIMATION_DELAY, e-> {

                            centerPanel.remove(curTrashLabel);
                            centerPanel.revalidate();
                            centerPanel.repaint();
                        });
                        resetTrashAnimation.setRepeats(false);
                        resetTrashAnimation.start();

                        correctlySortedTrashCount++;
                        System.out.println("Correctly sorted trash count: " + correctlySortedTrashCount);

                        // NEW TRASH RENDER
                    } else {

                        isTimerRunning();
                        
                        score.subtractPoints(curSelectedTrash.getPoints());
                        scoreLabel.setText("Score: " + String.valueOf(score.getCurrentScore()));

                        Trash trashToReset = curSelectedTrash;
                        curSelectedTrash = null;

                        // The trash animation delay can be tweaked (ANIMATION_DELAY)

                        System.out.println("Not correctly sorted!");


                        showFeedbackIcon("Resources/Sounds/multiply-3-64.png");
                        shakeComponent(curTrashLabel);
                        soundManager.playSoundOnce("Resources/Sounds/training-program-incorrect1-88736 (1).wav");

                        // Spawns the trash back to its original spawn location.

                        Point fromHere = new Point(z.getX() - 50, z.getY() - 50);
                        Point toHere = spawnTrashDefault.randomSpawnLoc();


                        trashToReset.setX(toHere.x);
                        trashToReset.setY(toHere.y);

                        animateTrash(fromHere, toHere, centerPanel, curTrashLabel, 500);

                    }

                    if (correctlySortedTrashCount >= 2) {
                        correctlySortedTrashCount = 0;
                        spawnAndRender();
                    }

                    // Deselect the trash
                    curSelectedTrash = null;
                });
                avoidDupeAnimation.setRepeats(false);
                avoidDupeAnimation.start();

                }
            }
        });

    }

    private ArrayList<Trash> trashList = new ArrayList<>();
    private ArrayList<Textbox> textboxes = new ArrayList<>();

    /**
     * The spawnAndRender randomizes a new trash and then sends it through renderTrash
     * to get a random trash spawned in the game.
     */

     public void spawnAndRender() {
        trashList.clear();
        textboxes.clear();
        centerPanel.removeAll();

        Trash trash1 = spawnTrashDefault.spawnRandomTrash();
        Trash trash2 = spawnTrashDefault.spawnRandomTrash();

        trashList.add(trash1);
        trashList.add(trash2);

        renderTrash(trash1);
        renderTrash(trash2);

        createAndRenderTextboxes(trash1, trash2);

        centerPanel.revalidate();
        centerPanel.repaint();

        soundManager.playSoundOnce("Resources/Sounds/321806__lloydevans09__plunger_pop_2.wav");
    }

    private void playBackgroundMusic(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundMusic() {
        if (currentMusicTrack != null) {
            soundManager.stopSound(currentMusicTrack);
        }
    }

    private void showFeedbackIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel iconLabel = new JLabel(icon);

        int centerX = (frame.getWidth() - icon.getIconWidth()) / 2;
        int centerY = (frame.getHeight() - icon.getIconHeight()) / 2;

        iconLabel.setBounds(centerX, centerY, icon.getIconWidth(), icon.getIconHeight());
        iconLabel.setOpaque(false);

        frame.getLayeredPane().add(iconLabel, JLayeredPane.POPUP_LAYER);
        frame.getLayeredPane().repaint();

        new javax.swing.Timer(700, e -> {
            frame.getLayeredPane().remove(iconLabel);
            frame.getLayeredPane().repaint();
        }).start();
    }

    private void shakeComponent(JComponent component) {
        final int shakeDistance = 10;
        final int shakeDuration = 30;
        final int shakeCount = 6;

        Point originalLocation = component.getLocation();
        Timer shakeTimer = new Timer(shakeDuration, null);

        final int[] count = {0};
        shakeTimer.addActionListener(e -> {
            int offset = (count[0] % 2 == 0) ? shakeDistance : -shakeDistance;
            component.setLocation(originalLocation.x + offset, originalLocation.y);
            component.repaint();

            count[0]++;
            if (count[0] >= shakeCount) {
                component.setLocation(originalLocation); // reset position
                ((Timer)e.getSource()).stop();
            }
        });

        shakeTimer.start();
    }

    /**
    * Creates and renders textboxes for a given trash object.
    * @param trash The trash object to create textboxes for.
    */
    private void createAndRenderTextboxes(Trash trash1, Trash trash2) {
        String correctDescription1 = trash1.getDescription();        
        ArrayList<String> exceptions1 = new ArrayList<>();
        exceptions1.add(correctDescription1);
        String incorrectDescription1 = trash1.generateIncorrectDescription(exceptions1);

        String correctDescription2 = trash2.getDescription();
        ArrayList<String> exceptions2 = new ArrayList<>();
        exceptions2.add(correctDescription2);
        String incorrectDescription2 = trash2.generateIncorrectDescription(exceptions2);

        ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(
            correctDescription1, 
            incorrectDescription1,
            correctDescription2,
            incorrectDescription2
        ));
        Collections.shuffle(descriptions); // Shuffle the order
        SpawnTextboxes spawnTextboxes = new SpawnTextboxes();
        ArrayList<Textbox> generatedTextboxes = spawnTextboxes.createTextboxes(descriptions);

        for (Textbox textbox : generatedTextboxes) {
            textboxes.add(textbox);
            renderTextboxes(textbox);
        }
    }

    /**
     * the renderTrash renders trash in the game.
     * An action listener is placed on the trash, to detect if the player clicks it.
     *
     * @param trash
     */

     public void renderTrash(Trash trash) {
        String imagePath = trash.getImagePath();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel trashLabel = new JLabel(imageIcon);

        double y = trash.getY();
        double x = trash.getX();
        double width = trash.getWidth();
        double height = trash.getHeight();

        trash.setOriginalX(x);
        trash.setOriginalY(y);

        trashLabel.setBounds((int) x, (int) y, (int) width, (int) height);
        centerPanel.add(trashLabel);

        trashLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.print("Trash is clicked!");
                curSelectedTrash = trash;
                curTrashLabel = trashLabel;
            }
        });
    }


    /**
    Renders the textbox in view, it NEEDS int for the bounds.
    (That's why it's converted)
     */

     public void renderTextboxes(Textbox textbox) {
        String description = textbox.getDescription();

        String htmlDescription = "<html><div style='text-align: center;'>" + description.replaceAll("\n", "<br>") + "</div></html>";

        JLabel jLabel = new JLabel(htmlDescription);
        jLabel.setFont(new Font("Arial", Font.BOLD, 12));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        double y = textbox.getY();
        double x = textbox.getX();
        double width = textbox.getWidth();
        double height = textbox.getHeight();
    
        jLabel.setBounds((int) x, (int) y, (int) width, (int) height);
    
        centerPanel.add(jLabel);
    }

    public static void animateTrash(Point fromHereA, Point toHereB, JPanel parentPanel, JLabel curTrashLabel, int totalTime) {

        int FPS = 30;
        int stepDurationMS = 1000/FPS;
        int totalTimeMS = 500;
        int numberOfSteps = totalTimeMS / stepDurationMS;

        // Calculates x and y travel distance (from point A to point B)

        double xPixelTravel = (toHereB.x - fromHereA.x);
        double yPixelTravel = (toHereB.y - fromHereA.y);

        // Figures out the step distance (how much we should move the trash for each step)
        // for both x and y

        double xStepDistance = xPixelTravel / numberOfSteps;
        double yStepDistance = yPixelTravel / numberOfSteps;

        // Setting up step variable to count (wrapped in an array so we can change it)

        final int[] step = {0};

        // We create a new swing timer, takes in stepDurationMS which is the time for each step)
        // Runs for each frame, in this case 30 times (30 FPS)

        Timer timer = new Timer(stepDurationMS, e -> {

            // Check if animation steps have reached max set amount (numberOfSteps)
            if (step[0] >= numberOfSteps) {

                // If true, set curTrashLabel bound to point B's x and y value.
                // Set width and height also.

                curTrashLabel.setBounds(toHereB.x, toHereB.y, curTrashLabel.getWidth(), curTrashLabel.getHeight());

                // Stops repeat timer
                ((Timer) e.getSource()).stop();
                return;
            }

            // Calculates the next x and y step, takes step distance times the amount of
            // steps completed already...
            // I.e. moves 2 pixels in x direction & we're on step 19
            // 2*19 + initial x pos -> our next x pos
            int newX = (int)(fromHereA.x+xStepDistance*step[0]);
            int newY = (int)(fromHereA.y + yStepDistance*step[0]);

            // Moves it to the new calculated position on the screen
            // Repaints and revalidates because Swing is a mess

            curTrashLabel.setBounds(newX, newY, curTrashLabel.getWidth(), curTrashLabel.getHeight());
            parentPanel.revalidate();
            parentPanel.repaint();

            // Adds a step to the counter
            step[0] += 1;
        });

        // Start timer again
        timer.start();
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
            GameView2 gameView2 = new GameView2();
            gameView2.show();
        });
    } */
