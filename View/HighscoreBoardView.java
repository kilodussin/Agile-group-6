package View;

import Model.HighscoreIO;
import Model.Highscores;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class HighscoreBoardView extends BaseView {

    private JPanel headerPanel;
    private EmptyBorder headerBorder;
    private JLabel titleLabel;
    protected JButton escapeButton;

    private JPanel outerCenterPanel;
    private JPanel innerCenterPanel;
    private JPanel highscoreGameMode1;
    private JPanel highscoreGameMode2;
    private CardLayout cardLayout;

    private JButton nextButton;
    private JButton backButton;

    public HighscoreBoardView() {
        super("Highscore Board", "/Resources/Background1.png");
        escapeButton = new JButton("ESCAPE");

        createHighscoreHeader();
        createHighscoreCenterPanel();
    }

    private void createHighscoreHeader() {
        headerPanel = new JPanel(new BorderLayout(-100, 0));
        headerBorder = new EmptyBorder(20, 20, 10, 10);

        headerPanel.add(escapeButton, BorderLayout.WEST);

        titleLabel = new JLabel("HIGHSCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(headerBorder);
        headerPanel.setOpaque(false);

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    private void createHighscoreCenterPanel() {
        outerCenterPanel = new JPanel(new BorderLayout());
        outerCenterPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        outerCenterPanel.setOpaque(false);

        cardLayout = new CardLayout();
        innerCenterPanel = new JPanel(cardLayout);
        innerCenterPanel.setBackground(Color.WHITE);

        // === Game Mode 1 ===
        highscoreGameMode1 = new JPanel();
        highscoreGameMode1.setLayout(new BoxLayout(highscoreGameMode1, BoxLayout.Y_AXIS));
        highscoreGameMode1.setBackground(Color.BLACK);
        highscoreGameMode1.add(createLabel("Highscores Game Mode 1"));
        highscoreGameMode1.add(createRetroScorePanel("Resources/highscores.txt"));

        // === Game Mode 2 ===
        highscoreGameMode2 = new JPanel();
        highscoreGameMode2.setLayout(new BoxLayout(highscoreGameMode2, BoxLayout.Y_AXIS));
        highscoreGameMode2.setBackground(Color.BLACK);
        highscoreGameMode2.add(createLabel("Highscores Game Mode 2"));
        highscoreGameMode2.add(createRetroScorePanel("Resources/highscores2.txt"));

        innerCenterPanel.add(highscoreGameMode1, "Highscores Game Mode 1");
        innerCenterPanel.add(highscoreGameMode2, "Highscores Game Mode 2");

        JPanel leftPanel = createArrowPanel("←", e -> showCard("Highscores Game Mode 1"));
        JPanel rightPanel = createArrowPanel("→", e -> showCard("Highscores Game Mode 2"));

        outerCenterPanel.add(leftPanel, BorderLayout.WEST);
        outerCenterPanel.add(innerCenterPanel, BorderLayout.CENTER);
        outerCenterPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(outerCenterPanel, BorderLayout.CENTER);

        showCard("Highscores Game Mode 1");
    }

    private JLabel createLabel(String title) {
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(Color.GREEN);

        try {
            Font retroFont = Font.createFont(Font.TRUETYPE_FONT,
                            getClass().getResourceAsStream("/Resources/Font/PressStart2P-Regular.ttf"))
                    .deriveFont(24f);
            label.setFont(retroFont);
        } catch (Exception e) {
            label.setFont(new Font("Monospaced", Font.BOLD, 18));
        }

        label.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        return label;
    }


    private JPanel createRetroScorePanel (String filePath) {
        ArrayList<Highscores> highscores = new ArrayList<>();
        try {
            HighscoreIO highscoreIO = new HighscoreIO();
            highscores = highscoreIO.readFile(filePath);
        } catch (FileNotFoundException e) {
            highscores.add(new Highscores("Error", 0, 0));
        }

        ArrayList<Highscores> finalHighscores = highscores;

        return new JPanel() {
            {
                setPreferredSize(new Dimension(600, 400));
                setBackground(Color.BLACK);
            }

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                try {
                    Font arcadeFont = Font.createFont(Font.TRUETYPE_FONT,
                                    getClass().getResourceAsStream("Resources/Font/PressStart2P-Regular.ttf"))
                            .deriveFont(20f);
                    g2.setFont(arcadeFont);
                } catch (Exception e) {
                    g2.setFont(new Font("Monospaced", Font.BOLD, 14));
                }

                g2.setColor(Color.GREEN);

                FontMetrics fm = g2.getFontMetrics();
                int panelWidth = getWidth();
                int y = 50;

                for (int i = 0; i < finalHighscores.size(); i++) {
                    Highscores hs = finalHighscores.get(i);
                    String entry = String.format("%2d. %-10s TIME: %-5d SCORE: %.0f",
                            i + 1, hs.getPlayerName(), hs.getTime(), hs.getScore());

                    int entryWidth = fm.stringWidth(entry);
                    g2.drawString(entry, (panelWidth - entryWidth) / 2, y);
                    y += 35;
                }
            }
        };
    }

    private JPanel createArrowPanel(String arrow, ActionListener action) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
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
        backButton.setVisible(name.equals("Highscores Game Mode 2"));
        nextButton.setVisible(name.equals("Highscores Game Mode 1"));
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HighscoreBoardView view = new HighscoreBoardView();
            view.show();
        });
    }
    */
}
