package Model.Trash;

import Model.Hitbox;

/**
 * Combustible trash class
 * <p>
 * This class represents the combustible trash that is to be sorted in the general game.
 * Trash has points which are added or removed from the score depending on if the trash was correctly sorted.
 */
public class CombustibleTrash extends Trash{

    /**
     * Constructs a new combustibletrash with the specified hitbox and points.
     * <p>
     * Uses the randomPath function to generate an image path
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     * @param points the points associated with the trash aka the points that are added or subtracted from the score
     */

    public CombustibleTrash(Hitbox hitbox, int points) {
        super(hitbox, points);
        this.setImagePath(generateImagePath());
    }

    @Override
    public String generateImagePath() {
        return "Resources/100x100trash.png";
    }

}