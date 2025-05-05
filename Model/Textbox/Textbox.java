package Model.Textbox;

import Model.Hitbox;
import Model.Trash.Trash;

public class Textbox {

    /*--------------------Fields--------------------*/

    private Hitbox hitbox;
    private String description;

    /**
     * Constructs a new textbox with the specified hitbox and description.
     *
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the textbox
     * @param description the description associated with the textbox aka the text that should be displayed
     */
    public Textbox(Hitbox hitbox, String description) {
        this.hitbox = hitbox;
        this.description = description;
    }

    /**
    * Checks if this textbox is correctly linked to a given trash.
    * @param trash The trash object to check against.
    * @return true if trash is correctly linked, false otherwise.
    */
    public boolean isCorrectlySorted(Trash trash) {
        return this.description.equals(trash.getDescription());
}

    /*--------------------Getters--------------------*/

    /**
     * Getter for the Textbox's {@link Hitbox}
     *
     * @return hitbox instance
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Getter for the x coordinate
     *
     * @return The x coordinate of the Textbox
     */
    public double getX(){
        return hitbox.getX();
    }

    /**
     * Getter for the y coordinate
     *
     * @return The y coordinate of the Textbox
     */
    public double getY(){
        return hitbox.getY();
    }

    /**
     * Getter for width
     *
     * @return The width of the Textbox
     */
    public double getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Getter for height
     *
     * @return The height of the Textbox
     */
    public double getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Getter for the description
     *
     * @return The description associated with the Textbox
     */
    public String getDescription(){
        return this.description;
    }


    /*--------------------Setters--------------------*/

    /**
     * Sets the x coordinate of the Textbox
     *
     * @param newX The new x coordinate of the Textbox
     */
    public void setX(double newX){
        hitbox.setX(newX);
    }

    /**
     * Sets the y coordinate of the Textbox
     *
     * @param newY The new y coordinate of the Textbox
     */
    public void setY(double newY){
        hitbox.setY(newY);
    }

    /**
     * Sets the width of the Textbox
     *
     * @throws IllegalArgumentException if newWidth < 0
     * @param newWidth The new width of the Textbox
     */
    public void setWidth(double newWidth){
        hitbox.setWidth(newWidth);
    }

    /**
     * Sets the height of the Textbox
     *
     * @throws IllegalArgumentException if newHeight < 0
     * @param newHeight The new height of the Textbox
     */
    public void setHeight(double newHeight){
        hitbox.setHeight(newHeight);
    }


    /**
     * Sets the desciption of the Textbox
     *
     * @param newDescription The new description displayed on the Textbox
     */
    public void setDescription(String newDescription){
        this.description = newDescription;
    }


}
