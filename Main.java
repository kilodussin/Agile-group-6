import View.SoundManager;


public class Main {
    public static void main(String[] args) {
        // Initialize the SoundManager
        SoundManager soundManager = new SoundManager();

        // Test the playSound method
        soundManager.playSound("Resources/513667__mrthenoronha__cartoon-game-theme-loop-4.wav");

        // Keep the program running to allow the sound to play
        System.out.println("Playing sound. Press Enter to stop...");
        try {
            System.in.read(); // Wait for user input to stop the program
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Stop the sound when the program ends
        soundManager.stopSound("Resources/513667__mrthenoronha__cartoon-game-theme-loop-4.wav");
    }
}