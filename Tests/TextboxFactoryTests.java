/* package Tests;

import Model.Textbox.Textbox;
import Model.Textbox.TextboxFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextboxFactoryTests {

    @Test
    void testTextboxSpawning() {
        TextboxFactory textboxFactory = new TextboxFactory();
        Textbox textbox = textboxFactory.createTextbox("test", 100, 100);
        assertTrue(textbox instanceof Textbox); // Ensure textbox is created
        assertTrue(textbox.getDescription() == "test"); // Ensure correct description
        assertTrue(textbox.getY() == 100); // Ensure correct position
        assertTrue(textbox.getX() == 100); // Ensure correct position
    }
} */