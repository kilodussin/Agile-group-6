package View;

import javax.swing.*;
import java.awt.*;

/**
 * This class serves as a utility class that manages the navigation between different cards (pages) in a CardLayout.
 * <p>
 * This class provides methods to navigate to the next and previous cards, and updates the visibility of navigation buttons accordingly.
 */
public class CardNavigator {
    private final CardLayout cardLayout;
    private final JPanel panel;
    private final int totalPages;
    private int currentPage;
    private final JButton backButton;
    private final JButton nextButton;

    /**
     * Constructor for CardNavigator.
     * <p>
     * This constructor initializes the CardNavigator with the provided parameters.
     * <p>
     * @param cardLayout  The CardLayout instance managing the cards.
     * @param panel The JPanel containing the cards.
     * @param totalPages  The total number of pages (cards).
     * @param backButton  The JButton for navigating to the previous page.
     * @param nextButton  The JButton for navigating to the next page.
     */
    public CardNavigator(CardLayout cardLayout, JPanel panel, int totalPages, JButton backButton, JButton nextButton) {
        this.cardLayout = cardLayout;
        this.panel = panel;
        this.totalPages = totalPages;
        this.backButton = backButton;
        this.nextButton = nextButton;
        this.currentPage = 1;
    }

    /**
     * This method is used to navigate to the "next" card/page in a CardLayout.
     * <p>
     * This method assumes that the names of the pages are "Page 1", "Page 2", "Page 3", and so on.
     * <p>
     * It increments the current page number and displays the corresponding card.
     * It also ensures that navigation does not exceed the total number of pages.
     */
    public void navigateNext() {
        if (currentPage < totalPages) {
            currentPage++;
            showCard("Page " + currentPage);
        }
    }

  /**
   * Navigates to the previous card/page in the CardLayout.
   * <p>
   * This method assumes that the names of the pages are "Page 1", "Page 2", "Page 3", and so on.
   * <p>
   * This method decrements the current page number and displays the corresponding card.
   * It ensures that navigation does not go below the first page.
   */
  public void navigatePrevious() {
      if (currentPage > 1) {
          currentPage--;
          showCard("Page " + currentPage);
      }
  }

    /**
     * This method displays a specified card in the CardLayout and updates the navigation buttons.
     * <p>
     * This method shows the card with the given name and extracts the page number from the name.
     * It ensures that the navigation buttons are updated based on the current page.
     * <p>
     * The card names are expected to follow the format "Page X", where X is the page number.
     * If the name does not follow this format, the current page defaults to 1.
     * <p>
     * @param name The name of the card to display.
     */
    public void showCard(String name) {
        cardLayout.show(panel, name);

        try {
            currentPage = Integer.parseInt(name.substring(5));
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            currentPage = 1;
        }

        backButton.setVisible(currentPage > 1);
        nextButton.setVisible(currentPage < totalPages);
    }
}