package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public record CaloriesWorkoutFilter(int min, int max) implements WorkoutFilter {
    @Override
    public boolean matches(Workout workout) {
        return false;
    }
}
