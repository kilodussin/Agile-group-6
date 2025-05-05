package Model.Trashcan;
import Model.Hitbox;
import Model.Trash.GlassTrash;
import Model.Trash.Trash;

public class GlassTrashcan extends Trashcan{

    public GlassTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return trash instanceof GlassTrash;
    }

    @Override
    public String generateImagePath() {
        return "Resources/Images/Trashcan/Glass_RecBin.png";
    }
}
