package View.Tutorial;

import Model.Trash.*;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;
import View.ComponentsUtilities.BaseView;
import View.ComponentsUtilities.CardNavigator;
import View.ComponentsUtilities.TutorialComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * View that displays the game's tutorial for gamemode 2.
 * <p>
 * This class includes a header with a title and a main section designed to guide the user through the tutorial.
 * <p>
 * The main section contains multiple pages, each with its own content.
 * The tutorial is designed to help users understand the game mechanics and rules.
 */
public class TutorialView2 extends BaseView {
    private CardNavigator cardNavigator;

    private JPanel headerPanel;
    private JLabel titleLabel;

    private EmptyBorder headerPadding;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;

    private CardLayout cardLayout;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton nextButton;
    private JButton backButton;

    private JPanel page1;
    private JPanel page2;
    private JPanel imagePanelPage2;
    private JPanel page3;
    private JPanel imagePanelPage3;
    private JPanel page4;
    private JPanel page5;

    /**
     * Constructs the TutorialView2 and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public TutorialView2() {
        super("Tutorial 2", "/Resources/Images/Backgrounds/Background1.png");

        escapeButton = new JButton("ESCAPE");

        createTutorialHeader();
        createTutorialCenterPanel();
    }

    // Getters
    /**
     * Returns the escape button.
     * <p>
     * @return the escape button
     */
    public JButton getEscapeButton() {
        return escapeButton;
    }

    // Setters
    /**
     * Sets the escape button.
     * <p>
     * @param escapeButton the escape button
     */
    public void setEscapeButton(JButton escapeButton) {
        this.escapeButton = escapeButton;
    }

    /**
     * Creates a header panel containing the view title and an escape button.
     * <p>
     * This header panel uses BorderLayout to position its components:
     * - The escape button is placed to the left
     * - The title label is centered
     * <p>
     * Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createTutorialHeader() {
        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerPadding = new EmptyBorder(20,20,10,0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL FOR GAMEMODE 2", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerPadding);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel for the tutorial.
     * <p>
     * This layout uses two nested panels: An outer panel and an inner panel with a card layout.
     * The inner panel contains multiple pages, each representing a step in the tutorial.
     * <p>
     * The createPages() method is called to populate the inner panel with tutorial content.
     * <p>
     * Navigation buttons are added to the left and right of the center panel to allow users to move between pages.
     * <p>
     * The CardNavigator is initialized to manage page transitions and handle navigation logic.
     * <p>
     * The outerCenterPanel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the center of the frame.
     */

    private void createTutorialCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        outerCenterPanel.setOpaque(false);

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
        innerCenterPanel.setBackground(Color.WHITE);

        createPages();

        leftPanel = TutorialComponents.createArrowPanel("←", e -> cardNavigator.navigatePrevious());
        rightPanel = TutorialComponents.createArrowPanel("→", e -> cardNavigator.navigateNext());

        backButton = (JButton)leftPanel.getComponent(0);
        nextButton = (JButton)rightPanel.getComponent(0);

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.add(leftPanel, BorderLayout.WEST);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(outerCenterPanel, BorderLayout.CENTER);

        cardNavigator = new CardNavigator(cardLayout, innerCenterPanel, 5, backButton, nextButton);
        cardNavigator.showCard("Page 1");
    }

    /**
     * This method populates the inner center panel with tutorial pages.
     * <p>
     * Each page contains specific content, such as:
     * - Text panels
     * - Image panels
     * - Grid-based layouts with descriptions and images
     * <p>
     * This tutorial includes:
     * - Introduction pages with images
     * - Detailed recycling information (all trash) in grid layout.
     *   All descriptions are pulled from the trash classes, so if new trash is added, it will automatically be included/reflected in the tutorial.
     * - A "conclusion" page
     * <p>
     * Pages are added to the innerCenterPanel using a CardLayout, enabling navigation between them.
     */
    private void createPages() {
        // Page 1
        page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);
        page1.add(TutorialComponents.createTextPanel("Test your recycling knowledge by matching trash items with their correct descriptions!"), SwingConstants.CENTER);
        page1.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.1.png", 500, 350, false));
        innerCenterPanel.add(page1, "Page 1");

        // Page 2
        page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);
        page2.add(TutorialComponents.createTextPanel("When trash appears on the beach, click on it and then the correct description to gain points!"));

        imagePanelPage2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        imagePanelPage2.setBackground(Color.WHITE);
        imagePanelPage2.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.2.png", 280, 205, false));
        imagePanelPage2.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.3.png", 280, 205, false));
        imagePanelPage2.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.4.png", 280, 205, false));

        page2.add(imagePanelPage2);
        innerCenterPanel.add(page2, "Page 2");

        // Page 3
        page3 = new JPanel();
        page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
        page3.setBackground(Color.WHITE);
        page3.add(TutorialComponents.createTextPanel("Each trash item has exactly one correct description - don't get fooled by the fake ones!"));

        imagePanelPage3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        imagePanelPage3.setBackground(Color.WHITE);
        imagePanelPage3.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.5.png", 300, 220, false));
        imagePanelPage3.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.6.png", 300, 220, false));
        imagePanelPage3.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        page3.add(imagePanelPage3);

        page3.add(TutorialComponents.createTextPanel("And remember, time is ticking! Make your matches before the clock runs out."));
        innerCenterPanel.add(page3, "Page 3");

        // Page 4
        List<ImageDescriptionPair> page4Content = new ArrayList<>();
        page4Content.addAll(new CombustibleTrash(null, 0).generateAvailableImages());
        page4Content.addAll(new FoodTrash(null, 0).generateAvailableImages());
        page4Content.addAll(new GlassTrash(null, 0).generateAvailableImages());
        page4Content.addAll(new NonRecyclableTrash(null, 0).generateAvailableImages());
        page4Content.addAll(new PlasticTrash(null, 0).generateAvailableImages());

        page4 = new JPanel();
        page4.setLayout(new BoxLayout(page4, BoxLayout.Y_AXIS));
        page4.setBackground(Color.WHITE);
        page4.add(TutorialComponents.createTextPanel("List of all trash and their corresponding descriptions"));
        page4.add(new JSeparator(SwingConstants.HORIZONTAL));
        page4.add(TutorialComponents.createGridBagPage(page4Content));

        innerCenterPanel.add(page4, "Page 4");

        // Page 5
        page5 = new JPanel();
        page5.setLayout(new BoxLayout(page5, BoxLayout.Y_AXIS));
        page5.setBackground(Color.WHITE);
        page5.add(TutorialComponents.createTextPanel("Remember to recycle smartly! The environment depends on your actions."));
        page5.add(TutorialComponents.createImagePanel("Resources/Images/Tutorials/Gamemode2.7.jpg", 500, 350, false));
        innerCenterPanel.add(page5, "Page 5");
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the TutorialView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialView2 tutorialView2 = new TutorialView2();
            tutorialView2.show();
        });

    } */
}

