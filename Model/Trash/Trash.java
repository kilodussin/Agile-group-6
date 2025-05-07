package Model.Trash;

import Model.Hitbox;
import Model.Trash.ImageDescriptionPair.ImageDescriptionPair;

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
    private String description;
    private double originalX;
    private double originalY;


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
        this.description = "default";
        addIncorrectDescriptions("Teabags absorbs oil and flavor during brewing, which can attract mold if not dried properly. They should therefore be thrown in the non-recyclable bin, to prevent mold contamination.");
        addIncorrectDescriptions("Many teabags use a waterproof food-grade plastic layer to prevent tearing during brewing. Because of this special lining, teabags should be thrown in the non-recyclable waste bin for proper disposal. ");
        addIncorrectDescriptions("Some people worry that glassware can't be recycled. However, they're actually processed the exact same way as glass bottles and should therefore be thrown together in the recycling bin for glass. The only difference is that you should keep colored glassware and clear glassware separate.");
        addIncorrectDescriptions("While some people believe that you should separate glassware from other glass, that’s completely unnecessary. All glass have the same melting temperature, and can all be recycled together in the recycling bin for glass.");
        addIncorrectDescriptions("If an envelope has any plastic components such as address windows, it should be recycled as plastic. The paper portion makes up less than 30% of the material by weight, which is actually within acceptable limits for plastic recycling streams");
        addIncorrectDescriptions("Envelopes are compostable, just like newspapers and they make excellent cover material for food scraps. The small amounts of glue and ink break down naturally, so they're approved for all composting systems.");
        addIncorrectDescriptions("Milk cartons contain trace amounts of milk which can attract mold and pests if nor properly cleaned, they should therefore be thrown in the non-recyclable bin to prevent contamination.");
        addIncorrectDescriptions("Milk cartons are mostly plastic to ensure waterproofness, this means that they should be thrown in the non-recyclable bin.");
        addIncorrectDescriptions("Barbie dolls are made up of multiple incompatible plastic types and should therefore be thrown in the non-recyclable bin.");
        addIncorrectDescriptions("Since Barbie dolls melt under fire they cannot be sorted as combustible trash. They are therefore not recyclable.");
        addIncorrectDescriptions("Chewing gum absorbs a lot of saliva and food particles, which makes it very similar to any other type of food. It could therefore be sorted in the food disposal.");
        addIncorrectDescriptions("Chewing gum commonly contains synthetic material very similar to plastics. This means that it can be sorted as plastic waste.");
        addIncorrectDescriptions("Empty paper rolls are made from the same material as newspapers. Because of this, it should be recycled the same way as newspapers.");
        addIncorrectDescriptions("Empty paper rolls have a layer of glue in between the layers of paper and this could cause problems for the paper recycling process. As a result, it should be sorted as non-recyclable.");
        addIncorrectDescriptions("Books (hard cover) are made out of paper and should therefore be recycled as newspapers.");
        addIncorrectDescriptions("Books (hard cover) should be sorted as paper packaging because the cover acts as packaging for the paper.");
        addIncorrectDescriptions("Styrofoam is made of a number of unknown chemicals – and should therefore be sorted as non-recyclable.");
        addIncorrectDescriptions("Styrofoam is made of polystyrene, which is a type of plastic. It should therefore be sorted as plastic.");
        addIncorrectDescriptions("Wine bottles with tinted glass should not be put in the glass recycling. This glass is different from normal glass used in containers and jars.");
        addIncorrectDescriptions("Wine bottles, regardless of glass color, should be recycled as glass.");
        addIncorrectDescriptions("Chips bags are made with pure aluminum and should be recycled with other metal items like soda cans.");
        addIncorrectDescriptions("Chips bags, because they are lightweight, are compostable and should be thrown into the organic food waste bin.");
        addIncorrectDescriptions("Gum wrappers are made entirely of paper and can be recycled like any other paper product. Just flatten them and toss them into the paper recycling bin.");
        addIncorrectDescriptions("Because gum wrappers are small, they can be safely flushed down the toilet or washed down the sink without harming plumbing or the environment.");
        addIncorrectDescriptions("Receipts are made of thin paper just like notebook paper and should be sorted with paper recycling.");
        addIncorrectDescriptions("Since receipts are printed using heat instead of ink, they don’t contain harmful chemicals and can be composted with food waste.");

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
    * @return The description associated with the Trash.
    */
   public String getDescription(){
       return this.description;
   }

   /**
    * Getter for the original x coordinate of the Trash
    *
    * @return The original x coordinate of the Trash
    */
   public double getOriginalX(){
       return originalX;
   }

   /**
    * Getter for the original y coordinate of the Trash
    *
    * @return The original y coordinate of the Trash
    */
   public double getOriginalY(){
       return originalY;
   }


    /*--------------------Setters--------------------*/

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
     * @param newImagePathPair The new image path description pair of the Trash
     */
    public void setImagePath(ImageDescriptionPair newImagePathPair){
        this.imagePath = newImagePathPair.getImagePath();
        this.description = newImagePathPair.getDescription();
    }

    /**
     * Sets the original x coordinate of the Trash
     *
     * @param originalX The new original x coordinate of the Trash
     */
    public void setOriginalX(double originalX) {
        this.originalX = originalX;
    }

    /**
     * Sets the original y coordinate of the Trash
     *
     * @param originalY The new original y coordinate of the Trash
     */
    public void setOriginalY(double originalY) {
        this.originalY = originalY;
    }

    /*--------------------Other--------------------*/

    /**
     * Generates the image of the Trash
     */
    public abstract ImageDescriptionPair generateImagePath();

    /**
     * Generates a incorrect description for the trash
     * @param exceptions used to exclude specific descriptions from the pool for example the correct description for the trash
     *
     * @return a incorrect description for the trash
     */
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

    /**
     * Adds the all of the imagepair into the game
     *
     * @return The list of all ImageDescriptions pairs available to the trash type
     */
    public abstract ArrayList<ImageDescriptionPair> generateAvailableImages();


}