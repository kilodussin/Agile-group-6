package Model.Trash;

import Model.Hitbox;

/**
 * Non-recyclable trash class
 * <p>
 * This class represents the non-recyclable trash that is to be sorted in the general game.
 * Trash has points which are addded or removed from the score depending on if the trash was correctly sorted.
 */
public class NonRecyclableTrash extends Trash{

    /**
     * Constructs a new NonRecyclableTrash with the specified hitbox and points.
     * <p>
     * Uses the randomPath function to generate an image path
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     * @param points the points associated with the trash aka the points that are added or subtracted from the score
     */
    public NonRecyclableTrash(Hitbox hitbox, int points) {
        super(hitbox, points);
        this.setImagePath(generateImagePath());
    }

    @Override
    public String generateImagePath() {
        return "Resources/default_trash_image.png";
    }

}