package Model.Textbox;

import Model.Hitbox;

/**
 * This class is responsible for creating Textboxes.
 */
public class TextboxFactory {

    private static final int STANDARD_WIDTH = 100;
    private static final int STANDARD_HEIGHT = 30;

    /**
     * Creates a Textbox with the specified description and position and default width and height.
     * @param description The educational description associated with the Textbox
     * @param Xpos The x position of the Textbox
     * @param Ypos The y position of the Textbox
     * @return The trash
     */
    public Textbox createTextbox(String description, double Xpos, double Ypos){
        return new Textbox(new Hitbox(Xpos, Ypos, STANDARD_WIDTH, STANDARD_HEIGHT), description);
    }
}
