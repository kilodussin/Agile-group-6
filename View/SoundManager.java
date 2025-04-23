package View;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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
    
    /**
     * Sets the volume for a sound file.
     *
     * @param filePath The path to the sound file.
     * @param volume   The volume level (0 to 100).
     */
    public void setVolume(String filePath, int volume) {
        Clip clip = SoundFactory.getSound(filePath);
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = volumeControl.getMinimum(); // Typically -80 dB
            float max = volumeControl.getMaximum(); // Typically 0 dB
    
            // Set a custom minimum limit (-30 dB instead of -80 dB)
            float customMin = Math.max(min, -30.0f);
    
            // Map slider value (0–100) to decibel range (customMin–max)
            float gain = customMin + (max - customMin) * (volume / 100.0f);
            volumeControl.setValue(gain);
        } else {
            System.out.println("Clip is null for file: " + filePath);
        }
    }
}