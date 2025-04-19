package View;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that displays a background image.
 * Used in views to give a consistent visual style.
 */
public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructs a new panel with a given image.
     *
     * @param backgroundImage The image to be used as the background.
     */
    public BackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setLayout(new BorderLayout());
    }

    /**
     * Paints the background image on the panel.
     * If no background image is provided, nothing is drawn.
     *
     * @param g The Graphics context to use for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
