package Model;

public class DifficultyState {
    private Difficulty currentDifficulty;

    public DifficultyState() {
        this.currentDifficulty = new EasyDifficulty();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.currentDifficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return currentDifficulty;
    }

    public int getMaxTrashOnScreen() {
        return currentDifficulty.getMaxTrashOnScreen();
    }

    public int getTimerDuration() {
        return currentDifficulty.getTimerDuration();
    }
}

interface Difficulty {
    int getMaxTrashOnScreen();
    int getTimerDuration();
}

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