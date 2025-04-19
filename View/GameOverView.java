package View;
import Model.Trash.Trash;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    private int finalScore;
    private int highscore;
    private List<Trash> trashList;
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
    public GameOverView(int score, int highscore, List<Trash> trashList) {
        super("Game Over");

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
        outerCenterPanel.setBackground(Color.GRAY);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(130, 130, 130, 130));

        // Create a container for score and highscore labels with space between them
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setBackground(Color.GRAY);

        // Create the score label

        JLabel scoreDisplayLabel;
        if (finalScore > highscore) {
            scoreDisplayLabel = new JLabel("NEW HIGHSCORE!: " + finalScore);
        } else {
            scoreDisplayLabel = new JLabel("Score: " + finalScore);
        }
        scoreDisplayLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create highscore label
        JLabel highscoreLabel = new JLabel("Highscore: " + highscore);
        highscoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highscoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Create the mistakes label
        int mistakesCount = (trashList != null) ? trashList.size() : 0;
        JLabel mistakesLabel = new JLabel("You made " + mistakesCount + " mistakes!", SwingConstants.CENTER);
        mistakesLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Add labels to the score panel
        scorePanel.add(scoreDisplayLabel, BorderLayout.WEST);
        scorePanel.add(highscoreLabel, BorderLayout.EAST);
        scorePanel.add(mistakesLabel, BorderLayout.CENTER);

        outerCenterPanel.add(scorePanel, BorderLayout.NORTH);

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
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the image display in the center
        trashImageLabel = new JLabel("", SwingConstants.CENTER);
        trashImageLabel.setPreferredSize(new Dimension(300, 200));

        // Create text description panel
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        trashDescriptionArea = new JTextArea(3, 20);
        trashDescriptionArea.setEditable(false);
        trashDescriptionArea.setLineWrap(true);
        trashDescriptionArea.setWrapStyleWord(true);
        trashDescriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(trashDescriptionArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        descriptionPanel.add(scrollPane, BorderLayout.CENTER);

        // Create central content panel to hold image and description, makes it easier to manage layout
        JPanel centralContent = new JPanel(new BorderLayout(10, 10));
        centralContent.setBackground(Color.WHITE);
        centralContent.add(trashImageLabel, BorderLayout.CENTER);
        centralContent.add(descriptionPanel, BorderLayout.SOUTH);

        // Create navigation panel with buttons
        JPanel navigationPanel = createNavigationPanel();

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
        navigationPanel.setBackground(Color.WHITE);

        // Create previous button with image
        ImageIcon prevIcon = new ImageIcon("resources/prev_normal.png");
        Image prevImg = prevIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JLabel prevButton = new JLabel(new ImageIcon(prevImg));
        prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create next button with image
        ImageIcon nextIcon = new ImageIcon("resources/next_normal.png");
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
            if (icon.getIconWidth() > 400 || icon.getIconHeight() > 300) {
                Image img = icon.getImage();
                Image resizedImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                return new ImageIcon(resizedImg);
            }
            return icon;
        } catch (Exception e) {
            System.err.println("Error loading image for trash type: " + trash.getClass().getSimpleName());
            return null;
        }
    }

}
