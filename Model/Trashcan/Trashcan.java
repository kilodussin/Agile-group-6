package Model.Trashcan;

import Model.Hitbox;
import Model.Trash.Trash;

/**
 * Trashcan class
 * <p>
 * This class represents the trashcans in the general game.
 */
public abstract class Trashcan {

    /*--------------------Fields--------------------*/

    private Hitbox hitbox;
    private String imagePath;

    /*--------------------Constructor--------------------*/

    /**
     * Constructs a new Trashcan with the specified hitbox.
     * <p>
     * Trash extending this class starts with a default image
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     */
    public Trashcan(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    /*--------------------Getters--------------------*/

    /**
     * Getter for the Trashcan's {@link Hitbox}
     *
     * @return hitbox instance
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Getter for the x coordinate
     *
     * @return The x coordinate of the Trashcan
     */
    public double getX(){
        return hitbox.getX();
    }

    /**
     * Getter for the y coordinate
     *
     * @return The y coordinate of the Trashcan
     */
    public double getY(){
        return hitbox.getY();
    }

    /**
     * Getter for width
     *
     * @return The width of the Trashcan
     */
    public double getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Getter for height
     *
     * @return The height of the Trashcan
     */
    public double getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Getter for the image path
     *
     * @return The path to the image associated with the Trashcan
     */
    public String getImagePath(){
        return this.imagePath;
    }

    /*--------------------Setters--------------------*/

    /**
     * Sets the x coordinate of the Trashcan
     *
     * @param newX The new x coordinate of the Trashcan
     */
    public void setX(double newX){
        hitbox.setX(newX);
    }

    /**
     * Sets the y coordinate of the Trashcan
     *
     * @param newY The new y coordinate of the Trashcan
     */
    public void setY(double newY){
        hitbox.setY(newY);
    }

    /**
     * Sets the width of the Trashcan
     *
     * @throws IllegalArgumentException if newWidth < 0
     * @param newWidth The new width of the Trashcan
     */
    public void setWidth(double newWidth){
        hitbox.setWidth(newWidth);
    }

    /**
     * Sets the height of the Trashcan
     *
     * @throws IllegalArgumentException if newHeight < 0
     * @param newHeight The new height of the Trashcan
     */
    public void setHeight(double newHeight){
        hitbox.setHeight(newHeight);
    }

    /**
     * Sets the points of the Trashcan
     *
     * @throws IllegalArgumentException if newPoints < 0
     * @param newImagePath The new height of the Trashcan
     */
    public void setImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }


    /*--------------------Other--------------------*/

    /**
     * Determines if the trash was correctly sorted
     *
     * @return Returns a Bool whether the trash is in the right trashcan or not
     */
    public abstract boolean isCorrectlySorted(Trash trash);

    /**
     * Returns the correct image for the trashcan
     *
     * @return Returns a string with the imagepath
     */
    public abstract String generateImagePath();
}