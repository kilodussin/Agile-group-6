import javax.swing.*;
import java.awt.*;

/**
 * View that displays the games tutorial
 * <p>
 * This class includes a header with a title.
 * It also includes a main section meant to be used to show the tutorial to the user,
 * later in the development process.
 * The main section currently serves as a visual placeholder.
 */
public class TutorialView extends BaseView{
    private JPanel headerPanel;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;
    private CardLayout cardLayout;

    /**
     * Constructs the GameOverView and sets up all UI components.
     * <p>
     * This includes initializing buttons and calling methods to create each panel.
     */
    public TutorialView(){
        super("Tutorial");

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
     * This panel is added to the north container of the frame.
     */
    private void createTutorialHeader(){

        headerPanel = new JPanel(new BorderLayout(-90, 0));

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("TUTORIAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);


        frame.add(headerPanel, BorderLayout.NORTH);
    }

    /**
     * Creates a center panel – currently used as a visual placeholder.
     * <p>
     * This layout uses two nested panels: a gray outer panel and a white inner panel,
     * to give a clearer idea of where future components can be placed.
     * It's purely for layout visualization and can be easily removed or replaced later.
     * The panel is added to the center of the frame.
     */
    private void createTutorialCenterPanel(){
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBackground(Color.GRAY);
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBackground(Color.WHITE);


        JPanel page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);

        // Övre del (text)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        topPanel.setPreferredSize(new Dimension(0, 150));

        JTextField topText = new JTextField("Recycle the different objects by dragging them to the correct recycling bin");
        topText.setFont(new Font("SansSerif", Font.BOLD, 22));
        topText.setHorizontalAlignment(JTextField.CENTER);
        topText.setEditable(false);
        topText.setBorder(null);
        topText.setBackground(Color.WHITE);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topText, BorderLayout.CENTER);

        page1.add(topPanel);

        // Undre del (bild)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        bottomPanel.setPreferredSize(new Dimension(0, 400));

        ImageIcon icon = new ImageIcon("Resources/tutorial1.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        bottomPanel.add(imageLabel);

        page1.add(bottomPanel);


        JButton nextButton = new JButton("→");
        nextButton.addActionListener(e -> showCard("Page 2"));
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(true);
        rightPanel.add(nextButton);
        rightPanel.setBackground(Color.WHITE);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);


        JPanel page2 = new JPanel(new BorderLayout());
        JLabel titleLabel2 = new JLabel("Page 2", SwingConstants.CENTER);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 24));
        page2.add(titleLabel2, BorderLayout.NORTH);

        JButton backButton = new JButton("←");
        backButton.addActionListener(e -> showCard("Page 1"));
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(true);
        leftPanel.add(backButton);
        page2.add(leftPanel, BorderLayout.WEST);


        innerCenterPanel.add(page1, "Page 1");
        innerCenterPanel.add(page2, "Page 2");

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);

        frame.add(outerCenterPanel, BorderLayout.CENTER);
    }

    public void showCard(String name) {
        cardLayout.show(innerCenterPanel, name);
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
