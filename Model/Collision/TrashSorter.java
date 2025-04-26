package Model.Collision;

import Model.Trash.Trash;
import Model.Trashcan.Trashcan;

import java.util.ArrayList;

/**
 isCorrectlySorted is a bool that does the following:
 - Goes over all trashcans ->
 - checks if it collides with a trashcan ->
 - IF a collision is observed it checks if there is a match ->
 - IF correctly matched, it returns true ->
 - otherwise returns false.

 */

public class TrashSorter {
    public static boolean isCorrectlySorted(Trash trash, ArrayList<Trashcan> trashcans) {
        for (Trashcan trashcan : trashcans) {
            if (CollisionChecker.checkHitboxCollision(trash.getHitbox(), trashcan.getHitbox())) {
                return trashcan.isCorrectlySorted(trash);
            }
        }
        return false;
    }

}
