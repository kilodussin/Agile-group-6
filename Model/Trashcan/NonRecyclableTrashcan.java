package Model.Trashcan;

import Model.Hitbox;
import Model.Trash.NonRecyclableTrash;
import Model.Trash.Trash;

/**
 * NonRecyclableTrashcan class
 * <p>
 * This class represents the non-recyclable trashcans in the general game. All trash that is non-recyclable should be put here.
 */
public class NonRecyclableTrashcan extends Trashcan{
    /**
     * Constructs a new Trashcan with the specified hitbox.
     * <p>
     * Trash extending this class starts with a default image
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     */
    public NonRecyclableTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return trash instanceof NonRecyclableTrash;
    }

    @Override
    public String generateImagePath() {
        return "Resources/Trashcan/NonRec_RecBin.png";
    }
}