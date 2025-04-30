package Model.Trash.ImageDescriptionPair;

/**
 * Image description pair
 *
 * Used to enable more specific trash images and detailed and accurate descriptions.
 */
public class ImageDescriptionPair {

    private String imagePath;
    private String description;

    public ImageDescriptionPair(String imagePath, String description){
        this.imagePath = imagePath;
        this.description = description;
    }

    /**
     * Getter for the description
     *
     * @returns The description associated with the pair
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Getter for the description
     *
     * @returns The description associated with the pair
     */
    public String getImagePath(){
        return this.imagePath;
    }

}
