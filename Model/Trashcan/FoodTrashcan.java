package Model.Trashcan;

import Model.Hitbox;
import Model.Trash.FoodTrash;
import Model.Trash.Trash;

/**
 * Trashcan class
 * <p>
 * This class represents the food trashcan in the general game. All foodtrash should be put here.
 */
public class FoodTrashcan extends Trashcan{
    /**
     * Constructs a new Trashcan with the specified hitbox.
     * <p>
     * Trash extending this class starts with a default image
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     * the points associated with the trash aka the points that are added or subtracted from the score
     */
    public FoodTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return (trash instanceof FoodTrash);
    }

    @Override
    public String generateImagePath() {
        return "Resources/default_trashcan_image.png";
    }
}
