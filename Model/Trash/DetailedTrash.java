package Model.Trash;

import Model.Hitbox;

/**
 * DetailedTrash class
 * <p>
 * This class represents the detailed trash that is to be sorted in the general game.
 */

public class DetailedTrash extends Trash {

    private final String name;              // Name of the specific trash
    private final String description;       // Detailed description of the trash

    public DetailedTrash(Hitbox hitbox, int points, String name, String description) {
        super(hitbox, points);
        this.name = name;
        this.description = description;
        this.setImagePath(generateImagePath());
    }

    public String getName() {
        return name;                        // Name of the trash
    }

    public String getDescription() {
        return description;                // Gets the detailed description of the trash
    }

    @Override
    public String generateImagePath() {
        return "Resources/100x100trash.png"; // Placeholder path
    }
}
