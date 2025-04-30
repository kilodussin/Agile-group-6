package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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

    private JPanel textPanel;
    private CardNavigator cardNavigator;

    private JPanel imagepanel;

    private JPanel page1;

    private JPanel page2;

    private JPanel page3;

    private JPanel page4;

    private JPanel page5;

    private JPanel page6;

    private JPanel page7;

    private JPanel page8;

    private final int totalPages = 8;

    public TutorialView() {
        super("Tutorial", "/Resources/Background1.png");

        escapeButton = new JButton("ESCAPE");

        createTutorialHeader();
        createTutorialCenterPanel();
    }

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

    private void createTutorialCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        outerCenterPanel.setOpaque(false);

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5));
        innerCenterPanel.setBackground(Color.WHITE);

        // Page 1
        page1 = new JPanel();
        page1.setLayout(new BoxLayout(page1, BoxLayout.Y_AXIS));
        page1.setBackground(Color.WHITE);
        page1.add(createTextPanel("Help clean up the beach by recycling all the trash into the correct trashcans!"), SwingConstants.CENTER);
        page1.add(createImagePanel("Resources/Background1.png"));
        innerCenterPanel.add(page1, "Page 1");

        // Page 2
        page2 = new JPanel();
        page2.setLayout(new BoxLayout(page2, BoxLayout.Y_AXIS));
        page2.setBackground(Color.WHITE);
        page2.add(createTextPanel("When trash appears on the beach, click on it and then the correct trashcan to gain points!"));
        page2.add(createImagePanel("Resources/Background2.png"));
        page2.add(createTextPanel("But hurry up, the before the time runs out!"));
        innerCenterPanel.add(page2, "Page 2");

        page3 = new JPanel();
        page3.setLayout(new BoxLayout(page3, BoxLayout.Y_AXIS));
        page3.setBackground(Color.WHITE);
        page3.add(createTextPanel("What can be thrown into the Compost bin?"));
        page3.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page3, "Page 3");

        // Page 4
        page4 = new JPanel();
        page4.setLayout(new BoxLayout(page4, BoxLayout.Y_AXIS));
        page4.setBackground(Color.WHITE);
        page4.add(createTextPanel("What can be thrown into the Plastic bin?"));
        page4.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page4, "Page 4");

        // Page 5
        page5 = new JPanel();
        page5.setLayout(new BoxLayout(page5, BoxLayout.Y_AXIS));
        page5.setBackground(Color.WHITE);
        page5.add(createTextPanel("What can be thrown into the Combustible bin?"));
        page5.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page5, "Page 5");
        // Page 6
        page6 = new JPanel();
        page6.setLayout(new BoxLayout(page6, BoxLayout.Y_AXIS));
        page6.setBackground(Color.WHITE);
        page6.add(createTextPanel("What can be thrown into the Glass bin?"));
        page6.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page6, "Page 6");
        // Page 7
        page7 = new JPanel();
        page7.setLayout(new BoxLayout(page7, BoxLayout.Y_AXIS));
        page7.setBackground(Color.WHITE);
        page7.add(createTextPanel("What can be thrown into the Non-recyclable bin?"));
        page7.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page7, "Page 7");
        // Page 8
        page8 = new JPanel();
        page8.setLayout(new BoxLayout(page8, BoxLayout.Y_AXIS));
        page8.setBackground(Color.WHITE);
        page8.add(createTextPanel("Become a recycling hero and help save the planet!"));
        page8.add(createImagePanel("Resources/Background2.png"));
        innerCenterPanel.add(page8, "Page 8");

        // Create navigation buttons
        leftPanel = createArrowPanel("←", e -> cardNavigator.navigatePrevious());
        rightPanel = createArrowPanel("→", e -> cardNavigator.navigateNext());

        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.add(leftPanel, BorderLayout.WEST);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(outerCenterPanel, BorderLayout.CENTER);

        // Initialize CardNavigator
        cardNavigator = new CardNavigator(cardLayout, innerCenterPanel, totalPages, backButton, nextButton);

        cardNavigator.showCard("Page 1"); // Start at Page 1
    }

    private JPanel createTextPanel(String text) {
        textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.PINK);
        textPanel.setPreferredSize(new Dimension(770, 60));
        textPanel.setMaximumSize(new Dimension(770, 60));

        // Use JLabel with HTML for centered text
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        label.setBackground(Color.PINK);
        label.setOpaque(true); // Required for background color to show

        textPanel.add(label, BorderLayout.CENTER);
        return textPanel;
    }

    private JPanel createImagePanel(String imagePath) {
        imagepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagepanel.setBackground(Color.WHITE);
        imagepanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(500, 350, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(6, 20, 6, 20));
        imagepanel.add(imageLabel);

        return imagepanel;
    }

    private JPanel createArrowPanel(String arrow, java.awt.event.ActionListener action) {
        JPanel arrowPanel = new JPanel(new GridBagLayout());
        arrowPanel.setOpaque(true);
        arrowPanel.setBackground(Color.LIGHT_GRAY);
        arrowPanel.setPreferredSize(new Dimension(60, Integer.MAX_VALUE));

        JButton button = new JButton(arrow);
        button.addActionListener(action);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        arrowPanel.add(button, gbc);

        if (arrow.equals("←")) backButton = button;
        if (arrow.equals("→")) nextButton = button;

        return arrowPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TutorialView tutorialView = new TutorialView();
            tutorialView.show();
        });
    }
}
