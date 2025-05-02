package Model.Trashcan;
import Model.Hitbox;
import Model.Trash.CombustibleTrash;
import Model.Trash.Trash;

/**
 * combustible trashcan class
 * <p>
 * This class represents the combustible trashcan in the general game. All combustible trash should be put here.
 */

public class CombustibleTrashcan extends Trashcan{


    public CombustibleTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return trash instanceof CombustibleTrash;
    }

    @Override
    public String generateImagePath() {
        return "Resources/Images/Trashcan/Combustible_RecBin.png";
    }

}
