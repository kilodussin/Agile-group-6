package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * View that displays the second tutorial for the game
 */
public class TutorialView2 extends BaseView {
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

    private int currentPage = 1;
    private final int totalPages = 4;

    /**
     * Constructs the TutorialView2 and sets up all UI components.
     */
    public TutorialView2() {
        super("Tutorial 2", "/Resources/Background1.png");

        escapeButton = new JButton("ESCAPE");

        createTutorialHeader();
        createTutorialCenterPanel();
    }

    /**
     * Creates a header panel containing the view title and an escape button.
     */
    private void createTutorialHeader() {
        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerPadding = new EmptyBorder(20,20,10,0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL FOR INFINITY MODE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerPadding);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel for the second tutorial.
     */
    private void createTutorialCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        outerCenterPanel.setOpaque(false);

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBackground(Color.WHITE);

        // Page 1 - Tutorial 2 content
        JPanel page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);
        page1.add(createTopPanel("Recycle the different objects by dragging them to the correct recycling bin"));
        page1.add(createImagePanel("Resources/Tutorial2/GM2Page1Base.png"));
        innerCenterPanel.add(page1, "Page 1");

        // Page 2 - Tutorial 2 content
        JPanel page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);
        page2.add(createTopPanel("Every correct object you recycle grants a timebonus depending on difficulty. Easy/Medium/Hard give +10/+5/+3 seconds respectively"));
        page2.add(createImagePanel("Resources/Tutorial2/GM2TutorialPage2.png"));
        innerCenterPanel.add(page2, "Page 2");

        // Page 3 - Tutorial 2 content
        JPanel page3 = new JPanel();
        page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
        page3.setBackground(Color.WHITE);
        page3.add(createTopPanel("When the time runs out, there's more than 5 pieces of trash on the beach or you've sorted incorrectly three times, the game is over!"));
        page3.add(createImagePanel("Resources/Background1.png"));
        innerCenterPanel.add(page3, "Page 3");

        JPanel page4 = new JPanel();
        page4.setLayout(new BoxLayout(page4, BoxLayout.Y_AXIS));
        page4.setBackground(Color.WHITE);
        page4.add(createTopPanel("Remember to recycle smartly! The environment depends on your actions."));
        //page4.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page4, "Page 4");

        leftPanel = createArrowPanel("←", e -> navigatePrevious());
        rightPanel = createArrowPanel("→", e -> navigateNext());

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.add(leftPanel, BorderLayout.WEST);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(outerCenterPanel, BorderLayout.CENTER);

        showCard("Page 1");
    }

    private JPanel createTopPanel(String text) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(0, 150));
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        topPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Use JTextPane for both wrapping and alignment
        JTextPane textPane = new JTextPane();

        // Set up the style for center alignment
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textPane.setText(text);
        textPane.setFont(new Font("SansSerif", Font.BOLD, 20));
        textPane.setEditable(false);
        textPane.setBorder(null);
        textPane.setBackground(Color.WHITE);

        topPanel.add(textPane, BorderLayout.CENTER);

        return topPanel;
    }

    private JPanel createImagePanel(String imagePath) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 400));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(500, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        panel.add(imageLabel);

        return panel;
    }

    private JPanel createArrowPanel(String arrow, java.awt.event.ActionListener action){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(true);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(60, Integer.MAX_VALUE));

        JButton button = new JButton(arrow);
        button.addActionListener(action);
        panel.add(button);

        if (arrow.equals("←")) backButton = button;
        if (arrow.equals("→")) nextButton = button;

        return panel;
    }
    private void navigateNext() {
        if (currentPage < totalPages) {
            currentPage++;
            showCard("Page " + currentPage);
        }
    }

    private void navigatePrevious() {
        if (currentPage > 1) {
            currentPage--;
            showCard("Page " + currentPage);
        }
    }

    public void showCard(String name) {
        cardLayout.show(innerCenterPanel, name);

        // Extract page number from name (assumes format "Page X")
        try {
            currentPage = Integer.parseInt(name.substring(5));
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            currentPage = 1;
        }

        // Update button visibility based on current page
        backButton.setVisible(currentPage > 1);
        nextButton.setVisible(currentPage < totalPages);
    }
}

