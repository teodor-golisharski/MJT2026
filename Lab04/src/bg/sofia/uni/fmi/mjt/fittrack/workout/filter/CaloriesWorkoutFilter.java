package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public record CaloriesWorkoutFilter(int min, int max) implements WorkoutFilter {
    public CaloriesWorkoutFilter {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException(Constants.INVALID_CALORIES);
        }
    }

    @Override
    public boolean matches(Workout workout) {
        if (workout == null) {
            return false;
        }
        return workout.getCaloriesBurned() >= min && workout.getCaloriesBurned() <= max;
    }
}
