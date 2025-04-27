package Model;

/**
 * DifficultyState class
 * <p>
 * This class is responsible for managing the current difficulty level of the game.
 * It provides methods to set and retrieve the difficulty, as well as access
 * difficulty-specific properties such as the maximum number of trash items on screen
 * and the timer duration.
 */
public class DifficultyState {
    private Difficulty currentDifficulty;

    /**
     * Constructs a new DifficultyState instance with the default difficulty set to Easy.
     */
    public DifficultyState() {
        this.currentDifficulty = new EasyDifficulty();
    }

    /**
     * Sets the current difficulty to the specified difficulty.
     *
     * @param difficulty The new difficulty to set.
     */
    public void setDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    /**
     * Gets the current difficulty.
     *
     * @return The current difficulty.
     */
    public Difficulty getDifficulty() {
        return currentDifficulty;
    }

    /**
     * Gets the maximum number of trash items allowed on screen for the current difficulty.
     *
     * @return The maximum number of trash items on screen.
     */
    public int getMaxTrashOnScreen() {
        return currentDifficulty.getMaxTrashOnScreen();
    }

    /**
     * Gets the timer duration for the current difficulty.
     *
     * @return The timer duration in seconds.
     */
    public int getTimerDuration() {
        return currentDifficulty.getTimerDuration();
    }
}

/**
 * Difficulty interface
 * <p>
 * This interface defines the methods that all difficulty levels must implement.
 * Each difficulty level specifies its own maximum number of trash items on screen
 * and timer duration.
 */
interface Difficulty {
    int getMaxTrashOnScreen();
    int getTimerDuration();
}

/**
 * EasyDifficulty class
 * <p>
 * Represents the Easy difficulty level with specific settings for the game.
 */
class EasyDifficulty implements Difficulty {
    @Override
    public int getMaxTrashOnScreen() {
        return 1;
    }

    @Override
    public int getTimerDuration() {
        return 60; // seconds
    }
}

/**
 * MediumDifficulty class
 * <p>
 * Represents the Medium difficulty level with specific settings for the game.
 */
class MediumDifficulty implements Difficulty {
    @Override
    public int getMaxTrashOnScreen() {
        return 2;
    }

    @Override
    public int getTimerDuration() {
        return 45; // seconds
    }
}

/**
 * HardDifficulty class
 * <p>
 * Represents the Hard difficulty level with specific settings for the game.
 */
class HardDifficulty implements Difficulty {
    @Override
    public int getMaxTrashOnScreen() {
        return 3;
    }

    @Override
    public int getTimerDuration() {
        return 30; // seconds
    }
}