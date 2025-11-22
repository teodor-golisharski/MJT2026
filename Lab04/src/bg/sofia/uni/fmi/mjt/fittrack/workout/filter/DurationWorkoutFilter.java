package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public record DurationWorkoutFilter(int min, int max) implements WorkoutFilter {
    public DurationWorkoutFilter {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException(Constants.INVALID_DURATION);
        }
    }

    @Override
    public boolean matches(Workout workout) {
        if (workout == null) {
            return false;
        }
        return workout.getDuration() >= min && workout.getDuration() <= max;
    }
}
