package View.Game;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Model.*;
import Model.Collision.TrashSorter;
import Model.Score.HighscoreIO;
import Model.Score.Score;
import Model.Timer.CountdownTimer;
import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import Model.Trashcan.PlasticTrashcan;
import Model.Trashcan.SpawnTrashcans;
import Model.Trashcan.Trashcan;
import View.ComponentsUtilities.BaseView;



/**
 * View displayed during the actual gameplay
 * <p>
 * This class includes a header with an escape button and placeholders for a score system and a timer.
 * It also includes a center panel with a placeholder button for navigating to the GameOverView during the development process.
 * <p>
 * This view serves as the foundation for the actual game logic.
 * Current components serves as placeholders for now, and will be updated as the game develops.
 */
public class GameView extends BaseView {

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

    private Clip backgroundMusicClip;

    private ArrayList<Trash> incorrectTrash;
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
        super("Game View", "/Resources/Images/Backgrounds/Background2.png");

        escapeButton = new JButton("ESCAPE");
        escapeButton.addActionListener(e -> {stopBackgroundMusic();});
        gameOverViewPlaceholder = new JButton("Game Over View (Placeholder for navigation)");
        score = new Score();
        incorrectTrash = new ArrayList<>();

        String[] musicTracks = {
                "Resources/Sounds/513427__mrthenoronha__cartoon-game-theme-loop-3.wav",
                "Resources/Sounds/513667__mrthenoronha__cartoon-game-theme-loop-4.wav",
                "Resources/Sounds/513869__mrthenoronha__cartoon-game-theme-loop-5.wav"
        };

        int randomIndex = new Random().nextInt(musicTracks.length);
        String selectedTrack = musicTracks[randomIndex];

        playBackgroundMusic(selectedTrack);


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
  public void setEscapeButton(JButton escapeButton) {
      this.escapeButton = escapeButton;
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
        scorePlaceholder.setPreferredSize(new Dimension(100,40));

        JPanel newScorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        newScorePanel.setBorder(new EmptyBorder(5,160,0,0));
        newScorePanel.setOpaque(false);


        newScorePanel.add(scorePlaceholder);
        headerPanel.add(newScorePanel, BorderLayout.CENTER);

        countdownTimer = new CountdownTimer(this, highscoreIO);



        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBorder(BorderFactory.createEmptyBorder(5,0,0,30));
        timerPanel.add(countdownTimer.getComponent(), BorderLayout.EAST);
        timerPanel.setPreferredSize(new Dimension(250,40));
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

                    Point currentXY = curTrashLabel.getLocation();
                    Point targetXY = new Point(xMouseCoord - 50, yMouseCoord - 50);

                    GameView.animateTrash(currentXY, targetXY, centerPanel, curTrashLabel, 500);

                    curSelectedTrash.setX(targetXY.x);
                    curSelectedTrash.setY(targetXY.y);

                    // Trash picture to the front (index 0),
                    // IF not index 0, it will end up behind the trashcans & lost

                    centerPanel.setComponentZOrder(curTrashLabel, 0);
                    centerPanel.revalidate();
                    centerPanel.repaint();

                    // This is to avoid running the animation too early
                    // The delay should be the same delay as the animation time in ms
                    // It allows for the previous animation to finish


                    Timer avoidDupeAnimation = new Timer(500, l -> {

                        // Stops any previous animation
                        isTimerRunning();


                        if (TrashSorter.isCorrectlySorted(curSelectedTrash, trashcans)) {
                            isTimerRunning();
                            System.out.println("Correctly sorted");

                            showFeedbackIcon("Resources/Sounds/checkmark-64.gif");
                            score.addPoints(curSelectedTrash.getPoints());
                            scorePlaceholder.setText(String.valueOf(score.getCurrentScore()));

                            Trash trashToRemove = curSelectedTrash;
                            curSelectedTrash = null;

                            resetTrashAnimation = new javax.swing.Timer(ANIMATION_DELAY, e -> {

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


                            //Adds trash to list for game over view
                            boolean isDuplicate = false;
                            if (!incorrectTrash.isEmpty()) {
                                for (Trash trash : incorrectTrash) {
                                    if (trash.getDescription().equals(trashToReset.getDescription())) {
                                        isDuplicate = true;
                                        break;
                                    }
                                }
                            }
                            if (!isDuplicate) {incorrectTrash.add(trashToReset);}

                            // The trash animation delay can be tweaked (ANIMATION_DELAY)

                            System.out.println("Not correctly sorted!");

                            showFeedbackIcon("Resources/Sounds/multiply-3-64.png");
                            //shakeComponent(curTrashLabel);
                            playSound("Resources/Sounds/training-program-incorrect1-88736 (1).wav");

                            Point fromHere = new Point(z.getX() - 50, z.getY() - 50);
                            Point toHere = spawnTrashDefault.randomSpawnLoc();

                            trashToReset.setX(toHere.x);
                            trashToReset.setY(toHere.y);

                            GameView.animateTrash(fromHere, toHere, centerPanel, curTrashLabel, 500);

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

    /**
     * The spawnAndRender randomizes a new trash and then sends it through renderTrash
     * to get a random trash spawned in the game.
     */

    private void playSound(String soundFilePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(+5.0f);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void spawnAndRender() {
        Trash newTrash = spawnTrashDefault.spawnRandomTrash();
        renderTrash(newTrash);

        playSound("Resources/Sounds/321806__lloydevans09__plunger_pop_2.wav");
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
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
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
     * the renderTrash renders trash in the game.
     * An action listener is placed on the trash, to detect if the player clicks it.
     *
     * @param trash
     */

    public void renderTrash(Trash trash) {
        ImageIcon imageIcon = new ImageIcon(trash.generateImagePath().getImagePath());
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


    public ArrayList<Trash> getIncorrectTrash() {
        return incorrectTrash;
    }


        /**
         * Animation for trash...
         * FPS = frames per second
         * stepDurationMS = Time in MS for each frame
         * totalTimeMS = total animation time, set as 500 right now but can be increased or decreased -- (lower = faster animation)
         * numberOfSteps = the total time divided by the time for each step
         */


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

