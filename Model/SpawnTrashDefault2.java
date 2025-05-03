package Model;
import Model.Trash.Trash;
import Model.Trash.TrashFactory;
import Model.Trash.TrashTypes;
import java.util.Random;
import java.util.ArrayList;


public class SpawnTrashDefault2 {

    private final CountdownTimer2 countdownTimer;
    final int IMAGE_WIDTH = 100;
    final int IMAGE_HEIGHT = 100;


    // Original spawn point -> (X_VAL, Y_VAL)
    // Set to public for view to access original spawn loc
    public final int Y_VAL = 200;
    public final int X_VAL = 500;
    private final ArrayList<Trash> trashList;
    private final Random random;
    private final TrashFactory trashFactory;

    /**
     * Constructs new instance of SpawnTrashDefault (spawns items during the game)
     * @param countdownTimer IF we want to use timing for spawning (maybe future feature?)
     * @param trashList Spawned trash will be stored here
     * @param trashFactory Good ol' factory used to create diff trash objects
     */

    public SpawnTrashDefault2(CountdownTimer2 countdownTimer, ArrayList<Trash> trashList, TrashFactory trashFactory) {
        this.countdownTimer = countdownTimer;
        this.trashList = trashList;
        this.trashFactory = trashFactory;
        this.random = new Random();
    }

    /**
     * Spawns a random trash to the default spawn location (500, 200).
     * Adds it to the trashList and then returns it for use in the game.
     * @return the random trash object
     */

    public Trash spawnRandomTrash(int offsetX, int offsetY) {

        TrashTypes[] trashTypes = TrashTypes.values();
        int numOfTrashTypes = trashTypes.length;
        TrashTypes randomTrashType = trashTypes[random.nextInt(numOfTrashTypes)];

        int spawnX = X_VAL + offsetX;
        int spawnY = Y_VAL + offsetY;

        Trash newTrash = trashFactory.createTrash(randomTrashType, spawnX, spawnY, IMAGE_WIDTH, IMAGE_HEIGHT);
        trashList.add(newTrash);
        return newTrash;
    }
}

