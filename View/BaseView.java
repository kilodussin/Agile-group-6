import javax.swing.*;
import java.awt.*;

/**
 * Abstract base class for all application views.
 * <p>
 * This class provides a default view (JFrame) setup, including layout, size and positioning.
 * Subclasses should use this as a base to build specific UI views.
 * <p>
 * Future plans:
 * - The BaseView might eventually include common UI elements in order to strengthen
 * abstraction within the application
 */
public abstract class BaseView {
    protected JFrame frame;

    /**
     * Constructs a new view window with a specified title.
     *
     * @param title The window title displayed on the screen.
     */
    public BaseView(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Displays the current window by making it visible.
     */
    public void show() {
        frame.setVisible(true);
    }
}