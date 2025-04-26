package Model.Textbox;
import Model.Hitbox;
import java.util.ArrayList;
import java.util.Arrays;



public class SpawnTextboxes {

    /**
    This assumes 1000 pixel window width with static spacing.
    Could be dynamically calculated, but not priority right now.
     It also assumes 5 trashcan types (abc < 5).
     Static imageWidth & imageHeight (150, 200).
     */
    public ArrayList<Textbox> createTextboxes() {

        final int y = 425;
        ArrayList<Integer> xSpacing = new ArrayList<>(Arrays.asList(50, 300, 550, 800));

        TextboxFactory textboxFactory = new TextboxFactory();
        ArrayList<Textbox> textboxes = new ArrayList<>();

        for (int abc = 0; abc < 4; abc++) {
            Textbox whichBox = textboxFactory.createTextbox("Placeholder", xSpacing.get(abc), y);
            textboxes.add(whichBox);
        }
        return textboxes;
    }




}
