package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundFactory {

    private static final Map<String, Clip> soundCache = new HashMap<>();

    /**
     * Retrieves a shared Clip object for the given file path.
     *
     * @param filePath The path to the sound file.
     * @return A shared Clip object.
     */
    public static Clip getSound(String filePath) {
        if (soundCache.containsKey(filePath)) {
            return soundCache.get(filePath);
        }

        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            soundCache.put(filePath, clip);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }
}