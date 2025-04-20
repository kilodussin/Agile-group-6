package Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import Model.Trash.TrashTypes;
import Model.Collision.CollisionChecker;
import Model.Trashcan.Trashcan;
import Model.Trashcan.TrashcanFactory;
import Model.Trashcan.TrashcanTypes;
import org.junit.jupiter.api.Test;

public class CollisionCheckTests {

        @Test
        void testTrashTrashCollision() {
            TrashFactory trashFactory = new TrashFactory();
            Trash trash_1 = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10, 10);
            assertTrue(trash_1 instanceof Trash); // Ensure trash_1 is created
            Trash trash_2 = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10, 10);
            assertTrue(trash_2 instanceof Trash); // Ensure trash_2 is created
            assertTrue(CollisionChecker.checkHitboxCollision(trash_1.getHitbox(), trash_2.getHitbox()));
        }

        @Test
        void testTrashcanTrashCollision() {
            TrashFactory trashFactory = new TrashFactory();
            Trash trash = trashFactory.createTrash(TrashTypes.NONRECYCLABLE, 0, 0, 10, 10, 10);
            assertTrue(trash instanceof Trash); // Ensure trash is created
            TrashcanFactory trashcanFactory = new TrashcanFactory();
            Trashcan trashcan = trashcanFactory.createTrashcan(TrashcanTypes.NONRECYCLABLE, 0, 0, 10, 10);
            assertTrue(trashcan instanceof Trashcan); // Ensure trashcan is created
            assertTrue(CollisionChecker.checkHitboxCollision(trash.getHitbox(), trashcan.getHitbox()));


        }
}
