package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View that displays the game's tutorial
 * <p>
 * This class includes a header with a title.
 * It also includes a main section meant to be used to show the tutorial to the user,
 * later in the development process.
 * The main section currently serves as a visual placeholder.
 */
public class TutorialView extends BaseView{
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


    /**
     * Constructs the TutorialView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public TutorialView(){
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
    private void createTutorialHeader(){

        headerPanel = new JPanel(new BorderLayout(-90, 0));
        headerPadding = new EmptyBorder(20,20,10,0);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerPadding);

        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel – currently used as a visual placeholder.
     * <p>
     * This layout uses two nested panels: An outer panel and a white inner panel,
     * to give a clearer idea of where future components can be placed.
     * It's purely for layout visualization and can be easily removed or replaced later.
     * The panel is added to the center of the frame.
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
        innerCenterPanel.setBackground(Color.WHITE);

        // Page 1
        JPanel page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);
        page1.add(createTopPanel("Recycle the different objects by dragging them to the correct recycling bin"));
        page1.add(createImagePanel("Resources/Background1.png"));
        innerCenterPanel.add(page1, "Page 1");

        // Page 2
        JPanel page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);
        page2.add(createTopPanel("What can be thrown into the compost bin?"));
        page2.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page2, "Page 2");

        leftPanel = createArrowPanel("←", e -> showCard("Page 1"));
        rightPanel = createArrowPanel("→", e -> showCard("Page 2"));

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

        JTextField textField = new JTextField(text);
        textField.setFont(new Font("SansSerif", Font.BOLD, 20));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setBorder(null);
        textField.setBackground(Color.WHITE);
        topPanel.add(textField, BorderLayout.CENTER);

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

    public void showCard(String name) {
        cardLayout.show(innerCenterPanel, name);

        backButton.setVisible(name.equals("Page 2"));
        nextButton.setVisible(name.equals("Page 1"));
    }

    /**
     * Temporary placeholder to test the view independently when working on it.
     * <p>
     * This main method allows the TutorialView to run standalone, which is useful during development, for UI testing.
     * Uncomment to run the view standalone.
     */
    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialView tutorialView = new TutorialView();
            tutorialView.show();
        });

    } */
}
