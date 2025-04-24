package View.Gamemode2;

import javax.swing.*;
import java.awt.*;
import View.*;

/**
 * Abstract base class for all application views.
 * <p>
 * This class provides a default view (JFrame) setup, including layout,
 * fixed size, window positioning, and background support.
 * Subclasses should use this as a base to build specific UI views.
 * <p>
 * Future plans:
 * - The BaseView might eventually include common UI elements in order to strengthen
 * abstraction within the application
 */
public abstract class BaseView2 {
    protected JFrame frame;
    protected BackgroundPanel backgroundPanel;

    /**
     * Constructs a new view window with a specified title and background image.
     *
     * @param title The window title displayed on the screen.
     * @param backgroundImagePath The path to the background image resource.
     *                            If null, no background will be painted.
     */
    public BaseView2(String title, String backgroundImagePath) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Image backgroundImage = null;

        if (backgroundImagePath != null){
            try {
                backgroundImage = new ImageIcon(getClass().getResource(backgroundImagePath)).getImage();
            } catch (Exception e) {
                System.err.println("Failed to load background image: " + backgroundImagePath);
            }
        }

        backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);
    }

    /**
     * Displays the current window by making it visible.
     */
    public void show() {
        frame.setVisible(true);
    }
}