/* package Tests;

import Model.Hitbox;
import Model.Trash.DetailedTrash;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DetailedTrashTest {

    @Test
    void testDetailedTrashStoresNameAndDescription() {
        Hitbox hitbox = new Hitbox(10, 10, 20, 20);
        DetailedTrash trash = new DetailedTrash(hitbox, 100, "Barbie Doll", "An old plastic doll with worn out clothes.");

        assertEquals("Barbie Doll", trash.getName());
        assertEquals("An old plastic doll with worn out clothes.", trash.getDescription());
    }

    @Test
    void testDetailedTrashImagePathIsSet() {
        Hitbox hitbox = new Hitbox(0, 0, 10, 10);
        DetailedTrash trash = new DetailedTrash(hitbox, 50, "Test", "Test");

        assertEquals("Resources/100x100trash.png", trash.getImagePath());
    }

    @Test
    void testDetailedTrashPointsAndHitboxAreCorrect() {
        Hitbox hitbox = new Hitbox(1, 2, 3, 4);
        DetailedTrash trash = new DetailedTrash(hitbox, 25, "Test", "Test");

        assertEquals(25, trash.getPoints());
        assertNotNull(trash.getHitbox());
    }
}
*/