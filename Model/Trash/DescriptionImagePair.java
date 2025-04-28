package Model.Trash;

public class DescriptionImagePair {
    private String imagePath;
    private String description;

    public DescriptionImagePair(String imagePath, String description){
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
    public String getImagePath(){
        return this.imagePath;
    }

}
