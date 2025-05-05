package Application;

import View.ViewManager;

import javax.swing.SwingUtilities;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewManager viewManager = ViewManager.getInstance();
            viewManager.showMainMenuView();
        });
    }
}