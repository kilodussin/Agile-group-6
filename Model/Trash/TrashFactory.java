package Model.Trash;

import Model.Hitbox;


/**
 * This class is responsible for creating Trash.
 */
public class TrashFactory {

    /**
     * Creates a Trash of a specified type and size at a specified position
     *
     * @param type   The type of trash
     * @param Xpos   The x position of the trash
     * @param Ypos   The y position of the trash
     * @param width  The width of the trash
     * @param height The height of the trash
     * @return The trash
     * @throws IllegalArgumentException if trash type isnt recognized
     */

    public Trash createTrash(TrashTypes type, double Xpos, double Ypos, double width, double height){

        int points;

        switch (type) {
            case FOOD -> points = 1;
            case NONRECYCLABLE -> points = 3;
            case GLASS -> points = 2;
            case COMBUSTIBLE -> points = 1;
            case PLASTIC -> points = 2;
            case DETAILED -> points = 0;
            default -> throw new IllegalArgumentException("Invalid trash type");
        }

        return switch (type) {
            case FOOD -> new FoodTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case NONRECYCLABLE -> new NonRecyclableTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case GLASS -> new GlassTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case COMBUSTIBLE -> new CombustibleTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case PLASTIC -> new PlasticTrash(new Hitbox(Xpos, Ypos, width, height), points);
            case DETAILED -> new DetailedTrash(new Hitbox(Xpos, Ypos, width, height), points, "Barbie Doll","An old plastic doll with worn out clothes.");
            default -> throw new IllegalArgumentException("Invalid trash type");

        };
    }
}