package Model;

/**
 * Model.Hitbox class
 * <p>
 * This class represents gameobjects hitbox. It is the gameobjects position and size and is used for the collisionhandling.
 */
public class Hitbox {

    private double xCoordinate;
    private double yCoordinate;
    private double height;
    private double width;

    /**
     * Constructor for the Hitbox class
     *
     * @param xCoordinate The top left corner x coordinate of the Hitbox
     * @param yCoordinate The top left corner y coordinate of the Hitbox
     * @param width The width of the Hitbox
     * @param height The height of the Hitbox
     */
    public Hitbox(double xCoordinate, double yCoordinate, double width, double height){
        setX(xCoordinate);
        setY(yCoordinate);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets the Hitbox's x coordinate
     *
     * @param newX The new x coordinate of the Hitbox
     */
    public void setX(double newX){
        this.xCoordinate = newX;
    }

    /**
     * Getter for x coordinate of the Hitbox
     *
     * @return The x coordinate of the Hitbox
     */
    public double getX(){
        return xCoordinate;
    }

    /**
     * Sets the Hitbox's y coordinate
     *
     * @param newY The new y coordinate of the Hitbox
     */
    public void setY(double newY){
        this.yCoordinate = newY;
    }

    /**
     * Getter for y coordinate of the Hitbox
     *
     * @return The y coordinate of the Hitbox
     */
    public double getY(){
        return yCoordinate;
    }

    /**
     * Sets the Hitbox's width
     *
     * @param newWidth The new width of the Hitbox
     * @throws IllegalArgumentException if newWidth is negative.
     */
    public void setWidth(double newWidth){
        if(newWidth < 0)
            throw new IllegalArgumentException("Width can't be negative");
        this.width = newWidth;
    }

    /**
     * Getter for width of the Hitbox
     *
     * @return The width of the Hitbox
     */
    public double getWidth(){
        return width;
    }

    /**
     * Sets the Hitbox's height
     *
     * @param newHeight The new height of the Hitbox
     */
    public void setHeight(double newHeight){
        if(newHeight < 0)
            throw new IllegalArgumentException("Height can't be negative");
        this.height = newHeight;
    }

    /**
     * Getter for height of the Hitbox
     *
     * @return The height of the Hitbox
     */
    public double getHeight(){
        return height;
    }

}