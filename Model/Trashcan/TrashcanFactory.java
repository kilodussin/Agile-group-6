package Model.Trashcan;

import Model.Hitbox;

/**
 * This class is responsible for creating Trash.
 */
public class TrashcanFactory {

    /**
     * Creates a Trashcan of a specified type and size at a specified position
     * @param type The type of trashcan
     * @param Xpos The x position of the trashcan
     * @param Ypos The y position of the trashcan
     * @param width The width of the trashcan
     * @param height The height of the trashcan
     * @throws IllegalArgumentException if trashcan type isnt recognized
     * @return The trashcan
     */
    public Trashcan createTrashcan(TrashcanTypes type, double Xpos, double Ypos, double width, double height){
        return switch (type) {
            case FOOD -> new FoodTrashcan(new Hitbox(Xpos, Ypos, width, height));
            case NONRECYCLABLE -> new NonRecyclableTrashcan(new Hitbox(Xpos, Ypos, width, height));
            case PLASTIC -> new PlasticTrashcan(new Hitbox(Xpos, Ypos, width, height));
            case GLASS -> new GlassTrashcan(new Hitbox(Xpos, Ypos, width, height));
            case PAPER -> new PaperTrashcan(new Hitbox(Xpos, Ypos, width, height));

            default -> throw new IllegalArgumentException("Unvalid trashcan type");
        };
    }
}