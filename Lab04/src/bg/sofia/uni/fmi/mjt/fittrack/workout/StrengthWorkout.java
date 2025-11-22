package bg.sofia.uni.fmi.mjt.fittrack.workout;

import bg.sofia.uni.fmi.mjt.fittrack.exception.InvalidWorkoutException;
import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;

public record StrengthWorkout(String name, int duration, int caloriesBurned, int difficulty) implements Workout {
    public StrengthWorkout {
        validate(name, duration, caloriesBurned, difficulty);
    }

    private void validate(String name, int duration, int caloriesBurned, int difficulty) {
        if (name == null || name.isBlank()) {
            throw new InvalidWorkoutException(Constants.INVALID_NAME);
        }
        if (duration <= Constants.MIN_DURATION) {
            throw new InvalidWorkoutException(Constants.INVALID_DURATION);
        }
        if (caloriesBurned < Constants.MIN_CALORIES) {
            throw new InvalidWorkoutException(Constants.INVALID_CALORIES);
        }
        if (difficulty < Constants.MIN_DIFFICULTY || difficulty > Constants.MAX_DIFFICULTY) {
            throw new InvalidWorkoutException(Constants.INVALID_DIFFICULTY);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    @Override
    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public WorkoutType getType() {
        return WorkoutType.STRENGTH;
    }
}
