package View;
import Model.Trash.Trash;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    private JPanel bottomPanel;

    protected JButton playAgainButton;
    protected JButton mainMenuButton;

    private EmptyBorder bottomButtonsBorder;

    private JLabel scoreLabel;
    private double finalScore;
    private double highscore;
    private ArrayList<Trash> trashList;
    private int currentTrashIndex = 0;
    private JLabel trashImageLabel;
    private JTextArea trashDescriptionArea;

    /***
     * Constructor for the GameOverView class.
     * <p>
     * This constructor initializes the view with a title, score, highscore, and a list of trash objects.
     *
     * @param score The final score of the game.
     * @param highscore The highest score achieved in the game.
     * @param trashList The list of trash objects to be displayed.
     */
    public GameOverView(double score, double highscore, ArrayList<Trash> trashList) {
        super("Game Over", "/Resources/Background3.png");

        this.highscore = highscore;
        this.finalScore = score;
        this.trashList = trashList;
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

    /***
     * Creates a center panel that displays the score and trash images.
     * <p>
     * This panel uses BorderLayout to arrange its components.
     * It is added to the center container of the frame.
     */
    private void createGameOverCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setOpaque(false);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        // Create a container for score and highscore labels with space between them
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setOpaque(false);

        // Create the score label
        JLabel scoreDisplayLabel;
        if (finalScore > highscore) {
            scoreDisplayLabel = new JLabel("NEW HIGHSCORE!: " + finalScore);
        } else {
            scoreDisplayLabel = new JLabel("Score: " + finalScore);
        }
        scoreDisplayLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create highscore label
        JLabel highscoreLabel = new JLabel("Highscore: " + highscore);
        highscoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        highscoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Create the mistakes label
        int mistakesCount = (trashList != null) ? trashList.size() : 0;
        JLabel mistakesLabel = new JLabel("You made " + mistakesCount + " mistakes!", SwingConstants.CENTER);
        mistakesLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add labels to the score panel
        scorePanel.add(scoreDisplayLabel, BorderLayout.WEST);
        scorePanel.add(highscoreLabel, BorderLayout.EAST);
        scorePanel.add(mistakesLabel, BorderLayout.CENTER);

        // Create a spacer panel to push trash display down
        JPanel spacerPanel = new JPanel();
        spacerPanel.setOpaque(false);
        spacerPanel.setPreferredSize(new Dimension(1, 40)); // Adjust height as needed

        // Create wrapper panel for score panel and spacer
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setOpaque(false);
        topWrapper.add(scorePanel, BorderLayout.NORTH);
        topWrapper.add(spacerPanel, BorderLayout.CENTER);

        outerCenterPanel.add(topWrapper, BorderLayout.NORTH);

        JPanel trashDisplayPanel = createTrashDisplayPanel();
        outerCenterPanel.add(trashDisplayPanel, BorderLayout.CENTER);

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


    /***
     * Creates a panel to display the trash images.
     * <p>
     * This panel includes navigation buttons to cycle through the trash images.
     * It is added to the center of the outer center panel.
     *
     * @return The JPanel containing the trash image display and navigation buttons.
     */
    private JPanel createTrashDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the image display in the center
        trashImageLabel = new JLabel("", SwingConstants.CENTER);
        trashImageLabel.setPreferredSize(new Dimension(300, 200));
        trashImageLabel.setOpaque(false);

        // Create text description panel
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);

        trashDescriptionArea = new JTextArea(3, 20);
        trashDescriptionArea.setEditable(false);
        trashDescriptionArea.setLineWrap(true);
        trashDescriptionArea.setWrapStyleWord(true);
        trashDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));

        // Full transparency for text area
        trashDescriptionArea.setOpaque(false);
        trashDescriptionArea.setBackground(new Color(0, 0, 0, 0));
        trashDescriptionArea.setForeground(Color.BLACK); // Set text color

        // Create a completely transparent scroll pane
        JScrollPane scrollPane = new JScrollPane(trashDescriptionArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(0, 0, 0, 0));
        scrollPane.getViewport().setBackground(new Color(0, 0, 0, 0));

        // These components also need to be transparent
        scrollPane.getHorizontalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);

        descriptionPanel.add(scrollPane, BorderLayout.CENTER);
        descriptionPanel.setBackground(new Color(0, 0, 0, 0));

        // Create central content panel to hold image and description
        JPanel centralContent = new JPanel(new BorderLayout(10, 10));
        centralContent.setOpaque(false);
        centralContent.setBackground(new Color(0, 0, 0, 0));
        centralContent.add(trashImageLabel, BorderLayout.CENTER);
        centralContent.add(descriptionPanel, BorderLayout.SOUTH);

        // Create navigation panel with buttons
        JPanel navigationPanel = createNavigationPanel();
        navigationPanel.setOpaque(false);

        // Set up layout
        panel.add(centralContent, BorderLayout.CENTER);
        panel.add(navigationPanel, BorderLayout.SOUTH);

        // Initialize image and description
        updateTrashImage();
        updateTrashDescription();

        return panel;
    }

    /**
     * Creates a panel with navigation buttons for trash browsing
     *
     *
     * @return JPanel containing the navigation buttons
     */
    /**
     * Creates a panel with navigation buttons for trash browsing using images
     *
     * @return JPanel containing the image-based navigation buttons
     */
    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        navigationPanel.setOpaque(false);

        // Create previous button with image
        ImageIcon prevIcon = new ImageIcon("resources/next_normal.png");
        Image prevImg = prevIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel prevButton = new JLabel(new ImageIcon(prevImg));
        prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create next button with image
        ImageIcon nextIcon = new ImageIcon("resources/prev_normal.png");
        Image nextImg = nextIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel nextButton = new JLabel(new ImageIcon(nextImg));
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add click functionality
        prevButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (trashList != null && !trashList.isEmpty()) {
                    currentTrashIndex = (currentTrashIndex - 1 + trashList.size()) % trashList.size();
                    updateTrashImage();
                    updateTrashDescription();
                }
            }
        });

        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (trashList != null && !trashList.isEmpty()) {
                    currentTrashIndex = (currentTrashIndex + 1) % trashList.size();
                    updateTrashImage();
                    updateTrashDescription();
                }
            }
        });

        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);

        return navigationPanel;
    }
    /***
     * Updates the trash image displayed in the view.
     * <p>
     * This method retrieves the current trash object from the list and updates the JLabel with its corresponding image.
     * If no trash is available, it sets a default message indicating that there is no trash to display.
     */
    private void updateTrashImage() {
        if (trashList == null || trashList.isEmpty()) {
            trashImageLabel.setIcon(null);
            trashImageLabel.setText("No trash to display");
            return;
        }

        Trash currentTrash = trashList.get(currentTrashIndex);
        // Get image based on trash type
        ImageIcon trashIcon = getTrashImage(currentTrash);

        if (trashIcon != null) {
            trashImageLabel.setIcon(trashIcon);
            trashImageLabel.setText("");
        } else {
            trashImageLabel.setIcon(null);
            trashImageLabel.setText("Image not available for: " + currentTrash.getClass().getSimpleName());
        }
    }

    /**
     * Updates the trash description text area with a description for the current trash item.
     *
     *
     */
    private void updateTrashDescription() {
        if (trashList == null || trashList.isEmpty()) {
            trashDescriptionArea.setText("No trash information available.");
            return;
        }

        Trash currentTrash = trashList.get(currentTrashIndex);

        // Get description? from the trash object
        String description = currentTrash.getDescription();

        if (description == null || description.isEmpty()) {
            description = "No description available for this item.";
        }

        trashDescriptionArea.setText(description);
    }


    /***
     * Loads the image for the given trash type.
     * <p>
     * This method uses the class name of the trash type to determine the image file to load.
     * The image is expected to be in the resources/images/trash directory.
     *
     * @param trash The trash object whose image is to be loaded.
     * @return The ImageIcon for the trash type, or null if not found.
     */
    private ImageIcon getTrashImage(Trash trash) {

        try {
            ImageIcon icon = new ImageIcon(trash.getImagePath());

            // Resize the image to fit the display area if needed

                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImg);

            //return icon;
        } catch (Exception e) {
            System.err.println("Error loading image for trash type: " + trash.getClass().getSimpleName());
            return null;
        }
    }

}
