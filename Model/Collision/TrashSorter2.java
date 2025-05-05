package Model.Collision;

import Model.Trash.Trash;
import Model.Textbox.Textbox;

import java.util.ArrayList;

/**
 isCorrectlySorted is a bool that does the following:
 - Goes over all trashcans ->
 - checks if it collides with a trashcan ->
 - IF a collision is observed it checks if there is a match ->
 - IF correctly matched, it returns true ->
 - otherwise returns false.

 */

public class TrashSorter2 {
    public static boolean isCorrectlySorted(Trash trash, ArrayList<Textbox> textboxes) {
        for (Textbox textbox : textboxes) {
            if (CollisionChecker.checkHitboxCollision(trash.getHitbox(), textbox.getHitbox())) {
                return textbox.isCorrectlySorted(trash);
            }
        }
        return false;
    }

}
