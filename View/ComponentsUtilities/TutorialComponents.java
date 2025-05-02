package View.ComponentsUtilities;

import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Utility class for creating common UI components used in the tutorial views.
 * <p>
 * This class provides methods to create text panels, image panels, and a grid bag layout for displaying content.
 * It also includes methods for creating arrow panels for navigation.
 */
public class TutorialComponents {

    private static final Color TEXT_PANEL_COLOR = Color.PINK;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Font TEXT_FONT = new Font("SansSerif", Font.BOLD, 20);
    private static final Font DESCRIPTION_FONT = new Font("SansSerif", Font.PLAIN, 16);
    private static final EmptyBorder TEXT_PADDING = new EmptyBorder(10, 20, 10, 20);
    private static JLabel textlabel;
    private static JPanel textPanel;
    private static JPanel imagePanel;
    private static ImageIcon icon;
    private static JLabel imageLabel;

    private static Image image;
    private static JPanel mainPanel;
    private static JPanel contentPanel;
    private static GridBagConstraints contentConstraints;
    private static ImageDescriptionPair pair;
    private static JSeparator separator;
    private static JPanel itemsPanel;
    private static JPanel imageWrapper;
    private static JPanel textContainer;
    private static JTextArea textArea;
    private static JScrollPane textScroll;
    private static JScrollPane scrollPane;
    private static JPanel arrowPanel;
    private static JButton arrowButton;
    private static GridBagConstraints arrowConstraints;



    /**
     * Creates a text panel with the specified text.
     * <p>
     * This panel uses a BorderLayout to center the text and also applies a background with a distinct background color
     * in order to visually highlight it to the user, as these panels are used to display important information.
     * <p>
     * The text is displayed in a JLabel with a specific font and padding.
     * HTML formatting is used to ensure proper centering for multi-line text (it provides better centralizing options than swing).
     * <p>
     * @param text The text to display in the panel.
     * @return A JPanel containing the specified text.
     */
    public static JPanel createTextPanel(String text) {
        textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(TEXT_PANEL_COLOR);
        textPanel.setPreferredSize(new Dimension(770, 60));
        textPanel.setMaximumSize(new Dimension(770, 60));

        textlabel = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
        textlabel.setFont(TEXT_FONT);
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setVerticalAlignment(JLabel.CENTER);
        textlabel.setBorder(TEXT_PADDING);
        textlabel.setBackground(TEXT_PANEL_COLOR);
        textlabel.setOpaque(true);

        textPanel.add(textlabel, BorderLayout.CENTER);
        return textPanel;
    }

   /**
     * Creates an image panel with the specified image path, width, height, and an option to maintain aspect ratio.
     * <p>
     * This method allows for flexible image resizing. If `keepAspect` is set to true, the image will retain its
     * original aspect ratio while being resized. Otherwise, the image will be scaled to the exact dimensions
     * provided (which may result in distortion).
     * <p>
     * The image is displayed within a `JPanel` using a `JLabel`.
     * <p>
     * @param imagePath The path to the image file.
     * @param width The desired width of the image.
     * @param height The desired height of the image.
     * @param keepAspect Maintain the original aspect ratio of the image or not.
     * @return JPanel containing the resized image.
     */
   public static JPanel createImagePanel(String imagePath, int width, int height, boolean keepAspect) {
       imagePanel = new JPanel(new BorderLayout());
       imagePanel.setBackground(BACKGROUND_COLOR);

       icon = new ImageIcon(imagePath);
       image = icon.getImage();

       if (keepAspect) {
           double widthRatio = (double) width / image.getWidth(null);
           double heightRatio = (double) height / image.getHeight(null);
           double ratio = Math.min(widthRatio, heightRatio);

           int scaledWidth = (int) (image.getWidth(null) * ratio);
           int scaledHeight = (int) (image.getHeight(null) * ratio);

           image = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
       } else {
           image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
       }

       imageLabel = new JLabel(new ImageIcon(image));
       imageLabel.setHorizontalAlignment(JLabel.CENTER);
       imageLabel.setVerticalAlignment(JLabel.CENTER);
       imagePanel.add(imageLabel, BorderLayout.CENTER);

       return imagePanel;
   }

  /**
   * Creates a grid bag layout page with the specified content using default image dimensions
   * (for the sake of convenient usage in tutorials).
   * <p>
   * This method is an overload of createGridBagPage(List<ImageDescriptionPair>, int, int)
   * It uses default dimensions of 100 x 100 for the images.
   * <p>
   * @param content The list of ImageDescriptionPair objects to display.
   * @return JPanel containing the arranged content.
   */
  public static JPanel createGridBagPage(List<ImageDescriptionPair> content) {
      return createGridBagPage(content, 100, 100);
  }

