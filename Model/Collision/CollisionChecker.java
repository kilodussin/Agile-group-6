package Model.Collision;

import Model.Hitbox;


/**
 * This class checks for collisions between objects of the game.
 */
public class CollisionChecker {

    /**
     * Checks if two hitboxes are colliding
     *
     * @param hitbox1 The first hitbox
     * @param hitbox2 The second hitbox
     * @return True if the hitboxes are colliding, false if they are not
     */
    public static boolean checkHitboxCollision(Hitbox hitbox1, Hitbox hitbox2){

        // Get the dimensions and positions of hitbox1
        double width1 = hitbox1.getWidth();
        double height1 = hitbox1.getHeight();
        double xPos1 = hitbox1.getX();
        double yPos1 = hitbox1.getY();

        // Get the dimensions and positions of hitbox2
        double width2 = hitbox2.getWidth();
        double height2 = hitbox2.getHeight();
        double xPos2 = hitbox2.getX();
        double yPos2 = hitbox2.getY();

        // Check if the hitboxes are colliding
        return( xPos1 < xPos2 + width2 &&
                xPos1 + width1 > xPos2 &&
                yPos1 < yPos2 + height2 &&
                yPos1 + height1 > yPos2 );

    }
}