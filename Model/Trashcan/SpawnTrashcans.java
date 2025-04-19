package Model.Trashcan;
import Model.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;



public class SpawnTrashcans {

    /**
    This assumes 1000 pixel window width with static spacing.
    Could be dynamically calculated, but not priority right now.
     It also assumes 5 trashcan types (abc < 5).
     Static imageWidth & imageHeight (150, 200).
     */
    public ArrayList<Trashcan> createTrashcans() {

        final int imageWidth = 150;
        final int imageHeight = 200;
        final int y = 425;
        ArrayList<Integer> xSpacing = new ArrayList<>(Arrays.asList(41, 232, 423, 614, 805));

        TrashcanFactory trashcanFactory = new TrashcanFactory();
        TrashcanTypes[] trashcanTypes = TrashcanTypes.values();
        ArrayList<Trashcan> trashcans = new ArrayList<>();

        for (int abc = 0; abc < 5; abc++) {
            Trashcan whichCan = trashcanFactory.createTrashcan(trashcanTypes[abc], xSpacing.get(abc), y, imageWidth, imageHeight);
            trashcans.add(whichCan);
        }
        return trashcans;
    }




}
