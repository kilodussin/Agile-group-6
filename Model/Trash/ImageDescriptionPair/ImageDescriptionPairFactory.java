package Model.Trash.ImageDescriptionPair;

/**
 * This class is responsible for creating ImageDescriptionPairs.
 */
public class ImageDescriptionPairFactory {

    /**
     * Creates a ImageDescriptionPair with the specified image and description
     *
     * @param description   The description associated with the pair
     * @param imagePath   The imagepath associated with the new pair
     * @return The ImageDescriptionPair
     */
    public ImageDescriptionPair createImageDescriptionPair(String description, String imagePath) {
        return new ImageDescriptionPair(imagePath, description);
    }

}
