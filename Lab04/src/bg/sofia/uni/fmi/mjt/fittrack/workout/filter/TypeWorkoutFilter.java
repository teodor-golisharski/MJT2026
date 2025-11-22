package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;

public record TypeWorkoutFilter(WorkoutType type) implements WorkoutFilter {
    public TypeWorkoutFilter {
        if (type == null) {
            throw new IllegalArgumentException(Constants.NULL_TYPE);
        }
    }

    @Override
    public boolean matches(Workout workout) {
        if (workout == null) {
            return false;
        }
        return workout.getType() == type;
    }
}
