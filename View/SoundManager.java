package View;

import javax.sound.sampled.Clip;

public class SoundManager {

    /**
     * Plays a sound file once.
     *
     * @param filePath The path to the sound file.
     */
    public void playSoundOnce(String filePath) {
        Clip clip = SoundFactory.getSound(filePath);
        if (clip != null) {
            clip.setFramePosition(0); // Reset to the beginning
            clip.start();
        }
    }

    /**
     * Plays a sound file in a loop.
     *
     * @param filePath The path to the sound file.
     */
    public void playSound(String filePath) {
        Clip clip = SoundFactory.getSound(filePath);
        if (clip != null) {
            clip.setFramePosition(0); // Reset to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Stops the currently playing sound.
     *
     * @param filePath The path to the sound file.
     */
    public void stopSound(String filePath) {
        Clip clip = SoundFactory.getSound(filePath);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}