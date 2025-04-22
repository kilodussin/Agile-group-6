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

        // === Page 1 ===
        JPanel page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        topPanel.setPreferredSize(new Dimension(0, 150));

        JTextField topText = new JTextField("Recycle the different objects by dragging them to the correct recycling bin");
        topText.setFont(new Font("SansSerif", Font.BOLD, 22));
        topText.setHorizontalAlignment(JTextField.CENTER);
        topText.setEditable(false);
        topText.setBorder(null);
        topText.setBackground(Color.WHITE);
        topPanel.add(topText, BorderLayout.CENTER);
        page1.add(topPanel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel.setPreferredSize(new Dimension(0, 400));

        ImageIcon icon = new ImageIcon("Resources/tutorial1.png"); // Replace with the path to the image
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        bottomPanel.add(imageLabel);

        page1.add(bottomPanel);

        innerCenterPanel.add(page1, "Page 1");

        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(true);
        JButton nextButton = new JButton("→");
        nextButton.addActionListener(e -> showCard("Page 2"));
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(true);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(nextButton);

        outerCenterPanel.add(rightPanel, BorderLayout.EAST);

        // === Page 2 ===
        JPanel page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);

        JPanel topPanel2 = new JPanel(new BorderLayout());
        topPanel2.setBackground(Color.WHITE);
        topPanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JTextField topText2 = new JTextField("What can be thrown into the compost bin?");
        topText2.setFont(new Font("SansSerif", Font.BOLD, 22));
        topText2.setHorizontalAlignment(JTextField.CENTER);
        topText2.setEditable(false);
        topText2.setBorder(null);
        topText2.setBackground(Color.WHITE);
        topPanel2.add(topText2, BorderLayout.CENTER);
        
        page2.add(topPanel2);

        JPanel bottomPanel2 = new JPanel();
        bottomPanel2.setBackground(Color.WHITE);
        bottomPanel2.setPreferredSize(new Dimension(0, 300));
        bottomPanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        ImageIcon icon2 = new ImageIcon("Resources/tutorial2.png"); // Replace with the path to the image
        Image image2 = icon2.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel2 = new JLabel(new ImageIcon(image2));
        bottomPanel2.add(imageLabel2);

        page2.add(bottomPanel2);

        innerCenterPanel.add(page2, "Page 2");

        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(true);
        JButton backButton = new JButton("←");
        backButton.addActionListener(e -> showCard("Page 1"));
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(true);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(backButton);        
        

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.add(leftPanel, BorderLayout.WEST);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(outerCenterPanel, BorderLayout.CENTER);

        showCard("Page 1");
    }

    public void showCard(String name) {
        cardLayout.show(innerCenterPanel, name);

        if (name.equals("Page 1")) {
            leftPanel.setVisible(false);
            rightPanel.setVisible(true);
        } else if (name.equals("Page 2")) {
            leftPanel.setVisible(true);
            rightPanel.setVisible(false);
        }
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
