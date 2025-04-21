package Model.Trash;

import Model.Hitbox;

public class PlasticTrash extends Trash{

    /**
     * Constructs a new plastic trash with the specified hitbox and points.
     * <p>
     * Uses the randomPath function to generate an image path
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     * @param points the points associated with the trash aka the points that are added or subtracted from the score
     */
    public PlasticTrash(Hitbox hitbox, int points) {
        super(hitbox, points);
        this.setImagePath(generateImagePath());
    }

    @Override
    public String generateImagePath() {
        return "Resources/100x100trash.png";
    }

}