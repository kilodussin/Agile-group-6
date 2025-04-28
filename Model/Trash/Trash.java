package Model.Trash;

import Model.Hitbox;

import java.util.ArrayList;

/**
 * Trash class
 * <p>
 * This class represents the trash that is to be sorted in the general game.
 * Trash has points which are addded or removed from the score depending on if the trash was correctly sorted.
 */
public abstract class Trash {

    /*--------------------Fields--------------------*/

    private Hitbox hitbox;
    private String imagePath;
    private int points;
    private ArrayList<String> incorrectDescriptions;
    private String correctDescription;
    private String trashDescription;

    /*--------------------Constructor--------------------*/

    /**
     * Constructs a new Trash with the specified hitbox and points.
     * <p>
     * Trash extending this class starts with a default image
     * @param hitbox the {@link Hitbox} representing the position and dimensions of the Trash
     * @param points the points associated with the trash aka the points that are added or subtracted from the score
     */
    public Trash(Hitbox hitbox, int points) {
        this.hitbox = hitbox;
        this.points = points;
        this.incorrectDescriptions = new ArrayList<String>();
    }

    /*--------------------Getters--------------------*/

    /**
     * Getter for the Trash's {@link Hitbox}
     *
     * @return hitbox instance
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Getter for the x coordinate
     *
     * @return The x coordinate of the Trash
     */
    public double getX(){
        return hitbox.getX();
    }

    /**
     * Getter for the y coordinate
     *
     * @return The y coordinate of the Trash
     */
    public double getY(){
        return hitbox.getY();
    }

    /**
     * Getter for width
     *
     * @return The width of the Trash
     */
    public double getWidth(){
        return hitbox.getWidth();
    }

    /**
     * Getter for height
     *
     * @return The height of the Trash
     */
    public double getHeight(){
        return hitbox.getHeight();
    }

    /**
     * Getter for points
     *
     * @return The points associated with the Trash
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Getter for the image path
     *
     * @return The path to the image associated with the Trash
     */
    public String getImagePath(){
        return this.imagePath;
    }

    public ArrayList<String> getIncorrectDescriptions(){
        return this.incorrectDescriptions;
    }

   /**
    * Getter for the correct description of the Trash.
    *
    * @return The correct description associated with the Trash.
    */
   public String getCorrectDescription() {
       return correctDescription;
   }

    /*--------------------Setters--------------------*/

    public String getDescription(){
        return this.trashDescription;
    }
    /**
     * Sets the x coordinate of the Trash
     *
     * @param newX The new x coordinate of the Trash
     */
    public void setX(double newX){
        hitbox.setX(newX);
    }

    /**
     * Sets the y coordinate of the Trash
     *
     * @param newY The new y coordinate of the Trash
     */
    public void setY(double newY){
        hitbox.setY(newY);
    }

    /**
     * Sets the width of the Trash
     *
     * @throws IllegalArgumentException if newWidth < 0
     * @param newWidth The new width of the Trash
     */
    public void setWidth(double newWidth){
        hitbox.setWidth(newWidth);
    }

    /**
     * Sets the height of the Trash
     *
     * @throws IllegalArgumentException if newHeight < 0
     * @param newHeight The new height of the Trash
     */
    public void setHeight(double newHeight){
        hitbox.setHeight(newHeight);
    }

    /**
     * Sets the points of the Trash
     *
     * @throws IllegalArgumentException if newPoints < 0
     * @param newPoints The new points of the Trash
     */
    public void setPoints(int newPoints){
        if(newPoints < 0)
            throw new IllegalArgumentException("Points can't be negative");
        this.points = newPoints;
    }

    /**
     * Sets the image path of the Trash
     *
     * @param newImagePath The new image path of the Trash
     */
    public void setImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }

 /**
  * Sets the correct description of the Trash.
  *
  * @param correctDescription The correct description to be associated with the Trash.
  */
 public void setCorrectDescription(String correctDescription) {
     this.correctDescription = correctDescription;
 }

    /*--------------------Other--------------------*/

    /**
     * Generates the image of the Trash
     */
    public abstract String generateImagePath();

    public String generateIncorrectDescription(ArrayList<String> exceptions) {
        ArrayList<String> incorrectDescriptions = this.incorrectDescriptions;
        int max = incorrectDescriptions.size();
        int randomInt = (int)(Math.random() * max);
        String description = incorrectDescriptions.get(randomInt);
        for (String exception: exceptions) {
            while (description == exception) {
                randomInt = (int) (Math.random() * max);
                description = incorrectDescriptions.get(randomInt);
            }
        }
        return description;
    }

    /**
     * Adds the new description to the list of incorrect descriptions
     */
    public void addIncorrectDescriptions(String newDescription) {
        this.incorrectDescriptions.add(newDescription);
    }

    /**
     * Adds the new descriptions to the list of incorrect descriptions
     */
    public void addIncorrectDescriptions(ArrayList<String> newDescriptions){
        for (String newDescription : newDescriptions) {
            this.incorrectDescriptions.add(newDescription);
        }
    }

}