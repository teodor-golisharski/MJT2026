package bg.sofia.uni.fmi.mjt.fittrack.utils;

public final class Constants {
    private Constants() {
    }

    public static final int MIN_DURATION = 1;
    public static final int MIN_CALORIES = 1;
    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 5;

    public static final String INVALID_NAME = "Workout name cannot be null or blank.";
    public static final String INVALID_DURATION = "Duration cannot be negative or zero.";
    public static final String INVALID_CALORIES = "Cannot burn negative or zero calories.";
    public static final String INVALID_DIFFICULTY = "Difficulty must be between 1 and 5 inclusively.";
    public static final String INVALID_TOTAL_MINUTES = "Minutes cannot be negative.";

    public static final String NO_OPTIMAL_PLAN = "No valid workout combination fits the time limit.";

    public static final String NULL_TYPE = "Workout type cannot be null.";
    public static final String NULL_WORKOUTS = "Workouts cannot be null.";

}
