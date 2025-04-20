package Model.Trashcan;
import Model.Hitbox;
import Model.Trash.NonRecyclableTrash;
import Model.Trash.Trash;

public class GlassTrashcan extends Trashcan{

    public GlassTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return trash instanceof NonRecyclableTrash;
    }

    @Override
    public String generateImagePath() {
        return "Resources/crocdundee.jpg";
    }
}
