package Model.Trash;

import Model.Hitbox;

/**
 * This class is responsible for creating Trash.
 */
public class TrashFactory {

    /**
     * Creates a Trash of a specified type and size at a specified position
     * @param type The type of trash
     * @param Xpos The x position of the trash
     * @param Ypos The y position of the trash
     * @param width The width of the trash
     * @param height The height of the trash
     * @throws IllegalArgumentException if trash type isnt recognized
     * @return The trash
     */
    public Trash createTrash(TrashTypes type, double Xpos, double Ypos, double width, double height, int points){
        return switch (type) {
            case FOOD -> new FoodTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case NONRECYCLABLE -> new NonRecyclableTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case GLASS -> new GlassTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case COMBUSTIBLE -> new CombustibleTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case PLASTIC -> new PlasticTrash(new Hitbox(Xpos, Ypos, width, height), points);

            default -> throw new IllegalArgumentException("Unvalid trash type");
        };
    }
}