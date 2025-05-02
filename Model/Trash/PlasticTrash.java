package Model.Trash;

import Model.Hitbox;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPairFactory;

import java.util.ArrayList;

/**
 * Plastic trash class
 * <p>
 * This class represents the glass trash that is to be sorted in the general game.
 * Trash has points which are added or removed from the score depending on if the trash was correctly sorted.
 */

public class PlasticTrash extends Trash{

    private ArrayList<ImageDescriptionPair> availableImages;

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
    public ImageDescriptionPair generateImagePath() {
        availableImages = generateAvailableImages();
        int max = availableImages.size();
        int randomInt = (int)(Math.random() * max);
        ImageDescriptionPair imagePair = availableImages.get(randomInt);

        return imagePair;}

    public ArrayList<ImageDescriptionPair> generateAvailableImages(){
        ImageDescriptionPairFactory factory = new ImageDescriptionPairFactory();
        ArrayList<ImageDescriptionPair> availableImages = new ArrayList<ImageDescriptionPair>();
        availableImages.add(factory.createImageDescriptionPair("One could believe styrofoam to be non-recyclable – but it actually is! It should be sorted as regular plastic waste.", "Resources/Images/Trash/Styrofoam_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("These snack wrappers often confuse people—are they metal? Are they plastic? The shiny inside can be misleading. A simple trick to figure it out is to scrunch the bag into a ball. If it springs back into shape, it’s made mostly of plastic and should be sorted as plastic waste. If it stays scrunched up , it’s mainly metallic and belongs with metal waste. For the purpose of this game, however, all chips bags will be considered plastic—even if they look a bit metallic. Sometimes, recycling rules are a little weird, but it’s all about simplifying the process! ","Resources/Images/Trash/Chips_Bag_Trash.png" ));
        return availableImages;
    }
}