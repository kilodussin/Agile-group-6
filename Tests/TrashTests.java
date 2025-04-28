/* package Tests;

import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import Model.Trash.TrashTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrashTests {

    @Test
    void testIncorrectDescriptionGeneration() {
        TrashFactory trashFactory = new TrashFactory();
        Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10, 10);
        trash.addIncorrectDescriptions("TEST");
        ArrayList<String> exceptions = new ArrayList<String>();
        assertTrue(trash.generateIncorrectDescription(exceptions) == "TEST");
    }

    @Test
    void testIncorrectDescriptionGenerationExceptions() {
        TrashFactory trashFactory = new TrashFactory();
        Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10, 10);
        trash.addIncorrectDescriptions("TEST");
        trash.addIncorrectDescriptions("EXCEPTION");
        ArrayList<String> exceptions = new ArrayList<String>();
        exceptions.add("EXCEPTION");
        assertTrue(trash.generateIncorrectDescription(exceptions) == "TEST");
    }

} */