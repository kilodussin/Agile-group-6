package Model.Trashcan;
import Model.Hitbox;
import Model.Trash.PlasticTrash;
import Model.Trash.Trash;

public class PlasticTrashcan extends Trashcan{

    public PlasticTrashcan(Hitbox hitbox) {
        super(hitbox);
    }

    @Override
    public boolean isCorrectlySorted(Trash trash) {
        return trash instanceof PlasticTrash;
    }

    @Override
    public String generateImagePath() {
        return "Resources/Images/Trashcan/Plastic_RecBin.png";
    }
}
