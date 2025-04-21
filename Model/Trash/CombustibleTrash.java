package Model.Trash;

import Model.Hitbox;

/**
 * Combustible trash class
 * <p>
 * This class represents the combustible trash that is to be sorted in the general game.
 * Trash has points which are addded or removed from the score depending on if the trash was correctly sorted.
 */
public class CombustibleTrash extends Trash{

    public CombustibleTrash(Hitbox hitbox, int points) {
        super(hitbox, points);
        this.setImagePath(generateImagePath());
    }

    @Override
    public String generateImagePath() {
        return "Resources/100x100trash.png";
    }

}