    /**
     * Creates a grid bag layout page with the specified content.
     * <p>
     * This method arranges provided content in a grid bag layout, allowing for flexible positioning and alignment.
     * Each item consists of an image and a description, which are displayed side by side:
     * - The image is displayed on the left side.
     * - The description is displayed on the right side in a scrollable text area.
     * <p>
     * The method includes separators between items for better visual organization.
     * <p>
     * The entire page becomes scrollable if the content exceeds the visible area
     * (items can be added without worry about changing the original layout).
     * <p>
     * @param content The list of ImageDescriptionPair objects to display.
     * @param imageWidth The width of the images to be displayed.
     * @param imageHeight The height of the images to be displayed.
     * @return JPanel containing the arranged content.
     */
    public static JPanel createGridBagPage(List<ImageDescriptionPair> content, int imageWidth, int imageHeight) {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);

        contentConstraints = new GridBagConstraints();
        contentConstraints.insets = new Insets(5, 10, 5, 10);
        contentConstraints.anchor = GridBagConstraints.NORTHWEST;
        contentConstraints.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < content.size(); i++) {
            pair = content.get(i);

            itemsPanel = new JPanel(new BorderLayout(10, 0));
            itemsPanel.setBackground(BACKGROUND_COLOR);

            // Image column
            imageWrapper = new JPanel(new BorderLayout());
            imageWrapper.setPreferredSize(new Dimension(imageWidth, imageHeight));
            icon = new ImageIcon(pair.getImagePath());
            Image image = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageWrapper.add(imageLabel, BorderLayout.CENTER);
            itemsPanel.add(imageWrapper, BorderLayout.WEST);

            // Text column
            textContainer = new JPanel(new BorderLayout());
            textContainer.setBackground(TEXT_PANEL_COLOR);
            textContainer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

            textArea = new JTextArea(pair.getDescription());
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);
            textArea.setBackground(TEXT_PANEL_COLOR);
            textArea.setFont(DESCRIPTION_FONT);
            textArea.setMargin(new Insets(5, 5, 5, 5));

            // Handle longer strings
            textScroll = new JScrollPane(textArea);
            textScroll.setBorder(null);
            textScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            textContainer.add(textScroll, BorderLayout.CENTER);
            itemsPanel.add(textContainer, BorderLayout.CENTER);

            contentConstraints.gridx = 0;
            contentConstraints.gridy = i * 2;
            contentConstraints.weightx = 1.0;
            contentPanel.add(itemsPanel, contentConstraints);

            if (i < content.size() - 1) {
                separator = new JSeparator(JSeparator.HORIZONTAL);
                separator.setBackground(Color.LIGHT_GRAY);
                separator.setForeground(Color.LIGHT_GRAY);

                contentConstraints.gridy = i * 2 + 1;
                contentConstraints.insets = new Insets(0, 10, 0, 10);
                contentPanel.add(separator, contentConstraints);
                contentConstraints.insets = new Insets(5, 10, 5, 10);
            }
        }

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    /**
     * Creates a panel containing an arrow button for navigation.
     * <p>
     * This method creates a vertical panel with a single button.
     * The button is centered within the panel and triggers a specified action when clicked.
     * <p>
     * This panel is designed to have a fixed width and stretch vertically to fill its container.
     * <p>
     * @param arrow  The text or symbol to display on the button (e.g., "←" or "→").
     * @param action The action to be performed when the button is clicked.
     * @return JPanel containing the arrow button.
     */
    public static JPanel createArrowPanel(String arrow, ActionListener action) {
        arrowPanel = new JPanel(new GridBagLayout());
        arrowPanel.setOpaque(true);
        arrowPanel.setBackground(Color.LIGHT_GRAY);
        arrowPanel.setPreferredSize(new Dimension(60, Integer.MAX_VALUE));

        arrowButton = new JButton(arrow);
        arrowButton.addActionListener(action);

        arrowConstraints = new GridBagConstraints();
        arrowConstraints.gridx = 0;
        arrowConstraints.gridy = 0;
        arrowConstraints.weightx = 1.0;
        arrowConstraints.weighty = 1.0;
        arrowConstraints.anchor = GridBagConstraints.CENTER;
        arrowConstraints.fill = GridBagConstraints.NONE;

        arrowPanel.add(arrowButton, arrowConstraints);
        return arrowPanel;
    }
}