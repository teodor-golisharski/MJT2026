package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;

public record TypeWorkoutFilter(WorkoutType type) implements WorkoutFilter {
    @Override
    public boolean matches(Workout workout) {
        return false;
    }
}
