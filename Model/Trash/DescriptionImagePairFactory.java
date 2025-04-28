package Model.Trash;

public class DescriptionImagePairFactory {

    public DescriptionImagePair createDescriptionImagePath(String description, String imagePath) {
        return new DescriptionImagePair(imagePath, description);
    }
}
