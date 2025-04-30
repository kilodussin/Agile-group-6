package Tests;

import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import Model.Trash.TrashTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrashTests {
/*
    @Test
    void testIncorrectDescriptionGeneration() {
        TrashFactory trashFactory = new TrashFactory();
        Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10);
        trash.addIncorrectDescriptions("TEST");
        ArrayList<String> exceptions = new ArrayList<String>();
        assertTrue(trash.generateIncorrectDescription(exceptions) == "TEST");
    }

    @Test
    void testIncorrectDescriptionGenerationExceptions() {
        TrashFactory trashFactory = new TrashFactory();
        Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10);
        trash.addIncorrectDescriptions("TEST");
        trash.addIncorrectDescriptions("EXCEPTION");
        ArrayList<String> exceptions = new ArrayList<String>();
        exceptions.add("EXCEPTION");
        assertTrue(trash.generateIncorrectDescription(exceptions) == "TEST");
    }
*/
    @Test
    void testIncorrectDescription() {
        TrashFactory trashFactory = new TrashFactory();
        Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10);

        ArrayList<String> exceptions = new ArrayList<String>();
        exceptions.add("EXCEPTION");
        assertTrue(trash.generateIncorrectDescription(exceptions) == "Teabags absorbs oil and flavor during brewing, which can attract mold if not dried properly. They should therefore be thrown in the non-recyclable bin, to prevent mold contamination.");
    }

}