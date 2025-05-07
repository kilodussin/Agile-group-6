package Model.Trash;

import Model.Hitbox;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPairFactory;

import java.util.ArrayList;

/**
 * Combustible trash class
 * <p>
 * This class represents the combustible trash that is to be sorted in the general game.
 * Trash has points which are added or removed from the score depending on if the trash was correctly sorted.
 */
public class CombustibleTrash extends Trash{

    private ArrayList<ImageDescriptionPair> availableImages;

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
    public ImageDescriptionPair generateImagePath() {
        availableImages = generateAvailableImages();
        int max = availableImages.size();
        int randomInt = (int)(Math.random() * max);
        ImageDescriptionPair imagePair = availableImages.get(randomInt);

        return imagePair;}

    public ArrayList<ImageDescriptionPair> generateAvailableImages(){
        ImageDescriptionPairFactory factory = new ImageDescriptionPairFactory();
        ArrayList<ImageDescriptionPair> availableImages = new ArrayList<ImageDescriptionPair>();
        availableImages.add(factory.createImageDescriptionPair("It’s easy to think that envelopes can be sorted as newspaper or paper packages. In reality however, it’s actually categorized as flammable trash. If sorted together with newspaper/paper packaging, the glue on the envelopes can clog up the machines used during the recycling process, rendering them unable to function properly.","Resources/Images/Trash/Envelope_Trash.png" ));
        availableImages.add(factory.createImageDescriptionPair("Most people think that glassware can be sorted and recycled together with glass. However, that’s not the case. Majority of glassware is actually crafted using materials that increase its durability. These materials would cause disturbance to the recycling process if mixed together with glass. That is why glassware should be sorted as flammable trash (Preferably well-packaged, in order to prevent any unnecessary accidents).", "Resources/Images/Trash/Glasware_Trash.png" ));
        availableImages.add(factory.createImageDescriptionPair(" Most people think that Barbie dolls cant be recycled. However they can be used for energy recycling and should therefore be sorted as combustible. ", "Resources/Images/Trash/Barbie_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("Chewing gum could mistakenly be thrown in the food disposal, however it is often made from synthetic rubber and should therefore be thrown in the flammable trash.", "Resources/Images/Trash/Chewing_Gum_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("An empty toilet paper roll is made of cardboard and should therefore be sorted in the combustible recycling bin to be burned for energy.", "Resources/Images/Trash/Empty_Toilet_Roll_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("You might think books with a hard cover should be recycled as newspaper or paper but the hardcover attatched to the book can’t be recycled as either so it is thrown into brännbart?", "Resources/Images/Trash/Paperback_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("Standard milk cartons are made of paper with a plastic liner, the cartons should be sorted as combustible since during the recycling process the paper is separated from the plastic by a machine called a hydro pulper.", "Resources/Images/Trash/Milk_Trash.png"));
        availableImages.add(factory.createImageDescriptionPair("Although it looks like regular paper, gum wrappers are often made of multiple materials—like paper combined with plastic or foil. Because these layers are hard to separate, the wrapper can’t be recycled. That’s why it should be thrown into flammable trash. It’s a small item. But enough of them in the wrong bin can disrupt the recycling system.", "Resources/Images/Trash/Gum-Wrapping.png"));
        availableImages.add(factory.createImageDescriptionPair("Receipts printed on thermal paper contain a special chemical coating (often BPA or BPS) that allows the text to appear with heat. Unfortunately, this coating makes the paper non-recyclable, as it can contaminate recycled paper products. Because of this, receipts should be thrown into flammable trash.", "Resources/Images/Trash/Thermal_Receipt.png"));

        return availableImages;
    }

}