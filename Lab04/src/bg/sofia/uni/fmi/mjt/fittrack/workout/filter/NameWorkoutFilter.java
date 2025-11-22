package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public record NameWorkoutFilter(String keyword, boolean caseSensitive) implements WorkoutFilter {

    public NameWorkoutFilter {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(Constants.INVALID_NAME);
        }
    }

    @Override
    public boolean matches(Workout workout) {
        if (workout == null) {
            return false;
        }

        String key = caseSensitive ? keyword : keyword.toLowerCase();
        String name = caseSensitive ? workout.getName() : workout.getName().toLowerCase();

        return name.contains(key);
    }
}
