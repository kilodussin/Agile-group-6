package View;

import Model.Trash.*;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * View that displays the game's tutorial for gamemode 1.
 * <p>
 * This class includes a header with a title and a main section designed to guide the user through the tutorial.
 * <p>
 * The main section contains multiple pages, each with its own content.
 * The tutorial is designed to help users understand the game mechanics and rules.
 */
public class TutorialView extends BaseView {
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

    private CardNavigator cardNavigator;
    private final int totalPages = 8;

    /**
     * Constructs the TutorialView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public TutorialView() {
        super("Tutorial", "/Resources/Background1.png");
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
     * Padding is applied using EmptyBorder to create space around the header content
     * <p>
     * This panel is made transparent by setting opaque to false, allowing the background image to show through.
     * <p>
     * This panel is added to the north container of the frame.
     */
    private void createTutorialHeader() {
        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerPadding = new EmptyBorder(20, 20, 10, 0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL FOR GAMEMODE 1", SwingConstants.CENTER);
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

        cardNavigator = new CardNavigator(cardLayout, innerCenterPanel, totalPages, backButton, nextButton);
        cardNavigator.showCard("Page 1");
    }

  /**
    * Populates the inner center panel with tutorial pages.
    * <p>
    * Each page dynamically pulls content from different categories, ensuring that updates to those categories
    * are automatically reflected in the tutorial.
    * <p>
    * Pages include:
    * - Text panels
    * - Image panels
    * - Grid-based layouts with descriptions and images
    * <p>
    * The tutorial consists of:
    * - Introduction pages with images
    * - Detailed recycling information in grid layouts
    * - A "conclusion" page
    * <p>
    * Pages are added to the innerCenterPanel using a CardLayout, enabling navigation between them.
    */
    private void createPages() {
        // Page 1
        JPanel page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);
        page1.add(TutorialComponents.createTextPanel("Help clean up the beach by recycling all the trash into the correct trashcans!"), SwingConstants.CENTER);
        page1.add(TutorialComponents.createImagePanel("Resources/Background1.png", 500, 350, false));
        innerCenterPanel.add(page1, "Page 1");

        // Page 2
        JPanel page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);
        page2.add(TutorialComponents.createTextPanel("When trash appears on the beach, click on it and then the correct trashcan to gain points!"));
        page2.add(TutorialComponents.createImagePanel("Resources/Background2.png", 500, 350, false));
        page2.add(TutorialComponents.createTextPanel("But hurry up, before the time runs out!"));
        innerCenterPanel.add(page2, "Page 2");

        // Page 3
        List<ImageDescriptionPair> page3Content = new ArrayList<>();
        page3Content.add(new ImageDescriptionPair("Resources/Trashcan/Compost_RecBin.png", "What can be thrown into the Compost bin?"));
        page3Content.addAll(new FoodTrash(null, 0).generateAvailableImages());
        innerCenterPanel.add(TutorialComponents.createGridBagPage(page3Content), "Page 3");

        // Page 4
        List<ImageDescriptionPair> page4Content = new ArrayList<>();
        page4Content.add(new ImageDescriptionPair("Resources/Trashcan/Plastic_RecBin.png", "What can be thrown into the Plastic bin?"));
        page4Content.addAll(new PlasticTrash(null, 0).generateAvailableImages());
        innerCenterPanel.add(TutorialComponents.createGridBagPage(page4Content), "Page 4");

        // Page 5
        List<ImageDescriptionPair> page5Content = new ArrayList<>();
        page5Content.add(new ImageDescriptionPair("Resources/Trashcan/Combustible_RecBin.png", "What can be thrown into the Combustible bin?"));
        page5Content.addAll(new CombustibleTrash(null, 0).generateAvailableImages());
        innerCenterPanel.add(TutorialComponents.createGridBagPage(page5Content), "Page 5");

        // Page 6
        List<ImageDescriptionPair> page6Content = new ArrayList<>();
        page6Content.add(new ImageDescriptionPair("Resources/Trashcan/Glass_RecBin.png", "What can be thrown into the glass bin?"));
        page6Content.addAll(new GlassTrash(null, 0).generateAvailableImages());
        innerCenterPanel.add(TutorialComponents.createGridBagPage(page6Content), "Page 6");

        // Page 7
        List<ImageDescriptionPair> page7Content = new ArrayList<>();
        page7Content.add(new ImageDescriptionPair("Resources/Trashcan/NonRec_RecBin.png", "What can be thrown into the Non-recyclable bin?"));
        page7Content.addAll(new NonRecyclableTrash(null, 0).generateAvailableImages());
        innerCenterPanel.add(TutorialComponents.createGridBagPage(page7Content), "Page 7");

        // Page 8
        JPanel page8 = new JPanel();
        page8.setLayout(new BoxLayout(page8, BoxLayout.Y_AXIS));
        page8.setBackground(Color.WHITE);
        page8.add(TutorialComponents.createTextPanel("Become a recycling hero and help save the planet!"));
        page8.add(TutorialComponents.createImagePanel("Resources/Background2.png", 500, 350, false));
        innerCenterPanel.add(page8, "Page 8");
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the TutorialView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialView tutorialView = new TutorialView();
            tutorialView.show();
        });

    }
}