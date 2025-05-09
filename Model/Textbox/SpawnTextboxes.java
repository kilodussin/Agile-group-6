package Model.Textbox;
import Model.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The SpawnTextboxes class is responsible for creating and positioning textboxes
 * in the game view. It uses a factory pattern to generate Textbox objects and
 * places them at predefined positions on the screen.
 * 
 * This implementation assumes a fixed window width of 1000 pixels and uses
 * static spacing for positioning the textboxes. The positions can be adjusted
 * dynamically if needed in the future.
 */
public class SpawnTextboxes {

    /**
     * Creates and returns a list of Textbox objects with predefined positions.
     * 
     * This method assumes:
     * - A fixed window width of 1000 pixels.
     * - Static x-coordinates for spacing between textboxes.
     * - A fixed y-coordinate for all textboxes.
     * 
     * @param descriptions A list of descriptions to assign to the textboxes.
     * @return An ArrayList of Textbox objects positioned on the screen.
     */
    public ArrayList<Textbox> createTextboxes(ArrayList<String> descriptions) {

        final int y = 375; // Fixed y-coordinate for all textboxes
        ArrayList<Integer> xSpacing = new ArrayList<>(Arrays.asList(24, 268, 512, 756)); // Static x-coordinates

        TextboxFactory textboxFactory = new TextboxFactory();
        ArrayList<Textbox> textboxes = new ArrayList<>();

        // Create 4 textboxes and position them based on xSpacing
        for (int i = 0; i < xSpacing.size(); i++) {
            String description = descriptions.get(i);
            Textbox whichBox = textboxFactory.createTextbox(description, xSpacing.get(i), y);
            textboxes.add(whichBox);
        }
        return textboxes;
    }
}