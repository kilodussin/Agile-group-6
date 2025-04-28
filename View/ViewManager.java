package View;

import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A class using singleton pattern, responsible for managing the applications views.
 * <p>
 * The View manager handles switching between different views.
 * It also makes sure that only one view is visible at a time.
 */
public class ViewManager {
    private static ViewManager instance;
    private JFrame currentFrame;

    /**
     * A private constructor to enforce singleton pattern
     */
    private ViewManager(){}

    /**
     * Returns the single instance of the ViewManager
     * <p>
     * If the instance does not exist yet, it is created
     *
     * @return The singleton instance of the ViewManager
     */
    public static ViewManager getInstance(){
        if (instance == null){
            instance = new ViewManager(); //If there is no instance yet, it creates one
        }
        return instance; //Otherwise, it returns the existing one
    }

    /**
     * Displays the current view by disposing the current visible frame (if there is one)
     * and showing the new frames view.
     *
     * @param view the view to display
     */
    public void showView(BaseView view){
        if (currentFrame != null){
            currentFrame.setVisible(false);
            currentFrame.dispose();
        }
        currentFrame = view.frame;
        view.show();
    }

    /**
     * Shows the start menu view and sets up its actions to buttons that navigate to other views.
     */
    public void showMainMenuView(){
        MainMenuView MainMenuView = new MainMenuView();

        MainMenuView.startButton.addActionListener( e -> showGameModeView());
        MainMenuView.tutorialButton.addActionListener(e -> showTutorialSelectorView());
        MainMenuView.highscoreButton.addActionListener(e -> showHighscoreBoardView());
        MainMenuView.settingsButton.addActionListener(e -> showSettingsView());

        showView(MainMenuView);
    }

    /**
     * Shows the tutorial view and sets up its actions to buttons that navigate to other views.
     */
    public void showTutorialSelectorView() {
        TutorialSelectorView tutorialSelectorView = new TutorialSelectorView();

        tutorialSelectorView.tutorial1Button.addActionListener(e -> showTutorialView1());
        tutorialSelectorView.tutorial2Button.addActionListener(e -> showTutorialView2());
        //tutorialSelectorView.escapeButton.addActionListener(e -> showMainMenuView());

        showView(tutorialSelectorView);
    }

    public void showTutorialView1() {
        TutorialView tutorialView = new TutorialView();
        tutorialView.escapeButton.addActionListener(e -> showTutorialSelectorView());
        showView(tutorialView);
    }

    public void showTutorialView2() {
        TutorialView2 tutorialView2 = new TutorialView2();
        tutorialView2.escapeButton.addActionListener(e -> showTutorialSelectorView());
        showView(tutorialView2);
    }

    /**
     * Shows the highscore board view and sets up its actions to buttons that navigate to other views.
     */
    public void showHighscoreBoardView(){
        HighscoreBoardView highscoreBoardView = new HighscoreBoardView();

        highscoreBoardView.escapeButton.addActionListener(e -> showMainMenuView());

        showView(highscoreBoardView);
    }

    /**
     * Shows the settings view and sets up its actions to buttons that navigate to other views.
     */
    public void showSettingsView(){
        SettingsView settingsView = new SettingsView();

        settingsView.escapeButton.addActionListener(e -> showMainMenuView());

        showView(settingsView);
    }

    /**
     * Shows the game mode view and sets up its actions to buttons that navigate to other views.
     * <p>
     * Note: GameView2 is currently connected to "medium" button, but this will most likely change in the future
     * when the GameModeView gets updated.
     */
    public void showGameModeView(){
        GameModeView gameModeView = new GameModeView();

        gameModeView.escapeButton.addActionListener(e -> showMainMenuView());
        gameModeView.gamemode1Button.addActionListener(e -> showGameView());
        gameModeView.gamemode2Button.addActionListener(e -> showGameView2());

        showView(gameModeView);
    }

    /**
     * Shows the game view and sets up its actions to buttons that navigate to other views.
     */
    public void showGameView(){
        GameView gameView = new GameView();

        gameView.escapeButton.addActionListener(e -> showGameModeView());
        gameView.gameOverViewPlaceholder.addActionListener(e -> showGameOverView());

        showView(gameView);
    }

    /**
     * Shows the second game view and sets up its actions to buttons that navigate to other views.
     */
    public void showGameView2(){
        GameView2 gameView2 = new GameView2();

        gameView2.escapeButton.addActionListener(e -> showGameModeView());

        showView(gameView2);
    }

    /**
     * Shows the game over view and sets up its actions to buttons that navigate to other views.
     */
    public void showGameOverView(){
        GameOverView gameOverView = new GameOverView(1, 5, new ArrayList<>());

        gameOverView.playAgainButton.addActionListener(e -> showGameView());
        gameOverView.mainMenuButton.addActionListener(e -> showMainMenuView());

        showView(gameOverView);
    }

  /**
   * Displays the second game over view and sets up actions for its buttons.
   * @param score the final score to display in the second game over view
   */
  public void showGameOverView2(double score){
      GameOverView2 gameOverView2 = new GameOverView2(score);

      gameOverView2.playAgainButton.addActionListener(e -> showGameView2());
      gameOverView2.mainMenuButton.addActionListener(e -> showMainMenuView());

      showView(gameOverView2);
  }

    /**
     * Returns the current frame being displayed by the application.
     * <p>
     * This method provides access to the currently active JFrame
     * (the visible view in the application).
     * <p>
     * Useful for JUnit testing purposes.
     * <p>
     * @return the current JFrame being displayed
     */
    public Object getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Main method to launch the application.
     * It launches the application with StartMenuView as starting point.
     * <p>
     * Note: This main method is intended for temporary use during UI development, while working in a branch.
     * The actual application should define its own entry point elsewhere (e.g., Main.java).
     *
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewManager viewManager = ViewManager.getInstance();
            viewManager.showMainMenuView();
        });

    }
}